package com.myproject.automation.base;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.myproject.automation.annotations.Retrievable;
import com.myproject.automation.config.AppiumServerPlatform;
import com.myproject.automation.config.DeviceManager;
import com.myproject.automation.constants.ParallelRunMode;
import com.myproject.automation.constants.RegexExpressions;
import com.myproject.automation.constants.TestNGParameters;
import com.myproject.automation.dataproviders.DataProviderHelper;
import com.myproject.automation.drivers.appium.AutomationDriver;
import com.myproject.automation.drivers.websocket.SocketDriver;
import com.myproject.automation.enums.TestStatus;
import com.myproject.automation.enums.TestrailStatusId;
import com.myproject.automation.enums.config.HeadSpinProperty;
import com.myproject.automation.enums.config.Path;
import com.myproject.automation.enums.config.TestProperty;
import com.myproject.automation.enums.config.TestrailProperty;
import com.myproject.automation.exceptions.PMEnvironmentException;
import com.myproject.automation.exceptions.PMException;
import com.myproject.automation.models.DeviceConfig;
import com.myproject.automation.models.application.User;
import com.myproject.automation.models.pojo.Device;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.debug.DebugConsoleSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.utils.*;
import com.myproject.automation.utils.testrail.TestRailException;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriverException;
import org.springframework.util.SocketUtils;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.myproject.automation.constants.RegexExpressions.*;
import static com.myproject.automation.listeners.DataDrivenTestsListener.GROUPS;
import static com.myproject.automation.listeners.TestRailFilterListener.testMethodsToInclude;
import static org.testng.util.Strings.isNullOrEmpty;

public abstract class BaseTest extends BaseSteps implements ITest, IConfigurable {

    private static final String INTEGRATIONS_SUITE_NAME = "integrations";
    @Getter
    private static final String PLATFORM = System.getProperty("platform");
    private static final String BUILD_NUMBER = System.getProperty("buildnumber");
    //FIELD_NAME_OF_FLAG_OF_SUCCESS_OF_BEFORE should store the name of a variable that is the flag
    // of success of 'beforeMethod'
    private static AtomicLong countOfMethodCalls = new AtomicLong(0);
    public static final int TESTRAIL_PROJECT_ID = Integer.parseInt(DataHolder.getTestrailProperty(TestrailProperty.TESTRAIL_PROJECT_ID));
    private static final TestRailIntegration testRailClient = new TestRailIntegration(DataHolder.getTestrailProperty(TestrailProperty.TESTRAIL_URL),
            DataHolder.getTestrailProperty(TestrailProperty.TESTRAIL_USERNAME),
            DataHolder.getTestrailProperty(TestrailProperty.TESTRAIL_PASSWORD));
    private String stringTestPlanId = DataHolder.getTestrailProperty(TestrailProperty.TESTRAIL_TEST_PLAN_ID);
    private String testPlanName = DataHolder.getTestrailProperty(TestrailProperty.TESTRAIL_TEST_PLAN_NAME);
    private final boolean isTestRailEnabled = !stringTestPlanId.isEmpty() || !testPlanName.isEmpty();
    protected User user = LoginDeterminator.getAccount(false);
    private ThreadLocal<String> testNgXmlTestName = new ThreadLocal<>();
    private ThreadLocal<String> testCaseName = new ThreadLocal<>();
    private ThreadLocal<String> logName = new ThreadLocal<>();
    private ThreadLocal<String> videoPath = new ThreadLocal<>();
    private ThreadLocal<String> screenshotPath = new ThreadLocal<>();
    private ThreadLocal<String> debugConsoleLogsName = new ThreadLocal<>();
    @Getter
    private Throwable failedBeforeException;
    @Getter
    private DeviceConfig deviceConfig;
    @Getter
    private boolean isBeforePass = false;
    @Getter
    private ThreadLocal<String> currentTestParametersAsString = new InheritableThreadLocal<>();
    private static List<DeviceConfig> deviceConfigList = new ArrayList<>();
    private static List<DeviceConfig> devicesWithIssuesList = new ArrayList<>();

    static {
        if (AutomationDriver.isHeadSpinMultipleDeviceLimit() || !DeviceManager.getHeadSpinPredefinedDevices().isEmpty()) {
            String buildDirPath = StringUtil.appendString(System.getProperty("user.dir"), "/build/reports/logs/");
            Logger.setLogger(StringUtil.appendString(buildDirPath, "headspindevices.log"));
            AutomationDriver.connectToHeadSpinDevicesAndUpdateJsonFile();
            reinstallAppForHsAvailableDevices();
        }
        //List of all DeviceConfig for later redistribution to avoid skipped tests due to device issues
        for (Device device : DeviceManager.getAllAvailableDevices()) {
            deviceConfigList.add(getDeviceConfig(device));
        }
    }

    @Override
    public void run(IConfigureCallBack callBack, ITestResult testResult) {
        callBack.runConfigurationMethod(testResult);
        if (testResult.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(Retrievable.class) &&
                testResult.getThrowable() != null) {
            Retrievable retrievable = testResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Retrievable.class);
            String caughtException = testResult.getThrowable().getCause().toString();
            if (caughtException.contains(PMEnvironmentException.class.getSimpleName())) {
                for (int i = 1; i <= retrievable.attempts(); i++) {
                    Logger.get().info(String.format("Trying to retry configuration method. Attempt: %s", i));
                    if (testResult.getThrowable() == null) {
                        break;
                    }
                    releasePort(testResult);
                    changeUnavailableDevice(testResult);
                    callBack.runConfigurationMethod(testResult);
                }
            }
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext iTestContext) {
        FacebookAccountsProvider.init();
    }

    private void initReportTools(Object[] objects) {
        String parameters = Arrays.stream(objects).map(Object::toString).collect(Collectors.joining(StringUtils.SPACE));
        String caseName = String.join(StringUtils.SPACE, this.getClass().getSimpleName(), parameters).replace(COLON, StringUtils.EMPTY);

        logName.set(getLogName(DataHolder.TEMPLATE_LOG_NAME, caseName));
        debugConsoleLogsName.set(getLogName(DataHolder.DEBUG_CONSOLE_LOG_NAME, caseName));
        videoPath.set(getLogName(DataHolder.getTestProperty(TestProperty.VIDEO_RECORDS_PATH_PATTERN), caseName)
                .replaceAll(StringUtils.SPACE, RegexExpressions.HYPHEN_SYMBOL));
        testNgXmlTestName.set(getLogName("%s Device: %s UDID: %s:", caseName));
        testCaseName.set(this.getClass().getSimpleName());
        this.deviceConfig.setLogsPath(String.format(DataHolder.DEVICE_LOG_NAME,
                Instant.now().toEpochMilli(), this.deviceConfig.getDeviceName(), this.deviceConfig.getUdid()).trim());
    }

    @BeforeMethod(alwaysRun = true)
    @Retrievable(attempts = 1)
    public void beforeMethod(Object[] objects, ITestResult iTestResult) {
        initReportTools(objects);
        currentTestParametersAsString.set(String.join(COMMA, Arrays.copyOf(objects,
                objects.length, String[].class)));
        Thread.currentThread().setName(Thread.currentThread().getName()
                .replaceAll(DataHolder.getTestProperty(TestProperty.FB_MARK_FOR_FINISHED_TEST), StringUtils.EMPTY));
        Logger.setLogger(logName.get());
        Logger.get().info(String.format("*----Constructed %1$s on %2$s %3$s", this.getClass().getSimpleName(),
                deviceConfig.getDeviceName(), deviceConfig.getUdid()));
        iTestResult.getMethod().setRetryAnalyzerClass(PMRetryAnalyzer.class);
        try {
            if (devicesWithIssuesList.stream().anyMatch(device -> device.getUdid().equals(this.deviceConfig.getUdid()))) {
                Logger.get().info(String.format("Device with issue %s. The device configuration will be changed", this.deviceConfig.getUdid()));
                changeDeviceConfig();
            }
            AppSteps.get().init(this.deviceConfig.getUdid(), deviceConfig);
            AppSteps.get().dismissAlertIfPresent();
            SocketDriver.initLogsBuilder();
            SocketDriver.initWs();
            AutomationDriver.startVideoRecord();
            UserSteps.get().waitForGameAccountIsReceived();
            DebugConsoleSteps.get().sendInternalMessage(this.getClass().getSimpleName());
            countOfMethodCalls.incrementAndGet();
            isBeforePass = true;
            // This code needs to skip tutorial/GDPR after first app reinstallation on each device
            if (countOfMethodCalls.get() <= DeviceManager.getAllAvailableDevices().size()) {
                user.setApiToken(UserSteps.get().getUserApiToken());
                this.user = AppSteps.get().getUserAndResetGdpr();
                ApiSteps.get().acceptTutorial(this.user.getApiToken());
                AppSteps.get().relaunchApp();
            }
        } catch (Throwable ex) {
            isBeforePass = false;
            failedBeforeException = ex;
            throw new PMEnvironmentException("Exception during app init. Device: " + this.getDeviceConfig(), ex);
        }
    }

    private void cleanup() {
        Thread.currentThread().setName(DataHolder.getTestProperty(TestProperty.FB_MARK_FOR_FINISHED_TEST)
                + Thread.currentThread().getName());
        if (AutomationDriver.isUdidOccupiedByMe(this.deviceConfig.getUdid(), String.valueOf(Thread.currentThread().getId()))) {
            try {
                //do not add additional calls of driver methods before release()
                AppSteps.get().release(this.deviceConfig.getUdid());
            } catch (PMException | PMEnvironmentException | WebDriverException pmEx) {
                Logger.get().warn(pmEx.getMessage());
            } finally {
                try {
                    if (!this.deviceConfig.isUseRemoteHeadSpinServer()) {
                        ScriptUtils.killProcessOnPort(Integer.parseInt(this.deviceConfig.getServerPort()));
                    }
                    FileUtils.deleteQuietly(new File(this.getDeviceConfig().getLogsPath()));
                } catch (IOException | InterruptedException ex) {
                    Logger.get().error(String.format("Error happened during port '%s' clearing for device '%s'",
                            this.deviceConfig.getPort(), this.deviceConfig.getDeviceName()));
                }
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void sendResultToTestrail(ITestResult result) {
        TestrailStatusId statusId;
        int runId;
        if (isTestRailEnabled) {
            String comment = result.getName();
            if (System.getenv("BUILD_URL") != null) {
                comment += String.format("\r\n%sallure", System.getenv("BUILD_URL"));
            }
            switch (result.getStatus()) {
                case ITestResult.SUCCESS: {
                    statusId = TestrailStatusId.PASSED;
                    break;
                }
                case ITestResult.CREATED:
                case ITestResult.FAILURE: {
                    statusId = TestrailStatusId.FAILED;
                    Issue issue = result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Issue.class);
                    if (issue != null) {
                        comment = String.format("%s\r\nIssue with id %s", comment, issue.value());
                    }
                    break;
                }
                default:
                    statusId = TestrailStatusId.RETEST;
            }
            String deviceName = this.deviceConfig.getDeviceName();
            TmsLink tms = result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(TmsLink.class);
            TmsLinks tmsLinks = result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(TmsLinks.class);
            synchronized (testRailClient) {
                try {
                    if (tmsLinks != null) {
                        tmsLinks = result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(TmsLinks.class);
                        for (TmsLink tmsLink : tmsLinks.value()) {
                            runId = testRailClient.getRunId(tmsLink.value(), stringTestPlanId, testPlanName, TESTRAIL_PROJECT_ID);
                            testRailClient.publishResult(tmsLink.value(), runId, statusId, comment, deviceName);
                        }
                    } else if (tms != null) {
                        runId = testRailClient.getRunId(tms.value(), stringTestPlanId, testPlanName, TESTRAIL_PROJECT_ID);
                        testRailClient.publishResult(tms.value(), runId, statusId, comment, deviceName);
                    } else {
                        Logger.get().error("Autotest doesn't contain the Testrail Id");
                    }
                } catch (TestRailException e) {
                    Logger.get().error(String.format("Error in publishing results to TestRail. Stacktrace: %s", e));
                }
            }
        }
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "attachLogs")
    public void releasePort(ITestResult testResult) {
        cleanup();
        cleanupStepNumber();
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo")
    public void attachLogs(ITestResult iTestResult) {
        if (!iTestResult.isSuccess() && isBeforePass) {
            FileUtils.writeStringToFile(debugConsoleLogsName.get(), SocketDriver.getLogsBuilder().toString());
            AllureHelper.attachFileToReport(debugConsoleLogsName.get());
            AllureHelper.attachFileToReport(AutomationDriver.getDeviceConf().getLogsPath());
        }
        AllureHelper.attachFileToReport(logName.get());
    }

    @AfterMethod(alwaysRun = true)
    protected void createLaunchesProperties() {
        FileUtils.createLaunchesPropertiesFile(countOfMethodCalls);
    }

    @AfterMethod(alwaysRun = true)
    protected void tryToSaveScreenshotAndVideo(ITestResult iTestResult) {
        try {
            String base64Video = AutomationDriver.stopVideoRecord();
            if (!iTestResult.isSuccess() && isBeforePass) {
                FileUtils.encodeFromBase64AndSave(base64Video, videoPath.get());
                if (!BUILD_NUMBER.isEmpty()) {
                    screenshotPath.set(createScreenshot(TestStatus.FAILED));
                    String videoName = GsUtil.saveVideoToGoogleBucket(videoPath.get());
                    String screenshotName = GsUtil.saveScreenshotToGoogleBucket(screenshotPath.get());
                    AllureHelper.removeFileAndAttachLinkToAllure(videoPath.get(), GsUtil.getGoogleBucketVideoLink(videoName));
                    AllureHelper.removeFileAndAttachLinkToAllure(screenshotPath.get(), GsUtil.getGoogleBucketScreenshotLink(screenshotName));
                } else {
                    attachScreenshot(TestStatus.FAILED);
                    AllureHelper.attachFileToReport(videoPath.get());
                }
            }
        } catch (WebDriverException | PMEnvironmentException ex) {
            Logger.get().fatal("Appium session was broken unexpectedly. Screenshot can't be created", ex);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        FacebookAccountsProvider.releaseAll();
    }

    protected String createScreenshot(TestStatus testStatus) {
        return createScreenshot(this.getClass(), testStatus.toString());
    }

    protected void attachScreenshot(String description) {
        attachScreenshot(this.getClass(), description);
    }

    private void attachScreenshot(TestStatus testStatus) {
        attachScreenshot(this.getClass(), testStatus.toString());
    }

    @Factory
    @SneakyThrows
    public Object[] factory(XmlTest test) {
        Set<BaseTest> tests = new HashSet<>();
        List<Device> devices = DeviceManager.getAllAvailableDevices();
        Collections.shuffle(devices);
        for (Device activeDevice : devices) {
            BaseTest testClass = this.getClass().getDeclaredConstructor().newInstance();
            if (testMethodsToInclude.stream().anyMatch(filteredTest -> filteredTest.getName()
                    .equalsIgnoreCase(testClass.getClass().getSimpleName())) || test.getXmlPackages().isEmpty()) {
                testClass.deviceConfig = getDeviceConfig(activeDevice);
                tests.add(testClass);
                Logger.get().info(String.format("Adding test : %s to run", testClass.getClass().getSimpleName()));
            }
        }
        int threadCount = devices.size() * TestNGParameters.THREAD_COUNT_MULTIPLIER;
        test.setThreadCount(threadCount);
        test.getSuite().setThreadCount(threadCount);
        test.getSuite().setDataProviderThreadCount(devices.size());
        return tests.toArray();
    }

    /**
     * Provides data to a test from json file with the same name
     * <p>
     * The test data JSON-file should have the same name as test class. For instance:
     * <p>
     * For test class: DailyWheelTest
     * JSON-file name should be: DailyWheelTest.json
     * <p>
     * !!! IMPORTANT !!!
     * <p>
     * This particular data provider won't work if the test marked with it doesn't also have annotation `TestDataKeys`.
     *
     * @return Object with test data
     */
    @DataProvider(name = "TestDataFromJson", parallel = true)
    public synchronized Object[][] getTestDataFromJson(Method method) {
        String suite = System.getProperty("suite");
        List<String> groups = Arrays.asList(method.getAnnotation(Test.class).groups());
        if (isNullOrEmpty(suite) || !GROUPS.contains(suite)) {
            suite = groups.get(0);
        }

        String testDataFile = String.format(DataHolder.getTestProperty(TestProperty.TEST_DATA_PATH_PATTERN),
                suite,
                this.getClass().getSimpleName());

        JsonElement jsonElement = JsonParser.parseReader(FileUtils.getFileReader(testDataFile));
        JsonArray jsonArray = jsonElement.getAsJsonObject().getAsJsonArray(
                DataHolder.getTestProperty(TestProperty.TEST_DATA_KEY_NAME));

        ArrayList<Object[]> dataList = new ArrayList<>();
        if (System.getProperty("parallelmode").equals(ParallelRunMode.DISTRIBUTED)) {
            return DataProviderHelper.getDataForDistributedMode(jsonArray, dataList, method, isIntegrationsSuite(suite));
        }

        return DataProviderHelper.getDataForAllDevices(jsonArray, dataList, method, isIntegrationsSuite(suite));
    }

    /**
     * This method sets test name for TestNG xml-files (files with test results)
     * Required for Reporting Portal results parsing
     *
     * @return the name of test (unique for each DDT-test)
     */
    @Override
    public String getTestName() {
        return testNgXmlTestName.get();
    }

    public String getTestCaseName() {
        return testCaseName.get();
    }

    private String getLogName(String template, String caseName) {
        return String.format(template, caseName, deviceConfig.getDeviceName(), deviceConfig.getUdid())
                .trim().replaceAll(LOG_CHARACTER_EXCLUSION_REGEX, UNDERSCORE);
    }

    private boolean isIntegrationsSuite(String suite) {
        return suite.equals(INTEGRATIONS_SUITE_NAME);
    }

    private static void reinstallAppForHsAvailableDevices() {
        List<Device> devices = DeviceManager.getHeadSpinPredefinedDevices();
        String path = PLATFORM.equals(AppiumServerPlatform.IOS.getKey()) ? DataHolder.getPathCapability(Path.IOS_IPA) :
                DataHolder.getPathCapability(Path.ANDROID_APK);
        String appId = ApiSteps.get().uploadAppToHeadSpin(path).getAppId();
        System.setProperty(HeadSpinProperty.HEADSPIN_APP_ID.getProperty(), appId);
        for (Device device : devices) {
            String udid = device.getHsUDID();
            ApiSteps.get().uninstallAppFromHeadSpinBySelector(PLATFORM, DataHolder.getTestProperty(TestProperty.APP_PACKAGE_NAME), udid);
            ApiSteps.get().installAppOnHeadSpin(appId, udid);
        }
    }

    private static DeviceConfig getDeviceConfig(Device device) {
        return DeviceConfig.builder()
                .deviceName(device.getModel())
                .udid(device.getUDID())
                .hsUdid(device.getHsUDID())
                .deviceAddress(device.getDeviceAddress())
                .ip(device.getIp())
                .wdaPort(device.getWdaPort())
                .port(String.valueOf(SocketUtils.findAvailableTcpPort()))
                .useRemoteHeadSpinServer(device.isUseRemoteHeadSpinServer())
                .hsServerUrl(device.getHsServerUrl())
                .platformVersion(device.getPlatformVersion())
                .build();
    }

    private void changeUnavailableDevice(ITestResult testResult) {
        Collections.shuffle(deviceConfigList);
        Throwable throwable = testResult.getThrowable();
        if (DeviceErrorUtils.isDeviceErrorMessagePresent(throwable, deviceConfig)) {
            Logger.get().info(String.format("Adding device %s to the list of devices with issues", deviceConfig.getUdid()));
            devicesWithIssuesList.add(deviceConfig);
            changeDeviceConfig();
            Logger.get().info(String.format("Changed device config to %s", deviceConfig.getUdid()));
        }
    }

    private void changeDeviceConfig() {
        Logger.get().info("Changing device configuration");
        deviceConfig = deviceConfigList.stream()
                .filter(device -> !deviceConfig.getUdid().equals(device.getUdid()))
                .filter(device -> devicesWithIssuesList.stream().noneMatch(issueDevice -> issueDevice.getUdid().equals(device.getUdid())))
                .findFirst()
                .orElseThrow(() -> new PMException("No new device found."));
    }
}
