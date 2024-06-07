package com.myproject.automation.tests.collectables.displaysetcompletepopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.TotalProgressScreenSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.BOXES_AMOUNT_TO_COMPLETE_SET;
import static com.myproject.automation.enums.collectables.Boxes.BASE_FULL_SET_BOX;

public class CollectablesHomePageIsShownAfterTappingCollectablesButtonOnLobbyTest extends BasePMTest {

    @Link(name = "CA-4450", type = "icq")
    @TmsLink("10200642")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void collectablesHomePageIsShownAfterTappingCollectablesButtonOnLobbyTest() {
        IntStream.range(0, BOXES_AMOUNT_TO_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(this.user, BASE_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(this.user.getApiToken(), BASE_FULL_SET_BOX));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
        TotalProgressScreenSteps.get().clickUpgrade();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
    }
}
