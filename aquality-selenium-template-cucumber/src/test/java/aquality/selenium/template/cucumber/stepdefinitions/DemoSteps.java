package aquality.selenium.template.cucumber.stepdefinitions;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.template.configuration.Configuration;
import aquality.selenium.template.forms.TopBarMenu;
import aquality.selenium.template.forms.pages.ContactUsPage;
import aquality.selenium.template.forms.pages.MainPage;
import aquality.selenium.template.models.ContactUsInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static aquality.selenium.template.forms.TopBarMenu.Item.CONTACT_US;

public class DemoSteps {

    private final MainPage mainPage = new MainPage();
    private final TopBarMenu topBarMenu = new TopBarMenu();
    private final ContactUsPage contactUsPage = new ContactUsPage();

    @Given("Main page is opened")
    public void mainPageIsOpened() {
        Assert.fail("Test fail");
        AqualityServices.getBrowser().goTo(Configuration.getStartUrl());
    }

    @When("I open Contact us page")
    public void openContactUsPage() {
        mainPage.acceptCookies();
        topBarMenu.openHeaderMenu(CONTACT_US);
    }

    @Then("Contact us page is opened")
    public void checkContactUsPageIsOpened() {
        Assert.assertTrue(contactUsPage.isDisplayed(), "Contact us page is opened");
    }

    @When("I fill contact form using following data:")
    public void fillContactUsPage(ContactUsInfo contactUsInfo) {
        contactUsPage.setName(contactUsInfo.getName())
                .setCompany(contactUsInfo.getCompany())
                .setPhone(contactUsInfo.getPhone())
                .setComment(contactUsInfo.getComment());
    }

    @When("I accept Privacy and Cookies Policy")
    public void acceptPrivacyAndCookiesPolicy() {
        contactUsPage.checkPrivacyAndCookies();
    }

    @When("I click Send button")
    public void clickSendButton(){
        contactUsPage.clickSend();
    }

    @Then("Notification about empty fields is present")
    public void checkEmptyFieldWarning() {
        Assert.assertTrue(contactUsPage.isEmailValidationMessagePresent(),
                "Email validation message should be displayed");
    }
}
