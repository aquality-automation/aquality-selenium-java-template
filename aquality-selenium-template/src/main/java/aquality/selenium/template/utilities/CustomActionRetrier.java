package aquality.selenium.template.utilities;

import aquality.selenium.core.configurations.IRetryConfiguration;
import aquality.selenium.core.utilities.ElementActionRetrier;
import com.google.inject.Inject;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;

import java.util.ArrayList;
import java.util.List;

public class CustomActionRetrier extends ElementActionRetrier {
    @Inject
    public CustomActionRetrier(IRetryConfiguration retryConfiguration) {
        super(retryConfiguration);
    }

    @Override
    public List<Class<? extends Throwable>> getHandledExceptions() {
        List<Class<? extends Throwable>> handledExceptions = new ArrayList<>();
        handledExceptions.add(ElementClickInterceptedException.class);
        handledExceptions.add(ElementNotInteractableException.class);
        handledExceptions.addAll(super.getHandledExceptions());
        return handledExceptions;
    }
}
