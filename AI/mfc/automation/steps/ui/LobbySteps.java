package com.myproject.automation.steps.ui;

import com.myproject.automation.base.BaseSteps;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.drivers.appium.AutomationDriver;
import com.myproject.automation.enums.SwipeDirection;
import com.myproject.automation.enums.SwipeType;
import com.myproject.automation.enums.config.TestProperty;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.exceptions.PMException;
import com.myproject.automation.models.pojo.Element;
import com.myproject.automation.screens.GameScreen;
import com.myproject.automation.screens.LobbyScreen;
import com.myproject.automation.screens.MissionsScreen;
import com.myproject.automation.screens.views.CollectablesWelcomeView;
import com.myproject.automation.screens.views.ExitConfirmationView;
import com.myproject.automation.screens.views.UserReachedMaxStackCountView;
import com.myproject.automation.utils.Logger;
import com.myproject.automation.utils.waiters.SmartWait;
import io.qameta.allure.Step;
import org.openqa.selenium.Point;

import java.util.List;

public class LobbySteps extends BaseSteps {

    private static ThreadLocal<BaseSteps> stepsInstanceHolder = new InheritableThreadLocal<>();

    public static synchronized LobbySteps get() {
        return (LobbySteps) get(stepsInstanceHolder, LobbySteps.class).get();
    }

    // action steps
    @Step("Clicking Deal")
    public void clickDeal() {
        new LobbyScreen().clickDeal();
    }

    @Step("Getting center of Deal button")
    public Point getCenterOfDealButton() {
        return new LobbyScreen().getCenterOfDealBtn();
    }

    @Step("Getting timer element name")
    public String getTimerElementName() {
        return new LobbyScreen().getTimerElementName();
    }

    @Step("Clicking Star level button")
    public void clickStarLevelButton() {
        new LobbyScreen().clickStarLevel();
    }

    @Step("Spam touches on Star Level button")
    public void spamTouchesOnStarLevelButton(int countOfSpamTouches) {
        new LobbyScreen().spamTapStarLevelButton(countOfSpamTouches);
    }

    @Step("Scrolling lobby end to end")
    public void scrollLobbyEndToEnd() {
        swipeLobbyByDirection(SwipeDirection.LEFT);
        swipeLobbyByDirection(SwipeDirection.RIGHT);
    }

    @Step("Opening 'Lightning Strikes'")
    public void openLightningStrikes() {
        new LobbyScreen().clickLightningStrikes();
    }

    @Step("Tapping Collectables icon")
    public void clickCollectablesIcon() {
        new LobbyScreen().clickCollectablesButton();
    }

    @Step("Tapping any available slot")
    public void tapAnyAvailableSlot() {
        new LobbyScreen().tapAnyAvailableSlot();
    }

    @Step("Tapping upgrade notification")
    public void tapUpgradeNotification() {
        new LobbyScreen().tapUpgradeNotification();
    }

    // asserting steps
    @Step("Asserting that lobby is opened")
    public void assertThatLobbyIsOpened() {
        AppSteps.get().waitForStableResponseFromWebsocket();
        if (!new CollectablesWelcomeView().isScreenNotPresent(DataHolder.getTimeout(Timeout.DEFAULT_ELEMENT_GET_SECONDS))) {
            try {
                new CollectablesWelcomeView().closePopUp();
            } catch (PMException ex) {
                Logger.get().info("Welcome View is not present");
            }
        }
        assertion.assertTrue(new LobbyScreen().isScreenPresent(DataHolder.getTimeout(Timeout.LONG_CONDITION_SECONDS)),
                "Lobby is not opened");
    }

    @Step("Asserting that exit confirmation popup is opened")
    public void assertThatExitConfirmationPopupIsOpened() {
        assertion.assertTrue(new ExitConfirmationView().isScreenPresent(), "Exit confirmation popup is not opened");
    }

    @Step("Asserting that exit confirmation popup is not opened")
    public void assertThatExitConfirmationPopupIsNotOpened() {
        assertion.assertTrue(new ExitConfirmationView().isScreenNotPresent(), "Exit confirmation popup is opened");
    }

    @Step("Asserting that lobby is not opened")
    public void assertThatLobbyIsNotOpened() {
        assertion.assertTrue(new LobbyScreen().isScreenNotPresent(), "Lobby is opened");
    }

    @Step("Waiting for the Lobby screen is present and assert that tutorial slot does not load")
    public void waitForLobbyScreenAndAssertThatTutorialSlotDoesNotLoad() {
        assertion.assertTrue(
                SmartWait.waitForTrue(y -> new LobbyScreen().isScreenPresent(DataHolder.getTimeout(Timeout.SHORT_ELEMENT_APPEARANCE_SECONDS))
                                || new GameScreen().isScreenPresent(DataHolder.getTimeout(Timeout.SHORT_ELEMENT_APPEARANCE_SECONDS)),
                        DataHolder.getTimeout(Timeout.DEFAULT_CONDITION_SECONDS)),
                "The application is not loaded");
        GameSteps.get().assertThatTutorialSlotIsNotOpened();
        assertThatLobbyIsOpened();
    }

    @Step("Asserting that Deal button is present")
    public void assertThatDealIsPresent() {
        assertion.assertTrue(new LobbyScreen().isDealPresent(), "Deal is not present");
    }

    @Step("Asserting that Deal button is not present")
    public void assertThatDealIsNotPresent() {
        assertion.assertTrue(new LobbyScreen().isDealNotPresent(), "Deal is present");
    }

    @Step("Asserting that Timer counter is present")
    public void assertThatTimerCounterIsPresent() {
        assertion.assertTrue(new LobbyScreen().isTimerCounterPresent(), "Timer counter is not present");
    }

    @Step("Asserting that Timer counter is not present")
    public void assertThatTimerCounterIsNotPresent() {
        assertion.assertTrue(new LobbyScreen().isTimerCounterNotPresent(), "Timer counter is present");
    }

    @Step("Asserting that timer is changed")
    public void assertThatTimerChanged(String nonExpectedTimerElementName) {
        assertion.assertNotEquals(getTimerElementName(), nonExpectedTimerElementName, "Timer element is not changed");
    }

    @Step("Asserting that 'User reached max stack count' view is present")
    public void assertThatUserReachedMaxStackCountViewIsPresent() {
        assertion.assertTrue(new UserReachedMaxStackCountView().isScreenPresent(), "'User reached max stack count' view is not present");
    }

    @Step("Asserting that 'User reached max stack count' text is equal to '{text}'")
    public void assertThatUserReachedMaxStackCountTextEquals(String text) {
        assertion.assertEquals(new UserReachedMaxStackCountView().getHeaderText(), text,
                "'User reached max stack count' text is not equal to expected");
    }

    @Step("Asserting that Max Stack Count View Message text is equal to '{text}'")
    public void assertThatMaxStackCountViewMessageTextEquals(String text) {
        assertion.assertEquals(new UserReachedMaxStackCountView().getMessageText(), text,
                "Max Stack Count View Message text is not equal to expected");
    }

    @Step("Asserting that objective '{objective}' does not exist with open/close Missions view")
    public void assertThatExpectedObjectiveIsNotExistWithOpenCloseMissionsView(String objective) {
        TopBarMenuSteps.get().tapMissions();
        MissionsSteps.get().assertThatMissionsIsOpened();
        assertion.assertTrue(new MissionsScreen().isObjectiveNotPresent(objective), "Expected objective is present");
        MissionsSteps.get().closeView();
    }

    @Step("Asserting that objective '{objective}' exist with open/close Missions view")
    public void assertThatExpectedObjectiveIsExistWithOpenCloseMissionsView(String objective) {
        TopBarMenuSteps.get().tapMissions();
        MissionsSteps.get().assertThatMissionsIsOpened();
        assertion.assertFalse(new MissionsScreen().isObjectiveNotPresent(objective), "Expected objective is not present");
        MissionsSteps.get().closeView();
    }

    @Step("Asserting that Current Star Level is equal to '{expectedLevel}' level")
    public void assertThatCurrentStarLevelIsEqualToExpected(String expectedLevel) {
        assertion.assertEquals(new LobbyScreen().getStarLevel(), expectedLevel, "Star Levels are not equal");
    }

    @Step("Asserting that Locked Star Level button is present")
    public void assertThatLockedStarLevelButtonIsPresent() {
        assertion.assertTrue(new LobbyScreen().isLockedStarLevelPresent(), "Locked Star Level button is not present");
    }

    @Step("Asserting that Locked Star Level button is not present")
    public void assertThatLockedStarLevelButtonIsNotPresent() {
        assertion.assertTrue(new LobbyScreen().isLockedStarLevelNotPresent(), "Locked Star Level button is present");
    }

    @Step("Asserting that Star Level button is present")
    public void assertThatStarLevelButtonIsPresent() {
        assertion.assertTrue(new LobbyScreen().isStarLevelPresent(), "Star Level button is not present");
    }

    @Step("Asserting that Lightning Strikes button is present")
    public void assertThatLightningStrikesButtonIsPresent() {
        assertion.assertTrue(new LobbyScreen().isLightningStrikesPresent(), "Lightning Strikes button is not present");
    }

    @Step("Asserting that Lightning Strikes Counter value is equal to '{expValue}' on the Lobby")
    public void assertThatLightningStrikesCounterValueIsEqualTo(int expValue) {
        assertion.assertEquals(new LobbyScreen().getLSRewardCounterValue(), expValue,
                String.format("Lightning Strikes Counter value is not equal to expected %d", expValue));
    }

    @Step("Asserting that Lightning Strikes Reward Counter is not present")
    public void assertThatLightningStrikesRewardCounterIsNotPresent() {
        assertion.assertTrue(new LobbyScreen().isLSRewardCounterNotPresent(), "Lightning Strikes Reward Counter is present");
    }

    @Step("Getting game tiles from lobby")
    private List<Element> getGameTilesFromLobby() {
        return new LobbyScreen().getTilesFromLobby();
    }

    @Step("Swiping lobby by direction {swipeDirection}")
    private void swipeLobbyByDirection(SwipeDirection swipeDirection) {
        List<Element> initialTiles = getGameTilesFromLobby();
        for (int i = 0; i < DataHolder.getSafeConvertedInteger(DataHolder.getTestProperty(TestProperty.SWIPE_COUNT)); i++) {
            AutomationDriver.swipeByDirection(swipeDirection, SwipeType.USING_LONG_PRESS);
            AppSteps.get().waitForStableResponseFromWebsocket();
            if (initialTiles.equals(getGameTilesFromLobby())) {
                break;
            }
        }
    }

    @Step("Asserting that cards quantity on Collectables button equals to '{cardsAmount}'")
    public void assertThatCardsQuantityOnCollectablesButtonEqualsTo(int cardsAmount) {
        assertion.assertEquals(new LobbyScreen().getCardsQuantity(), cardsAmount,
                "Cards quantity on Collectables button not equals to expected");
    }

    @Step("Asserting that Collectables icon is present in Lobby'")
    public void assertThatCollectablesIconIsPresent() {
        assertion.assertTrue(new LobbyScreen().isCollectablesButtonPresent(), "Collectables icon is not present in Lobby");
    }

    @Step("Asserting that Upgrade notification is present")
    public void assertThatUpgradeNotificationIsPresent() {
        assertion.assertTrue(new LobbyScreen().isUpgradeNotificationPresent(), "Upgrade notification is not present");
    }

    @Step("Asserting that Upgrade notification is not present")
    public void assertThatUpgradeNotificationIsNotPresent() {
        assertion.assertTrue(new LobbyScreen().isUpgradeNotificationNotPresent(), "Upgrade notification is present");
    }

    @Step("Asserting that Upgrade notification is dismissed after six seconds")
    public void assertThatUpgradeNotificationIsDismissedAfterSixSeconds() {
        assertion.assertTrue(new LobbyScreen().isUpgradeNotificationDismissedAfterSixSeconds(), "Upgrade notification is not dismissed after six seconds");
    }

    @Step("Asserting that 'Lightning Strikes' notification is present")
    public void assertThatLSNotificationIsPresent() {
        assertion.assertTrue(new LobbyScreen().isLsNotificationPresent(), "'Lightning Strikes' notification is not present");
    }

    @Step("Asserting that 'Lightning Strikes' notification is not present")
    public void assertThatLSNotificationIsNotPresent() {
        assertion.assertTrue(new LobbyScreen().isLsNotificationNotPresent(), "'Lightning Strikes' notification is present");
    }
}
