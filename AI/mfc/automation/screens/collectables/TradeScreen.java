package com.myproject.automation.screens.collectables;

import com.myproject.automation.base.BaseScreen;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.constants.CommonTestConstants;
import com.myproject.automation.drivers.websocket.SocketDriver;
import com.myproject.automation.elements.ws.WsButton;
import com.myproject.automation.elements.ws.WsImage;
import com.myproject.automation.elements.ws.WsText;
import com.myproject.automation.enums.WebsocketComponentType;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.exceptions.PMException;
import com.myproject.automation.models.pojo.Element;
import com.myproject.automation.utils.ParseUtil;
import com.myproject.automation.utils.waiters.SmartWait;

import java.util.List;
import java.util.stream.Collectors;

import static com.myproject.automation.constants.CollectablesConstants.MINUTE;
import static com.myproject.automation.constants.CollectablesConstants.SECOND;

public class TradeScreen extends BaseScreen {

    private static final String LOC_TRADE_PACK_IMG = "TradePackItem Image";
    private static final String LOC_UNAVAILABLE_TRADE_PRICES_TXT = "TradeButton_Default>TradePointsCount_Text TextMeshProUGUI";
    private static final String LOC_AVAILABLE_TRADE_PRICE_TXT = "TradeButtonText_Text TextMeshProUGUI";
    private static final WsText TXT_HEADER = new WsText("CollectablesTradeView(Clone)>Background>BGCanvas0>Header>Header_Text", "Header Text");
    private final WsButton btnClose = new WsButton("CollectablesTradeView(Clone)>Background>BGCanvas0>Header>ButtonsBar>CloseButton", "Close Button");
    private final WsButton btnTradePrice = new WsButton("TradeButton_Enabled Button", "Trade Price Button");
    private final WsButton btnInfo = new WsButton("InfoButton Button", "Info Button");
    private final WsImage imgDisabledTradePrice = new WsImage("TradeButton_Disabled>Bg_Image Image", "Disabled trade Image");
    private final WsImage imgProgressBar = new WsImage("ProgressBarContainer>ProgressBar", "Progress Bar Image");
    private final WsImage imgHighlight = new WsImage("TradePackTileContainer_0>TradePackTile>Milestone", "Highlight Image");
    private final WsText txtTradePoints = new WsText("TradePointsCount_Text", "Trade Points Text");
    private final WsText txtTimer = new WsText("TradeCooldownText_Text TextMeshProUGUI", "Timer Text");
    private final WsText txtTradeAmount = new WsText("TradeAmountText_Text TextMeshProUGUI", "Trade amount Text");

    public TradeScreen() {
        super(TXT_HEADER, "Trade screen");
    }

    public void clickClose() {
        btnClose.click();
    }

    public void clickTradePriceButton() {
        if (isTradePriceButtonEnabled()) {
            btnTradePrice.click();
        } else {
            throw new PMException("Trade button is not enabled");
        }
    }

    public void spamTradePriceButton(int countOfTouches) {
        btnTradePrice.spamElement(countOfTouches, 1);
    }

    public boolean isTradePriceButtonPresent() {
        return btnTradePrice.waitForIsPresent();
    }

    public boolean isProgressionMeterEmpty() {
        return imgProgressBar.getRect().x + imgProgressBar.getRect().width < imgHighlight.getRect().x;
    }

    public boolean isProgressionMeterHigherThanFirstPack() {
        return imgProgressBar.getRect().x + imgProgressBar.getRect().width > imgHighlight.getRect().x;
    }

    public boolean isTradePriceButtonEnabled() {
        return btnTradePrice.waitForIsEnabled();
    }

    public boolean isCooldownButtonPresent() {
        String text = btnTradePrice.getText();
        return text.contains(SECOND) || text.contains(MINUTE);
    }

    public boolean isDisabledTradePriceNumber() {
        return isTextIsNumber(imgDisabledTradePrice.getText());
    }

    public boolean isTradePriceNumber(int index) {
        return isTextIsNumber(getTradePricesAsText().get(index));
    }

    public boolean isDisabledTradeButtonPresent() {
        return imgDisabledTradePrice.waitForIsPresent();
    }

    public boolean isProgressMeterPresent() {
        return imgProgressBar.waitForIsPresent();
    }

    public int getAmountOfTradePacks() {
        return getTradePacksImages().size();
    }

    public int getAmountOfTradePrices() {
        return Integer.sum(getUnavailableTradePrices().size(), getAvailableTradePrices().size());
    }

    public double getTradePoints() {
        return ParseUtil.getDoubleValueWithComma(txtTradePoints.getText());
    }

    public String getTradeHeaderText() {
        return TXT_HEADER.getText();
    }

    public int getDisabledTradePrice() {
        return ParseUtil.getIntValueWithComma(imgDisabledTradePrice.getText());
    }

    public boolean isCooldownTimerNotPresent() {
        return txtTimer.waitForDoesNotExist();
    }

    public List<Integer> getTradePricesForEachPack() {
        return SocketDriver.getElements(LOC_UNAVAILABLE_TRADE_PRICES_TXT)
                .stream()
                .map(el -> ParseUtil.getIntValueWithComma(el.getText()))
                .sorted().collect(Collectors.toList());
    }

    public List<String> getTradePricesAsText() {
        return SocketDriver.getElements(LOC_UNAVAILABLE_TRADE_PRICES_TXT)
                .stream()
                .map(Element::getText)
                .collect(Collectors.toList());
    }

    public void tapAreaOfTradePriceBtn() {
        btnTradePrice.tapAroundElement(CommonTestConstants.COUNT_OF_TOUCHES_AROUND_ELEMENT);
    }

    public boolean isTimerPresent() {
        return txtTimer.waitForIsPresent();
    }

    public boolean isTimerMatchesPattern(String pattern) {
        return SmartWait.waitForTrue(x -> txtTimer.getText().matches(pattern),
                DataHolder.getTimeout(Timeout.DEFAULT_CONDITION_SECONDS));
    }

    public boolean isTimerPresentUnderEligibleCard(int numberOfCard) {
        boolean isBeforeNextCard, isUnderCard, isAfterPreviousCard = true;
        WsImage nextCardOfEligibleCard = getTradePacksImages().get(numberOfCard + 1);
        WsImage eligibleCard = getTradePacksImages().get(numberOfCard + 1);
        isBeforeNextCard = nextCardOfEligibleCard.getRect().getX() > txtTimer.getRect().getX();
        isUnderCard = eligibleCard.getRect().getY() < txtTimer.getRect().getY();
        if (numberOfCard != 0) {
            WsImage previousCardOfEligibleCard = getTradePacksImages().get(numberOfCard - 1);
            isAfterPreviousCard = previousCardOfEligibleCard.getRect().getX() + previousCardOfEligibleCard.getRect().getWidth() < txtTimer.getRect().getX();
        }
        return isBeforeNextCard && isAfterPreviousCard && isUnderCard;
    }

    public double getTradeAmount() {
        return Double.parseDouble(txtTradeAmount.getText());
    }

    public void spamInfoBtn(int spamCount) {
        btnInfo.spamElement(spamCount, 1);
    }

    public void clickInfo() {
        btnInfo.click();
    }

    private List<WsImage> getTradePacksImages() {
        return SocketDriver.getElements(LOC_TRADE_PACK_IMG, WebsocketComponentType.UNITY_IMAGE).stream()
                .map(el -> new WsImage(el.getName(), el.getName()))
                .collect(Collectors.toList());
    }

    private List<WsText> getUnavailableTradePrices() {
        return SocketDriver.getElements(LOC_UNAVAILABLE_TRADE_PRICES_TXT, WebsocketComponentType.TMPRO_TEXT)
                .stream()
                .map(el -> new WsText(el.getName(), el.getName()))
                .collect(Collectors.toList());
    }

    private List<WsText> getAvailableTradePrices() {
        return SocketDriver.getElements(LOC_AVAILABLE_TRADE_PRICE_TXT, WebsocketComponentType.TMPRO_TEXT)
                .stream()
                .map(el -> new WsText(el.getName(), el.getName()))
                .collect(Collectors.toList());
    }

    private boolean isTextIsNumber(String text) {
        try {
            ParseUtil.getIntValueWithComma(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
