package com.myproject.automation.tests.collectables.itemdetailsscreen;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.FIRST_ITEM;

public class NoIssuesOccurAfterLosingContextOnItemDetailsScreenTest extends BasePMTest {

    @Link(name = "CA-4364", type = "icq")
    @TmsLink("6340200")
    @Test(groups = {"collectables", "base_collectables"})
    public void noIssuesOccurAfterLosingContextOnItemDetailsScreenTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(FIRST_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        AppSteps.get().backgroundApplication();
        AppSteps.get().foregroundApplication();
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
    }
}
