package aquality.selenium.template.cucumber.stepdefinitions.data;

import aquality.selenium.template.cucumber.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.inject.Inject;

import static org.testng.Assert.assertEquals;

public class ScenarioContextSteps {
    private final ScenarioContext scenarioContext;

    @Inject
    public ScenarioContextSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I store '{int}' as {string}")
    public void storeValue(int value, String key) {
        scenarioContext.add(key, value);
    }

    @When("I add '{contextKey}' to '{contextKey}' and store it as {string}")
    public void addAndStore(int value1, int value2, String resultKey) {
        scenarioContext.add(resultKey, value1 + value2);
    }

    @Then("'{contextKey}' should be equal to '{int}'")
    public void theResultShouldBeEqualTo(int actualResult, int expectedResult) {
        assertEquals(actualResult, expectedResult, "The result is incorrect");
    }
}
