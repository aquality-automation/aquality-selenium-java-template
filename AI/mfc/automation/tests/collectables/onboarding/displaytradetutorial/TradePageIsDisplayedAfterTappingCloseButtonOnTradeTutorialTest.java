package com.myproject.automation.tests.collectables.onboarding.displaytradetutorial;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.*;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TradePageIsDisplayedAfterTappingCloseButtonOnTradeTutorialTest extends BasePMTest {

    @Link(name = "CA-4797", type = "icq")
    @TmsLink("6502711")
    @Test(groups = {"collectables", "base_tutorial"})
    public void tradePageIsDisplayedAfterTappingCloseButtonOnTradeTutorialTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().clickClose();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsNotOpened();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        CollectablesMainTutorialSteps.get().clickCloseTutorialView();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsNotPresent();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickTradeButton();
        CollectablesTradeTutorialSteps.get().assertThatCollectablesTradeTutorialViewIsPresent();
        CollectablesTradeTutorialSteps.get().clickClose();
        TradeSteps.get().assertThatTradeScreenIsPresent();
    }
}
