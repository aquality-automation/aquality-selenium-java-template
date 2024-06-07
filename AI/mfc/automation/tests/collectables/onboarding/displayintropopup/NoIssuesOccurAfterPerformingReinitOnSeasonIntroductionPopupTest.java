package com.myproject.automation.tests.collectables.onboarding.displayintropopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.*;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurAfterPerformingReinitOnSeasonIntroductionPopupTest extends BasePMTest {

    @Link(name = "CA-4777", type = "icq")
    @TmsLink("6502614")
    @Test(groups = {"collectables", "base_tutorial"})
    public void noIssuesOccurAfterPerformingReinitOnSeasonIntroductionPopupTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        AppSteps.get().performAppReinit();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().openShop();
        ShopSteps.get().assertThatShopIsOpened();
    }
}
