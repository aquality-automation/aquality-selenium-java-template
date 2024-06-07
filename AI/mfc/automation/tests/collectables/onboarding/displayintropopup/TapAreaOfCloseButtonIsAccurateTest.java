package com.myproject.automation.tests.collectables.onboarding.displayintropopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TapAreaOfCloseButtonIsAccurateTest extends BasePMTest {

    @Link(name = "CA-4775", type = "icq")
    @TmsLink("6502609")
    @Test(groups = {"collectables", "base_tutorial"})
    public void tapAreaOfCloseButtonIsAccurateTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().tapAroundCloseBtn();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsNotPresent();
    }
}
