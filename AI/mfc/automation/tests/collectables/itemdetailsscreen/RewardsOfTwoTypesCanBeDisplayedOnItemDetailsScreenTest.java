package com.myproject.automation.tests.collectables.itemdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.SIXTH_ITEM;

public class RewardsOfTwoTypesCanBeDisplayedOnItemDetailsScreenTest extends BasePMTest {

    @Link(name = "CA-4356", type = "icq")
    @TmsLink("6360423")
    @Test(groups = {"collectables", "base_collectables"})
    public void rewardsOfTwoTypesCanBeDisplayedOnItemDetailsScreenTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(SIXTH_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertThatStellarPointsRewardIsPresentOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().assertThatCoinsRewardIsPresentOnItemDetailsScreen();
    }
}
