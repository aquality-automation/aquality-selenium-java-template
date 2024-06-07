package com.myproject.automation.tests.collectables.openingcollectablecards;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.MAX_BOXES_ON_OPEN_CARD_SCREEN_PLUS_ONE;
import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class NumberOfOpenedCardsIs0AtBeginningTest extends BasePMTest {

    private static final int EXPECTED_COUNT_OF_OPENED_CARDS = 0;

    @Link(name = "CA-4401", type = "icq")
    @TmsLink("6359647")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void numberOfOpenedCardsIs0AtBeginningTest() {
        IntStream.range(0, MAX_BOXES_ON_OPEN_CARD_SCREEN_PLUS_ONE).forEach(v -> ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().assertThatCountOfOpenedCardsIs(EXPECTED_COUNT_OF_OPENED_CARDS);
    }
}
