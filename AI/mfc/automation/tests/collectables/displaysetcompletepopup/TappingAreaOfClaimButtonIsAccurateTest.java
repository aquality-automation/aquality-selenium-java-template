package com.myproject.automation.tests.collectables.displaysetcompletepopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TappingAreaOfClaimButtonIsAccurateTest extends BasePMTest {

    @Link(name = "CA-4438", type = "icq")
    @TmsLink("10200630")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void tappingAreaOfClaimButtonIsAccurateTest() {
        upgradeToSilverLevel(user, false);
        SetCompletedSteps.get().tapAreaOfClaimBtn();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsNotPresent();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsNotPresent();
    }
}
