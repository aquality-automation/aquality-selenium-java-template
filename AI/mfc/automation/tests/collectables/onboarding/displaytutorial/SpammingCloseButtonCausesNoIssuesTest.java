package com.myproject.automation.tests.collectables.onboarding.displaytutorial;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesMainTutorialSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesTradingTutorialSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class SpammingCloseButtonCausesNoIssuesTest extends BasePMTest {

    @Link(name = "CA-4798", type = "icq")
    @TmsLink("6502712")
    @Test(groups = {"collectables", "base_tutorial"})
    public void spammingCloseButtonCausesNoIssuesTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().clickCheck();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        CollectablesMainTutorialSteps.get().clickCloseTutorialView();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickTradeButton();
        CollectablesTradingTutorialSteps.get().assertThatCollectablesTradingTutorialViewIsPresent();
        CollectablesTradingTutorialSteps.get().spamClose();
        CollectablesTradingTutorialSteps.get().assertThatCollectablesTradingTutorialViewIsNotPresent();
    }
}
