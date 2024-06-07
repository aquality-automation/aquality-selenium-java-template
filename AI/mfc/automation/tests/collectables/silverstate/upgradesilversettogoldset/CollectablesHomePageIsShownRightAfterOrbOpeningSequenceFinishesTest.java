package com.myproject.automation.tests.collectables.silverstate.upgradesilversettogoldset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class CollectablesHomePageIsShownRightAfterOrbOpeningSequenceFinishesTest extends BasePMTest {

    @Link(name = "CA-4499", type = "icq")
    @TmsLink("6438785")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void collectablesHomePageIsShownRightAfterOrbOpeningSequenceFinishesTest() {
        upgradeToSilverAndGoldLevel(user);
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        openAllOrbRewards();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
    }
}
