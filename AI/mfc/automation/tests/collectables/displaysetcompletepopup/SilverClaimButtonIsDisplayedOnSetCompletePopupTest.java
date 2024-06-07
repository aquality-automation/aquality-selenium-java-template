package com.myproject.automation.tests.collectables.displaysetcompletepopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class SilverClaimButtonIsDisplayedOnSetCompletePopupTest extends BasePMTest {

    @Link(name = "CA-4437", type = "icq")
    @TmsLink("10200629")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void silverClaimButtonIsDisplayedOnSetCompletePopupTest() {
        upgradeToSilverLevel(user, false);
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsNotPresent();
    }
}
