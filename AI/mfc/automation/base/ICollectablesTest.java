package com.myproject.automation.base;

import com.myproject.automation.models.application.User;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.*;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.*;
import static com.myproject.automation.enums.collectables.Boxes.*;

public interface ICollectablesTest {

    default void upgradeTotalProgressAndClaimUpgradeReward() {
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        TotalProgressScreenSteps.get().clickUpgrade();
        CollectablesAlbumSteps.get().clickSkipBtnIfPresent();
        ClaimUpgradeRewardPopupSteps.get().assertClaimUpgradeRewardPopupIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        ClaimUpgradeRewardPopupSteps.get().claimUpgradeReward();
    }

    default void completeBaseSetWithoutLastItem(User user) {
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(user, BASE_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(user.getApiToken(), BASE_FULL_SET_BOX));
        ApiSteps.get().addBoxForUser(user, BASE_SET_BOX_WITHOUT_LAST_ITEM);
        ApiSteps.get().openBoxForUser(user.getApiToken(), BASE_SET_BOX_WITHOUT_LAST_ITEM);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        upgradeTotalProgressAndClaimUpgradeReward();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkipIfPresent();
        openAllOrbRewards();
    }

    default void upgradeToSilverLevel(User user, boolean isSetCompletedScreenCanBeClosed) {
        completeBaseSetWithoutLastItem(user);
        ApiSteps.get().addBoxForUser(user, BOX_BASE_ITEM_7);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        upgradeTotalProgressAndClaimUpgradeReward();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
        if (isSetCompletedScreenCanBeClosed) {
            SetCompletedSteps.get().clickClaimBtn();
            SetCompletedSteps.get().assertThatSetCompletedScreenIsNotPresent();
        }
    }

    default void setSilverState(User user) {
        IntStream.range(0, BOXES_AMOUNT_TO_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(user, BASE_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(user.getApiToken(), BASE_FULL_SET_BOX));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        upgradeTotalProgressAndClaimUpgradeReward();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkipIfPresent();
        openAllOrbRewards();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        openAllOrbRewards();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatRewardSetTextIsSameAsExpected(SILVER_SET_REWARD_MESSAGE);
    }

    default void upgradeToGoldLevel(User user) {
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(user, SILVER_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(user.getApiToken(), SILVER_FULL_SET_BOX));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        upgradeTotalProgressAndClaimUpgradeReward();
        ApiSteps.get().addBoxForUser(user, SILVER_FULL_SET_BOX);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        upgradeTotalProgressAndClaimUpgradeReward();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkip();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickFinish();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
    }

    default void completeGoldState(User user) {
        upgradeToSilverAndGoldLevel(user);
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        openAllOrbRewards();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(user, GOLD_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(user.getApiToken(), GOLD_FULL_SET_BOX));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        upgradeTotalProgressAndClaimUpgradeReward();
        ApiSteps.get().addBoxForUser(user, GOLD_FULL_SET_BOX);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        upgradeTotalProgressAndClaimUpgradeReward();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
    }

    default void upgradeToSilverAndGoldLevel(User user) {
        upgradeToSilverLevel(user, true);
        upgradeToGoldLevel(user);
    }

    default void makeTrade(User user, int duplicateBalance) {
        ApiSteps.get().setUserDuplicateBalanceTo(user, duplicateBalance);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().clickTradeButton();
        TradeSteps.get().assertThatTradeScreenIsPresent();
        TradeSteps.get().assertThatTradePriceButtonIsPresent();
        TradeSteps.get().openTradePack();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().assertThatSkipAllButtonIsPresent();
        CollectablesCardsSteps.get().skipAll();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
    }

    default void openAllOrbRewards() {
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickOpenAll();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkipIfPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickFinish();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsNotPresent();
    }
}
