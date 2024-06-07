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

import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class ContinueButtonIsDisplayedToDisplaySummaryScreenWhenAllCardsAreOpenedTest extends BasePMTest {

    private static final int TWO_CARDS = 2;

    @Link(name = "CA-4420", type = "icq")
    @TmsLink("6359675")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void continueButtonIsDisplayedToDisplaySummaryScreenWhenAllCardsAreOpenedTest() {
        IntStream.range(0, TWO_CARDS).forEach(v -> ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        IntStream.rangeClosed(1, TWO_CARDS).forEach(c -> CollectablesCardsSteps.get().openCard(c));
        CollectablesCardsSteps.get().assertThatNumberOfOpenedCardsIsEqual(TWO_CARDS);
        CollectablesCardsSteps.get().assertThatContinueButtonIsPresent();
        CollectablesCardsSteps.get().clickContinue();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
    }
}
