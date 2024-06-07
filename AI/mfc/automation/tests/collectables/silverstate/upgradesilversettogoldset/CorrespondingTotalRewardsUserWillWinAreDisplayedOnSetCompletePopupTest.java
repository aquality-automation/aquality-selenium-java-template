package com.myproject.automation.tests.collectables.silverstate.upgradesilversettogoldset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.Orb;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class CorrespondingTotalRewardsUserWillWinAreDisplayedOnSetCompletePopupTest extends BasePMTest {

    private static final int EXPECTED_COINS_REWARD = 1000000;
    private static final int EXPECTED_LIGHTNING_REWARD = 100;
    private static final int EXPECTED_REWARD_PACKS = 2;

    @Link(name = "CA-4494", type = "icq")
    @TmsLink("6438782")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void correspondingTotalRewardsUserWillWinAreDisplayedOnSetCompletePopupTest() {
        upgradeToSilverAndGoldLevel(user);
        SetCompletedSteps.get().assertThatOrbIsPresent(Orb.WOOD);
        SetCompletedSteps.get().assertThatOrbIsPresent(Orb.EARTH);
        SetCompletedSteps.get().assertThatCoinsRewardIsPresent();
        SetCompletedSteps.get().assertThatLightningRewardIsPresent();
        SetCompletedSteps.get().assertThatCoinsRewardIsEqualTo(EXPECTED_COINS_REWARD);
        SetCompletedSteps.get().assertThatLightningRewardIsEqualTo(EXPECTED_LIGHTNING_REWARD);
        SetCompletedSteps.get().assertThatRewardPackRewardIsEqualTo(EXPECTED_REWARD_PACKS);
    }
}
