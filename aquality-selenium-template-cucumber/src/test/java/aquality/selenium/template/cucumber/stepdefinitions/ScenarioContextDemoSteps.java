package aquality.selenium.template.cucumber.stepdefinitions;

import aquality.selenium.template.cucumber.utilities.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import javax.inject.Inject;

import static org.testng.Assert.assertEquals;

public class ScenarioContextDemoSteps {

    private final ScenarioContext scenarioContext;

    @Inject
    public ScenarioContextDemoSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I store '{int}' as '{}'")
    public void storeValue(int value, String key) {
        scenarioContext.add(key, value);
    }

    @Then("I add '{}' to '{}' and '{int}' as a result")
    public void addNumbersAndGetResult(String key1, String key2, int result) {
        int value1 = scenarioContext.get(key1);
        int value2 = scenarioContext.get(key2);
        assertEquals(value1 + value2, result, "The result is incorrect");
    }
}
