package com.myproject.automation.tests.collectables.silverstate.itemsdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.SECOND_ITEM;

public class StellarPointsRewardForNextSilverItemLevelIsDisplayedOnItemDetailsScreenTest extends BasePMTest {

    @Link(name = "CA-4485", type = "icq")
    @TmsLink("6438540")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void stellarPointsRewardForNextSilverItemLevelIsDisplayedOnItemDetailsScreenTest() {
        upgradeToSilverLevel(user, true);
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkip();
        openAllOrbRewards();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(SECOND_ITEM);
        CollectablesItemsDetailSteps.get().assertThatStellarPointsRewardIsPresentOnItemDetailsScreen();
    }
}
