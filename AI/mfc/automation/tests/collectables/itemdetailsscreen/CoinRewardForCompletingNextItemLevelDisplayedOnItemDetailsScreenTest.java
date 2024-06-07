package com.myproject.automation.tests.collectables.itemdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.THIRD_ITEM;

public class CoinRewardForCompletingNextItemLevelDisplayedOnItemDetailsScreenTest extends BasePMTest {

    @Link(name = "CA-4351", type = "icq")
    @TmsLink("6340158")
    @Test(groups = {"collectables", "base_collectables"})
    public void coinRewardForCompletingNextItemLevelDisplayedOnItemDetailsScreenTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(THIRD_ITEM);
        CollectablesItemsDetailSteps.get().assertThatCoinsRewardIsPresentOnItemDetailsScreen();
    }
}
