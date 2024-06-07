package com.myproject.automation.tests.collectables.upgradesets;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.SILVER_SET_REWARD_MESSAGE;

public class UserIsUpgradedToSilverLevelAfterCollectingRewardsTest extends BasePMTest {

    @Link(name = "CA-4448", type = "icq")
    @TmsLink("6384431")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void userIsUpgradedToSilverLevelAfterCollectingRewardsTest() {
        upgradeToSilverLevel(user, true);
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkip();
        openAllOrbRewards();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatRewardSetTextIsSameAsExpected(SILVER_SET_REWARD_MESSAGE);
    }
}
