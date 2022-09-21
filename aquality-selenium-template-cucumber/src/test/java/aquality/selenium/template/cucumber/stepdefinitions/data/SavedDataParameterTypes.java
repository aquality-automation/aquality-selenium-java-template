package aquality.selenium.template.cucumber.stepdefinitions.data;

import aquality.selenium.template.cucumber.utilities.ScenarioContext;
import io.cucumber.java.ParameterType;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Parameter;
import io.qameta.allure.util.ResultsUtils;

import javax.inject.Inject;
import java.util.List;

public class SavedDataParameterTypes {
    private final ScenarioContext scenarioContext;

    @Inject
    public SavedDataParameterTypes(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @ParameterType(".*")
    public <T> T contextKey(String key) {
        if (!scenarioContext.hasSaved(key)) {
            throw new IllegalArgumentException(String.format("No value is stored in the context with name [%s]", key));
        }
        T value = scenarioContext.get(key);
        Parameter allureParameter = ResultsUtils.createParameter(key, value);
        Allure.getLifecycle().updateStep(stepResult -> {
            List<Parameter> parameters = stepResult.getParameters();
            parameters.add(allureParameter);
            stepResult.setParameters(parameters);
        });
        return value;
    }
}
