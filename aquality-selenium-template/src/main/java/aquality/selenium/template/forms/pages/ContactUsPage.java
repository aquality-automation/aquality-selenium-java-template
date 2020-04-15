package aquality.selenium.template.forms.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.template.forms.BaseAppForm;
import org.openqa.selenium.By;

public class ContactUsPage extends BaseAppForm {

    private final ITextBox txbName = getElementFactory().getTextBox(By.id("name"), "Name");
    private final ITextBox txbCompany = getElementFactory().getTextBox(By.id("company"), "Company");
    private final ITextBox txbPhone = getElementFactory().getTextBox(By.id("phone"), "Phone");
    private final ITextBox txbComment = getElementFactory().getTextBox(By.id("message"), "Comment");
    private final ICheckBox cmbPrivacy = getElementFactory().getCheckBox(By.name("privacy"), "Privacy");
    private final IButton btnSend = getElementFactory().getButton(By.xpath("//input[@value='Send']"), "Send");
    private final ILabel lblEmailAlert =getElementFactory().getLabel(By.xpath("//span[@role='alert']//preceding-sibling::input[@id='email']"), "Email validating message");

    public ContactUsPage(){
        super(By.id("contact-us"), "Contact Us");
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

    public ContactUsPage setComment(final String comment) {
        txbComment.clearAndType(comment);
        return this;
    }

    public ContactUsPage checkPrivacyAndCookies() {
        cmbPrivacy.check();
        return this;
    }

    public void clickSend(){
        btnSend.click();
    }

    public boolean isEmailValidationMessagePresent() {
        return lblEmailAlert.state().waitForDisplayed();
    }
}
