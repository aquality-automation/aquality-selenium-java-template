package com.myproject.automation.tests.collectables.displaysetcompletepopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbTotalRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class CollectablesHomePageIsShownRightAfterOrbOpeningSequenceTest extends BasePMTest {

    @Link(name = "CA-4446", type = "icq")
    @TmsLink("10200638")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void collectablesHomePageIsShownRightAfterOrbOpeningSequenceTest() {
        upgradeToSilverLevel(user, true);
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickOpenAll();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbTotalRewardsSteps.get().assertThatOrbTotalRewardsViewIsPresent();
        OrbTotalRewardsSteps.get().clickFinish();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
    }
}
