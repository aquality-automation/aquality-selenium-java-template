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

import static com.myproject.automation.constants.CollectablesConstants.FOURTH_CARD;
import static com.myproject.automation.constants.CollectablesConstants.SECOND_CARD;
import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class CardsCanBeOpenedInAnyOrderOfPriorityTest extends BasePMTest {

    private static final int COUNT_BOXES = 4;
    private static final int ONE_CARD_OPENED = 1;

    @Link(name = "CA-4408", type = "icq")
    @TmsLink("6359656")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void cardsCanBeOpenedInAnyOrderOfPriorityTest() {
        IntStream.range(0, COUNT_BOXES).forEach(v -> ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        CollectablesCardsSteps.get().openCard(SECOND_CARD);
        CollectablesCardsSteps.get().assertThatNumberOfOpenedCardsIsEqual(ONE_CARD_OPENED);
        CollectablesCardsSteps.get().openCard(FOURTH_CARD);
        CollectablesCardsSteps.get().assertThatNumberOfOpenedCardsIsEqual(ONE_CARD_OPENED + ONE_CARD_OPENED);
    }
}
