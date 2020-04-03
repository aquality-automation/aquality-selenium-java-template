package aquality.selenium.template.steps.hooks;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.template.configuration.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BrowserHooks {

    @Before
    public void startBrowser() {
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(Configuration.getCurrentEnvironment().getStartUrl());
    }

    @After
    public void closeBrowser() {
        AqualityServices.getBrowser().quit();
    }
}
