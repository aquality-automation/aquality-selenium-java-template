package com.myproject.automation.tests.collectables.silverstate.displaysilverset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.collectables.CollectablesSet;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.enums.collectables.ItemsState.SILVER;

public class SevenLobbyDecorItemsOfBronzeStateAreDisplayedOnCollectablesHomePageTest extends BasePMTest {

    @Link(name = "CA-4468", type = "icq")
    @TmsLink("6422907")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void sevenLobbyDecorItemsOfBronzeStateAreDisplayedOnCollectablesHomePageTest() {
        setSilverState(user);
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatLobbyDecorItemsAreDisplayedInBronzeState();
        CollectablesAlbumSteps.get().assertThatDecorItemsAreDisplayed(CollectablesSet.SILVER.getItemsCount(), SILVER);
    }
}
