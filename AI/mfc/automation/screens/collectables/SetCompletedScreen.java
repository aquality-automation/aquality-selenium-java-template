package com.myproject.automation.screens.collectables;

import com.myproject.automation.base.BaseScreen;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.constants.CommonTestConstants;
import com.myproject.automation.drivers.websocket.SocketDriver;
import com.myproject.automation.elements.ws.WsButton;
import com.myproject.automation.elements.ws.WsImage;
import com.myproject.automation.elements.ws.WsText;
import com.myproject.automation.enums.Attribute;
import com.myproject.automation.enums.Orb;
import com.myproject.automation.enums.WebsocketComponentType;
import com.myproject.automation.enums.collectables.CollectablesSet;
import com.myproject.automation.enums.collectables.ItemsState;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.models.pojo.Element;
import com.myproject.automation.utils.ParseUtil;
import com.myproject.automation.utils.waiters.AwaitilityWrapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.myproject.automation.enums.collectables.ItemsState.BASE;
import static com.myproject.automation.enums.collectables.ItemsState.SILVER;

public class SetCompletedScreen extends BaseScreen {

    private static final String LOC_IMG_ITEM = ">CapsuleContainer Image";
    private static final String LOC_REWARD_PACK = "CollectablesSetReward_RewardPackSetComplete(Clone)>AmountText TextMeshProUGUI";
    private static final String LOC_REWARD_PACK_IMG = "CollectablesSetReward_RewardPackSetComplete(Clone)>IconImage Image";
    private static final WsText TXT_SET_COMPLETED_TEXT = new WsText("SetCompletedText TextMeshProUGUI", "Set completed Text");
    private final WsButton btnClaim = new WsButton("ClaimButtonContainer>ClaimButton  Button", "Claim Button");
    private final WsText txtCoinReward = new WsText("CollectablesSetReward_CoinsSetComplete(Clone)>JackpotValue_Text TextMeshProUGUI", "Coins Text");
    private final WsText txtLightningReward = new WsText("LightningSetComplete(Clone)>JackpotValue_Text TextMeshProUGUI", "Lightning Text");
    private final WsImage imgLightning = new WsImage("Spinner>Lightning Image", "Lightning Animation Image");

    public SetCompletedScreen() {
        super(TXT_SET_COMPLETED_TEXT, "Set Completed screen");
    }

    public boolean isBronzeTurnedToSilver() {
        AwaitilityWrapper.waitForCondition(() -> getNumberOfContainers(BASE) == CollectablesSet.BASE.getItemsCount(),
                DataHolder.getTimeout(Timeout.DEFAULT_ELEMENT_GET_SECONDS));
        return AwaitilityWrapper.waitForCondition(() -> getNumberOfContainers(SILVER) == CollectablesSet.SILVER.getItemsCount(),
                DataHolder.getTimeout(Timeout.DEFAULT_ELEMENT_GET_SECONDS));
    }

    public String getSetCompletedText() {
        return TXT_SET_COMPLETED_TEXT.getText();
    }

    public boolean isClaimPresent() {
        return btnClaim.waitForIsPresent();
    }

    public void spamClaimButton(int spamCount) {
        btnClaim.spamElement(spamCount, 1);
    }

    public void clickClaim() {
        btnClaim.click();
    }

    public void tapAreaOfClaim() {
        btnClaim.tapAroundElement(CommonTestConstants.COUNT_OF_TOUCHES_AROUND_ELEMENT);
    }

    public List<WsImage> getListOfItemImages() {
        List<WsImage> listOfItemImages = SocketDriver.getElements(LOC_IMG_ITEM, WebsocketComponentType.UNITY_IMAGE).stream()
                .map(el -> new WsImage(el.getName(), el.getName()))
                .collect(Collectors.toList());
        Collections.reverse(listOfItemImages);
        return listOfItemImages;
    }

    public double getRewardCoinsCount() {
        return ParseUtil.getDoubleValueWithComma(txtCoinReward.getText());
    }

    public double getRewardLightningCount() {
        return ParseUtil.getDoubleValueWithComma(txtLightningReward.getText());
    }

    public int getRewardPackCount() {
        return SocketDriver.getElements(LOC_REWARD_PACK).stream().mapToInt(e -> Integer.parseInt(e.getText())).sum();
    }

    public boolean isLightningAnimationPresent() {
        return imgLightning.waitForIsPresent();
    }
  
    public boolean isOrbRewardPresent(Orb orbType) {
        return getListOfOrbRewards().stream()
                .anyMatch(y -> y.getAttribute(Attribute.SPRITE).contains(orbType.getSprite()));
    }

    public boolean isCoinRewardPresent() {
        return txtCoinReward.waitForIsPresent();
    }

    public boolean isLightningRewardPresent() {
        return txtLightningReward.waitForIsPresent();
    }

    private int getNumberOfContainers(ItemsState state) {
        return (int) SocketDriver.getElements(LOC_IMG_ITEM, WebsocketComponentType.UNITY_IMAGE)
                .stream()
                .map(Element::getSprite)
                .map(c -> c.contains(state.getSpriteAttr()))
                .count();
    }

    private List<WsImage> getListOfOrbRewards() {
        List<WsImage> listOfOrbRewards = SocketDriver.getElements(LOC_REWARD_PACK_IMG, WebsocketComponentType.UNITY_IMAGE).stream()
                .map(el -> new WsImage(el.getName(), el.getName()))
                .collect(Collectors.toList());
        Collections.reverse(listOfOrbRewards);
        return listOfOrbRewards;
    }
}
