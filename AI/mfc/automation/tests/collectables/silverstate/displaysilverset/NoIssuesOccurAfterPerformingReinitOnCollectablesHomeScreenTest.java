package com.myproject.automation.tests.collectables.silverstate.displaysilverset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.ShopSteps;
import com.myproject.automation.steps.ui.TopBarMenuSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurAfterPerformingReinitOnCollectablesHomeScreenTest extends BasePMTest {

    @Link(name = "CA-4482", type = "icq")
    @TmsLink("6501309")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void noIssuesOccurAfterPerformingReinitOnCollectablesHomeScreenTest() {
        setSilverState(user);
        AppSteps.get().performAppReinit();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().openShop();
        ShopSteps.get().assertThatShopIsOpened();
    }
}
