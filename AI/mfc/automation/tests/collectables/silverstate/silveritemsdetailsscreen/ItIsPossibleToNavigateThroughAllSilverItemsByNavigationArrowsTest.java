package com.myproject.automation.tests.collectables.silverstate.silveritemsdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.FIRST_ITEM;

public class ItIsPossibleToNavigateThroughAllSilverItemsByNavigationArrowsTest extends BasePMTest {

    @Link(name = "CA-4491", type = "icq")
    @TmsLink("6438557")
    @Test(groups = {"collectables", "base_collectables"})
    public void itIsPossibleToNavigateThroughAllSilverItemsByNavigationArrowsTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().clickItem(FIRST_ITEM);
        String firstItemName = CollectablesAlbumSteps.get().getItemNameOnItemDetailsScreen();
        CollectablesAlbumSteps.get().assertThatNextItemArrowsAreWorkingCorrectly();
        CollectablesAlbumSteps.get().assertItemsNameIsSameOnItemDetailsScreen(firstItemName);
        CollectablesAlbumSteps.get().assertThatPreviousItemArrowsAreWorkingCorrectly();
        CollectablesAlbumSteps.get().assertItemsNameIsSameOnItemDetailsScreen(firstItemName);
    }
}
