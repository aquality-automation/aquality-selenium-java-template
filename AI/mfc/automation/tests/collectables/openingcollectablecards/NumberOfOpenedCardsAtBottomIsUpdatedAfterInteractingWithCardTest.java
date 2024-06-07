package com.myproject.automation.tests.collectables.openingcollectablecards;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.constants.CollectablesConstants;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.INITIAL_AMOUNT_OF_OPENED_CARDS;
import static com.myproject.automation.constants.CollectablesConstants.MAX_BOXES_ON_OPEN_CARD_SCREEN_PLUS_ONE;
import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class NumberOfOpenedCardsAtBottomIsUpdatedAfterInteractingWithCardTest extends BasePMTest {

    private static final int EXP_AMOUNT_OF_OPENED_CARDS = 1;

    @Link(name = "CA-4412", type = "icq")
    @TmsLink("6359660")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void numberOfOpenedCardsAtBottomIsUpdatedAfterInteractingWithCardTest() {
        IntStream.range(0, MAX_BOXES_ON_OPEN_CARD_SCREEN_PLUS_ONE).forEach(box -> ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        CollectablesCardsSteps.get().assertThatCountOfOpenedCardsIs(INITIAL_AMOUNT_OF_OPENED_CARDS);
        CollectablesCardsSteps.get().openCard(CollectablesConstants.FIRST_CARD);
        AppSteps.get().waitForStableResponseFromWebsocket();
        CollectablesCardsSteps.get().assertThatCountOfOpenedCardsIs(EXP_AMOUNT_OF_OPENED_CARDS);
    }
}
