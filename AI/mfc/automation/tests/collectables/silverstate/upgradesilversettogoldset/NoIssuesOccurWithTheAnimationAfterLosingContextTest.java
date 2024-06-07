package com.myproject.automation.tests.collectables.silverstate.upgradesilversettogoldset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurWithTheAnimationAfterLosingContextTest extends BasePMTest {

    @Link(name = "CA-4503", type = "icq")
    @TmsLink("6438799")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void noIssuesOccurWithTheAnimationAfterLosingContextTest() {
        upgradeToSilverAndGoldLevel(user);
        AppSteps.get().backgroundApplication();
        AppSteps.get().foregroundApplication();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
    }
}
