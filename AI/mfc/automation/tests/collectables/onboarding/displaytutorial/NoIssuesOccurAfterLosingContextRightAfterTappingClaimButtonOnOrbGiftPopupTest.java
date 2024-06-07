package com.myproject.automation.tests.collectables.onboarding.displaytutorial;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesGiftSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesMainTutorialSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurAfterLosingContextRightAfterTappingClaimButtonOnOrbGiftPopupTest extends BasePMTest {

    @Link(name = "CA-4808", type = "icq")
    @TmsLink("6507397")
    @Test(groups = {"collectables", "base_tutorial", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void noIssuesOccurAfterLosingContextRightAfterTappingClaimButtonOnOrbGiftPopupTest() {
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopupWithoutCollectingRewards();
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
        CollectablesGiftSteps.get().clickClaimTutorialView();
        AppSteps.get().backgroundApplication();
        AppSteps.get().foregroundApplication();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
    }
}
