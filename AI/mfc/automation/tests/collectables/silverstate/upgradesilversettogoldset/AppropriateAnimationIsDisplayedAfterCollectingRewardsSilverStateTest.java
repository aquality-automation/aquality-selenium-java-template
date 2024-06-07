package com.myproject.automation.tests.collectables.silverstate.upgradesilversettogoldset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.constants.CommonTestConstants;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbTotalRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class AppropriateAnimationIsDisplayedAfterCollectingRewardsSilverStateTest extends BasePMTest {

    @Link(name = "CA-4497", type = "icq")
    @TmsLink("6438786")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"}, enabled = false)
    public void appropriateAnimationIsDisplayedAfterCollectingRewardsSilverStateTest() {
        upgradeToSilverAndGoldLevel(user);
        AppSteps.get().setSpeed(CommonTestConstants.SLOW_ANIMATION_SPEED);
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        SetCompletedSteps.get().assertThatLightningAnimationIsPresent();
        AppSteps.get().setSpeedToHalfPercent();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().assertThatOrbOpeningAnimationPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickOpenAll();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().assertThatOrbOpeningAnimationPresent();
        OrbTotalRewardsSteps.get().assertThatOrbTotalRewardsViewIsPresent();
    }
}
