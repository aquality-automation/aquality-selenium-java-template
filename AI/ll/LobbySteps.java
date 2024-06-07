package com.project.automation.steps.ui;

import com.project.automation.base.BaseSteps;
import com.project.automation.base.DataHolder;
import com.project.automation.constants.PigletBankConstants;
import com.project.automation.drivers.appium.AutomationDriver;
import com.project.automation.enums.SwipeDirection;
import com.project.automation.enums.SwipeType;
import com.project.automation.enums.config.TestProperty;
import com.project.automation.enums.config.Timeout;
import com.project.automation.exceptions.PMException;
import com.project.automation.models.pojo.Element;
import com.project.automation.screens.GameScreen;
import com.project.automation.screens.LobbyScreen;
import com.project.automation.screens.MissionsScreen;
import com.project.automation.screens.views.CollectablesWelcomeView;
import com.project.automation.screens.views.ExitConfirmationView;
import com.project.automation.screens.views.UserReachedMaxStackCountView;
import com.project.automation.utils.Logger;
import com.project.automation.utils.waiters.SmartWait;
import io.qameta.allure.Step;
import org.openqa.selenium.Point;

import java.util.List;

public class LobbySteps extends BaseSteps {

    private static final int SWIPE_COUNT = DataHolder.getSafeConvertedInteger(DataHolder.getTestProperty(TestProperty.SWIPE_COUNT));
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

    @Step("Getting timer value")
    public String getTimerValue() {
        return new LobbyScreen().getTimerValue();
    }

    @Step("Clicking Star level button")
    public void clickStarLevelButton() {
        AppSteps.get().waitForStableResponseFromWebsocket();
        new LobbyScreen().clickStarLevel();
    }

    @Step("Spam touches on Star Level button")
    public void spamTouchesOnStarLevelButton(int countOfSpamTouches) {
        new LobbyScreen().spamTapStarLevelButton(countOfSpamTouches);
    }

    @Step("Scrolling lobby end to end")
    public void scrollLobbyEndToEnd() {
        List<Element> initialTiles = getGameTilesFromLobby();
        for (int i = 0; i < SWIPE_COUNT; i++) {
            List<Element> tiles = getGameTilesFromLobby();
            AutomationDriver.swipeByDirection(SwipeDirection.LEFT, SwipeType.USING_LONG_PRESS);
            if (tiles.equals(getGameTilesFromLobby())) {
                break;
            }
        }
        for (int i = 0; i < SWIPE_COUNT; i++) {
            AutomationDriver.swipeByDirection(SwipeDirection.RIGHT, SwipeType.USING_LONG_PRESS);
            if (initialTiles.equals(getGameTilesFromLobby())) {
                break;
            }
        }
    }

    @Step("Opening 'rr Strikes'")
    public void openrrStrikes() {
        new LobbyScreen().clickrrStrikes();
    }

    @Step("Tapping Collectables icon")
    public void tapCollectablesIcon() {
        new LobbyScreen().tapCollectablesIcon();
    }

    @Step("Tapping any available slot")
    public void tapAnyAvailableSlot() {
        new LobbyScreen().tapAnyAvailableSlot();
    }

    @Step("Tapping upgrade notification")
    public void tapUpgradeNotification() {
        new LobbyScreen().clickUpgradeNotification();
    }

    @Step("Opening Starscapes")
    public void openStarscapes() {
        new LobbyScreen().clickStarscapesBtn();
    }

    // asserting steps
    @Step("Asserting that lobby is opened")
    public void assertThatLobbyIsOpened() {
        AppSteps.get().waitForStableResponseFromWebsocket();
        assertion.assertTrue(new LobbyScreen().isScreenPresent(DataHolder.getTimeout(Timeout.LONG_CONDITION_SECONDS)),
                "Lobby is not opened");
        if (!new CollectablesWelcomeView().isScreenNotPresent(DataHolder.getTimeout(Timeout.DEFAULT_ELEMENT_GET_SECONDS))) {
            try {
                new CollectablesWelcomeView().closePopUp();
            } catch (PMException ex) {
                Logger.get().info("Welcome View is not present");
            }
        }
    }

    @Step("Asserting that lobby is opened")
    public void assertThatLobbyIsOpenedWithoutStableResponse() {
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
    public void assertThatTimerChanged(String nonExpectedTimerValue) {
        assertion.assertNotEquals(getTimerValue(), nonExpectedTimerValue, "Timer element is not changed");
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

    @Step("Asserting that rr Strikes button is present")
    public void assertThatrrStrikesButtonIsPresent() {
        assertion.assertTrue(new LobbyScreen().isrrStrikesPresent(), "rr Strikes button is not present");
    }

    @Step("Asserting that rr Strikes Counter value is equal to '{expValue}' on the Lobby")
    public void assertThatrrStrikesCounterValueIsEqualTo(int expValue) {
        assertion.assertEquals(new LobbyScreen().getLSRewardCounterValue(), expValue,
                String.format("rr Strikes Counter value is not equal to expected %d", expValue));
    }

    @Step("Asserting that rr Strikes Reward Counter is not present")
    public void assertThatrrStrikesRewardCounterIsNotPresent() {
        assertion.assertTrue(new LobbyScreen().isLSRewardCounterNotPresent(), "rr Strikes Reward Counter is present");
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
        assertion.assertTrue(new LobbyScreen().isCollectablesIconPresent(), "Collectables icon is not present in Lobby");
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
        assertion.assertTrue(new LobbyScreen().isUpgradeNotificationDismissed(PigletBankConstants.NOTIFICATION_DISMISS_TIME_SECONDS +
                PigletBankConstants.NOTIFICATION_DISMISS_WEBSOCKET_DELAY_TIMEOUT), "Upgrade notification is not dismissed after six seconds");
    }

    @Step("Asserting that 'rr Strikes' notification is present")
    public void assertThatLSNotificationIsPresent() {
        assertion.assertTrue(new LobbyScreen().isLsNotificationPresent(), "'rr Strikes' notification is not present");
    }

    @Step("Asserting that 'rr Strikes' notification is not present")
    public void assertThatLSNotificationIsNotPresent() {
        assertion.assertTrue(new LobbyScreen().isLsNotificationNotPresent(), "'rr Strikes' notification is present");
    }

    @Step("Asserting Starscapes button is present")
    public void assertStarscapesButtonIsPresent() {
        new LobbyScreen().isStarscapesBtnPresent();
    }
}
