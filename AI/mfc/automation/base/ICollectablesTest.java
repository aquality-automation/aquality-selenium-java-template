package com.myproject.automation.base;

import com.myproject.automation.models.application.User;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.*;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbTotalRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.*;
import static com.myproject.automation.enums.collectables.Boxes.*;

public interface ICollectablesTest {

    default void upgradeTotalProgressAndClaimUpgradeReward() {
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
        TotalProgressScreenSteps.get().clickUpgrade();
        ClaimUpgradeRewardPopupSteps.get().assertClaimUpgradeRewardPopupIsPresent();
        AppSteps.get().waitForStableResponseFromWebsocket();
        ClaimUpgradeRewardPopupSteps.get().claimUpgradeReward();
    }

    default void completeBronzeSetWithoutLastItem(User user) {
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(user, BRONZE_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(user.getApiToken(), BRONZE_FULL_SET_BOX));
        ApiSteps.get().addBoxForUser(user, BRONZE_SET_BOX_WITHOUT_LAST_ITEM);
        ApiSteps.get().openBoxForUser(user.getApiToken(), BRONZE_SET_BOX_WITHOUT_LAST_ITEM);
        AppSteps.get().launchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().tapCollectablesIcon();
        upgradeTotalProgressAndClaimUpgradeReward();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkipIfPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickOpenAll();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkipIfPresent();
        OrbTotalRewardsSteps.get().assertThatOrbTotalRewardsViewIsPresent();
        OrbTotalRewardsSteps.get().clickFinish();
    }

    default void upgradeToSilverLevel(User user) {
        completeBronzeSetWithoutLastItem(user);
        ApiSteps.get().addBoxForUser(user, BOX_BRONZE_ITEM_7);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().tapCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        upgradeTotalProgressAndClaimUpgradeReward();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
    }

    default void setSilverState(User user) {
        IntStream.range(0, BOXES_AMOUNT_TO_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(user, BRONZE_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(user.getApiToken(), BRONZE_FULL_SET_BOX));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().tapCollectablesIcon();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
        TotalProgressScreenSteps.get().clickUpgrade();
        CollectablesAlbumSteps.get().clickSkipBtn();
        ClaimUpgradeRewardPopupSteps.get().assertClaimUpgradeRewardPopupIsPresent();
        ApiSteps.get().openBoxForUser(user.getApiToken(), BOX_BRONZE_ITEM_1);
        ApiSteps.get().openBoxForUser(user.getApiToken(), BOX_BRONZE_ITEM_1);
        ApiSteps.get().markItemsAsShown(user.getApiToken(), BRONZE_FULL_SET_BOX);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().tapCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatRewardSetTextIsSameAsExpected(SILVER_SET_REWARD_MESSAGE);
    }

    default void upgradeToGoldLevel(User user) {
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(user, SILVER_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(user.getApiToken(), SILVER_FULL_SET_BOX));
        AppSteps.get().launchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().tapCollectablesIcon();
        upgradeTotalProgressAndClaimUpgradeReward();
        ApiSteps.get().addBoxForUser(user, SILVER_FULL_SET_BOX);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().tapCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        upgradeTotalProgressAndClaimUpgradeReward();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
    }

    default void completeGoldState(User user) {
        upgradeToSilverAndGoldLevel(user);
        SetCompletedSteps.get().assertThatClaimBtnIsPresent();
        SetCompletedSteps.get().clickClaimBtn();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickOpenAll();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbTotalRewardsSteps.get().assertThatOrbTotalRewardsViewIsPresent();
        OrbTotalRewardsSteps.get().clickFinish();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().addBoxForUser(user, GOLD_FULL_SET_BOX));
        IntStream.range(0, BOXES_AMOUNT_TO_ALMOST_COMPLETE_SET).forEach(box -> ApiSteps.get().openBoxForUser(user.getApiToken(), GOLD_FULL_SET_BOX));
        AppSteps.get().launchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().tapCollectablesIcon();
        upgradeTotalProgressAndClaimUpgradeReward();
        ApiSteps.get().addBoxForUser(user, GOLD_FULL_SET_BOX);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().tapCollectablesIcon();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        upgradeTotalProgressAndClaimUpgradeReward();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsPresent();
    }

    default void upgradeToSilverAndGoldLevel(User user) {
        upgradeToSilverLevel(user);
        upgradeToGoldLevel(user);
    }

    default void makeTrade(User user, int duplicateBalance) {
        ApiSteps.get().setUserDuplicateBalanceTo(user, duplicateBalance);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().tapCollectablesIcon();
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
}
