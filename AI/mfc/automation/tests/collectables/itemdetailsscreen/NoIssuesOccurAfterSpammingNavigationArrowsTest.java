package com.myproject.automation.tests.collectables.itemdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.THIRD_ITEM;

public class NoIssuesOccurAfterSpammingNavigationArrowsTest extends BasePMTest {

    @Link(name = "CA-4360", type = "icq")
    @TmsLink("6340196")
    @Test(groups = {"collectables", "base_collectables"})
    public void noIssuesOccurAfterSpammingNavigationArrowsTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(THIRD_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        String firstItemName = CollectablesItemsDetailSteps.get().getItemNameOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().spamNextItemButton();
        CollectablesItemsDetailSteps.get().goToNextItemOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().waitForAnotherItemIsDisplayed(firstItemName);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertItemsNameIsNotSameOnItemDetailsScreen(firstItemName);
        String secondItemName = CollectablesItemsDetailSteps.get().getItemNameOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().spamPreviousItemButton();
        CollectablesItemsDetailSteps.get().goToPreviousItemOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().waitForAnotherItemIsDisplayed(secondItemName);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertItemsNameIsNotSameOnItemDetailsScreen(secondItemName);
    }
}
