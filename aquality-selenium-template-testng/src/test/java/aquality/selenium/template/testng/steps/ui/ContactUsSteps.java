package aquality.selenium.template.testng.steps.ui;

import aquality.selenium.template.forms.pages.ContactUsPage;
import aquality.selenium.template.models.ContactUsInfo;
import com.google.inject.Inject;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ContactUsSteps {
    @Inject
    private ContactUsPage contactUsPage;

    @Step("Contact us page is opened")
    public void checkContactUsPageIsOpened() {
        assertThat(contactUsPage.state().isDisplayed())
                .describedAs(contactUsPage.getName() + " must be opened")
                .isTrue();
    }

    @Step("I fill contact form using following data:")
    public void fillContactUsPage(ContactUsInfo contactUsInfo) {
        contactUsPage.setName(contactUsInfo.getName())
                .setCompany(contactUsInfo.getCompany())
                .setPhone(contactUsInfo.getPhone())
                .setComment(contactUsInfo.getComment());
    }

    @Step("I accept Privacy and Cookies Policy")
    public void acceptPrivacyAndCookiesPolicy() {
        contactUsPage.checkPrivacyAndCookies();
    }

    @Step("I click Send button")
    public void clickSendButton(){
        contactUsPage.clickSend();
    }

    @Step("Notification about empty fields is present")
    public void checkEmptyFieldWarning() {
        assertThat(contactUsPage.isEmailValidationMessagePresent())
                .describedAs("Email validation message should be displayed")
                .isTrue();
    }

    @Step("I save Contact us page dump")
    public void saveContactUsPageDump() {
        contactUsPage.dump().save();
    }

    @Step("Contact us page dump is different")
    public void contactUsPageDumpIsDifferent() {
        assertTrue(contactUsPage.dump().compare() > 0, "The form dump should differ");
    }
}
