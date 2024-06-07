package com.myproject.automation.tests.collectables.onboarding.displaytradetutorial;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesTradeTutorialSteps;
import com.myproject.automation.steps.ui.collectables.TradeSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TradeTutorialIsTriggeredByClickingOnInfoButtonOnTradePageTest extends BasePMTest {

    @Link(name = "CA-4794", type = "icq")
    @TmsLink("6502703")
    @Test(groups = {"collectables", "base_tutorial"})
    public void tradeTutorialIsTriggeredByClickingOnInfoButtonOnTradePageTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), true);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickTradeButton();
        TradeSteps.get().assertThatTradeScreenIsPresent();
        TradeSteps.get().clickInfo();
        CollectablesTradeTutorialSteps.get().assertThatCollectablesTradeTutorialViewIsPresent();
    }
}
