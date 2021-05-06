import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
    private int stepNumber;

    @BeforeClass(alwaysRun = true)
    public void beforeMethod() {
        stepNumber = 1;
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo("https://userinyerface.com/game.html%20target=");
        AqualityServices.getBrowser().waitForPageToLoad();

    }

    @AfterClass(alwaysRun = true)
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            //AqualityServices.getBrowser().quit();
        }
    }


    protected void logStep(String message) {
        String format = "Step %d: %s";
        AqualityServices.getLogger().info(String.format(format, stepNumber, message));
        stepNumber++;
    }

}