package com.myproject.automation.screens;

import com.myproject.automation.base.BaseScreen;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.drivers.appium.AutomationDriver;
import com.myproject.automation.drivers.websocket.SocketDriver;
import com.myproject.automation.elements.ws.WsButton;
import com.myproject.automation.elements.ws.WsImage;
import com.myproject.automation.elements.ws.WsText;
import com.myproject.automation.enums.Attribute;
import com.myproject.automation.enums.LocatorAttribute;
import com.myproject.automation.enums.WebsocketComponentType;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.models.pojo.Element;
import com.myproject.automation.utils.MeasuresUtils;
import com.myproject.automation.utils.PMRandomUtils;
import com.myproject.automation.utils.waiters.AwaitilityWrapper;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;

import java.util.List;
import java.util.stream.Collectors;

import static com.myproject.automation.constants.PigletBankConstants.NOTIFICATION_DISMISS_TIME_SECONDS;

public class LobbyScreen extends BaseScreen {

    private static final WsImage TXT_BONUS_READY_HEADER = new WsImage("GamesContainer>Viewport Image", "Lobby Games Image");
    private static final String TEMPLATE_GAME_SPRITE = "GameTiles_";
    private static final String TEMPLATE_GAME = "GameTile_";
    private final WsButton btnBonusReady = new WsButton("MiniGameButton Button", "Bonus Ready Button");
    private final WsText txtHoldAndSpinCount = new WsText("AvailablePlaysNotification>Image>Quantity TextMeshProUGUI", "Count of Hold and Spin Text");
    private final WsButton btnDeal = new WsButton("DealButton(Clone) Button", "Deal Button");
    private final WsText txtTimerCounter = new WsText("Timer_Container>Timer_Image>Timer_CounterText TextMeshProUGUI", "Timer counter Text");
    private final WsImage imgTimer = new WsImage("DB_TimerBg", "Timer Image", LocatorAttribute.SPRITE);
    private final WsText txtStarLevel = new WsText("StarLevel TextMeshProUGUI", "Star Level Text");
    private final WsButton btnStarLevel = new WsButton("StarLevelButton Button", "Star Level Button");
    private final WsImage imgLockedStarLevel = new WsImage("LockedStarLevelButton", "Locked Star Level Image");
    private final WsButton btnLS = new WsButton("LightningStrikesLobbyView>LS_Button Button", "Lightning Strikes Button");
    private final WsText txtLSRewardCount = new WsText("RewardReady>RewardsCount TextMeshProUGUI", "LS Reward Count Text");
    private final WsButton btnCollectablesButton = new WsButton("CollectablesButtonContainer Button", "Collectables Button");
    private final WsImage imgLogoBig = new WsImage("LogoBig Image", "Big Logo Container Image");
    private final WsText txtCardsQuantity = new WsText("ReadyToCollect>Image>Quantity TextMeshProUGUI", "Collectables cards quantity Text");
    private final WsText txtUpgradeNotification = new WsText("Component_Notification_Piglets_Reset(Clone)>Mask>BalancePanel>PiggyNotificationHeader",
            "Upgrade notification Text");
    private final WsText txtLSNotification = new WsText("LightningStrikesObjectiveNotificationView(Clone)>Content>Main_Container>Text>Body_Text TextMeshProUGUI", "Lightning Strikes notification Text");
    private final WsText txtPiggyFullNotificationDescriptions = new WsText("PiggyNotificationDescriptions_Text",
            "Piggy notification descriptions Text");

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
        return AwaitilityWrapper.waitForCondition(() -> getCountHoldAndSpin() == getCountHoldAndSpin,
                DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS));
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

    public String getTimerElementName() {
        return imgTimer.getAttribute(Attribute.NAME);
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

    public boolean isLightningStrikesPresent() {
        return btnLS.waitForIsPresent();
    }

    public boolean isUpgradeNotificationPresent() {
        return txtUpgradeNotification.waitForIsPresent();
    }

    public boolean isUpgradeNotificationNotPresent() {
        return txtUpgradeNotification.waitForDoesNotExist(DataHolder.getTimeout(Timeout.SHORT_ELEMENT_APPEARANCE_SECONDS));
    }

    public boolean isUpgradeNotificationDismissedAfterSixSeconds() {
        return txtUpgradeNotification.waitForDoesNotExist(NOTIFICATION_DISMISS_TIME_SECONDS);
    }

    public void clickLightningStrikes() {
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

    public void clickCollectablesButton() {
        btnCollectablesButton.click();
    }

    public boolean isCollectablesButtonPresent() {
        return btnCollectablesButton.waitForIsPresent();
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
        imgLogoBig.tap();
    }

    public void tapUpgradeNotification() {
        txtUpgradeNotification.tap();
    }

    public String getPiggyFullNotificationDescriptionsText() {
        return txtPiggyFullNotificationDescriptions.getText();
    }
}
