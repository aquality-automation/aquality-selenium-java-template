package com.myproject.automation.tests.collectables.displaysetcompletepopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurAfterSpammingClaimButtonTest extends BasePMTest {

    @Link(name = "CA-4449", type = "icq")
    @TmsLink("10200641")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void noIssuesOccurAfterSpammingClaimButtonTest() {
        upgradeToSilverLevel(user, false);
        SetCompletedSteps.get().spamClaimButton();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
    }
}
