package aquality.selenium.template.cucumber.hooks;

import aquality.selenium.browser.AqualityServices;
import io.cucumber.java.After;

public class BrowserHooks {

    @After(order = 0)
    public void closeBrowser() {
        AqualityServices.getBrowser().quit();
    }
}
