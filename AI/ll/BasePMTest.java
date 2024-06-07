package com.project.automation.base;

import com.project.automation.commands.models.AbTestingConfig;
import com.project.automation.commands.models.GameAccount;
import com.project.automation.commands.models.GameAccountIdsForRequests;
import com.project.automation.commands.models.UpdateRulesRequest;
import com.project.automation.constants.CommonTestConstants;
import com.project.automation.constants.MissionsConstants;
import com.project.automation.constants.PigletBankConstants;
import com.project.automation.enums.*;
import com.project.automation.enums.config.TestProperty;
import com.project.automation.enums.pigletbank.PigletBankType;
import com.project.automation.steps.api.ApiSteps;
import com.project.automation.steps.debug.DebugConsoleSteps;
import com.project.automation.steps.ui.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.automation.constants.CollectablesConstants.DEFAULT_DUPLICATE_BALANCE;
import static com.project.automation.constants.CommonTestConstants.DEFAULT_USER_STACK_COUNT;
import static com.project.automation.constants.PigletBankConstants.*;

public class BasePMTest extends BaseTest implements ICollectablesTest {

    protected static final String DEFAULT_GEMS_AMOUNT = "10000";
    protected static final String DEFAULT_rr_1_TIER = "1000";
    protected static final String DEFAULT_BALANCE = "900000000";
    public List<Integer> constellationIds = Arrays.stream(Constellation.values()).map(Constellation::getId).collect(Collectors.toList());
    protected List<String> tagIds = new ArrayList<>();
    protected String tagId;
    protected List<Integer> catalogIds = new ArrayList<>();
    protected int missionSetId;
    protected int tournamentId;

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_missions")
    public void addUserToMissions() {
        this.user = UserSteps.get().getUserInformation();
        this.missionSetId = MissionsConstants.GENERAL_SET_ID;
        AppSteps.get().resetGdpr();
        ApiSteps.get().acceptTutorial(user.getApiToken());
        ApiSteps.get().updateUser(this.user, GameAccount.builder().balance(DEFAULT_BALANCE).rr(DEFAULT_rr_1_TIER).build());
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.GENERAL_SET_ID);
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_global_generic_missions")
    public void addUserToGlobalGenericMissions() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.GLOBAL_GENERIC_MISSION_SET_ID);
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
        this.missionSetId = MissionsConstants.GLOBAL_GENERIC_MISSION_SET_ID;
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_global_missions")
    public void addUserToGlobalMissions() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.GLOBAL_MISSION_SET_ID);
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
        this.missionSetId = MissionsConstants.GLOBAL_MISSION_SET_ID;
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_persistent_missions")
    public void addUserToPersistentMissions() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.PERSISTENT_SET_ID);
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
        this.missionSetId = MissionsConstants.PERSISTENT_SET_ID;
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_persistent_missions_with_one_obj")
    public void addUserToPersistentMissionsWithOneObj() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.PERSISTENT_WITH_ONE_OBJ_SET_ID);
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), true);
        this.missionSetId = MissionsConstants.PERSISTENT_WITH_ONE_OBJ_SET_ID;
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_persistent_missions_with_bronze_reward")
    public void addUserToPersistentMissionsWithBronzeReward() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.PERSISTENT_MISSION_WITH_BRONZE_REWARD);
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
        this.missionSetId = MissionsConstants.PERSISTENT_MISSION_WITH_BRONZE_REWARD;
        ApiSteps.get().publishMissionsChanges();
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_persistent_missions_with_orbs")
    public void addUserToPersistentMissionsWithOrbs() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.PERSISTENT_MISSION_WITH_ORBS_ID);
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
        this.missionSetId = MissionsConstants.PERSISTENT_MISSION_WITH_ORBS_ID;
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_persistent_missions_v3")
    public void addUserToPersistentMissionsV3() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.PERSISTENT_MISSION_V3);
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
        this.missionSetId = MissionsConstants.PERSISTENT_MISSION_V3;
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_persistent_missions_v4")
    public void addUserToPersistentMissionsV4() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.PERSISTENT_MISSION_V4);
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
        this.missionSetId = MissionsConstants.PERSISTENT_MISSION_V4;
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_portrait_missions")
    public void addUserToPortraitMissions() {
        this.user = UserSteps.get().getUserInformation();
        this.missionSetId = MissionsConstants.PORTRAIT_MISSION_SET_ID;
        ApiSteps.get().updateUser(this.user, GameAccount.builder().balance(DEFAULT_BALANCE).rr(DEFAULT_rr_1_TIER).build());
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.PORTRAIT_MISSION_SET_ID);
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), true);
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_rr_strikes")
    public void addUserTorrStrikesMissions() {
        UserSteps.get().waitForGameAccountIsReceived();
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().addUserTorrStrikes(MissionsConstants.rr_STRIKES_SET_ID, this.user);
        ApiSteps.get().addUserToMissions(this.user, MissionsConstants.rr_STRIKES_MISSION_ID);
        this.missionSetId = MissionsConstants.rr_STRIKES_MISSION_ID;
        ApiSteps.get().setrrStrikesTutorialsSeen(user.getApiToken(), true);
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), true);
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_piggybank")
    public void initialSetupForPiggyBank() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_starscapes")
    public void updateUserForStarscapes() {
        UserSteps.get().waitForGameAccountIsReceived();
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().acceptTutorial(UserSteps.get().getUserApiToken());
        ApiSteps.get().updateUser(this.user, GameAccount.builder()
                .gems(DEFAULT_GEMS_AMOUNT)
                .seasonIdOverride(Seasons.CURRENT_SEASON.getId())
                .seasonInfoPopupSeen(true)
                .build());
        ApiSteps.get().setStarscapesTutorialsSeen(user.getApiToken(), true);
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_starscapes_tutorial")
    public void resetAndUpdateUserForStarscapes() {
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopup();
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().updateUser(this.user, GameAccount.builder().gems(DEFAULT_GEMS_AMOUNT).build());
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_stackcount")
    public void setUser() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), true);
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = {"base_ftue_tutorial", "base_ftue_tutorial_pigletbank"})
    public void resetUserAndAcceptGdpr() {
        UserSteps.get().resetUser();
        AppSteps.get().removeApp(DataHolder.getTestProperty(TestProperty.APP_PACKAGE_NAME));
        AppSteps.get().launchApp();
        this.user = UserSteps.get().getUserInformation();
        AppSteps.get().resetGdpr();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), true);
        AppSteps.get().relaunchApp();
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), true);
        GameSteps.get().assertThatGameScreenIsOpened();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = {"base_tournament"})
    public void updateUserTier() {
        resetUserAndAcceptAllPopups();
        ApiSteps.get().updateUser(this.user, GameAccount.builder().rrTierId(Tier.FIFTH.id()).build());
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_collectables")
    public void setCollectablesTutorialSeen() {
        UserSteps.get().waitForGameAccountIsReceived();
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
        //we need to collect orb, because we have after reseting user receives orb
        ApiSteps.get().collectUncollectedRewards(this.user);
        removeUserBoxes();
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_pigletbank")
    public void enablePigletBank() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), true);
        ApiSteps.get().updateUser(this.user, GameAccount.builder().balance(DEFAULT_BALANCE).build());
        AppSteps.get().resetGdpr();
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
        AppSteps.get().relaunchApp();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_reset_user_before")
    public void beforeResetUserAndAcceptAllPopups() {
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopup();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_tapjoy")
    public void enableTapjoy() {
        //TODO: enable 'Tapjoy' widget for user (LL-8944)
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "beforeMethod", onlyForGroups = "base_urw")
    public void enableURW() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setCollectablesTutorialsSeen(this.user.getApiToken(), true);
        ApiSteps.get().setAbTestingConfigForUser(this.user, AbTestingConfig.builder()
                .experimentConfigId(SPIN_TO_WIN_VS_BET_UP_MECHANIC_CONFIG_ID)
                .variantConfigId(SPIN_TO_WIN_VS_BET_UP_MECHANIC_ENABLE_ID).build());
        AppSteps.get().relaunchApp();
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = {"base_missions",
            "base_global_missions", "base_global_generic_missions", "base_portrait_missions", "base_rr_strikes"})
    public void cleanupMissionsProgress() {
        ApiSteps.get().cleanUpMissionsProgress(this.user, missionSetId);
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = {"cleanupMissionsProgress", "tryToSaveScreenshotAndVideo"}, onlyForGroups = {"base_missions",
            "base_global_missions", "base_global_generic_missions", "base_portrait_missions", "base_rr_strikes"})
    public void removeUserFromMissions() {
        removeUserFromMission();
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = {
            "base_persistent_missions",
            "base_persistent_missions_with_one_obj",
            "base_persistent_missions_with_orbs",
            "base_persistent_missions_with_bronze_reward",
            "base_persistent_missions_v3",
            "base_persistent_missions_v4"})
    public void cleanupUserAfterPersistentMissions() {
        removeUserFromMission();
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopup();
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_crm")
    public void removeUserFromTag() {
        if (tagIds.isEmpty()) {
            ApiSteps.get().removeUserFromTag(this.user, tagId);
        } else {
            tagIds.forEach(tagId -> ApiSteps.get().removeUserFromTag(this.user, tagId));
        }
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = {"tryToSaveScreenshotAndVideo", "removeUserFromTag"}, onlyForGroups = "base_payment")
    public void checkConfirmationPopupAndCloseIfPresent(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            PaymentsSteps.get().tryToCloseConfirmationPopupIfPresent();
        }
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = {"tryToSaveScreenshotAndVideo", "removeUserFromTag", "checkConfirmationPopupAndCloseIfPresent"},
            onlyForGroups = "base_bundlecatalog")
    public void checkConfirmationViewAndCloseIfPresent(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            PaymentsSteps.get().tryToCloseCongratulationsPopupIfPresent();
        }
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_gdpr")
    public void resetGdprAndAcceptTutorial() {
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().setGdprStatusTo(this.user.getId(), false);
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_manualbonus")
    public void collectManualOtherBonusType() {
        ApiSteps.get().collectBonus(this.user.getApiToken(), ManualBonusType.MANUAL_OTHER_BONUS);
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = {"base_tutorial", "base_ftue_tutorial", "base_ftue_tutorial_pigletbank"})
    public void acceptTutorial() {
        ApiSteps.get().acceptTutorial(this.user.getApiToken());
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_resettier")
    public void resetTier() {
        ApiSteps.get().updateUser(this.user, GameAccount.builder().rrTierId(Tier.FIRST.id()).build());
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = {"tryToSaveScreenshotAndVideo"}, onlyForGroups = "base_feature")
    public void resetUser(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            resetUserAndSubmitGdprWithTutorialAndSeasonsPopup();
        }
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_resetuser")
    public void resetUserAndAcceptAllPopups() {
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopup();
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_starscapes")
    public void lockConstellationPlanetsForUser() {
        //this step is not supported until we will update to the new non-linear starscapes
        //constellationIds.forEach(id -> ApiSteps.get().lockConstellation(this.user, id));
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopup();
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_stackcount")
    public void setDefaultUserStackCount() {
        ApiSteps.get().resetBonus(this.user, BonusType.rr_BONUS.getType());
        ApiSteps.get().setUserStackCount(this.user, DEFAULT_USER_STACK_COUNT);
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_starscapes_tutorial")
    public void setStarscapesTutorialSeen() {
        ApiSteps.get().setStarscapesTutorialsSeen(user.getApiToken(), true);
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_allocated_item")
    public void deleteAllAllocatedItems() {
        ApiSteps.get().deleteAllAllocatedItems(user);
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_collect_rewards")
    public void collectAllUncollectedRewards() {
        ApiSteps.get().collectUncollectedRewards(user);
        ApiSteps.get().resetBonus(this.user, BonusType.rr_BONUS.getType());
        ApiSteps.get().setUserStackCount(this.user, DEFAULT_USER_STACK_COUNT);
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_rr_strikes")
    public void removeUserFromrrStrikes() {
        removeUserFromMissions();
        ApiSteps.get().removeUserFromrrStrikes(MissionsConstants.rr_STRIKES_SET_ID, this.user);
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = {"base_tournament"})
    public void removeUserFromTournament() {
        ApiSteps.get().removeUserFromTournament(this.user, tournamentId);
        ApiSteps.get().updateUser(this.user, GameAccount.builder().rrTierId(Tier.FIRST.id()).build());
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_collectables")
    public void removeUserBoxes() {
        ApiSteps.get().deleteAllItemsForUser(user);
        ApiSteps.get().deleteAllBoxesForUser(user);
        ApiSteps.get().setUserDuplicateBalanceTo(user, DEFAULT_DUPLICATE_BALANCE);
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_pigletbank")
    public void resetPigletBankToDefault() {
        resetPigletBanksBalancesToDefault();
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_balance_to_set")
    public void setUserBalanceToDefault() {
        ApiSteps.get().updateUser(this.user, GameAccount.builder().balance(CommonTestConstants.HIGH_BALANCE_TO_SET).build());
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_urw")
    public void disableURW() {
        ApiSteps.get().setAbTestingConfigForUser(user, AbTestingConfig.builder()
                .experimentConfigId(SPIN_TO_WIN_VS_BET_UP_MECHANIC_CONFIG_ID)
                .variantConfigId(SPIN_TO_WIN_VS_BET_UP_MECHANIC_DISABLE_ID).build());
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_catalogue")
    public void deleteCatalogForUser() {
        ApiSteps.get().deleteCatalogueClientForUser(this.catalogIds);
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = "tryToSaveScreenshotAndVideo", onlyForGroups = "base_tapjoy")
    public void disableTapjoy() {
        //TODO: disable 'Tapjoy' widget for user (LL-8944)
    }

    protected void resetUserAndSubmitGdprWithTutorialAndSeasonsPopup() {
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopup(true);
    }

    protected void resetUserAndSubmitGdprWithTutorialAndSeasonsPopupWithoutCollectingRewards() {
        resetUserAndSubmitGdprWithTutorialAndSeasonsPopup(false);
    }

    protected void triggerrrPiggyIsFullNotification(String gameTitle) {
        ApiSteps.get().updatePigletBankInstanceWithBalance(user, PigletBankType.rr, PigletBankConstants.ALMOST_FULL_PIGGY_rr_BALANCE_TO_SET);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        DebugConsoleSteps.get().loadGameUsingDebugConsole(Game.getGameByTitle(gameTitle));
        GameSteps.get().assertThatGameScreenIsOpened();
        GameSteps.get().spinSlotWithoutWaitingForFinish();
        TopBarMenuSteps.get().assertThatPiggyFullNotificationIsPresent();
    }

    protected void triggerCoinPiggyIsFullNotification(String gameTitle) {
        ApiSteps.get().updatePigletBankInstanceWithBalance(user, PigletBankType.COINS, PigletBankConstants.ALMOST_FULL_PIGGY_COINS_BALANCE_TO_SET);
        AppSteps.get().relaunchApp();
        LobbySteps.get().assertThatLobbyIsOpened();
        DebugConsoleSteps.get().loadGameUsingDebugConsole(Game.getGameByTitle(gameTitle));
        GameSteps.get().assertThatGameScreenIsOpened();
        GameSteps.get().spinSlotWithoutWaitingForFinish();
        TopBarMenuSteps.get().assertThatPiggyFullNotificationIsPresent();
    }

    protected void resetPigletBanksBalancesToDefault() {
        ApiSteps.get().updatePigletBankInstanceWithBalance(user, PigletBankType.COINS, DEFAULT_COIN_PIGLET_BALANCE);
        ApiSteps.get().updatePigletBankInstanceWithBalance(user, PigletBankType.STELLAR, DEFAULT_STELLAR_PIGLET_BALANCE);
        ApiSteps.get().updatePigletBankInstanceWithBalance(user, PigletBankType.rr, DEFAULT_rr_PIGLET_BALANCE);
    }

    private void resetUserAndSubmitGdprWithTutorialAndSeasonsPopup(boolean withCollectingUncollectedRewards) {
        UserSteps.get().resetUser();
        AppSteps.get().relaunchApp();
        AppSteps.get().resetGdpr();
        this.user = UserSteps.get().getUserInformation();
        ApiSteps.get().acceptTutorial(UserSteps.get().getUserApiToken());
        ApiSteps.get().updateUser(this.user, GameAccount.builder().seasonInfoPopupSeen(true).build());
        if (withCollectingUncollectedRewards) {
            //we need to collect orb, because after resetting command user receives orb
            ApiSteps.get().collectUncollectedRewards(this.user);
        }
        AppSteps.get().relaunchApp();
    }

    private void removeUserFromMission() {
        GameAccountIdsForRequests accountIds = new GameAccountIdsForRequests();
        accountIds.addIdForRemoving(this.user.getId());
        ApiSteps.get().updateMissionsRules(missionSetId, UpdateRulesRequest.builder().gameAccountIds(accountIds).build());
        ApiSteps.get().publishMissionsChanges();
    }
}
