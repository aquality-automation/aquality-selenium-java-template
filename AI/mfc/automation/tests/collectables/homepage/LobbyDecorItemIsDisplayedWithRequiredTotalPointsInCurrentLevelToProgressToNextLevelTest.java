package com.myproject.automation.tests.collectables.homepage;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class LobbyDecorItemIsDisplayedWithRequiredTotalPointsInCurrentLevelToProgressToNextLevelTest extends BasePMTest {

    private static final int ITEM_NUMBER = 1;
    private static final String PROGRESS_VALUE = "0/50";

    @Link(name = "CA-4349", type = "icq")
    @TmsLink("10200157")
    @Test(groups = {"collectables", "base_collectables"})
    public void lobbyDecorItemIsDisplayedWithRequiredTotalPointsInCurrentLevelToProgressToNextLevelTest() {
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatLobbyDecorItemProgressIsCorrect(ITEM_NUMBER, PROGRESS_VALUE);
    }
}
