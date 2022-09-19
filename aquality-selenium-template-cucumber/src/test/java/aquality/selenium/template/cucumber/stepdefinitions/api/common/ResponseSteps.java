package aquality.selenium.template.cucumber.stepdefinitions.api.common;

import aquality.selenium.template.cucumber.utilities.ScenarioContext;
import aquality.selenium.template.utilities.FileHelper;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.testng.Assert;

import javax.inject.Inject;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static aquality.selenium.template.cucumber.utilities.SortingUtilities.sort;
import static aquality.selenium.template.cucumber.utilities.SortingUtilities.sortAsNumbers;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.lang.String.format;
import static org.hamcrest.Matchers.*;

public class ResponseSteps {
    private static final String SCHEMA_PATH_TEMPLATE = "jsonschemas/%s.json";
    private final ScenarioContext scenarioContext;

    @Inject
    public ResponseSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Then("the status code of the '{response}' is '{int}'")
    public void statusCodeOfResponseIs(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the {string} has the value saved as '{contextKey}' in the '{response}'")
    @Then("the {string} is {string} in the '{response}'")
    @Then("the {string} is {int} in the '{response}'")
    public void fieldInResponseIs(String fieldName, Object expectedValue, Response response) {
        response.then().body(fieldName, equalTo(expectedValue));
    }

    @Then("the {string} array has size less than or equal to {int} in the '{response}'")
    public void fieldArrayInResponseHasSizeLessThanOrEqualTo(String fieldName, int maxSize, Response response) {
        response.then().body(fieldName, hasSize(lessThanOrEqualTo(maxSize)));
    }

    @Then("the {string} array is ordered {isAscendingOrder} by {string} in the '{response}'")
    public void fieldArrayInResponseIsSortedBy(String path, boolean isAscendingOrder, String fieldName, Response response) {
        List<JsonNode> nodeList = response.then().extract().body().jsonPath().getList(path, JsonNode.class);
        if (nodeList == null || nodeList.isEmpty()) {
            throw new IllegalArgumentException("Cannot check order on null or empty collection");
        }
        List<String> actualOrder = nodeList.stream().map(node -> node.get(fieldName).toString()).collect(Collectors.toList());
        List<String> expectedOrder = nodeList.get(0).get(fieldName).isNumber()
                ? sortAsNumbers(actualOrder, isAscendingOrder)
                : sort(actualOrder, isAscendingOrder);
        Assert.assertEquals(actualOrder, expectedOrder,
                format("%s items must be sorted by %s in correct order. %nExpected:\t %s %n Actual:\t %s%n",
                        path, fieldName, expectedOrder, actualOrder));
    }

    @Then("the '{response}' matches json schema {string}")
    @SneakyThrows
    public void responseMatchesJsonSchema(Response response, String schemaName) {
        String pathToSchema = format(SCHEMA_PATH_TEMPLATE, schemaName);
        Allure.addAttachment("json schema",
                new FileInputStream(FileHelper.getResourceFileByName(pathToSchema)));
        response.then().body(matchesJsonSchemaInClasspath(pathToSchema));
    }

    @When("I extract the {string} from the '{response}' with saving it as {string}")
    public void extractAndSave(String path, Response response, String contextKey) {
        scenarioContext.add(contextKey, response.then().extract().path(path));
    }

    @Then("the '{response}' time is less than or equal to {long} seconds")
    public void responseTimeIsLessOrEqualTo(Response response, long seconds) {
        response.then().time(lessThanOrEqualTo(seconds), TimeUnit.SECONDS);
    }
}
