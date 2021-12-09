package aquality.selenium.template.forms.pages;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.template.forms.BaseAppForm;
import org.openqa.selenium.By;

public class ContactUsPage extends BaseAppForm {

    private final ITextBox txbName = getElementFactory().getTextBox(By.id("your-name"), "Name");
    private final ITextBox txbCompany = getElementFactory().getTextBox(By.id("your-company"), "Company");
    private final ITextBox txbPhone = getElementFactory().getTextBox(By.id("your-phone"), "Phone");
    private final ITextBox txbEmail = getElementFactory().getTextBox(By.id("your-email"), "Email");
    private final ITextBox txbComment = getElementFactory().getTextBox(By.id("your-message"), "Comment");
    private final ICheckBox cmbPrivacy = getElementFactory().getCheckBox(By.name("privacy"), "Privacy", ElementState.EXISTS_IN_ANY_STATE);
    private final IButton btnSend = getElementFactory().getButton(By.xpath("//*[contains(@class,'contactsForm__submit')]//button"), "Send");
    private final ILabel lblEmailAlert = getElementFactory().getLabel(By.id("modal-contacts-thanks"), "Email validating message");

    public ContactUsPage(){
        super(By.xpath("//section[contains(@class,'contactsForm')]"), "Contact Us");
    }

    public ContactUsPage setName(final String name) {
        txbName.clearAndType(name);
        return this;
    }

    public ContactUsPage setCompany(final String company) {
        txbCompany.clearAndType(company);
        return this;
    }

    public ContactUsPage setPhone(final String phone) {
        txbPhone.clearAndType(phone);
        return this;
    }

    public ContactUsPage setEmail(final String email) {
        txbEmail.clearAndType(email);
        return this;
    }

    public ContactUsPage setComment(final String comment) {
        txbComment.clearAndType(comment);
        return this;
    }

    public ContactUsPage checkPrivacyAndCookies() {
        cmbPrivacy.getJsActions().check();
        return this;
    }

    public void clickSend(){
        btnSend.click();
    }

    public boolean isEmailValidationMessagePresent() {
        return lblEmailAlert.state().waitForDisplayed();
    }
}
