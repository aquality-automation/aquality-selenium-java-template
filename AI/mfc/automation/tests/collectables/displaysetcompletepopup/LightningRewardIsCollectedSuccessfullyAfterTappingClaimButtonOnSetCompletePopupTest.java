package com.myproject.automation.tests.collectables.displaysetcompletepopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.TopBarMenuSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbTotalRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class LightningRewardIsCollectedSuccessfullyAfterTappingClaimButtonOnSetCompletePopupTest extends BasePMTest {

    private static final int EXPECTED_TOTAL_LIGHTNING_REWARD = 1865;

    @Link(name = "CA-4440", type = "icq")
    @TmsLink("6384422")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void lightningRewardIsCollectedSuccessfullyAfterTappingClaimButtonOnSetCompletePopupTest() {
        int initialLightningValue = TopBarMenuSteps.get().getLightningValue();
        upgradeToSilverLevel(user, true);
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickOpenAll();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbTotalRewardsSteps.get().assertThatOrbTotalRewardsViewIsPresent();
        TopBarMenuSteps.get().assertThatLightningValueIsSameAsExpected(initialLightningValue + EXPECTED_TOTAL_LIGHTNING_REWARD);
    }
}
