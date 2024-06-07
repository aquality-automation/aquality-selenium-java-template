package com.myproject.automation.screens.collectables;

import com.myproject.automation.base.BaseScreen;
import com.myproject.automation.elements.ws.WsButton;
import com.myproject.automation.elements.ws.WsImage;
import com.myproject.automation.elements.ws.WsText;

public class ItemsDetailsScreen extends BaseScreen {

    private static final int COUNT_OF_TOUCHES_AROUND_ELEMENT = 2;
    private static final WsText TXT_HEADER = new WsText("CollectablesItemDetailView(Clone)>Container>Header>Header_Text TextMeshProUGUI",
            "Header Text");
    private static final String LOC_BUTTON_ARROW = "ButtonArrow_";
    private final WsButton btnNextItem = new WsButton(String.format("%sNext", LOC_BUTTON_ARROW), "Next Button");
    private final WsButton btnPreviousItem = new WsButton(String.format("%sPrevious", LOC_BUTTON_ARROW), "Previous Button");
    private final WsImage imgStellarPoints = new WsImage("GemsImage Image", "Stellar Points Image");
    private final WsImage imgOrb = new WsImage("RewardPack(Clone)>IconImage Image", "Orb Image");
    private final WsImage imgCoins = new WsImage("CoinsImage Image", "Coins Image");
    private final WsImage imgLightning = new WsImage("LightningImage Image", "Lightning Image");
    private final WsButton btnBack = new WsButton("CollectablesBack_Button Button", "Back Button");

    public ItemsDetailsScreen() {
        super(TXT_HEADER, "Items details screen", true);
    }

    public boolean isStellarPointsRewardPresent() {
        return imgStellarPoints.waitForIsPresent();
    }

    public String getItemName() {
        return TXT_HEADER.getText();
    }

    public void openNextItem() {
        btnNextItem.click();
    }

    public void openPreviousItem() {
        btnPreviousItem.click();
    }

    public boolean isOrbRewardPresent() {
        return imgOrb.waitForIsPresent();
    }

    public boolean isCoinsRewardPresent() {
        return imgCoins.waitForIsPresent();
    }

    public boolean isLightningRewardPresent() {
        return imgLightning.waitForIsPresent();
    }

    public void clickBack() {
        btnBack.click();
    }

    public void spamBackButton(int spamCount) {
        btnBack.spamElement(spamCount, 1);
    }

    public void tapAroundBackBtn() {
        btnBack.tapAroundElement(COUNT_OF_TOUCHES_AROUND_ELEMENT);
    }

    public void spamNextItemButton(int spamCount) {
        btnNextItem.spamElement(spamCount, 1);
    }

    public void spamPreviousItemButton(int spamCount) {
        btnPreviousItem.spamElement(spamCount, 1);
    }
}
