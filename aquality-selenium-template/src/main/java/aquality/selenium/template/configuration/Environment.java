package aquality.selenium.template.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.nio.file.Path;
import java.nio.file.Paths;

class Environment {

    private Environment() {
    }

    static ISettingsFile getCurrentEnvironment() {
        String envVarValue = System.getProperty("environment");
        String envName = envVarValue == null ? "stage" : envVarValue;
        Path resourcePath = Paths.get("environment", envName, "config.json");
        return new JsonSettingsFile(resourcePath.toString());
    }
}
