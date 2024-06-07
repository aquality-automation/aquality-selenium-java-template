package com.project.automation.screens.starscapes;

import com.project.automation.base.BaseScreen;
import com.project.automation.elements.ws.WsButton;

public class AllStarsVisitedCongratulationPopUpView extends BaseScreen {

    private static final WsButton BTN_CLAIM = new WsButton(getFormattedIdForButton("Claim_Button"), "Claim Button");
    public AllStarsVisitedCongratulationPopUpView() {
        super(BTN_CLAIM, "'You Have Visited All Stars!' Congratulation Pop-up");
    }

    public void clickOnClaimBtn() {
        BTN_CLAIM.click();
    }
}
