package com.myproject.automation.tests.collectables.homepage;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class CollectablesLoadingScreenIsDisplayedWhenLoadingCollectablesHomePageTest extends BasePMTest {

    @Link(name = "CA-4342", type = "icq")
    @TmsLink("10200139")
    @Test(groups = {"collectables", "base_collectables"})
    public void collectablesLoadingScreenIsDisplayedWhenLoadingCollectablesHomePageTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        AppSteps.get().setSpeedToHalfPercent();
        CollectablesAlbumSteps.get().assertThatLoadingScreenIsPresent();
    }
}
