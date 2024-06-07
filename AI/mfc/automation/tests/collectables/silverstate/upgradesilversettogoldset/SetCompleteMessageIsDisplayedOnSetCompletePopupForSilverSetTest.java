package com.myproject.automation.tests.collectables.silverstate.upgradesilversettogoldset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.Links;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.Test;

public class SetCompleteMessageIsDisplayedOnSetCompletePopupForSilverSetTest extends BasePMTest {

    private static final String EXPECTED_MESSAGE = "SILVER SET COMPLETED!";

    @Links({@Link(type = "icq", name = "CA-4493"), @Link(type = "icq", name = "CA-4492")})
    @TmsLinks({@TmsLink("6438781"), @TmsLink("6438780")})
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void setCompleteMessageIsDisplayedOnSetCompletePopupForSilverSetTest() {
        upgradeToSilverAndGoldLevel(user);
        SetCompletedSteps.get().assertThatSetCompletedTextIsEqual(EXPECTED_MESSAGE);
    }
}
