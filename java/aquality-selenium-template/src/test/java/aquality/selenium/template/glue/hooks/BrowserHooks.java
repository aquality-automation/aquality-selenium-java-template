package aquality.selenium.template.glue.hooks;

import aquality.selenium.browser.AqualityServices;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BrowserHooks {

    @Before
    public void startBrowser() {
        AqualityServices.getBrowser();
    }

    @After(order = 0)
    public void closeBrowser() {
        AqualityServices.getBrowser().quit();
    }
}
