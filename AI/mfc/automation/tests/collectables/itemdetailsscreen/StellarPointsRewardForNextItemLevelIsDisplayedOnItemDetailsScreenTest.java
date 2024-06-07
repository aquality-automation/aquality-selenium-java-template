package com.myproject.automation.tests.collectables.itemdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.SECOND_ITEM;

public class StellarPointsRewardForNextItemLevelIsDisplayedOnItemDetailsScreenTest extends BasePMTest {

    @Link(name = "CA-4353", type = "icq")
    @TmsLink("6340160")
    @Test(groups = {"collectables", "base_collectables"})
    public void stellarPointsRewardForNextItemLevelIsDisplayedOnItemDetailsScreenTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(SECOND_ITEM);
        CollectablesItemsDetailSteps.get().assertThatStellarPointsRewardIsPresentOnItemDetailsScreen();
    }
}
