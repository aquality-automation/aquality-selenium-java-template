package com.myproject.automation.tests.collectables.displaysetcompletepopup;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.Orb;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.TopBarMenuSteps;
import com.myproject.automation.steps.ui.collectables.*;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbTotalRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET;
import static com.myproject.automation.enums.collectables.Boxes.*;

public class FourDifferentTypesOfRewardsCanBeCollectedTest extends BasePMTest {

    @Link(name = "CA-4444", type = "icq")
    @TmsLink("6384427")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void fourDifferentTypesOfRewardsCanBeCollectedTest() {
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
        ApiSteps.get().addBoxForUser(this.user, BOX_BASE_ITEM_7);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        double balanceBeforeSetComplete = TopBarMenuSteps.get().getUserBalance();
        int lightningBeforeSetComplete = TopBarMenuSteps.get().getLightningValue();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
        TotalProgressScreenSteps.get().clickUpgrade();
        double coinUpgradeReward = ClaimUpgradeRewardPopupSteps.get().getCoinReward();
        int lightningUpgradeReward = ClaimUpgradeRewardPopupSteps.get().getLightningReward();
        ClaimUpgradeRewardPopupSteps.get().assertClaimUpgradeRewardPopupIsPresent();
        ClaimUpgradeRewardPopupSteps.get().claimUpgradeReward();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
        SetCompletedSteps.get().assertThatOrbIsPresent(Orb.WOOD);
        SetCompletedSteps.get().assertThatOrbIsPresent(Orb.EARTH);
        SetCompletedSteps.get().assertThatCoinsRewardIsPresent();
        SetCompletedSteps.get().assertThatLightningRewardIsPresent();
        double setCompleteCoinsReward = SetCompletedSteps.get().getCoinReward();
        int setCompleteLightningReward = (int) SetCompletedSteps.get().getLightningReward();
        SetCompletedSteps.get().clickClaimBtn();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickOpenNext();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbTotalRewardsSteps.get().assertThatOrbTotalRewardsScreenIsPresent();
        double coinRewardForOrb = OrbTotalRewardsSteps.get().getCoinsReward();
        int lightingRewardForOrb = OrbTotalRewardsSteps.get().getLightningReward();
        OrbTotalRewardsSteps.get().clickFinish();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickCloseBtn();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().assertBalancesAreEqual(balanceBeforeSetComplete + coinUpgradeReward + coinRewardForOrb + setCompleteCoinsReward, true);
        TopBarMenuSteps.get().assertLightningsAreEqual(lightningBeforeSetComplete + lightningUpgradeReward + setCompleteLightningReward + lightingRewardForOrb, true);
    }
}
