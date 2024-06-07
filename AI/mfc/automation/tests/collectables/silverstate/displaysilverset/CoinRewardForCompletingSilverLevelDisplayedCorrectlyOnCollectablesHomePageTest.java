package com.myproject.automation.tests.collectables.silverstate.displaysilverset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class CoinRewardForCompletingSilverLevelDisplayedCorrectlyOnCollectablesHomePageTest extends BasePMTest {

    @Link(name = "CA-4475", type = "icq")
    @TmsLink("6422929")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void coinRewardForCompletingSilverLevelDisplayedCorrectlyOnCollectablesHomePageTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().assertThatCoinRewardIsPresent();
    }
}
