package com.myproject.automation.tests.collectables.silverstate.itemsdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NextItemIsDisplayedAfterTappingOnNextNavigationArrowSilverStateTest extends BasePMTest {

    private static final int FIRST_ITEM = 1;

    @Link(name = "CA-4489", type = "icq")
    @TmsLink("6438555")
    @Test(groups = {"collectables", "base_collectables", "critical"})
    public void nextItemIsDisplayedAfterTappingOnNextNavigationArrowSilverStateTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().clickItem(FIRST_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        String currentItemName = CollectablesItemsDetailSteps.get().getItemNameOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().goToNextItemOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().waitForAnotherItemIsDisplayed(currentItemName);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertItemsNameIsNotSameOnItemDetailsScreen(currentItemName);
    }
}
