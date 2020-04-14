package aquality.selenium.template.cucumber.runners;

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
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
