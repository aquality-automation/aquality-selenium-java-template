package com.myproject.automation.tests.collectables.onboarding.displayintropopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesMainTutorialSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TutorialIsTriggeredAfterTappingInfoButtonOnTheCollectablesHomeScreenTest extends BasePMTest {

    @Link(name = "CA-4790", type = "icq")
    @TmsLink("6502643")
    @Test(groups = {"collectables", "base_tutorial"})
    public void tutorialIsTriggeredAfterTappingInfoButtonOnTheCollectablesHomeScreenTest() {
        UserSteps.get().waitForGameAccountIsReceived();
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), true);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatInfoBtnOnCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickInfoButton();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
    }
}
