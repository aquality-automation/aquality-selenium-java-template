package com.myproject.automation.tests.collectables.displaysetcompletepopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.TopBarMenuSteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET;
import static com.myproject.automation.enums.collectables.Boxes.*;

public class NoIssuesOccurAfterForceClosingAppRightAfterTappingClaimButtonOnSetCompletePopupTest extends BasePMTest {

    private static final int SUM_OF_LIGHTNING_REWARDS = 800;
    private static final int SUM_OF_COIN_REWARDS = 125000;

    @Link(name = "CA-4455", type = "icq")
    @TmsLink("10200653")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void noIssuesOccurAfterForceClosingAppRightAfterTappingClaimButtonOnSetCompletePopupTest() {
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(this.user, BASE_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(this.user.getApiToken(), BASE_FULL_SET_BOX));
        ApiSteps.get().addBoxForUser(this.user, BASE_SET_BOX_WITHOUT_LAST_ITEM);
        ApiSteps.get().openBoxForUser(this.user.getApiToken(), BASE_SET_BOX_WITHOUT_LAST_ITEM);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        upgradeTotalProgressAndClaimUpgradeReward();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkip();
        openAllOrbRewards();
        int lightningCount = TopBarMenuSteps.get().getLightningValue();
        ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_7);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        upgradeTotalProgressAndClaimUpgradeReward();
        double initialBalance = UserSteps.get().getUserBalance();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().assertLightningsAreEqual(lightningCount + SUM_OF_LIGHTNING_REWARDS, false);
        TopBarMenuSteps.get().assertBalancesAreEqual(initialBalance + SUM_OF_COIN_REWARDS, false);
    }
}
