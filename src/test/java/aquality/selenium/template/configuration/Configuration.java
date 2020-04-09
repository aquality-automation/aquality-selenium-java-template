package aquality.selenium.template.configuration;

public class Configuration {

    private static Environment currentEnvironment;

    private Configuration() {
    }

    public static Environment getCurrentEnvironment() {
        if (currentEnvironment == null) {
            final String envPropertyValue = System.getProperty("environment");
            currentEnvironment = envPropertyValue != null
                    ? Environment.valueOf(envPropertyValue.toUpperCase())
                    : Environment.STAGE;
        }
        return currentEnvironment;
    }
}
