package com.myproject.automation.tests.collectables.silverstate.displaysilverset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.randomrewards.RewardPacksRevealSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static com.myproject.automation.constants.CollectablesConstants.BOXES_TO_COMPLETE_ITEM_IN_SET;
import static com.myproject.automation.constants.CollectablesConstants.SILVER_SET_REWARD_MESSAGE;
import static com.myproject.automation.enums.collectables.Boxes.BOX_SILVER_ITEM_1;

public class CheckedIconIsDisplayedWhenItemIsInCollectedStateTest extends BasePMTest {

    @Link(name = "CA-4474", type = "icq")
    @TmsLink("6422921")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void checkedIconIsDisplayedWhenItemIsInCollectedStateTest() {
        upgradeToSilverLevel(user, true);
        RewardPacksRevealSteps.get().assertThatRewardsPackRevealScreenIsPresent();
        RewardPacksRevealSteps.get().clickSkip();
        openAllOrbRewards();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatRewardSetTextIsSameAsExpected(SILVER_SET_REWARD_MESSAGE);
        CollectablesAlbumSteps.get().assertThatCompleteTickIsNotPresent();
        IntStream.range(0, BOXES_TO_COMPLETE_ITEM_IN_SET).forEach(v -> ApiSteps.get().addBoxForUser(this.user, BOX_SILVER_ITEM_1));
        IntStream.range(0, BOXES_TO_COMPLETE_ITEM_IN_SET).forEach(v -> ApiSteps.get().openBoxForUser(this.user.getApiToken(), BOX_SILVER_ITEM_1));
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().clickCollectablesIcon();
        upgradeTotalProgressAndClaimUpgradeReward();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        CollectablesAlbumSteps.get().assertThatCompleteTickIsPresent();
    }
}
