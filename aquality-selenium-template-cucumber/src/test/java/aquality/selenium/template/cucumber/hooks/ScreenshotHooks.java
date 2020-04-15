package aquality.selenium.template.cucumber.hooks;

import aquality.selenium.template.utilities.ScreenshotProvider;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;

public class ScreenshotHooks {

    @After(order = 1)
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotProvider screenshotProvider = new ScreenshotProvider();
            scenario.embed(screenshotProvider.takeScreenshot(), "image/png");
        }
    }
}
