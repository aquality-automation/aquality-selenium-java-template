package com.myproject.automation.tests.collectables.silverstate.displaysilverset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.Orb;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class MultipleOrbsAsRewardsForCompletingSilverLevelCanBeDisplayedTest extends BasePMTest {

    @Link(name = "CA-4480", type = "icq")
    @TmsLink("6423022")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void multipleOrbsAsRewardsForCompletingSilverLevelCanBeDisplayedTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().assertThatOrbRewardIsPresent(Orb.WOOD);
        CollectablesAlbumSteps.get().assertThatOrbRewardIsPresent(Orb.EARTH);
    }
}
