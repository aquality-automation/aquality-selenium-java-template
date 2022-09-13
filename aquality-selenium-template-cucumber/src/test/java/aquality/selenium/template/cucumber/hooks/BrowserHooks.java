package aquality.selenium.template.cucumber.hooks;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.template.utilities.IScreenshotProvider;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.logging.LogType;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class BrowserHooks {

    private final IScreenshotProvider screenshotProvider;

    @Inject
    public BrowserHooks(IScreenshotProvider screenshotProvider) {
        this.screenshotProvider = screenshotProvider;
    }

    @After(order = 1)
    public void attachArtifacts(Scenario scenario) {
        if (AqualityServices.isBrowserStarted()) {
            scenario.attach(screenshotProvider.takeScreenshot(), "image/png", "screenshot.png");
            scenario.attach(getBrowser().getDriver().getPageSource().getBytes(StandardCharsets.UTF_8),
                    "text/html", "source.html");
            scenario.attach(getBrowser().getLogs(LogType.BROWSER).toJson().toString().getBytes(StandardCharsets.UTF_8),
                    "text/json", "console.log");
        }
    }

    @After(order = 0)
    public void closeBrowser() {
        if (AqualityServices.isBrowserStarted()) {
            getBrowser().quit();
        }
    }
}
