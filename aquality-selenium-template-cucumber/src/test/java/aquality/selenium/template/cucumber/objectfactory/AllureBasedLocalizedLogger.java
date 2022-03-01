package aquality.selenium.template.cucumber.objectfactory;

import aquality.selenium.core.configurations.ILoggerConfiguration;
import aquality.selenium.core.localization.ILocalizationManager;
import aquality.selenium.core.localization.LocalizedLogger;
import aquality.selenium.core.logging.Logger;
import com.google.inject.Inject;
import io.qameta.allure.Allure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Established adding aquality localized messages to allure report.
 */
public class AllureBasedLocalizedLogger extends LocalizedLogger {
    private final ILocalizationManager localizationManager;

    @Inject
    public AllureBasedLocalizedLogger(ILocalizationManager localizationManager, Logger logger, ILoggerConfiguration loggerConfiguration) {
        super(localizationManager, logger, loggerConfiguration);
        this.localizationManager = localizationManager;
    }

    private String localizeMessage(String messageKey, Object... args) {
        return localizationManager.getLocalizedMessage(messageKey, args);
    }

    @Override
    public void infoElementAction(String elementType, String elementName, String messageKey, Object... args) {
        String message = String.format("%1$s '%2$s' :: %3$s", elementType, elementName, localizeMessage(messageKey, args));
        addStepToAllure(message);
        super.infoElementAction(elementType, elementName, messageKey, args);
    }

    @Override
    public void info(String messageKey, Object... args) {
        String message = localizeMessage(messageKey, args);
        addStepToAllure(message);
        super.info(messageKey, args);
    }

    private void addStepToAllure(String message) {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        Allure.step(String.format("%s - %s", formatter.format(new Date()), message));
    }
}
