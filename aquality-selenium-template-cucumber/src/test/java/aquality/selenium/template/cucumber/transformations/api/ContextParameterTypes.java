package aquality.selenium.template.cucumber.transformations.api;

import aquality.selenium.template.cucumber.utilities.ScenarioContext;
import io.cucumber.java.ParameterType;
import io.restassured.response.Response;

import javax.inject.Inject;

public class ContextParameterTypes {
    private final ScenarioContext scenarioContext;

    @Inject
    public ContextParameterTypes(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @ParameterType(".*response.*")
    public Response response(String key) {
        Response value = scenarioContext.get(key);
        if (value == null) {
            throw new IllegalArgumentException(String.format("No response is stored in the context with name [%s]", key));
        }
        return value;
    }
}
