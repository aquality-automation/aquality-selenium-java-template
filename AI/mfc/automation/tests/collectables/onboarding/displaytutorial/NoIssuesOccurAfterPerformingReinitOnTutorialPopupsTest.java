package com.myproject.automation.tests.collectables.onboarding.displaytutorial;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.*;
import com.myproject.automation.steps.ui.collectables.CollectablesMainTutorialSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurAfterPerformingReinitOnTutorialPopupsTest extends BasePMTest {

    @Link(name = "CA-4792", type = "icq")
    @TmsLink("6502648")
    @Test(groups = {"collectables", "base_tutorial"})
    public void noIssuesOccurAfterPerformingReinitOnTutorialPopupsTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().assertThatCheckButtonIsPresent();
        CollectablesWelcomeSteps.get().clickCheck();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        AppSteps.get().performAppReinit();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().openShop();
        ShopSteps.get().assertThatShopIsOpened();
    }
}
