package com.myproject.automation.tests.collectables.silverstate.displaysilverset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TappingCloseButtonClosesCollectablesHomePageSilverStateTest extends BasePMTest {

    @Link(name = "CA-4471", type = "icq")
    @TmsLink("6422912")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void tappingCloseButtonClosesCollectablesHomePageSilverStateTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().clickCloseBtn();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsNotPresent();
        LobbySteps.get().assertThatLobbyIsOpened();
    }
}
