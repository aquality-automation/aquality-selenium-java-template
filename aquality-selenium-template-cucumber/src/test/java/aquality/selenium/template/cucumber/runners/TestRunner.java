package aquality.selenium.template.cucumber.runners;

import aquality.selenium.template.cucumber.objectfactory.CustomObjectFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/aquality/selenium/template/cucumber/features"},
        glue = {
                "aquality.selenium.template.cucumber.hooks",
                "aquality.selenium.template.cucumber.transformations",
                "aquality.selenium.template.cucumber.stepdefinitions"
        },
        plugin = {
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
                "aquality.tracking.integrations.cucumber5jvm.AqualityTrackingCucumber5Jvm"
        },
        strict = true,
        objectFactory = CustomObjectFactory.class
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
