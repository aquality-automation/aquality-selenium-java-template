package com.myproject.automation.tests.collectables.backbutton;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesGiftSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesMainTutorialSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class OrbGiftPopupCannotBeClosedTest extends BasePMTest {

    @Link(name = "CA-4804", type = "icq")
    @TmsLink("9858547")
    @Test(groups = {"collectablesbackbutton", "base_tutorial", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void orbGiftPopupCannotBeClosedTest() {
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopupWithoutCollectingRewards();
        //wait is needed for set CollectablesTutorialsSeen to false
        AppSteps.get().waitForStableResponseFromWebsocket();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().assertThatCheckButtonIsPresent();
        CollectablesWelcomeSteps.get().clickCheck();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        CollectablesMainTutorialSteps.get().clickCloseTutorialView();
        CollectablesGiftSteps.get().assertThatCollectablesGiftViewIsPresent();
        AppSteps.get().pressBackButton();
        CollectablesGiftSteps.get().assertThatCollectablesGiftViewIsPresent();
        CollectablesGiftSteps.get().tapAroundClaimBtn();
        CollectablesGiftSteps.get().assertThatCollectablesGiftViewIsPresent();
    }
}
