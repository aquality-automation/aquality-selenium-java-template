package aquality.selenium.template.cucumber.objectfactory;

import aquality.selenium.browser.Browser;
import aquality.selenium.browser.BrowserModule;
import aquality.selenium.core.localization.ILocalizedLogger;
import com.google.inject.Provider;

public class CustomBrowserModule extends BrowserModule {
    public CustomBrowserModule(Provider<Browser> applicationProvider) {
        super(applicationProvider);
    }

    @Override
    public Class<? extends ILocalizedLogger> getLocalizedLoggerImplementation() {
        return AllureBasedLocalizedLogger.class;
    }
}
