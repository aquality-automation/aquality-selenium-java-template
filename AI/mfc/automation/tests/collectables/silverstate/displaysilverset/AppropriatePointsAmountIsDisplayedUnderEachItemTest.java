package com.myproject.automation.tests.collectables.silverstate.displaysilverset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.collectables.CollectablesSet;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbTotalRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class AppropriatePointsAmountIsDisplayedUnderEachItemTest extends BasePMTest {

    private static final int REWARD_LEVEL = 0;

    @Link(name = "CA-4472", type = "icq")
    @TmsLink("6422918")
    @Test(groups = {"collectables", "base_collectables"})
    public void appropriatePointsAmountIsDisplayedUnderEachItemTest() {
        upgradeToSilverLevel(user, true);
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickOpenAll();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbTotalRewardsSteps.get().assertThatOrbTotalRewardsViewIsPresent();
        OrbTotalRewardsSteps.get().clickFinish();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatCorrectPointsAmountIsDisplayedUnderEachItem(this.user.getApiToken(), CollectablesSet.SILVER.getItemsCount(), REWARD_LEVEL);
    }
}
