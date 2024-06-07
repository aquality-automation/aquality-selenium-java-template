package com.myproject.automation.tests.collectables.onboarding.displaytutorial;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesMainTutorialSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class SpammingCloseButtonClosesTutorialCausesNoIssuesTest extends BasePMTest {

    private static final int SPAM_COUNT = 1;

    @Link(name = "CA-4789", type = "icq")
    @TmsLink("6502642")
    @Test(groups = {"collectables", "base_tutorial"})
    public void spammingCloseButtonClosesTutorialCausesNoIssuesTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().clickCheck();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        CollectablesMainTutorialSteps.get().spamTapCloseBtn(SPAM_COUNT);
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsNotPresent();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
    }
}
