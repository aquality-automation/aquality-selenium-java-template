package aquality.selenium.template.cucumber.hooks;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.template.cucumber.objectfactory.CustomBrowserModule;
import io.cucumber.java.Before;

public class AqualityServicesHooks {

    @Before(order = 0)
    public void reinitializeAqualityServices() {
        AqualityServices.initInjector(new CustomBrowserModule(AqualityServices::getBrowser));
    }
}
