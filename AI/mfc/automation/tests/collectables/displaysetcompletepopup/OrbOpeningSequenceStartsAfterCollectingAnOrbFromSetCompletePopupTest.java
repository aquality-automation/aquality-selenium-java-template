package com.myproject.automation.tests.collectables.displaysetcompletepopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.Links;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.Test;

public class OrbOpeningSequenceStartsAfterCollectingAnOrbFromSetCompletePopupTest extends BasePMTest {

    @Links({@Link(type = "icq", name = "CA-4445"), @Link(type = "icq", name = "CA-4432")})
    @TmsLinks({@TmsLink("10200624"), @TmsLink("10200637")})
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    public void orbOpeningSequenceStartsAfterCollectingAnOrbFromSetCompletePopupTest() {
        upgradeToSilverLevel(user, true);
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
    }
}
