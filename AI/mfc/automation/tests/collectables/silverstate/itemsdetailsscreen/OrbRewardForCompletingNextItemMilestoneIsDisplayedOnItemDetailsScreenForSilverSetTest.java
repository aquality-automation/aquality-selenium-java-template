package com.myproject.automation.tests.collectables.silverstate.itemsdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.SECOND_ITEM;

public class OrbRewardForCompletingNextItemMilestoneIsDisplayedOnItemDetailsScreenForSilverSetTest extends BasePMTest {

    @Link(name = "CA-4486", type = "icq")
    @TmsLink("6438541")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void orbRewardForCompletingNextItemMilestoneIsDisplayedOnItemDetailsScreenForSilverSetTest() {
        setSilverState(user);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(SECOND_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().assertThatOrbRewardIsPresentOnItemDetailsScreen();
    }
}
