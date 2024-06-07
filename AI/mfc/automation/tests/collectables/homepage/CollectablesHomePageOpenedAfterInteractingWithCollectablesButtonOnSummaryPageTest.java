package com.myproject.automation.tests.collectables.homepage;

import com.myproject.automation.annotations.TestDataKeys;
import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.Game;
import com.myproject.automation.steps.ui.*;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesCardsSteps;
import com.myproject.automation.steps.ui.collectables.TotalProgressScreenSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class CollectablesHomePageOpenedAfterInteractingWithCollectablesButtonOnSummaryPageTest extends BasePMTest {

    @Link(name = "CA-4397", type = "icq")
    @TmsLink("10200370")
    @Test(dataProvider = "TestDataFromJson", groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "critical"})
    @TestDataKeys({"game_title", "balance_to_set", "lightning_for_orb"})
    public void collectablesHomePageOpenedAfterInteractingWithCollectablesButtonOnSummaryPageTest(String gameTitle, String balanceToSet,
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
        OrbRewardsSteps.get().clickCollectables();
        CollectablesCardsSteps.get().assertThatCollectableCardsScreenIsPresent();
        CollectablesCardsSteps.get().skipAll();
        TotalProgressScreenSteps.get().assertThatTotalProgressScreenIsPresent();
        TotalProgressScreenSteps.get().clickContinue();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
    }
}
