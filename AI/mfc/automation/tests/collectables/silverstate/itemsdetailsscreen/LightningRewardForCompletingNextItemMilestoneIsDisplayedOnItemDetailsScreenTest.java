package com.myproject.automation.tests.collectables.silverstate.itemsdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class LightningRewardForCompletingNextItemMilestoneIsDisplayedOnItemDetailsScreenTest extends BasePMTest {

    private static final int FOURTH_ITEM = 4;

    @Link(name = "CA-4484", type = "icq")
    @TmsLink("6438539")
    @Test(groups = {"collectables", "base_collectables"})
    public void lightningRewardForCompletingNextItemMilestoneIsDisplayedOnItemDetailsScreenTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().clickItem(FOURTH_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertThatLightningRewardIsPresentOnItemDetailsScreen();
    }
}
