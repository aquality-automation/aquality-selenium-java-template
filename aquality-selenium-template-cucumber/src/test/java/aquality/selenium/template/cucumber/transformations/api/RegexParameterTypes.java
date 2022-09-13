package aquality.selenium.template.cucumber.transformations.api;

import io.cucumber.java.ParameterType;

public class RegexParameterTypes {

    @ParameterType(".*response.*")
    public String responseKey(String key) {
        return key;
    }

    @ParameterType("/([\\w-/]+)")
    public String endpoint(String endpoint) {
        return endpoint;
    }
}
