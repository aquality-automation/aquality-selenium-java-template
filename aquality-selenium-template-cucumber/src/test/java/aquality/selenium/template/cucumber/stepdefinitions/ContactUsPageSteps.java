package aquality.selenium.template.cucumber.stepdefinitions;

import aquality.selenium.template.forms.pages.ContactUsPage;
import aquality.selenium.template.models.ContactUsInfo;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactUsPageSteps {

    private final ContactUsPage contactUsPage;

    public ContactUsPageSteps() {
        contactUsPage = new ContactUsPage();
    }

    @Then("Contact us page is opened")
    public void checkContactUsPageIsOpened() {
        assertTrue(contactUsPage.isDisplayed(), "Contact us page is opened");
    }

    @When("I fill contact form using following data:")
    public void fillContactUsPage(@Transpose List<ContactUsInfo> data) {
        ContactUsInfo contactUsInfo = data.get(0);
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
        assertTrue(contactUsPage.isEmailValidationMessagePresent(),
                "Email validation message should be displayed");
    }
}
