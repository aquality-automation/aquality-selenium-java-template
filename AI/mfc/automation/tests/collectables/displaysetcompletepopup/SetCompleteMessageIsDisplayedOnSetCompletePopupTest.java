package com.myproject.automation.tests.collectables.displaysetcompletepopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class SetCompleteMessageIsDisplayedOnSetCompletePopupTest extends BasePMTest {

    private static final String EXPECTED_MESSAGE = "BASE SET COMPLETED!";

    @Link(name = "CA-4433", type = "icq")
    @TmsLink("10200625")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void setCompleteMessageIsDisplayedOnSetCompletePopupTest() {
        upgradeToSilverLevel(user, false);
        SetCompletedSteps.get().assertThatSetCompletedTextIsEqual(EXPECTED_MESSAGE);
        SetCompletedSteps.get().clickClaimBtn();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsNotPresent();
    }
}
