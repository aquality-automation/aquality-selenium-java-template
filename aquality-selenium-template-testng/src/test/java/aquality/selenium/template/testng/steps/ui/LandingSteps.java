package aquality.selenium.template.testng.steps.ui;

import aquality.selenium.template.forms.TopBarMenu;
import aquality.selenium.template.forms.pages.MainPage;
import com.google.inject.Inject;
import io.qameta.allure.Step;

import static aquality.selenium.template.forms.TopBarMenu.Item.CONTACT_US;
import static org.assertj.core.api.Assertions.assertThat;

public class LandingSteps {

    @Inject
    private MainPage mainPage;
    @Inject
    private TopBarMenu topBarMenu;

    @Step("Main page is opened")
    public void checkMainPageIsOpened() {
        assertThat(mainPage.state().waitForDisplayed())
                .describedAs(mainPage.getName() + " must be opened")
                .isTrue();
    }

    @Step("I open Contact us page")
    public void openContactUsPage() {
        mainPage.acceptCookies();
        topBarMenu.openHeaderMenu(CONTACT_US);
    }
}
