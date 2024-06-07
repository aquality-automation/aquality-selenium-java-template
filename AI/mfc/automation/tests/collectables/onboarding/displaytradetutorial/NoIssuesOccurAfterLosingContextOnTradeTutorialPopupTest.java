package com.myproject.automation.tests.collectables.onboarding.displaytradetutorial;

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

public class NoIssuesOccurAfterLosingContextOnTradeTutorialPopupTest extends BasePMTest {

    @Link(name = "CA-4800", type = "icq")
    @TmsLink("6502716")
    @Test(groups = {"collectables", "base_tutorial"})
    public void noIssuesOccurAfterLosingContextOnTradeTutorialPopupTest() {
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
        AppSteps.get().backgroundApplicationForFewSeconds();
        AppSteps.get().foregroundApplication();
        CollectablesTradingTutorialSteps.get().assertThatCollectablesTradingTutorialViewIsPresent();
        CollectablesTradingTutorialSteps.get().clickClose();
        CollectablesTradingTutorialSteps.get().assertThatCollectablesTradingTutorialViewIsNotPresent();
    }
}
