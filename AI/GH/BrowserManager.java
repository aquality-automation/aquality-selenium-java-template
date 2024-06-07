package com.appsoftcorp.automation.managers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.appsoftcorp.framework.base.DataHolder;
import com.appsoftcorp.framework.enums.automation.ScreenOrientation;
import com.appsoftcorp.framework.enums.config.Timeout;
import com.appsoftcorp.framework.exceptions.PMException;
import com.appsoftcorp.framework.utils.Logger;
import com.appsoftcorp.framework.utils.waiters.SmartWait;
import org.openqa.selenium.*;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BrowserManager {

    private static final String SEPARATOR_FOR_SIZE = "x";
    private static final String PATTERN_FOR_SIZE = "%dx%d";

    public static void openPage(String url) {
        Selenide.open(url);
    }

    //TODO: This step is temporary. Remove when authorization in Game Hub will work without UAT authorization
    public static void openPage(String url, String token) {
        Selenide.open(String.format(url, token));
    }

    public static void back() {
        Selenide.back();
    }

    public static void setScreenOrientation(ScreenOrientation screenOrientation) {
        String currentSize = Configuration.browserSize;
        int x = Integer.parseInt(currentSize.split(SEPARATOR_FOR_SIZE)[0]);
        int y = Integer.parseInt(currentSize.split(SEPARATOR_FOR_SIZE)[1]);
        switch (screenOrientation) {
            case LANDSCAPE:
                Configuration.browserSize = String.format(PATTERN_FOR_SIZE, Math.max(x, y),
                        Math.min(x, y));
                setWindowSize(Math.max(x, y), Math.min(x, y));
                break;
            case PORTRAIT:
                Configuration.browserSize = String.format(PATTERN_FOR_SIZE, Math.min(x, y),
                        Math.max(x, y));
                setWindowSize(Math.min(x, y), Math.max(x, y));
                break;
            default:
                throw new PMException(String.format("Unknown screen orientation %s",
                        screenOrientation));
        }
    }

    public static void setWindowSize(int width, int height) {
        Logger.get().info(String.format("Setting window size to %dx%d", width, height));
        getWebDriver().manage().window().setSize(new Dimension(width, height));
    }

    public static void switchToFrame(SelenideElement element) {
        Selenide.switchTo().frame(element);
    }

    public static int getWindowHandleCount() {
        return getWebDriver().getWindowHandles().size();
    }

    public static <T> T openNewTabAndExecuteGetValueFuncAndCloseTab(String newTabUrl,
                                                                    Supplier<T> getValue) {
        String currentTab = getWebDriver().getWindowHandle();
        openNewTabAndSwitch();
        openPage(newTabUrl);
        T value = getValue.get();
        getWebDriver().close();
        getWebDriver().switchTo().window(currentTab);
        return value;
    }

    public static <T> T switchToTabOpenPageAndExecuteGetValueFunc(String tabId, String newTabUrl, Supplier<T> getValue) {
        String currentTab = getWebDriver().getWindowHandle();
        T value;
        String anotherTabUrl;
        switchTo(tabId);
        anotherTabUrl = getWebDriver().getCurrentUrl();
        openPage(newTabUrl);
        value = getValue.get();
        openPage(anotherTabUrl);
        switchTo(currentTab);
        return value;
    }

    public static <T> T switchToFirstTabAndExecuteGetValueFuncAndSwitchToPreviousTab(String tabId, Supplier<T> getValue) {
        String currentTab = getWebDriver().getWindowHandle();
        switchTo(tabId);
        T value = getValue.get();
        switchTo(currentTab);
        return value;
    }

    public static void openNewTabAndSwitch() {
        Selenide.switchTo().newWindow(WindowType.TAB);
    }

    public static void closeCurrentWindowHandle() {
        Selenide.closeWindow();
    }

    public static void switchToDefault() {
        Selenide.switchTo().defaultContent();
    }

    public static void switchTo(String windowHandle) {
        Selenide.switchTo().window(windowHandle);
    }

    public static void acceptAlertAndSwitchToDefaultContent() {
        waitForAlertToBePresent();
        getWebDriver().switchTo().alert().dismiss();
        getWebDriver().switchTo().defaultContent();
    }

    public static void refreshPage() {
        getWebDriver().navigate().refresh();
    }

    public static List<String> getWindowHandles() {
        return getWebDriver().getWindowHandles().stream().collect(Collectors.toList());
    }

    public static String getAlertMessage() {
        waitForAlertToBePresent();
        return getWebDriver().switchTo().alert().getText();
    }

    public static boolean waitForAlertToBePresent() {
        return SmartWait.waitForTrue(() -> getWebDriver().switchTo().alert().getText() != null,
                DataHolder.getTimeout(Timeout.ALERT_WAIT_SECONDS));
    }

    public static boolean waitForAlertToBeNotPresent() {
        return SmartWait.waitForTrue(() -> {
            try {
                getWebDriver().switchTo().alert();
            } catch (NoAlertPresentException e) {
                return true;
            }
            return false;
        }, DataHolder.getTimeout(Timeout.ALERT_WAIT_SECONDS));
    }

    public static String getWindowHandle() {
        return getWebDriver().getWindowHandle();
    }

    public static String getPageSource() {
        return getWebDriver().getPageSource();
    }

    public static String getCurrentUrl() {
        return getWebDriver().getCurrentUrl();
    }

    public static Cookie getCookieNamed(String name) {
        return getWebDriver().manage().getCookieNamed(name);
    }

    public static void openInvalidUrl(String invalidUrl) {
        try {
            Selenide.open(invalidUrl);
        } catch (WebDriverException exception) {
            Logger.get().info("WebDriverException occurred: " + exception.getMessage());
        }
    }
}
