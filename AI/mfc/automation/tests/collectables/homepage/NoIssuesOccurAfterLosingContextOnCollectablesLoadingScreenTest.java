package com.myproject.automation.tests.collectables.homepage;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurAfterLosingContextOnCollectablesLoadingScreenTest extends BasePMTest {

    @Link(name = "CA-4343", type = "icq")
    @TmsLink("10200141")
    @Test(groups = {"collectables", "base_collectables"})
    public void noIssuesOccurAfterLosingContextOnCollectablesLoadingScreenTest() {
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        AppSteps.get().setSpeedToHalfPercent();
        LobbySteps.get().clickCollectablesIcon();
        AppSteps.get().backgroundApplicationForFewSeconds();
        AppSteps.get().foregroundApplication();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
    }
}
