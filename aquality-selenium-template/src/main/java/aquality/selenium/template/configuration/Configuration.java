package aquality.selenium.template.configuration;

public class Configuration {

    private Configuration() {
    }

    public static String getStartUrl() {
        return Environment.getCurrentEnvironment().getValue("/startUrl").toString();
    }
}
