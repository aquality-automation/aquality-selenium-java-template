package com.myproject.automation.screens.views;

import com.myproject.automation.base.BaseScreen;
import com.myproject.automation.constants.CommonTestConstants;
import com.myproject.automation.drivers.websocket.SocketDriver;
import com.myproject.automation.elements.ws.WsButton;
import com.myproject.automation.elements.ws.WsImage;
import com.myproject.automation.elements.ws.WsText;
import com.myproject.automation.enums.Attribute;
import com.myproject.automation.enums.WebsocketComponentType;
import com.myproject.automation.utils.ParseUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrbRewardsView extends BaseScreen {

    protected static final String LOC_IMG_REWARD = "Reward>Icon_Container>Icon_Image";
    private static final String LOC_TOTAL_REWARD_ICON = "TopBar_TotalRewards_Container>CollectableBox>Icon Image";
    private static final WsText TXT_YOUR_REWARD = new WsText("NewReward_Text TextMeshProUGUI", "Your reward Text");
    private static final WsText AMOUNT_OF_COINS_FROM_REWARD = new WsText("Reward_01>Reward>Icon_Container>Icon_Image>Value_Text TextMeshProUGUI", "Amount of coins Text");
    private final WsImage imgCollectablesIcon = new WsImage("Lab_Button>Lab image Image", "Collectables icon Image");
    private final WsButton btnOpenAll = new WsButton("OpenAllButton_Container>OpenAll_Button Button", "Open all Button");
    private final WsButton btnOpenNext = new WsButton("Open_Button Button", "Open Next Button");
    protected final WsButton btnFinish = new WsButton("Footer>Close_Button", "Finish Button");
    private final WsButton btnCollectables = new WsButton("Lab_Button Button", "Collectables Button");
    private final WsText txtOrbCounter = new WsText("RewardContainer>RewardPack_RewardViews(Clone)>RewardValueText TextMeshProUGUI",
            "Orb counter Text");

    public OrbRewardsView() {
        super(TXT_YOUR_REWARD, "Orb Rewards view", true);
    }

    public OrbRewardsView(WsText loc, String name) {
        super(loc, name, true);
    }

    public boolean isCollectablesIconPresent() {
        return imgCollectablesIcon.waitForIsPresent();
    }

    public void clickCollectables() {
        btnCollectables.click();
    }

    public void clickOpenAll() {
        btnOpenAll.click();
    }

    public boolean isFinishBtnPresent() {
        return btnFinish.waitForIsPresent();
    }

    public boolean isFinishBtnNotPresent(long timeout) {
        return btnFinish.waitForDoesNotExist(timeout);
    }

    public void clickFinish() {
        btnFinish.click();
    }

    public void tapAroundFinishBtn() {
        btnFinish.tapAroundElement(CommonTestConstants.COUNT_OF_TOUCHES_AROUND_ELEMENT);
    }

    public int getRewardValue(String type) {
        return ParseUtil.getIntValueWithComma(getReward(type, LOC_IMG_REWARD).getText());
    }

    public int getTotalRewardValue(String type) {
        return Integer.parseInt(getReward(type, LOC_TOTAL_REWARD_ICON).getText());
    }

    public boolean isOpenNextPresent() {
        return btnOpenNext.waitForIsPresent();
    }

    public boolean isOpenAllBtnPresent() {
        return btnOpenAll.waitForIsPresent();
    }

    public void clickOpenNext() {
        btnOpenNext.click();
    }

    public boolean isOpenNextBtnNotPresent() {
        return btnOpenNext.waitForDoesNotExist();
    }

    public boolean isRewardAmountPresent(String type) {
        return getReward(type, LOC_IMG_REWARD).waitForIsPresent();
    }

    public void openCollectablesView() {
        btnCollectables.click();
    }

    public double getAmountOfCoins() {
        return ParseUtil.getDoubleValueWithComma(AMOUNT_OF_COINS_FROM_REWARD.getText());
    }

    public int getOrbCount() {
        return Integer.parseInt(txtOrbCounter.getText());
    }

    public boolean isOrbsCounterPresent() {
        return txtOrbCounter.waitForIsPresent();
    }

    private WsImage getReward(String type, String loc) {
        return getListImgRewards(loc).stream()
                .filter(el -> el.getAttribute(Attribute.SPRITE).contains(type))
                .findFirst().get();
    }

    private List<WsImage> getListImgRewards(String loc) {
        List<WsImage> listImgRewards = SocketDriver.getElements(loc, WebsocketComponentType.UNITY_IMAGE).stream()
                .map(el -> new WsImage(el.getName(), el.getName())).collect(Collectors.toList());
        Collections.reverse(listImgRewards);
        return listImgRewards;
    }
}
