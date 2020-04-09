package aquality.selenium.template.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum Environment {
    PROD("prod"),
    STAGE("stage");

    private ISettingsFile configFile;

    Environment(String name) {
        this.configFile = getConfigFile(name);
    }

    private ISettingsFile getConfigFile(final String envName) {
        Path resourcePath = Paths.get("environment", envName, "config.json");
        return new JsonSettingsFile(resourcePath.toString());
    }

    public String getStartUrl() {
        return this.configFile.getValue("/startUrl").toString();
    }
}
