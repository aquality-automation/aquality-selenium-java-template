package com.project.automation.screens;

import com.project.automation.base.BaseScreen;
import com.project.automation.base.DataHolder;
import com.project.automation.drivers.appium.AutomationDriver;
import com.project.automation.drivers.websocket.SocketDriver;
import com.project.automation.elements.ws.WsButton;
import com.project.automation.elements.ws.WsImage;
import com.project.automation.elements.ws.WsText;
import com.project.automation.enums.WebsocketComponentType;
import com.project.automation.enums.config.Timeout;
import com.project.automation.models.pojo.Element;
import com.project.automation.utils.MeasuresUtils;
import com.project.automation.utils.PMRandomUtils;
import com.project.automation.utils.waiters.SmartWait;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;

import java.util.List;
import java.util.stream.Collectors;

public class LobbyScreen extends BaseScreen {

    private static final WsImage TXT_BONUS_READY_HEADER = new WsImage("GamesContainer>Viewport Image", "Lobby Games");
    private static final String TEMPLATE_GAME_SPRITE = "GameTiles_";
    private static final String TEMPLATE_GAME = "GameTile_";
    private final WsButton btnBonusReady = new WsButton("MiniGameButton Button", "Bonus Ready");
    private final WsText txtHoldAndSpinCount = new WsText("AvailablePlaysNotification>Image>Quantity TextMeshProUGUI", "Count of Hold and Spin");
    private final WsButton btnDeal = new WsButton("DealButton(Clone) Button", "Deal");
    private final WsText txtTimerCounter = new WsText("Timer_Container>Timer_Image>Timer_CounterText TextMeshProUGUI", "Timer counter");
    private final WsText txtStarLevel = new WsText("StarLevel TextMeshProUGUI", "Star Level text");
    private final WsButton btnStarLevel = new WsButton("StarLevelButton Button", "Star Level");
    private final WsImage imgLockedStarLevel = new WsImage("LockedStarLevelButton", "Locked Star Level");
    private final WsButton btnLS = new WsButton("rrStrikesLobbyView>LS_Button Button", "rr Strikes");
    private final WsText txtLSRewardCount = new WsText("RewardReady>RewardsCount TextMeshProUGUI", "LS Reward Count");
    private final WsImage imgCollectablesIcon = new WsImage("CollectablesButtonContainer Image", "Collectables icon");
    private final WsButton btnPlay = new WsButton("PlayButton>PlayButton Button", "Play button Container");
    private final WsText txtCardsQuantity = new WsText("ReadyToCollect>Image>Quantity TextMeshProUGUI", "Collectables cards quantity");
    private final WsButton btnUpgradeNotification = new WsButton("Component_Notification_Piglets_Reset(Clone) Button",
            "Upgrade notification");
    private final WsText txtLSNotification = new WsText("rrStrikesObjectiveNotificationView(Clone)>Content>Main_Container>Text>Body_Text TextMeshProUGUI", "rr Strikes notification");
    private final WsText txtUpgradePiggyNotificationDescriptions = new WsText("PiggyNotificationDescriptions_Text TextMeshProUGUI",
            "Upgrade Piggy notification descriptions");
    private final WsText txtUpgradePiggyNotificationHeader = new WsText("PiggyNotification_Text TextMeshProUGUI",
            "Upgrade Piggy notification header");

    public LobbyScreen() {
        super(TXT_BONUS_READY_HEADER, "Lobby screen", true);
    }

    public boolean isHoldAndSpinPresent() {
        return btnBonusReady.waitForIsPresent();
    }

    public boolean isHoldAndSpinNotPresent() {
        return btnBonusReady.waitForDoesNotExist(DataHolder.getTimeout(Timeout.LONG_CONDITION_SECONDS));
    }

    public void clickHoldAndSpin() {
        btnBonusReady.waitForIsEnabled();
        btnBonusReady.click();
    }

    public void tapHoldAndSpin() {
        btnBonusReady.tap();
    }

    public int getCountHoldAndSpin() {
        return Integer.parseInt(txtHoldAndSpinCount.getText());
    }

    public boolean waitForCountHoldAndSpinToChange(int getCountHoldAndSpin) {
        return SmartWait.waitForTrue(i -> getCountHoldAndSpin() == getCountHoldAndSpin);
    }

    public boolean isDealPresent() {
        return btnDeal.waitForIsPresent();
    }

    public boolean isDealNotPresent() {
        return btnDeal.waitForDoesNotExist();
    }

    public boolean isTimerCounterPresent() {
        return txtTimerCounter.waitForIsPresent();
    }

    public boolean isTimerCounterNotPresent() {
        return txtTimerCounter.waitForDoesNotExist();
    }

    public void clickDeal() {
        btnDeal.click();
    }

    public Point getCenterOfDealBtn() {
        return btnDeal.getCenter();
    }

    public String getTimerValue() {
        return txtTimerCounter.getText();
    }

    public boolean isStarLevelPresent() {
        return txtStarLevel.waitForIsPresent();
    }

    public boolean isLockedStarLevelPresent() {
        return imgLockedStarLevel.waitForIsPresent();
    }

    public boolean isLockedStarLevelNotPresent() {
        return imgLockedStarLevel.waitForDoesNotExist();
    }

    public String getStarLevel() {
        return txtStarLevel.getText();
    }

    public void tapLockedStarLevel() {
        imgLockedStarLevel.tap();
    }

    public void clickStarLevel() {
        btnStarLevel.click();
    }

    public void spamTapStarLevelButton(int countOfSpamTouches) {
        Rectangle rect = MeasuresUtils.truncateRectBorder(txtStarLevel.getRect(), 1);
        List<Point> points = PMRandomUtils.getListOfRandomPointsFromRect(rect, countOfSpamTouches);
        AutomationDriver.rapidTouchesWithTapDuration(points);
    }

    public List<Element> getTilesFromLobby() {
        List<Element> tilesPackage = SocketDriver.getElements(TEMPLATE_GAME, WebsocketComponentType.UNITY_IMAGE);
        return tilesPackage.stream().filter(el -> el.getSprite().contains(TEMPLATE_GAME_SPRITE))
                .collect(Collectors.toList());
    }

    public boolean isrrStrikesPresent() {
        return btnLS.waitForIsPresent();
    }

    public boolean isUpgradeNotificationPresent() {
        return btnUpgradeNotification.waitForIsPresent();
    }

    public boolean isUpgradeNotificationNotPresent() {
        return btnUpgradeNotification.waitForDoesNotExist();
    }

    public boolean isUpgradeNotificationDismissed(long timeout) {
        return btnUpgradeNotification.waitForDoesNotExist(timeout);
    }

    public void clickrrStrikes() {
        btnLS.click();
    }

    public boolean isLSRewardCounterNotPresent() {
        if (txtLSRewardCount.waitForDoesNotExist(DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS))) {
            return true;
        }
        return txtLSRewardCount.getText().contains("!");
    }

    public int getLSRewardCounterValue() {
        return Integer.parseInt(txtLSRewardCount.getText());
    }

    public void tapCollectablesIcon() {
        imgCollectablesIcon.tap();
    }

    public boolean isCollectablesIconPresent() {
        return imgCollectablesIcon.waitForIsPresent();
    }

    public int getCardsQuantity() {
        return Integer.parseInt(txtCardsQuantity.getText());
    }

    public boolean isLsNotificationPresent() {
        return txtLSNotification.waitForIsPresent();
    }

    public boolean isLsNotificationNotPresent() {
        return txtLSNotification.waitForDoesNotExist();
    }

    public void tapAnyAvailableSlot() {
        btnPlay.click();
    }

    public void clickUpgradeNotification() {
        btnUpgradeNotification.click();
    }

    public String getUpgradePiggyNotificationDescriptionsText() {
        return txtUpgradePiggyNotificationDescriptions.getText();
    }

    public String getUpgradePiggyNotificationHeaderText() {
        return txtUpgradePiggyNotificationHeader.getText();
    }
}
