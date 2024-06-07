package com.myproject.automation.tests.collectables.silverstate.displaysilverset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.enums.Orb.EARTH;
import static com.myproject.automation.enums.Orb.WOOD;

public class FourRewardsForCompletingSilverLevelCanBeDisplayedCorrectlyOnCollectablesHomePageTest extends BasePMTest {

    @Link(name = "CA-4479", type = "icq")
    @TmsLink("6422932")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void fourRewardsForCompletingSilverLevelCanBeDisplayedCorrectlyOnCollectablesHomePageTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().assertThatCoinRewardIsPresent();
        CollectablesAlbumSteps.get().assertThatLightningRewardIsPresent();
        CollectablesAlbumSteps.get().assertThatOrbRewardIsPresent(WOOD);
        CollectablesAlbumSteps.get().assertThatOrbRewardIsPresent(EARTH);
    }
}
