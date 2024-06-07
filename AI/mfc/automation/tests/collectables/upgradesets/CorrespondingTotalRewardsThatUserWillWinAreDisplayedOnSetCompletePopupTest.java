package com.myproject.automation.tests.collectables.upgradesets;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class CorrespondingTotalRewardsThatUserWillWinAreDisplayedOnSetCompletePopupTest extends BasePMTest {

    private static final int EXPECTED_COINS_REWARD = 20000;
    private static final int EXPECTED_LIGHTNING_REWARD = 500;
    private static final int EXPECTED_REWARD_PACKS = 2;

    @Link(name = "CA-4436", type = "icq")
    @TmsLink("6384417")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void correspondingTotalRewardsThatUserWillWinAreDisplayedOnSetCompletePopupTest() {
        upgradeToSilverLevel(user, false);
        SetCompletedSteps.get().assertThatCoinsRewardIsEqualTo(EXPECTED_COINS_REWARD);
        SetCompletedSteps.get().assertThatLightningRewardIsEqualTo(EXPECTED_LIGHTNING_REWARD);
        SetCompletedSteps.get().assertThatRewardPackRewardIsEqualTo(EXPECTED_REWARD_PACKS);
    }
}
