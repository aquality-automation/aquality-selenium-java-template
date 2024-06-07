package com.myproject.automation.tests.collectables.openingcollectablecards;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.collectables.Boxes;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.MAX_AMOUNT_OF_CARDS_ON_SCREEN;
import static com.myproject.automation.constants.CollectablesConstants.MAX_ASSETS_COUNT_ON_SCREEN;
import static com.myproject.automation.enums.collectables.Boxes.*;

public class MaximumFiveAssetsCanBeDisplayedOnTheTopTest  extends BasePMTest {

    private static final List<Boxes> BOX_BASE_ITEMS_LIST = Arrays.asList(BOX_BASE_ITEM_1,
            BOX_BASE_ITEM_2, BOX_BASE_ITEM_3, BOX_BASE_ITEM_4, BOX_BASE_ITEM_5, BOX_BASE_ITEM_6, BOX_BASE_ITEM_7);

    @Link(name = "CA-4414", type = "icq")
    @TmsLink("6464316")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void maximumFiveAssetsCanBeDisplayedOnTheTopTest() {
        ApiSteps.get().addBoxesForUser(this.user, BOX_BASE_ITEMS_LIST);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        CollectablesCardsSteps.get().assertThatMaximumNumberOfCardsIsEqual(MAX_AMOUNT_OF_CARDS_ON_SCREEN);
        IntStream.rangeClosed(1, MAX_AMOUNT_OF_CARDS_ON_SCREEN).forEach(c -> CollectablesCardsSteps.get().openCard(c));
        CollectablesCardsSteps.get().assertThatNumberOfOpenedCardsIsEqual(MAX_AMOUNT_OF_CARDS_ON_SCREEN);
        CollectablesCardsSteps.get().assertThatMaximumNumberOfTopbarAssetsIsEqual(MAX_ASSETS_COUNT_ON_SCREEN);
    }
}
