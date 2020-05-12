package aquality.selenium.template.forms;

import aquality.selenium.forms.Form;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

public class TopBarMenu extends Form {

    public TopBarMenu() {
        super(By.id("masthead"), "Header");
    }

    public void openHeaderMenu(Item menuItem) {
        getElementFactory().getButton(menuItem.getMenuItemLocator(), menuItem.toString()).clickAndWait();
    }

    @Getter
    @AllArgsConstructor
    public enum Item {
        CONTACT_US(By.xpath("//div[@id='primary-navigation']//li[contains(@class, 'contact-us menu')]//a"));

        private final By menuItemLocator;
    }
}
