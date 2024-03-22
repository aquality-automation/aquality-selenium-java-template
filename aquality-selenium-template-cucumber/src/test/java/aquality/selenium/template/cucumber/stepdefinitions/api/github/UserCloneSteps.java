package aquality.selenium.template.cucumber.stepdefinitions.api.github;

import aquality.selenium.template.cucumber.utilities.ScenarioContext;
import aquality.selenium.template.models.User;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import javax.inject.Inject;

public class UserCloneSteps {
    private final ScenarioContext scenarioContext;

    @Inject
    public UserCloneSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I save the user from the '{response}' as {string} clone")
    public void saveTheUserFromTheResponse(Response response, String key) {
        scenarioContext.add(key, response.as(User.class));
    }

    @Then("User '{contextKey}' is different from the user '{contextKey}' clone")
    public void userUserIsDifferentFromTheUserUser(User user1, User user2) {
        Assert.assertNotEquals(user1, user2, "Users should not be equal");
    }
}
