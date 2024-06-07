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

import static com.myproject.automation.constants.CollectablesConstants.MAX_AMOUNT_OF_CARDS_ON_SCREEN;
import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class TapAreaOfNextButtonIsAccurateTest extends BasePMTest {

    private static final int COUNT_BOXES = 7;

    @Link(name = "CA-4419", type = "icq")
    @TmsLink("6359673")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void tapAreaOfNextButtonIsAccurateTest() {
        IntStream.range(0, COUNT_BOXES).forEach(v -> ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        IntStream.rangeClosed(1, MAX_AMOUNT_OF_CARDS_ON_SCREEN).forEach(c -> CollectablesCardsSteps.get().openCard(c));
        CollectablesCardsSteps.get().assertThatNumberOfOpenedCardsIsEqual(MAX_AMOUNT_OF_CARDS_ON_SCREEN);
        CollectablesCardsSteps.get().tapAroundNextBtn();
        CollectablesCardsSteps.get().assertThatNumberOfOpenedCardsIsEqual(MAX_AMOUNT_OF_CARDS_ON_SCREEN);
    }
}
