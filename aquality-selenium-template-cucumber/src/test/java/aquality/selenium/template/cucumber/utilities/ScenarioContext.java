package aquality.selenium.template.cucumber.utilities;

import io.cucumber.guice.ScenarioScoped;

import java.util.HashMap;
import java.util.Map;

/***
 * ScenarioContext allows you to share state between steps in a scenario.
 * It guarantees the clear state in a new scenario.
 */
@ScenarioScoped
public class ScenarioContext  {

    private final Map<String, Object> context;

    public ScenarioContext() {
        context = new HashMap<>();
    }

    public <T> void add(String key, T value) {
        context.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) context.get(key);
    }

    public boolean hasSaved(String key) {
        return context.containsKey(key);
    }
}
