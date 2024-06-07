package com.myproject.automation.tests.collectables.upgradesets;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import com.myproject.automation.steps.ui.collectables.TotalProgressScreenSteps;
import io.qameta.allure.Link;
import io.qameta.allure.Links;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.enums.collectables.Boxes.BOX_SILVER_ITEM_1;

public class ContinueButtonIsDisplayedOnSummaryScreenToCloseAndCollectPointsForSilverSetTest extends BasePMTest {

    private static final int BOXES_TO_ADD = 2;
    private static final int ITEM_NUMBER = 1;
    private static final String PROGRESS = "40/50";

    @Links({@Link(name = "CA-4456", type = "icq"), @Link(name = "CA-4557", type = "icq")})
    @TmsLinks({@TmsLink("6430081"), @TmsLink("6430082")})
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void continueButtonIsDisplayedOnSummaryScreenToCloseAndCollectPointsForSilverSetTest() {
        upgradeToSilverLevel(user, true);
        IntStream.range(0, BOXES_TO_ADD).forEach(box -> ApiSteps.get().addBoxForUser(this.user, BOX_SILVER_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().assertThatCollectablesIconIsPresent();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
        TotalProgressScreenSteps.get().assertThatContinueBtnIsPresent();
        TotalProgressScreenSteps.get().clickContinue();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsNotPresent();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatLobbyDecorItemProgressIsCorrect(ITEM_NUMBER, PROGRESS);
    }
}
