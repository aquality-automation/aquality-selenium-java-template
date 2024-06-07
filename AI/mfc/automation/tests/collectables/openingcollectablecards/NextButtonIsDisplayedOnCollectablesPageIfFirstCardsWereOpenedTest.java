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

public class NextButtonIsDisplayedOnCollectablesPageIfFirstCardsWereOpenedTest extends BasePMTest {

    private static final int COUNT_CARDS_FOR_OPENING = 5;

    @Link(name = "CA-4413", type = "icq")
    @TmsLink("6359670")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void nextButtonIsDisplayedOnCollectablesPageIfFirstCardsWereOpenedTest() {
        IntStream.range(0, MAX_BOXES_ON_OPEN_CARD_SCREEN_PLUS_ONE).forEach(v -> ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        IntStream.rangeClosed(1, COUNT_CARDS_FOR_OPENING).forEach(v -> CollectablesCardsSteps.get().openCard(v));
        CollectablesCardsSteps.get().assertThatNextButtonIsPresent();
    }
}
