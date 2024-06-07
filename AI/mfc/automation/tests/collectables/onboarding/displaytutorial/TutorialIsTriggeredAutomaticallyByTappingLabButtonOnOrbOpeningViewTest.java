package com.myproject.automation.tests.collectables.onboarding.displaytutorial;

import com.myproject.automation.annotations.TestDataKeys;
import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.enums.Game;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.*;
import com.myproject.automation.steps.ui.collectables.CollectablesMainTutorialSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesWelcomeSteps;
import com.myproject.automation.steps.ui.randomrewards.OrbRewardsSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TutorialIsTriggeredAutomaticallyByTappingLabButtonOnOrbOpeningViewTest extends BasePMTest {

    @Link(name = "CA-4781", type = "icq")
    @TmsLink("6507415")
    @Test(dataProvider = "TestDataFromJson", groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards", "base_tutorial"})
    @TestDataKeys({"game_title", "balance_to_set", "lightning_for_orb"})
    public void tutorialIsTriggeredAutomaticallyByTappingLabButtonOnOrbOpeningViewTest(String gameTitle,
                                                                                       String balanceToSet,
                                                                                       String lightningForOrb) {
        GameSteps.get().updateUserAndSpinGame(this.user, lightningForOrb, balanceToSet, Game.getGameByTitle(gameTitle));
        TopBarMenuSteps.get().assertThatUncollectedRewardsBubbleIndicatorIsPresent();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), false);
        AppSteps.get().relaunchApp();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsOpened();
        CollectablesWelcomeSteps.get().clickClose();
        CollectablesWelcomeSteps.get().assertThatCollectablesWelcomeViewIsNotOpened();
        LobbySteps.get().assertThatLobbyIsOpened();
        TopBarMenuSteps.get().clickLightning();
        RewardsSteps.get().assertThatRewardsScreenIsPresent();
        RewardsSteps.get().clickCollectAll();
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        OrbRewardsSteps.get().assertThatOrbRewardsViewIsPresent();
        OrbRewardsSteps.get().clickCollectables();
        CollectablesMainTutorialSteps.get().assertThatCollectablesMainTutorialViewIsPresent();
    }
}
