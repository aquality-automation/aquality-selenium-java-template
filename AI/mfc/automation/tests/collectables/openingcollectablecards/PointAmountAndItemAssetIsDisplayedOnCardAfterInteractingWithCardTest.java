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

import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class PointAmountAndItemAssetIsDisplayedOnCardAfterInteractingWithCardTest extends BasePMTest {

    private static final int UNOPENED_CARDS = 2;

    @Link(name = "CA-4409", type = "icq")
    @TmsLink("6359657")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void pointAmountAndItemAssetIsDisplayedOnCardAfterInteractingWithCardTest() {
        IntStream.range(0, UNOPENED_CARDS).forEach(v -> ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().openCard(UNOPENED_CARDS);
        CollectablesCardsSteps.get().assertThatItemAssetOfOpenedCardsIsPresent();
        CollectablesCardsSteps.get().assertThatPointAmountOfOpenedCardsIsPresent();
    }
}
