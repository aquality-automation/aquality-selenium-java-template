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

public class NoIssuesOccurAfterPerformingReinitOnTradeTutorialPopupTest extends BasePMTest {

    @Link(name = "CA-4799", type = "icq")
    @TmsLink("6502714")
    @Test(groups = {"collectables", "base_tutorial"})
    public void noIssuesOccurAfterPerformingReinitOnTradeTutorialPopupTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().clickClose();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        CollectablesMainTutorialSteps.get().clickCloseTutorialView();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickTradeButton();
        CollectablesTradeTutorialSteps.get().assertThatCollectablesTradeTutorialViewIsPresent();
        AppSteps.get().performAppReinit();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickTradeButton();
        TradeSteps.get().assertThatTradeScreenIsPresent();
    }
}
