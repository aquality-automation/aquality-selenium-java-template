package com.myproject.automation.tests.collectables.silverstate.upgradesilversettogoldset;

import com.myproject.automation.base.BasePMTest;
import com.myproject.automation.commands.models.GameAccount;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.steps.ui.LobbySteps;
import com.myproject.automation.steps.ui.TopBarMenuSteps;
import com.myproject.automation.steps.ui.UserSteps;
import com.myproject.automation.steps.ui.collectables.CollectablesAlbumSteps;
import com.myproject.automation.steps.ui.collectables.SetCompletedSteps;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class SetCompletePopupIsNotDisplayedOnCollectablesHomePageIfAppWasClosedPreviouslyOnThisPopupTest extends BasePMTest {

    private static final int SUM_OF_LIGHTNING_REWARDS_AFTER_GOLD_LEVEL = 23670;
    private static final int SUM_OF_COIN_REWARDS_AFTER_GOLD_LEVEL = 1619003;

    @Link(name = "CA-4501", type = "icq")
    @TmsLink("6438788")
    @Test(groups = {"collectables", "base_collectables", "base_allocated_item", "base_collect_rewards"})
    public void setCompletePopupIsNotDisplayedOnCollectablesHomePageIfAppWasClosedPreviouslyOnThisPopupTest() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().updateUser(this.user, GameAccount.builder().balance(DEFAULT_BALANCE)
                .lightning(DEFAULT_LIGHTNING_1_TIER).build());
        AppSteps.get().relaunchApp();
        upgradeToSilverLevel(user, true);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        int lightningCountAfterSilverLevel = TopBarMenuSteps.get().getLightningValue();
        double balanceAfterSilverLevel = UserSteps.get().getUserBalance();
        upgradeToGoldLevel(user);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        LobbySteps.get().assertThatCollectablesIconIsPresent();
        TopBarMenuSteps.get().assertLightningsAreEqual(lightningCountAfterSilverLevel + SUM_OF_LIGHTNING_REWARDS_AFTER_GOLD_LEVEL, false);
        TopBarMenuSteps.get().assertBalancesAreEqual(balanceAfterSilverLevel + SUM_OF_COIN_REWARDS_AFTER_GOLD_LEVEL, false);
        LobbySteps.get().clickCollectablesIcon();
        CollectablesAlbumSteps.get().assertThatCollectablesAlbumIsPresent();
        SetCompletedSteps.get().assertThatSetCompletedScreenIsNotPresent();
    }
}
