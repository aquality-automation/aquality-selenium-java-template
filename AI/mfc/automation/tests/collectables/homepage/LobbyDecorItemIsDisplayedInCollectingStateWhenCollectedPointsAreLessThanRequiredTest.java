package com.myproject.automation.tests.collectables.homepage;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.enums.collectables.Boxes.BOX_BASE_ITEM_1;

public class LobbyDecorItemIsDisplayedInCollectingStateWhenCollectedPointsAreLessThanRequiredTest extends BasePMTest {

    private static final int ITEM_NUMBER = 1;
    private static final int COUNT_BOXES = 2;
    private static final String COLLECTING_STATE = "40/50";

    @Link(name = "CA-4350", type = "icq")
    @TmsLink("10200158")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void lobbyDecorItemIsDisplayedInCollectingStateWhenCollectedPointsAreLessThanRequiredTest() {
        IntStream.range(0, COUNT_BOXES).forEach(box -> ApiSteps.get().addBoxForUser(user, BOX_BASE_ITEM_1));
        IntStream.range(0, COUNT_BOXES).forEach(box -> ApiSteps.get().openBoxForUser(user.getApiToken(), BOX_BASE_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatLobbyDecorItemProgressIsCorrect(ITEM_NUMBER, COLLECTING_STATE);
    }
}
