package com.appsoftcorp.automation.steps;

import com.codeborne.selenide.Selenide;
import com.appsoftcorp.automation.managers.BrowserManager;
import com.appsoftcorp.automation.utils.RegexUtils;
import com.appsoftcorp.framework.base.BaseSteps;
import com.appsoftcorp.framework.enums.automation.ScreenOrientation;
import com.appsoftcorp.framework.utils.waiters.SmartWait;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BrowserSteps extends BaseSteps {

    private static ThreadLocal<BaseSteps> stepsInstanceHolder = new InheritableThreadLocal<>();

    private ThreadLocal<List<String>> storedWindowHandles = new ThreadLocal<>();

    public static synchronized BrowserSteps get() {
        return (BrowserSteps) get(stepsInstanceHolder, BrowserSteps.class).get();
    }

    @Step("Setting screen orientation to {screenOrientation}")
    public void setScreenOrientation(ScreenOrientation screenOrientation) {
        BrowserManager.setScreenOrientation(screenOrientation);
    }

    @Step("Refresh page")
    public void refreshPage() {
        BrowserManager.refreshPage();
    }

    @Step("Store current window handle")
    public void storeCurrentWindowHandle() {
        if (storedWindowHandles == null) {
            storedWindowHandles.set(new ArrayList<>());
        }
        storedWindowHandles.get().add(BrowserManager.getWindowHandle());
    }

    @Step("Open new tab and store window handle")
    public void openNewTabAndStoreWindowHandle() {
        BrowserManager.openNewTabAndSwitch();
        storedWindowHandles.get().add(BrowserManager.getWindowHandle());
    }

    @Step("Open page {ulr}")
    public void openPage(String url) {
        BrowserManager.openPage(url);
    }

    @Step("Navigate to Invalid Url {invalidUrl}")
    public void navigateToInvalidUrl(String invalidUrl) {
        BrowserManager.openInvalidUrl(invalidUrl);
    }

    @Step("Go back to previous page")
    public void goBack() {
        BrowserManager.back();
    }

    @Step("Close current window handle and remove it from stored window handles")
    public void closeCurrentWindowHandleAndRemoveItFromWindowHandles() {
        storedWindowHandles.get().remove(BrowserManager.getWindowHandle());
        BrowserManager.closeCurrentWindowHandle();
    }

    @Step("Clear window handle list")
    public void clearWindowHandleList() {
        storedWindowHandles.set(new ArrayList<>());
    }

    @Step("Switch to last window handle")
    public void switchToLastWindowHandle() {
        List<String> windowHandles = BrowserManager.getWindowHandles();
        BrowserManager.switchTo(windowHandles.get(windowHandles.size() - 1));
    }

    @Step("Switch to first window handle")
    public void switchToFirstWindowHandle() {
        List<String> windowHandles = BrowserManager.getWindowHandles();
        BrowserManager.switchTo(windowHandles.get(0));
    }

    @Step("Switch to unknown window handle")
    public void switchToUnknownWindowHandle() {
        List<String> windowHandles = BrowserManager.getWindowHandles();
        windowHandles.removeAll(storedWindowHandles.get());
        Selenide.switchTo().window(windowHandles.stream().findFirst().get());
    }

    @Step("Accept alert")
    public void acceptAlert() {
        BrowserManager.acceptAlertAndSwitchToDefaultContent();
    }

    @Step("Switch to default content")
    public void switchToDefaultContent() {
        BrowserManager.switchToDefault();
    }

    @Step("Wait for alert to be present")
    public void waitForAlertToBePresent() {
        BrowserManager.waitForAlertToBePresent();
    }

    @Step("Switch to first tab, open page by {newTabUrl} url, execute get value function and switch back")
    public <T> T switchToFirstTabOpenPageAndExecuteGetValueFuncAndSwitchToPreviousTab(String newTabUrl,
                                                                                      Supplier<T> getValue) {
        return BrowserManager.switchToTabOpenPageAndExecuteGetValueFunc(storedWindowHandles.get().get(0),
                newTabUrl, getValue);
    }

    @Step("Switch to first tab, execute get value function and switch back")
    public <T> T switchToFirstTabAndExecuteGetValueFuncAndSwitchToPreviousTab(Supplier<T> getValue) {
        return BrowserManager.switchToFirstTabAndExecuteGetValueFuncAndSwitchToPreviousTab(storedWindowHandles.get()
                .get(0), getValue);
    }

    @Step("Get count of window handles")
    public int getCountOfWindowHandles() {
        return BrowserManager.getWindowHandleCount();
    }

    @Step("Getting alert text matching regex '{regex}'")
    public String getAlertTextMatchingRegex(String regex) {
        return RegexUtils.getMatch(regex, BrowserManager.getAlertMessage());
    }

    @Step("Getting alert presence state")
    public boolean isAlertPresent() {
        return BrowserManager.waitForAlertToBePresent();
    }

    // assertions

    @Step("Asserting that window handle count equal to {count}")
    public void assertThatWindowHandleCountEqualTo(int count, boolean waitFor) {
        if (waitFor) {
            SmartWait.waitForTrue(x -> BrowserManager.getWindowHandleCount() == count);
        }
        assertion.assertEquals(BrowserManager.getWindowHandleCount(), count,
                "Window handle counts aren't equal");
    }

    @Step("Asserting that url starts with '{partialUrl}'")
    public void assertThatCurrentWindowHandleUrlStartsWith(String partialUrl) {
        assertion.assertTrue(SmartWait.waitForTrue(x -> BrowserManager.getCurrentUrl().startsWith(partialUrl)),
                String.format("Url does not start with '%s'", partialUrl));
    }

    @Step("Asserting that alert text matches '{expectedMessage}'")
    public void asserThatAlertTextMatchesMessage(String expectedMessage) {
        assertion.assertTrue(RegexUtils.isMatch(expectedMessage, BrowserManager.getAlertMessage()),
                String.format("Alert text does not match '%s' regex", expectedMessage));
    }

    @Step("Asserting that alert is not present")
    public void asserThatAlertIsNotPresent() {
        assertion.assertTrue(BrowserManager.waitForAlertToBeNotPresent(), "Alert is present");
    }
}
