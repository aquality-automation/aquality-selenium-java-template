package com.myproject.automation.tests.collectables.silverstate.upgradesilversettogoldset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.GOLD_SET_REWARD_MESSAGE;

public class UserIsUpgradedToGoldLevelAfterCollectingRewardsTest extends BasePMTest {

    @Link(name = "CA-4500", type = "icq")
    @TmsLink("6438787")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void userIsUpgradedToGoldLevelAfterCollectingRewardsTest() {
        upgradeToSilverAndGoldLevel(user);
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        openAllOrbRewards();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatRewardSetTextIsSameAsExpected(GOLD_SET_REWARD_MESSAGE);
    }
}
