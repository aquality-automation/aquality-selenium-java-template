package com.myproject.automation.tests.collectables.silverstate.homepage;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.collectables.CollectablesSet;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.enums.collectables.ItemsState.GOLD;

public class SevenLobbyDecorItemsOfSilverStateAreDisplayedOnCollectablesHomePageTest extends BasePMTest {

    @Link(name = "CA-4469", type = "icq")
    @TmsLink("6422908")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void sevenLobbyDecorItemsOfSilverStateAreDisplayedOnCollectablesHomePageTest() {
        upgradeToSilverAndGoldLevel(user);
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        openAllOrbRewards();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatLobbyDecorItemsAreDisplayedInSilverState();
        CollectablesAlbumSteps.get().assertThatDecorItemsAreDisplayed(CollectablesSet.GOLD.getItemsCount(), GOLD);
    }
}
