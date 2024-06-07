package com.myproject.automation.tests.collectables.itemdetailsscreen;

import com.myproject.automation.base.BaseTest;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesItemsDetailSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.myproject.automation.constants.CollectablesConstants.FIRST_ITEM;

public class NoIssuesOccurAfterSpammingBackButtonTest extends BaseTest {

    @Link(name = "CA-4362", type = "icq")
    @TmsLink("6340198")
    @Test(groups = {"collectables", "base_collectables"})
    public void noIssuesOccurAfterSpammingBackButtonTest() {
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickItem(FIRST_ITEM);
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsPresent();
        CollectablesItemsDetailSteps.get().spamBackButton();
        CollectablesItemsDetailSteps.get().assertThatItemDetailsScreenIsNotPresent();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
    }
}
