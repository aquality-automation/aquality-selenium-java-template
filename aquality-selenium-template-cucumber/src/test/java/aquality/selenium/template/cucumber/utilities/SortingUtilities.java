package aquality.selenium.template.cucumber.utilities;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.validator.GenericValidator;

import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class SortingUtilities {
    public static List<String> sortAsNumbers(Collection<String> collection, boolean isAscendingOrder) {
        Stream<?> numbers = collection.stream().allMatch(GenericValidator::isInt)
                ? collection.stream().map(Integer::valueOf) : collection.stream().map(Float::valueOf);
        numbers = isAscendingOrder ? numbers.sorted() : numbers.sorted(Collections.reverseOrder());
        return numbers.map(String::valueOf).collect(Collectors.toList());
    }

    @SneakyThrows
    public static List<String> sort(Collection<String> collection, boolean isAscendingOrder) {
        List<String> expectedOrder = new ArrayList<>(collection);
        String rules = ((RuleBasedCollator) Collator.getInstance(Locale.US)).getRules();
        RuleBasedCollator comparator = new RuleBasedCollator(rules);
        comparator.setStrength(Collator.SECONDARY);
        expectedOrder.sort(isAscendingOrder ? comparator : comparator.reversed());
        return expectedOrder;
    }
}
