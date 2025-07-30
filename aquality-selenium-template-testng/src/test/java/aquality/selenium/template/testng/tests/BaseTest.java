package aquality.selenium.template.testng.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.localization.ILocalizedLogger;
import aquality.selenium.template.configuration.Configuration;
import aquality.selenium.template.modules.CustomBrowserModule;
import aquality.selenium.template.testng.steps.ui.LandingSteps;
import aquality.selenium.template.testng.utilities.ModuleFactory;
import aquality.selenium.template.utilities.IScreenshotProvider;
import com.google.inject.Inject;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;
import org.assertj.core.description.Description;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;

import java.io.ByteArrayInputStream;
import java.util.function.Consumer;

@Guice(moduleFactory = ModuleFactory.class)
public class BaseTest {
    @Inject
    LandingSteps landingSteps;
    @Inject
    IScreenshotProvider screenshotProvider;
    @Inject
    ILocalizedLogger localizedLogger;
    @Inject
    CustomBrowserModule customBrowserModule;
    private Browser browser;

    @BeforeTest
    public void beforeTest() {
        Consumer<Description> descriptionConsumer = description -> localizedLogger.info("Assertion: %s", description.value());
        Assertions.setDescriptionConsumer(descriptionConsumer);
    }

    @BeforeMethod
    public void setup() {
        AqualityServices.initInjector(customBrowserModule);
        browser = AqualityServices.getBrowser();
        browser.goTo(Configuration.getStartUrl());
        browser.waitForPageToLoad();
    }

    @AfterMetho
    public void cleanUp(ITestContext context) {
        if (AqualityServices.isBrowserStarted()) {
            Allure.addAttachment("page source", "text/html",
                    browser.getDriver().getPageSource(), "html");
            Allure.addAttachment("screenshot", "image/png",
                    new ByteArrayInputStream(screenshotProvider.takeScreenshot()), "png");
            browser.quit();
        }
    }
}







//adding teemfgqorugnoer