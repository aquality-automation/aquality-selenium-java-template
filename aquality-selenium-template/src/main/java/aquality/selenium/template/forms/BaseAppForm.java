package aquality.selenium.template.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class BaseAppForm extends Form {

    private final IButton btnAcceptCookies = getElementFactory().getButton(By.xpath("//*[contains(@class,'cookies__button')]//button"), "Accept cookies");

    protected BaseAppForm(final By locator, final String name) {
        super(locator, name);
    }

    public void acceptCookies() {
        btnAcceptCookies.click();
    }
}
