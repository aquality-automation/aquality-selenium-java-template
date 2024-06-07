package com.myproject.automation.tests.collectables.openingcollectablecards;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import com.myproject.automation.steps.ui.collectables.TotalProgressScreenSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.REWARD_FOR_BASE_AND_SILVER_CARD;
import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class TotalPointAmountCollectedForItemIsDisplayedOnSummaryScreenTest extends BasePMTest {

    private static final int BOXES_TO_ADD = 3;

    @Link(name = "CA-4424", type = "icq")
    @TmsLink("6359681")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void totalPointAmountCollectedForItemIsDisplayedOnSummaryScreenTest() {
        IntStream.range(0, BOXES_TO_ADD).forEach(box -> ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().assertThatCollectablesIconIsPresent();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        CollectablesCardsSteps.get().skipAll();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
        TotalProgressScreenSteps.get().assertThatCollectedTotalPointsIsEqualTo(BOXES_TO_ADD * REWARD_FOR_BASE_AND_SILVER_CARD);
    }
}
