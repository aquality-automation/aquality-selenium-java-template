package com.project.automation.steps.api;

import com.project.automation.api.clients.StarscapesClient;
import com.project.automation.api.clients.TournamentsClient;
import com.project.automation.api.clients.UserProfilesClient;
import com.project.automation.api.models.userprofile.CurrentSeason;
import com.project.automation.api.models.userprofile.UserProfileStatsResponse;
import com.project.automation.base.BaseSteps;
import com.project.automation.base.DataHolder;
import com.project.automation.commands.ApiClientGenerator;
import com.project.automation.commands.clients.*;
import com.project.automation.commands.gamebench.GbClient;
import com.project.automation.commands.models.*;
import com.project.automation.commands.models.catalogue.DeleteCatalogueRequest;
import com.project.automation.commands.models.catalogue.UpdateCatalogueRequest;
import com.project.automation.commands.models.collectables.*;
import com.project.automation.commands.models.collectables.getcollectables.GetCollectablesResponse;
import com.project.automation.commands.models.gamebench.metrics.*;
import com.project.automation.commands.models.gamebench.summary.Session;
import com.project.automation.commands.models.missions.MissionRequest;
import com.project.automation.commands.models.missions.ObjectiveRequest;
import com.project.automation.commands.models.piggybank.Instance;
import com.project.automation.commands.models.piggybank.PiggyBank;
import com.project.automation.commands.models.piggybank.PiggyBanksResponse;
import com.project.automation.commands.models.piggybank.UpdatePiggyInstanceRequest;
import com.project.automation.commands.models.uncollectedrewards.UncollectedReward;
import com.project.automation.commands.models.uncollectedrewards.UncollectedRewardsRequest;
import com.project.automation.commands.models.uncollectedrewards.UncollectedRewardsResponse;
import com.project.automation.constants.CommonTestConstants;
import com.project.automation.constants.MissionsConstants;
import com.project.automation.constants.StarscapesConstants;
import com.project.automation.enums.AllocatedItem;
import com.project.automation.enums.BonusType;
import com.project.automation.enums.FeatureCode;
import com.project.automation.enums.ManualBonusType;
import com.project.automation.enums.collectables.Boxes;
import com.project.automation.enums.config.GrafanaProperty;
import com.project.automation.enums.config.Token;
import com.project.automation.enums.config.Url;
import com.project.automation.enums.missions.*;
import com.project.automation.enums.pigletbank.PigletBankType;
import com.project.automation.exceptions.PMException;
import com.project.automation.models.ProfileSeasonStats;
import com.project.automation.models.application.User;
import com.project.automation.steps.ui.AppSteps;
import com.project.automation.steps.ui.LobbySteps;
import com.project.automation.steps.ui.UserSteps;
import io.qameta.allure.Step;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.automation.constants.CollectablesConstants.ALBUM_ID;

public class ApiSteps extends BaseSteps {

    private static final String ADMIN_TOKEN = DataHolder.getToken(Token.ADMIN);
    private static final int WAIT_FOR_API_STABLE_WORK = 2000;
    private static final GameAccountsClient GAME_ACCOUNTS_CLIENT = ApiClientGenerator.getClient(GameAccountsClient.class);
    private static final BonusesClient BONUSES_CLIENT = ApiClientGenerator.getClient(BonusesClient.class);
    private static final UserFlagsClient USER_FLAGS_CLIENT = ApiClientGenerator.getClient(UserFlagsClient.class);
    private static final PublishClient PUBLISH_CLIENT = ApiClientGenerator.getClient(PublishClient.class);
    private static final UserCollectablesClient USER_COLLECTABLES_CLIENT = ApiClientGenerator.getClient(UserCollectablesClient.class);
    private static final CollectablesClient COLLECTABLES_CLIENT = ApiClientGenerator.getClient(CollectablesClient.class);
    private static final CatalogueClient CATALOGUE_CLIENT = ApiClientGenerator.getClient(CatalogueClient.class);
    private static final rrStrikesClient rr_STIKES_CLIENT = ApiClientGenerator.getClient(rrStrikesClient.class);
    private static final PiggyBankClient PIGGY_BANK_CLIENT = ApiClientGenerator.getClient(PiggyBankClient.class);
    private static ThreadLocal<BaseSteps> stepsInstanceHolder = new InheritableThreadLocal<>();

    public static synchronized ApiSteps get() {
        return (ApiSteps) get(stepsInstanceHolder, ApiSteps.class).get();
    }

    @Step("Updating user information to following values: {modelToUpdate}")
    public void updateUser(User user, GameAccount modelToUpdate) {
        updateUser(user, modelToUpdate, true);
    }

    @Step("Updating Catalog of products to user")
    public void updateCatalogueClientForUser(List<Integer> catalogId) {
        AppSteps.get().waitForStableResponseFromWebsocket();
        CATALOGUE_CLIENT.updateCatalogForUser(UpdateCatalogueRequest.builder()
                .apiToken(ADMIN_TOKEN)
                .gameAccountId(Collections.singletonList(UserSteps.get().getCurrentGameAccountId()))
                .catalogId(catalogId).build());
        ApiSteps.get().publishCatalogChanges();
    }

    @Step("Deleting Catalog of products to user")
    public void deleteCatalogueClientForUser(List<Integer> catalogId) {
        CATALOGUE_CLIENT.deleteCatalogForUser(DeleteCatalogueRequest.builder()
                .apiToken(ADMIN_TOKEN)
                .gameAccountId(Collections.singletonList(UserSteps.get().getCurrentGameAccountId()))
                .catalogId(catalogId).build());
        ApiSteps.get().publishCatalogChanges();
    }

    @Step("Updating user information to following values: {modelToUpdate}")
    public void updateUser(User user, GameAccount modelToUpdate, boolean isNeedCollectablesSeen) {
        GAME_ACCOUNTS_CLIENT.updateGameAccount(String.valueOf(user.getId()), GameAccountsModel.builder()
                .gameAccount(modelToUpdate).build());
        if (isNeedCollectablesSeen) {
            ApiSteps.get().setCollectablesTutorialsSeen(user.getApiToken(), true);
        }
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Resetting bonus type: {bonusType} for user: {user}")
    public void resetBonus(User user, String bonusType) {
        GAME_ACCOUNTS_CLIENT.resetBonus(String.valueOf(user.getId()), bonusType, ADMIN_TOKEN);
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK, "Waiting for updating user");
    }

    @Step("Setting game account id to user tag: '{tagId}'")
    public void setGameIdToTag(User user, String tagId) {
        GAME_ACCOUNTS_CLIENT.assignTags(TagsRequest.builder()
                .gameAccountIds(user.getId())
                .tagIds(Long.parseLong(tagId)).build());
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Setting game tags to id")
    public void setGameIdToTags(User user, List<String> tagIds) {
        if (!tagIds.isEmpty()) {
            tagIds.forEach(tagId -> setGameIdToTag(user, tagId));
        }
    }

    @Step("Removing user: {user} from tag with id: {tagId}")
    public void removeUserFromTag(@NonNull User user, @NonNull String tagId) {
        GAME_ACCOUNTS_CLIENT.removeTags(TagsRequest.builder()
                .gameAccountIds(user.getId())
                .tagIds(Long.parseLong(tagId)).build());
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Accepting tutorial slot")
    public void acceptTutorial(@NonNull String userApiToken) {
        acceptTutorial(userApiToken, true);
    }

    @Step("Accepting tutorial slot")
    public void acceptTutorial(@NonNull String userApiToken, boolean isCollectablesSeen) {
        ApiClientGenerator.getClient(PlatformAccountsClient.class).acceptTutorial(userApiToken);
        ApiSteps.get().setCollectablesTutorialsSeen(userApiToken, isCollectablesSeen);
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Setting starscapes tutorials seen")
    public void setStarscapesTutorialsSeen(@NonNull String userApiToken, boolean isTutorialsSeen) {
        UserFlags flags = UserFlags.builder().starscapesTutorialMetagame(isTutorialsSeen).build();
        USER_FLAGS_CLIENT.setUserFlag(FlagsModel.builder().apiToken(userApiToken).flags(flags).build());
    }

    @Step("Setting rr Strikes tutorials seen")
    public void setrrStrikesTutorialsSeen(@NonNull String userApiToken, boolean isTutorialsSeen) {
        UserFlags flags = UserFlags.builder().rrStrikesSeen(isTutorialsSeen).rrStrikesIntroductionSeen(isTutorialsSeen).build();
        USER_FLAGS_CLIENT.setUserFlag(FlagsModel.builder().apiToken(userApiToken).flags(flags).build());
    }

    @Step("Setting Piggy bank introduction tutorial seen")
    public void setPiggyBankIntroductionTutorialsSeen(@NonNull String userApiToken, boolean isTutorialsSeen) {
        UserFlags flags = UserFlags.builder().piggyBankIntroductionSeen(isTutorialsSeen).build();
        USER_FLAGS_CLIENT.setUserFlag(FlagsModel.builder().apiToken(userApiToken).flags(flags).build());
    }

    @Step("Getting user current profile stats via API")
    public ProfileSeasonStats getUserProfileCurrentSeasonStats(@NonNull String userApiToken) {
        UserProfileStatsResponse response = ApiClientGenerator.getClient(UserProfilesClient.class).getUserProfileStatsInfo(userApiToken);
        CurrentSeason currentSeason = response.getStats().getCurrentSeason();
        return ProfileSeasonStats.builder()
                .rrs(currentSeason.getrr())
                .spins(currentSeason.getSpins())
                .jackpotWins(currentSeason.getJackpotWins())
                .rrBonusSpins(currentSeason.getrrBonusSpins())
                .tierUpgrades(currentSeason.getTierUpgrades())
                .build();
    }

    @Step("Creating manual bonus for user {user} type of {manualBonusType} with bonus amount {bonusAmount}")
    public void createManualBonus(User user, ManualBonusType manualBonusType, int bonusAmount) {
        ArrayList<BonusInfo> bonusInfos = new ArrayList<>();
        bonusInfos.add(BonusInfo.builder().accountId(user.getId()).bonusTypeId(manualBonusType.getId()).amount(bonusAmount).build());
        ApiClientGenerator.getClient(AdminUsersClient.class).createManualBonus(BonusDetails.builder()
                .bonusInfo(bonusInfos).build());
    }

    @Step("Collecting bonus with type: {bonusType}")
    public void collectBonus(@NonNull String userApiToken, BonusType... bonusType) {
        Arrays.stream(bonusType).forEach(bonus ->
                BONUSES_CLIENT.collectBonus(BonusTypes.builder()
                        .apiToken(userApiToken)
                        .bonusType(bonus.name().toLowerCase())
                        .build()));
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Collecting bonus type of {bonusType}")
    public void collectBonus(@NonNull String userApiToken, ManualBonusType bonusType) {
        BONUSES_CLIENT.collectBonus(BonusTypes.builder().apiToken(userApiToken)
                .bonusType(bonusType.name().toLowerCase()).build());
    }

    @Step("Setting Gdpr status for user with id '{userId}' to {ifConsentRequired}")
    public void setGdprStatusTo(long userId, boolean ifConsentRequired) {
        logStep(String.format("Setting Gdpr status for user with id '%s' to %s", userId, ifConsentRequired));
        GAME_ACCOUNTS_CLIENT.setGdprStatus(String.valueOf(userId), GdprStateRequest.builder().consentRequired(ifConsentRequired).build());
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Getting user platform account id")
    public long getPlatformAccountId(String userToken) {
        return ApiClientGenerator.getClient(GameUpdatesClient.class)
                .gameUpdates(GameUpdatesRequest.builder()
                        .apiToken(userToken)
                        .userInfo(true)
                        .build()).getUser().getPlatformAccountId();
    }

    @Step("Setting for user {user} stack count to: {stackCount}")
    public void setUserStackCount(User user, int stackCount) {
        ApiClientGenerator.getClient(com.project.automation.api.clients.GameAccountsClient.class)
                .setUserStackCount(user.getId(), stackCount, ADMIN_TOKEN);
    }

    @Step("Updating missions rules")
    public void updateMissionsRules(int setId, UpdateRulesRequest request) {
        ApiClientGenerator.getClient(MissionsClient.class).updateRules(setId, request);
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Publishing missions changes")
    public void publishMissionsChanges() {
        ApiClientGenerator.getClient(MissionsClient.class).publish(ADMIN_TOKEN);
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Locking constellation {constellationId} for user {user}")
    public void lockConstellation(User user, int constellationId) {
        ApiClientGenerator.getClient(StarscapesClient.class).lockConstellation(constellationId, (int) user.getId(), ADMIN_TOKEN);
    }

    @Step("Unlocking constellation {constellationId} for user {user}")
    public void unlockConstellation(User user, int constellationId) {
        ApiClientGenerator.getClient(StarscapesClient.class).unlockConstellation(constellationId, (int) user.getId(), ADMIN_TOKEN);
    }

    @Step("Unlocking planets")
    public void unlockPlanets(List<Integer> planetIds, User user) {
        ApiClientGenerator.getClient(StarscapesClient.class).unlockPlanet(planetIds, user.getApiToken());
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Adding user {user} to missions")
    public void addUserToMissions(User user, int setId) {
        GameAccountIdsForRequests accountIds = new GameAccountIdsForRequests();
        accountIds.addIdForAdding(user.getId());
        UpdateRulesRequest request = UpdateRulesRequest.builder()
                .gameAccountIds(accountIds)
                .build();
        ApiSteps.get().updateMissionsRules(setId, request);
        ApiSteps.get().publishMissionsChanges();
        ApiSteps.get().updateUser(user, GameAccount.builder().balance(CommonTestConstants.HIGH_BALANCE_TO_SET).build());
    }

    //assertion
    @Step("Asserting that rr Boost is rewarded")
    public void assertThatrrBoostIsRewarded(String userToken, AllocatedItem allocatedItem) {
        assertion.assertTrue(isrrBoostRewarded(userToken, allocatedItem), "rr boost is not rewarded");
    }

    @Step("Asserting that rr Boost is not rewarded")
    public void assertThatrrBoostIsNotRewarded(String userToken, AllocatedItem allocatedItem) {
        assertion.assertFalse(isrrBoostRewarded(userToken, allocatedItem), "rr boost is rewarded");
    }

    @Step("Cleaning up missions progress for set with id: {missionSetId}")
    public void cleanUpMissionsProgress(User user, int missionSetId) {
        switch (missionSetId) {
            case MissionsConstants.GENERAL_SET_ID:
                Arrays.stream(MissionsGeneralSet.values()).forEach(mission -> resetMission(user, mission.getMissionId()));
                break;
            case MissionsConstants.PERSISTENT_SET_ID:
                Arrays.stream(MissionsPersistentSet.values()).forEach(mission -> resetMission(user, mission.getMissionId()));
                break;
            case MissionsConstants.GLOBAL_MISSION_SET_ID:
                Arrays.stream(GlobalMissionsSet.values()).forEach(mission -> resetMission(user, mission.getMissionId()));
                break;
            case MissionsConstants.GLOBAL_GENERIC_MISSION_SET_ID:
                Arrays.stream(MissionsGlobalAndGeneralSet.values()).forEach(mission -> resetMission(user, mission.getMissionId()));
                break;
            case MissionsConstants.rr_STRIKES_MISSION_ID:
                Arrays.stream(MissionsrrStrikesSet.values()).forEach(mission -> resetMission(user, mission.getMissionId()));
                break;
            case MissionsConstants.PERSISTENT_SET_FOR_TUTORIAL_SLOT_ID:
                Arrays.stream(MissionsPersistentTutorialSet.values()).forEach(mission -> resetMission(user, mission.getMissionId()));
                break;
            case MissionsConstants.PORTRAIT_MISSION_SET_ID:
                Arrays.stream(MissonsPortraitSet.values()).forEach(mission -> resetMission(user, mission.getMissionId()));
                break;
            default:
                throw new PMException(String.format("Mission set - '%d' is not exist.", missionSetId));
        }
    }

    @Step("Completing mission with id: {missionId}")
    public void completeMission(User user, int missionId) {
        setMissionProgress(user, missionId, MissionAction.COMPLETE);
    }

    @Step("Setting progress to: {objectiveValue} for objective with id: {objectiveId}, mission id: {missionId}")
    public void setObjectiveProgress(User user, int missionId, int objectiveId, int objectiveValue) {
        ObjectiveRequest request = ObjectiveRequest.builder()
                .gameAccountId((int) user.getId())
                .missionId(missionId)
                .objectiveId(objectiveId)
                .objectiveValue(objectiveValue)
                .build();
        ApiClientGenerator.getClient(MissionsClient.class).setObjectiveProgress(request);
    }

    @Step("Add allocated item for user and enter lobby")
    public void addAllocatedItemForUserAndEnterLobby(User user, AllocatedItem item) {
        UserSteps.get().waitForGameAccountIsReceived();
        user.setId(UserSteps.get().getCurrentGameAccountId());
        user.setApiToken(UserSteps.get().getUserApiToken());
        addAllocatedItemForUser(user, item);
        AppSteps.get().relaunchApp();
        UserSteps.get().waitForGameAccountIsReceived();
        LobbySteps.get().assertThatLobbyIsOpened();
    }

    @Step("Add allocated item '{allocatedItem}' for user '{user}'")
    public Object addAllocatedItemForUser(User user, AllocatedItem allocatedItem) {
        return ApiClientGenerator.getClient(AccountItemsClient.class)
                .addAllocatedItem(AllocatedItemInfo.builder()
                        .allocatedItemsIds(Arrays.asList(allocatedItem.getId()))
                        .gameAccountId(user.getId())
                        .build());
    }

    @Step("Delete all allocated items for user '{user}'")
    public Object deleteAllAllocatedItems(User user) {
        return ApiClientGenerator.getClient(AccountItemsClient.class).deleteAllAllocatedItems(AllocatedItemInfo.builder()
                .gameAccountId(user.getId())
                .build());
    }

    @Step("Resetting progress for mission with id: {missionId}")
    private void resetMission(User user, int missionId) {
        setMissionProgress(user, missionId, MissionAction.RESET);
    }

    @Step("Collect all uncollected rewards")
    public void collectUncollectedRewards(User user) {
        UncollectedRewardsResponse response = ApiClientGenerator.getClient(UncollectedRewardsClient.class)
                .getUncollectedRewards(user.getApiToken());
        List<Long> uncollectedIds = response.getUncollectedRewards().stream().map(UncollectedReward::getUncollectedRewardId)
                .collect(Collectors.toList());
        ApiClientGenerator.getClient(UncollectedRewardsClient.class).collectRewards(user.getApiToken(),
                UncollectedRewardsRequest.builder().uncollectedRewardIds(uncollectedIds).build());
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK, "Waiting for collecting all uncollected rewards");
    }

    @Step("Add user to Tournament")
    public void addUserToTournament(User user, int tournamentId) {
        ApiClientGenerator.getClient(TournamentsClient.class).updateRules(ADMIN_TOKEN, user.getId(), tournamentId, "add");
        publishTournamentChanges();
    }

    @Step("Remove user from Tournament")
    public void removeUserFromTournament(User user, int tournamentId) {
        ApiClientGenerator.getClient(TournamentsClient.class).updateRules(ADMIN_TOKEN, user.getId(), tournamentId, "remove");
        publishTournamentChanges();
    }

    @Step("Publish Tournament changes")
    public void publishTournamentChanges() {
        PUBLISH_CLIENT.publishChanges(ADMIN_TOKEN, FeatureCode.TOURNAMENT_SETS.getCode());
    }

    @Step("Publish Catalog changes")
    public void publishCatalogChanges() {
        PUBLISH_CLIENT.publishChanges(ADMIN_TOKEN, FeatureCode.PRODUCT_CATALOGUES.getCode());
    }

    @Step("Setting collectables tutorials seen")
    public void setCollectablesTutorialsSeen(@NonNull String userApiToken, boolean isTutorialsSeen) {
        UserFlags flags = UserFlags.builder().collectablesAlbumTutorialSeen(isTutorialsSeen)
                .collectablesBoxesTutorialSeen(isTutorialsSeen)
                .collectablesTradeTutorialSeen(isTutorialsSeen)
                .collectablesSetTutorialSeen(isTutorialsSeen)
                .collectablesTrophyIntroTutorialSeen(isTutorialsSeen)
                .build();
        USER_FLAGS_CLIENT.setUserFlag(FlagsModel.builder().apiToken(userApiToken).flags(flags).build());
    }

    @Step("Adding box: '{box}' for user '{user}'")
    public void addBoxForUser(@NonNull User user, Boxes box) {
        COLLECTABLES_CLIENT.addBox(Box.builder().gameAccountId(user.getId())
                .boxId(box.getBoxId()).build());
    }

    @Step("Adding boxes: '{box}' for user '{user}'")
    public void addBoxesForUser(@NonNull User user, List<Boxes> boxes) {
        if (!boxes.isEmpty()) {
            boxes.forEach(box -> COLLECTABLES_CLIENT.addBox(Box.builder().gameAccountId(user.getId())
                    .boxId(box.getBoxId()).build()));
        }
    }

    @Step("Delete all items for user")
    public void deleteAllItemsForUser(User user) {
        USER_COLLECTABLES_CLIENT.deleteAllItems(DeleteItemsRequest.builder().albumId(ALBUM_ID)
                .gameAccountId(String.valueOf(user.getId())).build());
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Delete all Boxes for user")
    public void deleteAllBoxesForUser(User user) {
        COLLECTABLES_CLIENT.deleteAllBoxes(ADMIN_TOKEN, String.valueOf(user.getId()));
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Setting for user '{user}' duplicate balance to '{duplicateBalance}'")
    public void setUserDuplicateBalanceTo(User user, int duplicateBalance) {
        COLLECTABLES_CLIENT.addUserAlbumStatus(UserAlbumStatuses.builder()
                .gameAccountId(user.getId()).albumId(ALBUM_ID)
                .userAlbumStatus(UserAlbumStatus.builder().duplicateBalance(duplicateBalance).build()).build());
    }

    @Step("Open box '{box}' for user")
    public void openBoxForUser(@NonNull String userApiToken, Boxes box) {
        USER_COLLECTABLES_CLIENT.openBox(userApiToken, ALBUM_ID, box.getTierId());
    }

    @Step("Mark items from box '{box}' as shown in album")
    public void markItemsAsShown(@NonNull String userApiToken, Boxes box) {
        UpdateItemsRequest request = UpdateItemsRequest.builder()
                .apiToken(userApiToken)
                .albumId(box.getAlbumId())
                .itemIds(box.getItemIds())
                .build();
        USER_COLLECTABLES_CLIENT.updateItems(request);
    }

    private void setMissionProgress(User user, int missionId, MissionAction action) {
        MissionRequest request = MissionRequest.builder()
                .gameAccountId((int) user.getId())
                .missionId(missionId)
                .missionAction(action.getAction())
                .build();
        ApiClientGenerator.getClient(MissionsClient.class).setMissionProgress(request);
    }

    @Step("Getting is rr Boost rewarded for allocated item '{allocatedItem}'")
    private boolean isrrBoostRewarded(@NonNull String userToken, AllocatedItem allocatedItem) {
        return ApiClientGenerator.getClient(GameUpdatesClient.class)
                .gameUpdates(GameUpdatesRequest.builder()
                        .apiToken(userToken)
                        .allocatedItems(true)
                        .build()).getAllocatedItems().stream().anyMatch(
                        bonus -> bonus.getAllocatedItemId().equals(allocatedItem.getId()));
    }

    @Step("Get collectables information for user")
    public GetCollectablesResponse getCollectablesInformationForUser(@NonNull String userApiToken) {
        return USER_COLLECTABLES_CLIENT.getCollectables(userApiToken);
    }

    @Step("Get collectables points for user for item {item}")
    public String getCollectablesProgressForUser(String userApiToken, int item, int level) {
        GetCollectablesResponse response = getCollectablesInformationForUser(userApiToken);
        return String.format("%s/%s", getCurrentPointsForItem(response, item), getRequiredPointsForItem(response, item, level));
    }

    @Step("Get duplicate balance for user")
    public int getUserDuplicateBalance(String userApiToken) {
        GetCollectablesResponse response = getCollectablesInformationForUser(userApiToken);
        return response.getUserAlbumsStatus().get(0).getDuplicateBalance();
    }

    @Step("Setting Ab Testing config for '{user}' to '{abTestingConfig}'")
    public void setAbTestingConfigForUser(User user, AbTestingConfig abTestingConfig) {
        GAME_ACCOUNTS_CLIENT.addAbTestingConfig(String.valueOf(user.getId()), abTestingConfig);
    }

    @Step("Adding user {user} to rr Strikes")
    public void addUserTorrStrikes(int setId, User user) {
        GameAccountIdsForRequests accountIds = new GameAccountIdsForRequests();
        accountIds.addIdForAdding(user.getId());
        UpdateRulesRequest request = UpdateRulesRequest.builder()
                .gameAccountIds(accountIds)
                .build();
        rr_STIKES_CLIENT.updaterrStrikesRules(setId, request);
    }

    @Step("Updating Piglet Bank instance id for piglet bank type '{type}' with value '{balance}'")
    public void updatePigletBankInstanceWithBalance(User user, PigletBankType type, double balance) {
        Long pbInstanceId = getPigletBankInstanceId(user, type);
        UpdatePiggyInstanceRequest request = UpdatePiggyInstanceRequest.builder().apiToken(ADMIN_TOKEN).instance(
                Instance.builder().balance(balance).build()).build();
        PIGGY_BANK_CLIENT.updatePiggyBankInstance(pbInstanceId, request);
    }

    @Step("Remove user {user} from rr Strikes")
    public void removeUserFromrrStrikes(int setId, User user) {
        GameAccountIdsForRequests accountIds = new GameAccountIdsForRequests();
        accountIds.addIdForRemoving(user.getId());
        UpdateRulesRequest request = UpdateRulesRequest.builder()
                .gameAccountIds(accountIds)
                .build();
        rr_STIKES_CLIENT.updaterrStrikesRules(setId, request);
        AppSteps.get().sleep(WAIT_FOR_API_STABLE_WORK);
    }

    @Step("Setting Starscapes welcoming pop up seen ")
    public void setStarscapesWelcomingPopUpSeen(User user, boolean isSeen) {
        setUserFlag(user.getApiToken(), UserFlags.builder().starscapesNonlinearOnboardingSeen(isSeen).build());
    }

    @Step("Setting Starscapes welcoming bonus awarded and tutorial seen ")
    public void setStarscapesWelcomingBonusAwardedAndTutorialSeen(User user, boolean isSeen) {
        setUserFlag(user.getApiToken(), UserFlags.builder().starscapesNonlinearOnboardingRewardsAwarded(isSeen).build());
    }

    //In order to enable Starscapes we need to set A/B test Asset Bundle Resource to CND variant
    @Step("Setting Starscapes to '{variantConfig}'")
    public void setStarscapes(User user, String variantConfig) {
        setAbTestingConfigForUser(user, AbTestingConfig.builder().experimentConfigId(StarscapesConstants.STARSCAPES_AB_TEST_ID)
                .variantConfigId(variantConfig)
                .build());
    }

    @Step("Setting Asset Bundle Resource to '{variantConfig}'")
    public void setAssetBundleResource(User user, String variantConfig) {
        setAbTestingConfigForUser(user, AbTestingConfig.builder().experimentConfigId(StarscapesConstants.ASSET_BUNDLE_SOURCE_AB_TEST_ID)
                .variantConfigId(variantConfig)
                .build());
    }

    @Step("Getting Piglet Bank instance id")
    private Long getPigletBankInstanceId(User user, PigletBankType type) {
        PiggyBanksResponse response = PIGGY_BANK_CLIENT.getPiggyBanks(user.getApiToken());
        List <PiggyBank> piggyBanks = response.getPiggyBanks().stream()
                .filter(entity -> entity.getName().contains(type.getType())).collect(Collectors.toList());
        return piggyBanks.get(0).getId();
    }

    @Step("Getting session Summary from GameBench dashboard")
    public Session getSessionSummaryFromGameBenchDashboard(String sessionId) {
        return ApiClientGenerator.getClient(GbClient.class, Url.GB_DASHBOARD,
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_USERNAME),
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_TOKEN))
                .getSessionSummary(sessionId);
    }

    @Step("Getting session FPS metrics from GameBench dashboard")
    public Fps getSessionFpsMetricsFromGameBenchDashboard(String sessionId) {
        return ApiClientGenerator.getClient(GbClient.class, Url.GB_DASHBOARD,
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_USERNAME),
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_TOKEN))
                .getSessionFpsMetrics(sessionId);
    }

    @Step("Getting session CPU metrics from GameBench dashboard")
    public Cpu getSessionCpuMetricsFromGameBenchDashboard(String sessionId) {
        return ApiClientGenerator.getClient(GbClient.class, Url.GB_DASHBOARD,
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_USERNAME),
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_TOKEN))
                .getSessionCpuMetrics(sessionId);
    }

    @Step("Getting session Network metrics from GameBench dashboard")
    public Network getSessionNetworkMetricsFromGameBenchDashboard(String sessionId) {
        return ApiClientGenerator.getClient(GbClient.class, Url.GB_DASHBOARD,
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_USERNAME),
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_TOKEN))
                .getSessionNetworkMetrics(sessionId);
    }

    @Step("Getting session Memory metrics from GameBench dashboard")
    public Memory getSessionMemoryMetricsFromGameBenchDashboard(String sessionId) {
        return ApiClientGenerator.getClient(GbClient.class, Url.GB_DASHBOARD,
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_USERNAME),
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_TOKEN))
                .getSessionMemoryMetrics(sessionId);
    }

    @Step("Getting session GPU metrics from GameBench dashboard")
    public Gpu getSessionGpuMetricsFromGameBenchDashboard(String sessionId) {
        return ApiClientGenerator.getClient(GbClient.class, Url.GB_DASHBOARD,
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_USERNAME),
                        DataHolder.getGrafanaProperty(GrafanaProperty.GAMEBENCH_DASHBOARD_API_TOKEN))
                .getSessionGpuMetrics(sessionId);
    }

    private void setUserFlag(@NonNull String userApiToken, UserFlags flags) {
        USER_FLAGS_CLIENT.setUserFlag(FlagsModel.builder().apiToken(userApiToken).flags(flags).build());
    }

    private int getRequiredPointsForItem(GetCollectablesResponse response, int item, int level) {
        int itemId = getCollectableItem(response, item).getId();
        return response
                .getSeason()
                .getAlbums()
                .get(0)
                .getSets()
                .stream()
                .filter(x -> x.getItems().stream().anyMatch(y -> y.getId() == itemId))
                .findFirst()
                .get()
                .getItems()
                .stream()
                .filter(x -> x.getId() == itemId)
                .findFirst()
                .get()
                .getLevels()
                .get(level)
                .points;
    }

    private int getCurrentPointsForItem(GetCollectablesResponse response, int item) {
        return getCollectableItem(response, item).getCollectedPoints();
    }

    private CollectableItem getCollectableItem(GetCollectablesResponse response, int item) {
        return response
                .getUserAlbumsStatus()
                .get(0)
                .getCollectableItems()
                .stream()
                .filter(x -> x.getStatus().equals("Collecting"))
                .collect(Collectors.toList())
                .get(item);
    }
}
