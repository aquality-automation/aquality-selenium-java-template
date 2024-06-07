package com.myproject.automation.tests.collectables.itemdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.SECOND_ITEM;

public class PreviousItemDisplayedAfterTappingOnPreviousNavigationArrowTest extends BasePMTest {

    @Link(name = "CA-4358", type = "icq")
    @TmsLink("6340193")
    @Test(groups = {"collectables", "base_collectables"})
    public void previousItemDisplayedAfterTappingOnPreviousNavigationArrowTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(SECOND_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        String currentItemName = CollectablesItemsDetailSteps.get().getItemNameOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().goToPreviousItemOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().waitForAnotherItemIsDisplayed(currentItemName);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertItemsNameIsNotSameOnItemDetailsScreen(currentItemName);
    }
}
