package aquality.selenium.template.cucumber.stepdefinitions;

import aquality.selenium.template.cucumber.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.inject.Inject;

import static org.testng.Assert.assertEquals;

public class ScenarioContextDemoSteps {

    private final ScenarioContext scenarioContext;

    @Inject
    public ScenarioContextDemoSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I store '{int}' as '{}'")
    public void storeValue(int value, String key) {
        scenarioContext.add(key, value);
    }

    @When("I add '{}' to '{}' and store it as '{}'")
    public void addAndStore(String key1, String key2, String resultKey) {
        int value1 = scenarioContext.get(key1);
        int value2 = scenarioContext.get(key2);
        scenarioContext.add(resultKey, value1 + value2);
    }

    @Then("'{}' should be equal to '{int}'")
    public void theResultShouldBeEqualTo(String resultKey, int expectedResult) {
        int actualResult = scenarioContext.get(resultKey);
        assertEquals(actualResult, expectedResult, "The result is incorrect");
    }
}
