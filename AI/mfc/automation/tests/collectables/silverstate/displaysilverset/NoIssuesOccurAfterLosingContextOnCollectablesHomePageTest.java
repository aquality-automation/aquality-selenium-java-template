package com.myproject.automation.tests.collectables.silverstate.displaysilverset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurAfterLosingContextOnCollectablesHomePageTest extends BasePMTest {

    @Link(name = "CA-4481", type = "icq")
    @TmsLink("6422937")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void noIssuesOccurAfterLosingContextOnCollectablesHomePageTest() {
        setSilverState(user);
        AppSteps.get().backgroundApplicationForFewSeconds();
        AppSteps.get().foregroundApplication();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickCloseBtn();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsNotPresent();
        LobbySteps.get().assertThatLobbyIsOpened();
    }
}
