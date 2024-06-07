package com.myproject.automation.tests.collectables.upgradesets;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.collectables.CollectablesSet;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.enums.collectables.ItemsState.SILVER;

public class AllItemsInSilverStateAreDisplayedOnSetCompletePopupTest extends BasePMTest {

    @Link(name = "CA-4435", type = "icq")
    @TmsLink("6384416")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void allItemsInSilverStateAreDisplayedOnSetCompletePopupTest() {
        upgradeToSilverLevel(user, false);
        SetCompletedSteps.get().tapAreaOfClaimBtn();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsNotPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        SetCompletedSteps.get().assertThatDecorItemsAreDisplayed(CollectablesSet.SILVER.getItemsCount(), SILVER);
        SetCompletedSteps.get().clickClaimBtn();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsNotPresent();
    }
}
