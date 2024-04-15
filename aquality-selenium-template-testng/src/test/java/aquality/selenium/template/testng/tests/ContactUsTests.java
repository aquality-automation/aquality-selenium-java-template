package aquality.selenium.template.testng.tests;

import aquality.selenium.template.models.ContactUsInfo;
import aquality.selenium.template.testng.steps.ui.ContactUsSteps;
import aquality.selenium.template.testng.utilities.JsonDataProvider;
import com.google.inject.Inject;
import org.testng.annotations.Test;

public class ContactUsTests extends BaseTest {
    @Inject
    ContactUsSteps contactUsSteps;

    @Test(description = "Empty email validation is working on Contact Us page",
    dataProvider = "getContacts", dataProviderClass = JsonDataProvider.class)
    public void testEmailValidation(ContactUsInfo contactInfo) {
        landingSteps.checkMainPageIsOpened();
        landingSteps.openContactUsPage();
        contactUsSteps.checkContactUsPageIsOpened();

        contactUsSteps.saveContactUsPageDump();
        contactUsSteps.fillContactUsPage(contactInfo);
        contactUsSteps.acceptPrivacyAndCookiesPolicy();
        contactUsSteps.clickSendButton();

        contactUsSteps.checkEmptyFieldWarning();
        contactUsSteps.contactUsPageDumpIsDifferent();
    }
}
