package com.myproject.automation.tests.collectables.onboarding.displaytutorial;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesMainTutorialSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import io.qameta.allure.Link;
import io.qameta.allure.Links;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.Test;

public class TappingBackNextButtonTakesUserToPreviousTutorialTest extends BasePMTest {

    private static final int FIRST_PAGE = 1;
    private static final int SECOND_PAGE = 2;

    @Links({@Link(name = "CA-4785", type = "icq"), @Link(name = "CA-4786", type = "icq")})
    @TmsLinks({@TmsLink("6502638"), @TmsLink("6502639")})
    @Test(groups = {"collectables", "base_tutorial"})
    public void tappingBackNextButtonTakesUserToPreviousTutorialTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().clickCheck();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        CollectablesMainTutorialSteps.get().assertThatCurrentOpenedPageOfMainTutorialIsCorrect(FIRST_PAGE);
        CollectablesMainTutorialSteps.get().clickNextArrow();
        CollectablesMainTutorialSteps.get().assertThatCurrentOpenedPageOfMainTutorialIsCorrect(SECOND_PAGE);
        CollectablesMainTutorialSteps.get().clickPreviousArrow();
        CollectablesMainTutorialSteps.get().assertThatCurrentOpenedPageOfMainTutorialIsCorrect(FIRST_PAGE);
    }
}
