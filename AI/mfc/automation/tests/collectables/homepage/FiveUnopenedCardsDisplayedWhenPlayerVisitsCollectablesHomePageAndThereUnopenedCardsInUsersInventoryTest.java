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

import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class FiveUnopenedCardsDisplayedWhenPlayerVisitsCollectablesHomePageAndThereUnopenedCardsInUsersInventoryTest extends BasePMTest {

    private static final int UNOPENED_CARDS = 5;

    @Link(name = "CA-4398", type = "icq")
    @TmsLink("10200371")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void fiveUnopenedCardsDisplayedWhenPlayerVisitsCollectablesHomePageAndThereUnopenedCardsInUsersInventoryTest() {
        IntStream.range(0, UNOPENED_CARDS).forEach(v -> ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().assertThatNumberOfUnopenedCardsIsEqual(UNOPENED_CARDS);
    }
}
