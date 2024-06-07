package com.myproject.automation.tests.collectables.silverstate.upgradesilversettogoldset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class ClaimButtonIsDisplayedOnSetCompletePopupTest extends BasePMTest {

    @Link(name = "CA-4495", type = "icq")
    @TmsLink("6438783")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void claimButtonIsDisplayedOnSetCompletePopupTest() {
        upgradeToSilverAndGoldLevel(user);
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
    }
}
