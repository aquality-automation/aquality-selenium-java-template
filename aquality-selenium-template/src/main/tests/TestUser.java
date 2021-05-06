import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.template.forms.pages.SecondPage;
import aquality.selenium.template.forms.pages.WelcomePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class TestUser extends BaseTest {

    private static final String EXPECTED_URL_WELCOME_PAGE = "https://userinyerface.com/game.html%20target=";
    private static final String EXPECTED_NAME_FIRST_CARD = "1 / 4";
    private static final String EXPECTED_NAME_SECOND_CARD = "2 / 4";
    private static final String EXPECTED_NAME_THIRD_CARD = "3 / 4";
    private static final String EXPECTED_URL_SECOND_PAGE = "https://userinyerface.com/game.html";
    private static final String EXPECTED_TIMER_VALUE = "00:00:00";
    private WelcomePage welcomePage;
    private IElementFactory elementFactory = AqualityServices.getElementFactory();



    @BeforeTest
    private void setUp() {
        welcomePage = new WelcomePage();
    }

    @Test
    public void testTreeCardsShouldBeOpened() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(AqualityServices.getBrowser().getCurrentUrl(), EXPECTED_URL_WELCOME_PAGE);
        SecondPage secondPage = welcomePage.createSecondPageByClickHere();
        softAssert.assertEquals(secondPage.getCardName().getText(), EXPECTED_NAME_FIRST_CARD);
        secondPage.getPasswordBox().clearAndType("Aqua111111");
        secondPage.getEmail().clearAndType("Adrew");
        secondPage.getDomain().clearAndType("gmail");
        secondPage.insertOrgCode();
        secondPage.getTerms().click();
        secondPage.getNextButton().click();
        softAssert.assertEquals(secondPage.getCardName().getText(), EXPECTED_NAME_SECOND_CARD);
        secondPage.insertInterestChecks();
        secondPage.testUpload();
        softAssert.assertEquals(secondPage.getCardName().getText(), EXPECTED_NAME_THIRD_CARD);
    }

    @Test
    public void testHelpFormIsHidden(){
        SoftAssert softAssert = new SoftAssert();
        SecondPage secondPage = welcomePage.createSecondPageByClickHere();
        softAssert.assertEquals(AqualityServices.getBrowser().getCurrentUrl(), EXPECTED_URL_SECOND_PAGE);
        secondPage.getHelpFormButton().click();
        secondPage.getHelpFormButton().state().waitForNotDisplayed();
        softAssert.assertFalse(secondPage.getHelpFormButton().state().isDisplayed());
    }

    @Test
    public void testAcceptedCookies() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        SecondPage secondPage = welcomePage.createSecondPageByClickHere();
        softAssert.assertEquals(AqualityServices.getBrowser().getCurrentUrl(), EXPECTED_URL_SECOND_PAGE);
        secondPage.getAcceptedCookies().state().waitForDisplayed();
        secondPage.getAcceptedCookies().click();
        softAssert.assertFalse(secondPage.getAcceptedCookies().state().isClickable());
    }

    @Test
    public void testTimerStartFromZero(){
        SoftAssert softAssert = new SoftAssert();
        SecondPage secondPage = welcomePage.createSecondPageByClickHere();
        softAssert.assertEquals(secondPage.getTimer().getText(), EXPECTED_TIMER_VALUE);


    }
}