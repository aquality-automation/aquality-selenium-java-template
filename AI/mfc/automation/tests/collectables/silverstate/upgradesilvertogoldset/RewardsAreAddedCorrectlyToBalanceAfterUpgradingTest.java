package com.myproject.automation.tests.collectables.silverstate.upgradesilvertogoldset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.commands.models.GameAccount;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.TopBarMenuSteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET;
import static com.myproject.automation.enums.collectables.Boxes.SILVER_FULL_SET_BOX;

public class RewardsAreAddedCorrectlyToBalanceAfterUpgradingTest extends BasePMTest {

    private static final int SUM_OF_COIN_REWARDS = 1105000;
    private static final int SUM_OF_LIGHTNING_REWARDS = 320;

    @Link(name = "CA-4496", type = "icq")
    @TmsLink("6438800")
    @Test(groups = {"collectables", "base_collectables", "critical"})
    public void rewardsAreAddedCorrectlyToBalanceAfterUpgradingTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().updateUser(this.user, GameAccount.builder().balance(DEFAULT_BALANCE).build());
        upgradeToSilverLevel(user, true);
        openAllOrbRewards();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(this.user, SILVER_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(this.user.getApiToken(), SILVER_FULL_SET_BOX));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        upgradeTotalProgressAndClaimUpgradeReward();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkip();
        openAllOrbRewards();
        ApiSteps.get().addBoxForUser(this.user, SILVER_FULL_SET_BOX);
        AppSteps.get().relaunchApp();
        int lightingCount = TopBarMenuSteps.get().getLightningValue();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        upgradeTotalProgressAndClaimUpgradeReward();
        double initialBalance = UserSteps.get().getUserBalance();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickFinish();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        openAllOrbRewards();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickCloseBtn();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().assertLightningsAreEqual(lightingCount + SUM_OF_LIGHTNING_REWARDS, false);
        TopBarMenuSteps.get().assertBalancesAreEqual(initialBalance + SUM_OF_COIN_REWARDS, false);
    }
}
