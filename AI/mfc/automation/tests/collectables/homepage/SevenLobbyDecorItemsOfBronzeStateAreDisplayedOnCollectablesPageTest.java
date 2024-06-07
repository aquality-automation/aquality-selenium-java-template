package com.myproject.automation.tests.collectables.homepage;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.collectables.CollectablesSet;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.enums.collectables.ItemsState.BASE;

public class SevenLobbyDecorItemsOfBronzeStateAreDisplayedOnCollectablesPageTest extends BasePMTest {

    @Link(name = "CA-4345", type = "icq")
    @TmsLink("10200144")
    @Test(groups = {"collectables", "base_collectables", "critical"})
    public void sevenLobbyDecorItemsOfBronzeStateAreDisplayedOnCollectablesPageTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatDecorItemsAreDisplayed(CollectablesSet.BASE.getItemsCount(), BASE);
    }
}
