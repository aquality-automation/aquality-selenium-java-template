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

    public enum Item {
        CONTACT_US(By.xpath("//div[@id='primary-navigation']//li[contains(@class, 'contact-us menu')]//a"));

        Item(By menuItemLocator) {
            this.menuItemLocator = menuItemLocator;
        }

        private final By menuItemLocator;

        public By getMenuItemLocator() {
            return menuItemLocator;
        }
    }
}
