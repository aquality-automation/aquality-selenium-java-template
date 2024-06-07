package com.myproject.automation.tests.collectables.silverstate.itemsdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class RewardsOfTwoTypesCanBeDisplayedOnItemDetailsScreenSilverTest extends BasePMTest {

    private static final int FIRST_ITEM = 1;

    @Link(name = "CA-4488", type = "icq")
    @TmsLink("6438543")
    @Test(groups = {"collectables", "base_collectables"})
    public void rewardsOfTwoTypesCanBeDisplayedOnItemDetailsScreenSilverTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().clickItem(FIRST_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertThatLightningRewardIsPresentOnItemDetailsScreen();
        CollectablesItemsDetailSteps.get().assertThatCoinsRewardIsPresentOnItemDetailsScreen();
    }
}
