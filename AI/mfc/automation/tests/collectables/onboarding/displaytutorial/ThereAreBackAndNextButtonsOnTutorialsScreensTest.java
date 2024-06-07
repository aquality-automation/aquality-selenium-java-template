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

public class ThereAreBackAndNextButtonsOnTutorialsScreensTest extends BasePMTest {

    @Link(name = "CA-4784", type = "icq")
    @TmsLink("6502637")
    @Test(groups = {"collectables", "base_tutorial"})
    public void thereAreBackAndNextButtonsOnTutorialsScreensTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().clickCheck();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        CollectablesMainTutorialSteps.get().assertNextArrowBtnIsPresentOnCollectablesMainView();
        CollectablesMainTutorialSteps.get().assertPreviousArrowBtnIsNotPresentOnCollectablesMainView();
        CollectablesMainTutorialSteps.get().clickNextArrow();
        checkIfNextAndPreviousArrowsArePresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        CollectablesMainTutorialSteps.get().assertNextArrowBtnIsNotPresentOnCollectablesMainView();
        CollectablesMainTutorialSteps.get().assertPreviousArrowBtnIsPresentOnCollectablesMainView();
    }

    private void checkIfNextAndPreviousArrowsArePresent() {
        AppSteps.get().waitForStableResponseFromWebsocket();
        CollectablesMainTutorialSteps.get().assertNextArrowBtnIsPresentOnCollectablesMainView();
        CollectablesMainTutorialSteps.get().assertPreviousArrowBtnIsPresentOnCollectablesMainView();
        CollectablesMainTutorialSteps.get().clickNextArrow();
    }
}
