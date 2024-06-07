package com.myproject.automation.tests.collectables.homepage;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class CollectablesIsOpenedAfterInteractingWithIconInLobbyTest extends BasePMTest {

    @Link(name = "CA-4341", type = "icq")
    @TmsLink("10200138")
    @Test(groups = {"collectables", "base_collectables", "critical"})
    public void collectablesIsOpenedAfterInteractingWithIconInLobbyTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
    }
}
