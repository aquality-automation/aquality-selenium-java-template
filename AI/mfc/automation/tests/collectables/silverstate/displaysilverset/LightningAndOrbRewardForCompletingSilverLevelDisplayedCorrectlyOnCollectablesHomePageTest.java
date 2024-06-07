package com.myproject.automation.tests.collectables.silverstate.displaysilverset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.Links;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.Test;

public class LightningAndOrbRewardForCompletingSilverLevelDisplayedCorrectlyOnCollectablesHomePageTest extends BasePMTest {


    @Links({@Link(type = "icq", name = "CA-4476"), @Link(type = "icq", name = "CA-4470"), @Link(type = "icq", name = "CA-4477")})
    @TmsLinks({@TmsLink("6422931"), @TmsLink("6422911"), @TmsLink("6422933")})
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void lightningAndOrbRewardForCompletingSilverLevelDisplayedCorrectlyOnCollectablesHomePageTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().assertThatLightningRewardIsPresent();
        CollectablesAlbumSteps.get().assertThatOrbRewardIsPresent();
        CollectablesAlbumSteps.get().assertThatCloseBtnOnCollectablesAlbumIsPresent();
    }
}
