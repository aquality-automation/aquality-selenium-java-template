package com.myproject.automation.tests.collectables.silverstate.itemsdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.THIRD_ITEM;

public class MultipleRewardsForCompletingNextSilverItemLevelDisplayedOnItemDetailsScreenTest extends BasePMTest {

    @Link(name = "CA-4487", type = "icq")
    @TmsLink("6438542")
    @Test(groups = {"collectables", "base_collectables"})
    public void multipleRewardsForCompletingNextSilverItemLevelDisplayedOnItemDetailsScreenTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().clickItem(THIRD_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertThatStellarPointsRewardIsPresentOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().assertThatCoinsRewardIsPresentOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().assertThatLightningRewardIsPresentOnItemDetailsScreen();
    }
}