package aquality.selenium.template.forms.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.template.forms.BaseAppForm;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class SecondPage extends BaseAppForm {

    private IElementFactory elementFactory = AqualityServices.getElementFactory();
    private ITextBox passwordBox = elementFactory.getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "Choose Password");
    private IButton uploadButton = elementFactory.getButton(By.xpath("//a[@class='avatar-and-interests__upload-button']"), "uploadButton");
    private ITextBox cardName = elementFactory.getTextBox(By.xpath("//div[@class='page-indicator']"), "s");
    private ITextBox email = elementFactory.getTextBox(By.xpath("//input[@placeholder='Your email']"), "Your email");
    private ITextBox domain = elementFactory.getTextBox(By.xpath("//input[@placeholder='Domain']"), "Domain");
    private IButton opener = AqualityServices.getElementFactory().getButton(By.className("dropdown__opener"),"dropdown list");
    private IElement terms = elementFactory.get(ElementType.CHECKBOX, By.xpath("//span[@class='icon icon-check checkbox__check']"), "terms");
    private IButton nextButton = elementFactory.getButton(By.xpath("//a[@class='button--secondary']"), "Next");
    private IButton helpFormButton = elementFactory.getButton(By.xpath("//button[@class='button button--solid button--blue help-form__send-to-bottom-button']"), "helpFormButton");
    private IElement helpFormLabel = elementFactory.get(ElementType.LABEL, By.xpath("//h2[@class='help-form__title']"), "helpFormLabel");
    private IButton acceptedCookies = elementFactory.getButton(By.xpath("//button[@class='button button--solid button--transparent']"), "acceptedCookies");
    private IElement timer = elementFactory.get(ElementType.LABEL, By.xpath("//div[@class='timer timer--white timer--center']"), "timer");
    String separator = File.separator;

    SecondPage() {
        super(By.xpath("//div[@class='game view']"), "Second page");
    }

    public IElement getTimer() {
        return timer;
    }
    public IButton getAcceptedCookies() {
        return acceptedCookies;
    }
    public IElement getHelpFormLabel() {
        return helpFormLabel;
    }
    public ITextBox getPasswordBox() {
        return passwordBox;
    }
    public IButton getUploadButton() {
        return uploadButton;
    }
    public ITextBox getEmail() {
        return email;
    }
    public ITextBox getCardName() {
        return cardName;
    }

    public ITextBox getDomain() {
        return domain;
    }
    public IElement getTerms() {
        return terms;
    }
    public IButton getNextButton() {
        return nextButton;
    }
    public IButton getHelpFormButton() {
        return helpFormButton;
    }
    public void insertOrgCode() throws InterruptedException {
        opener.click();
        List<IElement> orgCodeLists = getElementFactory().findElements(By.cssSelector("div.dropdown__list-item"), ElementType.LABEL);
        int randomOrgCode = (int) (Math.random() * orgCodeLists.size());
        String itemName = orgCodeLists.get(randomOrgCode).getText();
        if (itemName.equals("other")){
            randomOrgCode =+ 1;
            itemName = orgCodeLists.get(randomOrgCode).getText();
        }
        getElementFactory().getButton((By.xpath(String.format("(//div[@class='dropdown__list-item' and text()='%s'])", itemName))),itemName).click();

    }
    public void insertInterestChecks() {
        int optionsListSize = elementFactory.findElements(By.xpath("//*[@class='avatar-and-interests__interests-list']//span[2]"), ElementType.LABEL).size();
        List<ICheckBox> valuesList = new ArrayList<>();
        for (int i = 1; i <= optionsListSize; i++) {
            valuesList.add(elementFactory.getCheckBox(By.xpath(String.format("//*[@class='avatar-and-interests__interests-list']/div[%d]//label", i)), "name"));
        }
        valuesList.get(20).click();
        valuesList.remove(17);
        valuesList.remove(19);
        for (int i = 0; i < 3; i++) {
            int randNumber = (int) (Math.random() * valuesList.size());
            valuesList.get(randNumber).click();
            valuesList.remove(randNumber);
        }
    }
    public void testUpload() throws InterruptedException {
        IButton uploadButtom = getElementFactory().getButton(By.xpath("//a[@class='avatar-and-interests__upload-button']"), "uploadButton");
        uploadButtom.click();
        Thread.sleep(2000);
        uploadFile("C:" + separator + "Users" + separator + "Artem" + separator + "aquality-selenium-java-template" + separator + "pozitivnie-kartinki.orig.jpg");
        Thread.sleep(4000);
    }

    public static void setClipboardData(String string) {
        //StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }
    public static void uploadFile(String fileLocation) {
        try {
            setClipboardData(fileLocation);
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
