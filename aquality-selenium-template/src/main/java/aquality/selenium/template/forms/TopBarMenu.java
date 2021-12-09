package aquality.selenium.template.forms;

import aquality.selenium.forms.Form;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

public class TopBarMenu extends Form {

    public TopBarMenu() {
        super(By.className("header"), "Header");
    }

    public void openHeaderMenu(Item menuItem) {
        getElementFactory().getButton(menuItem.getMenuItemLocator(), menuItem.toString()).clickAndWait();
    }

    @Getter
    @AllArgsConstructor
    public enum Item {
        CONTACT_US(By.xpath("//*[contains(@class,'header__contact')]//a"));

        private final By menuItemLocator;
    }
}
