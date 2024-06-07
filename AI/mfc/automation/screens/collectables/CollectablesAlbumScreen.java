package com.myproject.automation.screens.collectables;

import com.myproject.automation.base.BaseScreen;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.drivers.websocket.SocketDriver;
import com.myproject.automation.elements.ws.WsButton;
import com.myproject.automation.elements.ws.WsImage;
import com.myproject.automation.elements.ws.WsText;
import com.myproject.automation.enums.Attribute;
import com.myproject.automation.enums.Orb;
import com.myproject.automation.enums.WebsocketComponentType;
import com.myproject.automation.enums.collectables.CollectablesItem;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.utils.ParseUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectablesAlbumScreen extends BaseScreen {

    private static final String LOC_ITEM_COMMON_PART = "CollectablesSetItem_View(Clone)";
    private static final String LOC_IMG_ITEM = LOC_ITEM_COMMON_PART + ">!_Required>!_CapsuleImage Image";
    private static final String LOC_BTN_ITEM = LOC_ITEM_COMMON_PART + " Button";
    private static final WsImage IMG_ITEM = new WsImage(LOC_IMG_ITEM, "Item Image");
    private static final String LOC_BTN_ITEM_PROGRESS = "ProgressPanel>Progress_Text";
    private static final String LOC_ORB_IMG = "CollectablesSetReward_RewardPack(Clone)>IconImage Image";
    private static final String LOC_IMG_COMPLETE_TICK = "CompletedTick Image";
    private final WsButton btnClose = new WsButton("CloseButton Button", "Close Button");
    private final WsButton btnSkip = new WsButton("SkipButton Button", "Skip Button");
    private final WsButton btnTrade = new WsButton("CollectablesTrade_Button Button", "Trade Button");
    private final WsImage imgCompleteTick = new WsImage(LOC_IMG_COMPLETE_TICK, "Complete checkmark Image");
    private final WsText txtLightningReward = new WsText("CollectablesSetReward_Lightning(Clone)>JackpotValue_Text TextMeshProUGUI", "Lightning Reward Text");
    private final WsText txtCoinReward = new WsText("CollectablesSetReward_Coins(Clone)>JackpotValue_Text TextMeshProUGUI", "Coin Reward Text");
    private final WsText txtSetReward = new WsText("SetReward_Text", "Set Reward Text");
    private final WsImage imgOrb = new WsImage(LOC_ORB_IMG, "Orb Reward Image");
    private final WsImage imgTradePoints = new WsImage("LLab_Button_Trade>Subcontainer Image", "Trade Points Image");
    private final WsButton btnInfo = new WsButton("InfoButton>InfoIcon Button", "Info Button");

    public CollectablesAlbumScreen() {
        super(IMG_ITEM, "Collectables album screen");
    }

    public String getProgressValue(int itemNumber) {
        List<WsText> progressValues = getProgressText();
        Collections.reverse(progressValues);
        return progressValues.get(itemNumber - 1).getText();
    }

    public boolean isCloseBtnPresent() {
        return btnClose.waitForIsPresent();
    }

    public void clickCloseBtn() {
        btnClose.click();
    }

    public void clickSkipBtn() {
        btnSkip.click();
    }

    public void clickSkipBtnIfPresent() {
        if (btnSkip.waitForIsPresent(DataHolder.getTimeout(Timeout.SHORT_ELEMENT_APPEARANCE_SECONDS))) {
            btnSkip.click();
        }
    }

    public void clickItem(int position) {
        getListItemsBtn().get(position - 1).click();
    }

    public List<WsImage> getListOfItemImages() {
        List<WsImage> listOfItemImages = SocketDriver.getElements(LOC_IMG_ITEM, WebsocketComponentType.UNITY_IMAGE).stream()
                .map(el -> new WsImage(el.getName(), el.getName()))
                .collect(Collectors.toList());
        Collections.reverse(listOfItemImages);
        return listOfItemImages;
    }

    public String getRewardText() {
        return txtSetReward.getText();
    }

    public double getTradePoints() {
        return ParseUtil.getDoubleValueWithComma(imgTradePoints.getText());
    }

    public boolean isItemNotPresent(CollectablesItem item) {
        return new WsImage(item.getLocator(), item.toString()).waitForDoesNotExist();
    }

    public boolean isCompleteTickPresent() {
        return imgCompleteTick.waitForIsPresent();
    }

    public boolean isCompleteTickNotPresent() {
        return imgCompleteTick.waitForDoesNotExist();
    }

    public boolean isLightningRewardPresent() {
        return txtLightningReward.waitForIsPresent();
    }

    public boolean isCoinRewardPresent() {
        return txtCoinReward.waitForIsPresent();
    }

    public boolean isOrbRewardPresent() {
        return imgOrb.waitForIsPresent();
    }

    public boolean isOrbRewardPresent(Orb orbType) {
        return getListOfOrbRewards().stream()
                .anyMatch(y -> y.getAttribute(Attribute.SPRITE).contains(orbType.getSprite()));
    }

    public boolean isItemInStatePresent(CollectablesItem item, String state) {
        return SocketDriver.getElements(item.getLocator(), WebsocketComponentType.UNITY_IMAGE)
                .stream()
                .map(el -> new WsImage(el.getName(), el.getName()))
                .anyMatch(element -> element.getAttribute(Attribute.SPRITE).contains(state));
    }

    public int getAmountOfCompleteTickImages() {
        return getListOfCompleteTickImages().size();
    }

    public void tapTick() {
        imgCompleteTick.tap();
    }

    private List<WsImage> getListOfCompleteTickImages() {
        List<WsImage> listOfCompleteTickImages = SocketDriver.getElements(LOC_IMG_COMPLETE_TICK, WebsocketComponentType.UNITY_IMAGE).stream()
                .map(el -> new WsImage(el.getName(), el.getName()))
                .collect(Collectors.toList());
        Collections.reverse(listOfCompleteTickImages);
        return listOfCompleteTickImages;
    }

    private List<WsImage> getListOfOrbRewards() {
        List<WsImage> listOfOrbRewards = SocketDriver.getElements(LOC_ORB_IMG, WebsocketComponentType.UNITY_IMAGE).stream()
                .map(el -> new WsImage(el.getName(), el.getName()))
                .collect(Collectors.toList());
        Collections.reverse(listOfOrbRewards);
        return listOfOrbRewards;
    }

    private List<WsText> getProgressText() {
        return SocketDriver.getElements(LOC_BTN_ITEM_PROGRESS, WebsocketComponentType.TMPRO_TEXT)
                .stream()
                .map(el -> new WsText(el.getName(), el.getName()))
                .collect(Collectors.toList());
    }

    private List<WsButton> getListItemsBtn() {
        List<WsButton> listItemsBtn = SocketDriver.getElements(LOC_BTN_ITEM, WebsocketComponentType.UNITY_BUTTON).stream()
                .map(el -> new WsButton(el.getName(), el.getName()))
                .collect(Collectors.toList());
        Collections.reverse(listItemsBtn);
        return listItemsBtn;
    }

    public void clickTrade() {
        btnTrade.click();
    }

    public boolean isInfoBtnPresent() {
        return btnInfo.waitForIsPresent();
    }

    public void clickInfoBtn() {
        btnInfo.click();
    }
}
