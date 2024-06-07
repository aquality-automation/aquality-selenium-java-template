package com.myproject.automation.tests.collectables.homepage;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.REWARD_FOR_BASE_AND_SILVER_CARD;
import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;
import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_2;

public class TotalCollectedPointsOfItemAreDisplayedAtTopBarTest extends BasePMTest {

    private static final int BOXES_TO_ADD = 5;
    private static final int BOXES_TO_ADD_WITH_OTHER_ITEM = 1;

    @Link(name = "CA-4417", type = "icq")
    @TmsLink("10200403")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void totalCollectedPointsOfItemAreDisplayedAtTopBarTest() {
        IntStream.range(0, BOXES_TO_ADD).forEach(box -> ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1));
        ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_2);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().assertThatCollectablesIconIsPresent();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        IntStream.rangeClosed(1, BOXES_TO_ADD).forEach(v -> CollectablesCardsSteps.get().openCard(v));
        CollectablesCardsSteps.get().assertThatCollectablesPointsAtTopBarIsPresent();
        CollectablesCardsSteps.get().clickNextBtn();
        CollectablesCardsSteps.get().assertThatCollectablesPointsAtTopBarIsNotPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        CollectablesCardsSteps.get().openCard(BOXES_TO_ADD_WITH_OTHER_ITEM);
        CollectablesCardsSteps.get().assertThatCollectedTotalPointsIsEqualTo(BOXES_TO_ADD * REWARD_FOR_BASE_AND_SILVER_CARD);
    }
}
