package aquality.selenium.template.cucumber.transformations;

import io.cucumber.java.ParameterType;

import java.util.Arrays;
import java.util.List;

public class SortingParameterTypes {
    @ParameterType("alphabetically|A-Z|Z-A|ascending|descending|asc|desc")
    public boolean isAscendingOrder(String order) {
        List<String> ascOrder = Arrays.asList("alphabetically", "A-Z", "ascending", "asc");
        return ascOrder.contains(order);
    }
}
