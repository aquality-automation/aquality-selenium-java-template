package com.myproject.automation.tests.collectables.onboarding.displaytutorial;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesMainTutorialSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TutorialConsistsOf3StepsTest extends BasePMTest {

    private static final int FIRST_PAGE = 1;
    private static final int SECOND_PAGE = 2;
    private static final int THIRD_PAGE = 3;

    @Link(name = "CA-4783", type = "icq")
    @TmsLink("6502634")
    @Test(groups = {"collectables", "base_tutorial"})
    public void tutorialConsistsOf3StepsTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().assertThatCheckButtonIsPresent();
        CollectablesWelcomeSteps.get().clickCheck();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        CollectablesMainTutorialSteps.get().assertThatCurrentOpenedPageOfMainTutorialIsCorrect(FIRST_PAGE);
        CollectablesMainTutorialSteps.get().clickNextArrow();
        CollectablesMainTutorialSteps.get().assertThatCurrentOpenedPageOfMainTutorialIsCorrect(SECOND_PAGE);
        CollectablesMainTutorialSteps.get().clickNextArrow();
        CollectablesMainTutorialSteps.get().assertThatCurrentOpenedPageOfMainTutorialIsCorrect(THIRD_PAGE);
        CollectablesMainTutorialSteps.get().assertNextArrowBtnIsNotPresentOnCollectablesMainView();
        CollectablesMainTutorialSteps.get().assertPreviousArrowBtnIsPresentOnCollectablesMainView();
    }
}
