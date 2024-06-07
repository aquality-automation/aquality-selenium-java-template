package com.myproject.automation.tests.collectables.homepage;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TappingCloseButtonClosesCollectablesHomePageTest extends BasePMTest {

    @Link(name = "CA-4347", type = "icq")
    @TmsLink("10200146")
    @Test(groups = {"collectables", "base_collectables"})
    public void tappingCloseButtonClosesCollectablesHomePageTest() {
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickCloseBtn();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsNotPresent();
        LobbySteps.get().assertThatLobbyIsOpened();
    }
}
