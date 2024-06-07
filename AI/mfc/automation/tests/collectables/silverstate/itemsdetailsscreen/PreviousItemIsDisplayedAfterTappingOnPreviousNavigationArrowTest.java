package com.myproject.automation.tests.collectables.silverstate.itemsdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class PreviousItemIsDisplayedAfterTappingOnPreviousNavigationArrowTest extends BasePMTest {

    private static final int ITEM_NUMBER = 2;

    @Link(name = "CA-4490", type = "icq")
    @TmsLink("6438556")
    @Test(groups = {"collectables", "base_collectables", "critical"})
    public void previousItemIsDisplayedAfterTappingOnPreviousNavigationArrowTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().clickItem(ITEM_NUMBER);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        String currentItemName = CollectablesItemsDetailSteps.get().getItemNameOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().goToPreviousItemOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().waitForAnotherItemIsDisplayed(currentItemName);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertItemsNameIsNotSameOnItemDetailsScreen(currentItemName);
    }
}
