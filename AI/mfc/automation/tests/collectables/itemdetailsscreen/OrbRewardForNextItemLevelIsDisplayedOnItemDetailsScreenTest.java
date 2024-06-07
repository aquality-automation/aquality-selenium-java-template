package com.myproject.automation.tests.collectables.itemdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.FIFTH_ITEM;

public class OrbRewardForNextItemLevelIsDisplayedOnItemDetailsScreenTest extends BasePMTest {

    @Link(name = "CA-4354", type = "icq")
    @TmsLink("6340161")
    @Test(groups = {"collectables", "base_collectables"})
    public void orbRewardForNextItemLevelIsDisplayedOnItemDetailsScreenTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(FIFTH_ITEM);
        CollectablesItemsDetailSteps.get().assertThatOrbRewardIsPresentOnItemDetailsScreen();
    }
}
