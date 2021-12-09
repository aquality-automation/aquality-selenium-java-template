package aquality.selenium.template.forms.pages;

import aquality.selenium.template.forms.BaseAppForm;
import org.openqa.selenium.By;

public class MainPage extends BaseAppForm {

    public MainPage() {
        super(By.xpath("//section[@class='services']"), "Main page");
    }
}
