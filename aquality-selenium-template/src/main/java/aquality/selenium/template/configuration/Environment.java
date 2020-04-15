package aquality.selenium.template.configuration;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.nio.file.Path;
import java.nio.file.Paths;

class Environment {

    private Environment() {
    }

    static ISettingsFile getCurrentEnvironment() {
        String envName = (String) AqualityServices.get(ISettingsFile.class).getValue("/environment");
        Path resourcePath = Paths.get("environment", envName, "config.json");
        return new JsonSettingsFile(resourcePath.toString());
    }
}
