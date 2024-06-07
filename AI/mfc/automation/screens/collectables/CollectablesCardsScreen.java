package com.myproject.automation.screens.collectables;

import com.myproject.automation.base.BaseScreen;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.constants.CommonTestConstants;
import com.myproject.automation.constants.RegexExpressions;
import com.myproject.automation.drivers.websocket.SocketDriver;
import com.myproject.automation.elements.ws.WsButton;
import com.myproject.automation.elements.ws.WsImage;
import com.myproject.automation.elements.ws.WsText;
import com.myproject.automation.enums.Attribute;
import com.myproject.automation.enums.WebsocketComponentType;
import com.myproject.automation.enums.collectables.CardsFrontImageState;
import com.myproject.automation.enums.collectables.CollectablesItem;
import com.myproject.automation.enums.collectables.ItemsStateOnTopCardsScreen;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.models.pojo.Element;
import com.myproject.automation.utils.ParseUtil;
import com.myproject.automation.utils.RegexUtils;
import com.myproject.automation.utils.waiters.SmartWait;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectablesCardsScreen extends BaseScreen {

    private static final String LOC_CARD_BTN = "CollectablesSetItem_View_Reveal(Clone) Button";
    private static final String LOC_POINTS = "Footer_Container>ProgressPanel>Progress_Text";
    private static final String LOC_COLLECTED_POINTS = "TradePoints_UpgradeText";
    private static final String LOC_CARDS_FRONT_IMG = "Front Image";
    private static final String LOC_ITEM_ON_TOP_OF_SCREEN_IMG = "CollectablesCardTopBar(Clone)>Container>AssetContainer";
    private static final WsText TXT_TRADE = new WsText("CollectablesCardsRevealView(Clone)>Body>RevealsFlow>TradeContainer>TradeText TextMeshProUGUI",
            "Trade Text");
    private final WsButton btnSkipAll = new WsButton("RevealAllButton Button", "Skip all Button");
    private final WsButton btnNext = new WsButton("NextButton Button", "Next Button");
    private final WsText txtCardsCount = new WsText("CardsCount", "Cards count Text");
    private final WsButton btnContinue = new WsButton("ContinueButton Button", "Continue Button");
    private final WsText txtCardsCounter = new WsText("CardsCount", "Cards counter Text");
    private final WsImage imgItemAsset = new WsImage("AssetContainer", "Item asset Image");
    private final WsText txtTopBarCollectablesPoints = new WsText("TradePoints_RegularText TextMeshProUGUI",
            "Top bar Collectables Card points Text");
    private final WsText txtBottomBarTradePoints = new WsText("TradePointsContainer>TradePointsCount_Text",
            "Bottom bar trade points Text");
    private final WsText txtTradePoints = new WsText("TradePoints_Text TextMeshProUGUI", "Trade points Text");

    public CollectablesCardsScreen() {
        super(TXT_TRADE, "Collectables cards screen");
    }

    public void openCard(int cardNumber) {
        getListCardsBtn().get(cardNumber - 1).click();
    }

    public void openAllCards() {
        getListCardsBtn().forEach(WsButton::click);
    }

    public boolean isNextBtnPresent() {
        return btnNext.waitForIsPresent();
    }

    public boolean isContinueBtnPresent() {
        return btnContinue.waitForIsPresent();
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public boolean isSkipAllBtnPresent(long timeout) {
        return btnSkipAll.waitForIsPresent(timeout);
    }

    public boolean isCardsCounterNotPresent() {
        return txtCardsCounter.waitForDoesNotExist();
    }

    public int getNumberOfUnopenedCards() {
        return getListOfUnopenedCardsBtn().size();
    }

    public int getCountOfOpenedCards() {
        return Integer.parseInt(RegexUtils.getMatch(RegexExpressions.COUNT_OF_CARDS_OR_POINTS, txtCardsCount.getText(), 1));
    }

    public int getNumberOfOpenedCards() {
        return (int) getCards().stream()
                .filter(element -> !element.getInteractable())
                .map(el -> new WsButton(el.getName(), el.getName())).count();
    }

    public int getTotalNumberOfCards() {
        return getNumberOfUnopenedCards() + getNumberOfOpenedCards();
    }

    public int getTotalNumberOfTopbarAssets() {
        return getListOfTopbarAssets().size();
    }

    public void skipAll() {
        btnSkipAll.click();
    }

    public void tapAroundSkipAllButton() {
        btnSkipAll.tapAroundElement(CommonTestConstants.COUNT_OF_TOUCHES_AROUND_ELEMENT);
    }

    public void spamSkipAll(int countOfTouches) {
        btnSkipAll.spamElement(countOfTouches, 1);
    }

    public int getCountOfUnopenedCards() {
        return Integer.parseInt(RegexUtils.getMatch(RegexExpressions.COUNT_OF_CARDS_OR_POINTS, txtCardsCount.getText(), 2));
    }

    public void clickNext() {
        btnNext.click();
    }

    public void spamNext(int spamCount) {
        btnNext.spamElement(spamCount, 1);
    }

    public boolean isOpenedItemAssetPresent() {
        return imgItemAsset.waitForIsPresent();
    }

    public boolean isOpenedPointsAmountPresent() {
        return getListOfCardPoints().size() == 1;
    }

    public boolean isOpenedPointsAmountNotPresent() {
        return getListOfCardPoints().size() == 0;
    }

    public double getTotalPointsAmount() {
        return Double.parseDouble(txtTopBarCollectablesPoints.getText());
    }

    public double getBottomBarTradePointsAmount() {
        return ParseUtil.getDoubleValueWithComma(txtBottomBarTradePoints.getText());
    }

    public double getCardPointsAmount(int cardNumber) {
        return Double.parseDouble(getListOfCardPoints().get(cardNumber - 1).getText());
    }

    public boolean isTopBarCollectablesPointsPresent() {
        return txtTopBarCollectablesPoints.waitForIsPresent();
    }

    public void tapAroundNext() {
        btnNext.tapAroundElement(CommonTestConstants.COUNT_OF_TOUCHES_AROUND_ELEMENT);
    }

    public boolean isTopBarCollectablesPointsNotPresent() {
        return txtTopBarCollectablesPoints.waitForDoesNotExist();
    }

    public int getCountOfCollectedPoints() {
        return SocketDriver.getElements(LOC_COLLECTED_POINTS, WebsocketComponentType.TMPRO_TEXT)
                .stream()
                .map(el -> Integer.parseInt(new WsText(el.getName(), el.getName()).getText()))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean isCardPresent(CardsFrontImageState state) {
        return getListOfCardsFrontImages().stream()
                .anyMatch(y -> y.getAttribute(Attribute.SPRITE).contains(state.getSpriteAttr()));
    }

    public boolean isItemPresentOnTopOfScreen(ItemsStateOnTopCardsScreen state) {
        return SocketDriver.getElements(LOC_ITEM_ON_TOP_OF_SCREEN_IMG, WebsocketComponentType.UNITY_IMAGE).stream()
                .map(el -> new WsImage(el.getName(), el.getName()).getAttribute(Attribute.SPRITE))
                .anyMatch(y -> y.contains(state.getSpriteAttr()));
    }

    public String getOpenedItemSprite(CollectablesItem item) {
        return new WsImage(item.getLocator(), item.toString()).getAttribute(Attribute.SPRITE);
    }

    public boolean areTradePointsNotPresent(int tradePoints) {
        // locator is always present with default value +350000
        return !txtTradePoints.getText().equals("+" + tradePoints);
    }

    private List<WsText> getListOfCardPoints() {
        Supplier<Stream<WsText>> cardPointText = () -> SocketDriver.getElements(LOC_POINTS, WebsocketComponentType.TMPRO_TEXT)
                .stream()
                .filter(element -> element != null && StringUtils.isNotEmpty(element.getText()))
                .map(el -> new WsText(el.getName(), el.getName()));
        SmartWait.waitForTrue(i -> cardPointText.get().findAny().isPresent());
        return cardPointText.get().collect(Collectors.toList());
    }

    private List<WsButton> getListCardsBtn() {
        return getCards().stream()
                .map(el -> new WsButton(el.getName(), el.getName()))
                .collect(Collectors.toList());
    }

    private List<WsButton> getListOfUnopenedCardsBtn() {
        return getCards().stream()
                .filter(Element::getInteractable)
                .map(el -> new WsButton(el.getName(), el.getName()))
                .collect(Collectors.toList());
    }

    private List<Element> getCards() {
        SmartWait.waitForTrue(i -> !SocketDriver.getElements(LOC_CARD_BTN, WebsocketComponentType.UNITY_BUTTON).isEmpty(),
                DataHolder.getTimeout(Timeout.SHORT_ELEMENT_APPEARANCE_SECONDS));
        return SocketDriver.getElements(LOC_CARD_BTN, WebsocketComponentType.UNITY_BUTTON);
    }

    private List<WsText> getListOfTopbarAssets() {
        return SocketDriver.getElements(LOC_COLLECTED_POINTS, WebsocketComponentType.TMPRO_TEXT).stream()
                .filter(element -> element != null && StringUtils.isNotEmpty(element.getText()))
                .map(el -> new WsText(el.getName(), el.getName()))
                .collect(Collectors.toList());
    }

    private List<WsImage> getListOfCardsFrontImages() {
        return SocketDriver.getElements(LOC_CARDS_FRONT_IMG, WebsocketComponentType.UNITY_IMAGE).stream()
                .map(el -> new WsImage(el.getName(), el.getName()))
                .collect(Collectors.toList());
    }
}
