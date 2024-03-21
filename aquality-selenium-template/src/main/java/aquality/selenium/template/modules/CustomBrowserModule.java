package aquality.selenium.template.modules;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.browser.BrowserModule;
import aquality.selenium.core.localization.ILocalizedLogger;
import aquality.selenium.core.utilities.IElementActionRetrier;
import aquality.selenium.template.utilities.AllureBasedLocalizedLogger;
import aquality.selenium.template.utilities.CustomActionRetrier;
import com.google.inject.Provider;

public class CustomBrowserModule extends BrowserModule {
    private static final Provider<Browser> browserProvider = AqualityServices::getBrowser;

    public CustomBrowserModule() {
        super(browserProvider);
    }

    @Override
    public Class<? extends ILocalizedLogger> getLocalizedLoggerImplementation() {
        return AllureBasedLocalizedLogger.class;
    }

    @Override
    public Class<? extends IElementActionRetrier> getElementActionRetrierImplementation() {
        return CustomActionRetrier.class;
    }
}
