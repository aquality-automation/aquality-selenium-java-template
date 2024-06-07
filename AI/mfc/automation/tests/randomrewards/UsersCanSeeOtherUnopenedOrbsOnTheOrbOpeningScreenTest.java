package com.myproject.automation.tests.randomrewards;

import com.myproject.automation.annotations.TestDataKeys;
import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.Game;
import com.myproject.automation.enums.Orb;
import com.myproject.automation.steps.ui.*;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class UsersCanSeeOtherUnopenedOrbsOnTheOrbOpeningScreenTest extends BasePMTest {

    @Link(name = "CA-3158", type = "icq")
    @TmsLink("7599502")
    @Test(dataProvider = "TestDataFromJson", groups = {"randomrewards", "base_allocated_item", "base_collect_rewards"})
    @TestDataKeys({"game_title", "balance_to_set", "lightning_for_bronze", "lightning_for_silver", "lightning_for_gold", "lightning_for_purple"})
    public void usersCanSeeOtherUnopenedOrbsOnTheOrbOpeningScreenTest(String gameTitle, String balanceToSet,
                                                                      String lightningForBronze, String lightningForSilver,
                                                                      String lightningForGold, String lightningForPurple) {
        Game currentGame = Game.getGameByTitle(gameTitle);
        UserSteps.get().waitForGameAccountIsReceived();
        this.user = UserSteps.get().getUserInformation();
        GameSteps.get().updateUserAndSpinGame(this.user, lightningForBronze, balanceToSet, currentGame);
        GameSteps.get().updateUserAndSpinGame(this.user, lightningForSilver, balanceToSet, currentGame);
        GameSteps.get().updateUserAndSpinGame(this.user, lightningForGold, balanceToSet, currentGame);
        GameSteps.get().updateUserAndSpinGame(this.user, lightningForPurple, balanceToSet, currentGame);
        TopBarMenuSteps.get().assertThatUncollectedRewardsBubbleIndicatorIsPresent();
        TopBarMenuSteps.get().clickLightning();
        RewardsSteps.get().assertThatRewardsScreenIsPresent();
        AppSteps.get().setSpeedToHalfPercent();
        RewardsSteps.get().clickCollectAll();
        RewardPacksRevealSteps.get().assertThatUnopenedOrbIsPresent(Orb.EARTH.getOrbName(), Orb.GOLD.getOrbName(), Orb.PURPLE.getOrbName());
    }
}
