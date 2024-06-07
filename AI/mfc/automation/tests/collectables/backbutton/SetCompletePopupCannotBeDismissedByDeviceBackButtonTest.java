package com.myproject.automation.tests.collectables.backbutton;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class SetCompletePopupCannotBeDismissedByDeviceBackButtonTest extends BasePMTest {

    @Link(name = "CA-4453", type = "icq")
    @TmsLink("9857709")
    @Test(groups = {"collectablesbackbutton", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void setCompletePopupCannotBeDismissedByDeviceBackButtonTest() {
        upgradeToSilverLevel(user, false);
        AppSteps.get().pressBackButton();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsNotPresent();
    }
}
