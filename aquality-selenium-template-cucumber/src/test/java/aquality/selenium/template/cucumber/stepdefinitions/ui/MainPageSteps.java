package aquality.selenium.template.cucumber.stepdefinitions.ui;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.template.configuration.Configuration;
import aquality.selenium.template.forms.TopBarMenu;
import aquality.selenium.template.forms.pages.MainPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static aquality.selenium.template.forms.TopBarMenu.Item.CONTACT_US;

public class MainPageSteps {

    private final MainPage mainPage;
    private final TopBarMenu topBarMenu;

    public MainPageSteps() {
        mainPage = new MainPage();
        topBarMenu = new TopBarMenu();
    }

    @Given("Main page is opened")
    public void mainPageIsOpened() {
        AqualityServices.getBrowser().goTo(Configuration.getStartUrl());
    }

    @When("I open Contact us page")
    public void openContactUsPage() {
        mainPage.acceptCookies();
        topBarMenu.openHeaderMenu(CONTACT_US);
    }
}
