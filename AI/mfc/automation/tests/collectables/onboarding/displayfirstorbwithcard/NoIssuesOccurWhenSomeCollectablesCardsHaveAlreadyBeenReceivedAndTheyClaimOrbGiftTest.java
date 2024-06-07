package com.myproject.automation.tests.collectables.onboarding.displayfirstorbwithcard;

import com.myproject.automation.annotations.TestDataKeys;
import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.Game;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.*;
import com.myproject.automation.steps.ui.collectables.*;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurWhenSomeCollectablesCardsHaveAlreadyBeenReceivedAndTheyClaimOrbGiftTest extends BasePMTest {

    @Link(name = "CA-4809", type = "icq")
    @TmsLink("6507412")
    @Test(dataProvider = "TestDataFromJson", groups = {"collectables", "base_tutorial", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    @TestDataKeys({"game_title", "balance_to_set", "lightning_for_orb"})
    public void noIssuesOccurWhenSomeCollectablesCardsHaveAlreadyBeenReceivedAndTheyClaimOrbGiftTest(String gameTitle,
                                                                                                     String balanceToSet,
                                                                                                     String lightningForOrb) {
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopupWithoutCollectingRewards();
        //wait is needed for set CollectablesTutorialsSeen to false
        AppSteps.get().waitForStableResponseFromWebsocket();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().waitForStableResponseFromWebsocket();
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().clickClose();
        LobbySteps.get().assertThatLobbyIsOpened();
        GameSteps.get().updateUserAndSpinGame(this.user, lightningForOrb, balanceToSet, Game.getGameByTitle(gameTitle), false);
        TopBarMenuSteps.get().assertThatUncollectedRewardsBubbleIndicatorIsPresent();
        GameSteps.get().returnToLobby();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().clickLightning();
        RewardsSteps.get().assertThatRewardsScreenIsPresent();
        RewardsSteps.get().clickCollectAll();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickFinish();
        RewardsSteps.get().assertThatRewardsScreenIsPresent();
        RewardsSteps.get().closeRewards();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
        CollectablesMainTutorialSteps.get().clickCloseTutorialView();
        CollectablesGiftSteps.get().assertThatCollectablesGiftViewIsPresent();
        CollectablesGiftSteps.get().clickClaimTutorialView();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickFinish();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
        TotalProgressScreenSteps.get().clickContinue();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
    }
}
