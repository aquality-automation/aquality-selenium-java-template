package com.myproject.automation.tests.collectables.openingcollectablecards;

import com.myproject.automation.annotations.TestDataKeys;
import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.Game;
import com.myproject.automation.steps.ui.*;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class NoIssuesOccurAfterPerformingReinitOnCardOpeningScreenTest extends BasePMTest {

    @Link(name = "CA-4431", type = "icq")
    @TmsLink("6467797")
    @Test(dataProvider = "TestDataFromJson", groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    @TestDataKeys({"game_title", "balance_to_set", "lightning_for_orb"})
    public void noIssuesOccurAfterPerformingReinitOnCardOpeningScreenTest(String gameTitle, String balanceToSet,
                                                                          String lightningForOrb) {
        GameSteps.get().updateUserAndSpinGame(this.user, lightningForOrb, balanceToSet, Game.getGameByTitle(gameTitle));
        TopBarMenuSteps.get().assertThatUncollectedRewardsBubbleIndicatorIsPresent();
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().clickLightning();
        RewardsSteps.get().assertThatRewardsScreenIsPresent();
        RewardsSteps.get().clickCollectAll();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().openCollectables();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        AppSteps.get().performAppReinit();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().openShop();
        ShopSteps.get().assertThatShopIsOpened();
    }
}
