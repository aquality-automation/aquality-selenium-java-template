package com.myproject.automation.tests.collectables.openingcollectablecards;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import io.qameta.allure.Link;
import io.qameta.allure.Links;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.Test;

import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class CardIsOpenedAfterInteractingWithItTest extends BasePMTest {

    private static final int UNOPENED_CARDS = 1;
    private static final int OPENED_CARDS = 1;
    private static final int CARD_NUMBER = 1;

    @Links({@Link(name = "CA-4405", type = "icq"), @Link(name = "CA-4406", type = "icq")})
    @TmsLinks({@TmsLink("6359651"), @TmsLink("6359652")})
    @Test(groups = {"basesuite", "collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void cardIsOpenedAfterInteractingWithItTest() {
        ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1);
        ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        CollectablesCardsSteps.get().openCard(CARD_NUMBER);
        AppSteps.get().waitForStableResponseFromWebsocket();
        CollectablesCardsSteps.get().assertThatNumberOfUnopenedCardsIsEqual(UNOPENED_CARDS);
        CollectablesCardsSteps.get().assertThatNumberOfOpenedCardsIsEqual(OPENED_CARDS);
    }
}
