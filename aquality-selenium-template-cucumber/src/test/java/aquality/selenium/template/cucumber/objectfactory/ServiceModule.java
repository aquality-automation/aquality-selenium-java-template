package aquality.selenium.template.cucumber.objectfactory;

import aquality.selenium.template.utilities.IScreenshotProvider;
import aquality.selenium.template.utilities.ScreenshotProvider;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IScreenshotProvider.class).toInstance(new ScreenshotProvider());
    }
}
