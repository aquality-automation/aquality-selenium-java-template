package com.myproject.automation.tests.collectables.itemdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.FIRST_ITEM;

public class MultipleRewardsForCompletingNextItemLevelDisplayedOnItemDetailsScreenTest extends BasePMTest {

    @Link(name = "CA-4355", type = "icq")
    @TmsLink("6340162")
    @Test(groups = {"collectables", "base_collectables"})
    public void multipleRewardsForCompletingNextItemLevelDisplayedOnItemDetailsScreenTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(FIRST_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertThatStellarPointsRewardIsPresentOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().assertThatCoinsRewardIsPresentOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().assertThatLightningRewardIsPresentOnItemDetailsScreen();
    }
}
