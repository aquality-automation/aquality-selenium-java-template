package aquality.selenium.template.cucumber.stepdefinitions.api.github;

import aquality.selenium.template.cucumber.utilities.ScenarioContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import javax.inject.Inject;

import static aquality.selenium.template.utilities.RequestSpecifications.commonGiven;

public class RequestCloneSteps {
    private final ScenarioContext scenarioContext;

    @Inject
    public RequestCloneSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I send GET '{endpoint}' request to github with saving the '{responseKey}' clone")
    @When("I send GET request to github endpoint saved as '{contextKey}' with saving the '{responseKey}' clone")
    public void sendGetRequestToGitHubAndSaveResponse(String endpoint, String contextKey) {
        Response response = commonGiven().when().get(endpoint);
        scenarioContext.add(contextKey, response);
    }
}
