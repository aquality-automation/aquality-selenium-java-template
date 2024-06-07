package com.myproject.automation.tests.collectables.openingcollectablecards;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.constants.CollectablesConstants;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import com.myproject.automation.steps.ui.collectables.TotalProgressScreenSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class NoIssuesOccurWhenLosingContextWhileCardIsOpeningTest extends BasePMTest {

    @Link(name = "CA-4407", type = "icq")
    @TmsLink("6359655")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void noIssuesOccurWhenLosingContextWhileCardIsOpeningTest() {
        ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_1);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().openCard(CollectablesConstants.FIRST_CARD);
        AppSteps.get().backgroundApplicationForFewSeconds();
        AppSteps.get().foregroundApplication();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().assertThatContinueButtonIsPresent();
        CollectablesCardsSteps.get().clickContinue();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
    }
}
