package aquality.selenium.template.cucumber.stepdefinitions;

import aquality.selenium.template.forms.pages.ContactUsPage;
import aquality.selenium.template.models.ContactUsInfo;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertTrue;

public class ContactUsPageSteps {

    private final ContactUsPage contactUsPage;

    public ContactUsPageSteps() {
        contactUsPage = new ContactUsPage();
    }

    @Then("Contact us page is opened")
    public void checkContactUsPageIsOpened() {
        assertTrue(contactUsPage.state().isDisplayed(), "Contact us page is opened");
    }

    @When("I fill contact form using following data:")
    public void fillContactUsPage(@Transpose ContactUsInfo contactUsInfo) {
        contactUsPage.setName(contactUsInfo.getName())
                .setCompany(contactUsInfo.getCompany())
                .setPhone(contactUsInfo.getPhone())
                .setEmail(contactUsInfo.getEmail())
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
