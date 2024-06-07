package com.myproject.automation.tests.collectables.homepage;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurAfterLosingContextOnCollectablesHomePageTest extends BasePMTest {

    @Link(name = "CA-4348", type = "icq")
    @TmsLink("10200149")
    @Test(groups = {"collectables", "base_collectables"})
    public void noIssuesOccurAfterLosingContextOnCollectablesHomePageTest() {
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        AppSteps.get().backgroundApplicationForFewSeconds();
        AppSteps.get().foregroundApplication();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        AppSteps.get().lockDevice();
        AppSteps.get().unlockDevice();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickCloseBtn();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsNotPresent();
        LobbySteps.get().assertThatLobbyIsOpened();
    }
}
