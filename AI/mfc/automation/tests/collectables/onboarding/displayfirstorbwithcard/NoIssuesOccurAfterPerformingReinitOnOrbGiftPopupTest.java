package com.myproject.automation.tests.collectables.onboarding.displayfirstorbwithcard;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.ShopSteps;
import com.myproject.automation.steps.ui.TopBarMenuSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesGiftSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesMainTutorialSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurAfterPerformingReinitOnOrbGiftPopupTest extends BasePMTest {

    @Link(name = "CA-4807", type = "icq")
    @TmsLink("6507395")
    @Test(groups = {"collectables", "base_tutorial", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void noIssuesOccurAfterPerformingReinitOnOrbGiftPopupTest() {
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopupWithoutCollectingRewards();
        //wait is needed for set CollectablesTutorialsSeen to false
        AppSteps.get().waitForStableResponseFromWebsocket();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().waitForStableResponseFromWebsocket();
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().assertThatCheckButtonIsPresent();
        CollectablesWelcomeSteps.get().clickCheck();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        CollectablesMainTutorialSteps.get().clickCloseTutorialView();
        CollectablesGiftSteps.get().assertThatCollectablesGiftViewIsPresent();
        AppSteps.get().performAppReinit();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().openShop();
        ShopSteps.get().assertThatShopIsOpened();
    }
}
