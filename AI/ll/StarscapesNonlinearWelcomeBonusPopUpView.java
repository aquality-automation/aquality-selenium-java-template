package com.project.automation.screens.views;

import com.project.automation.base.BaseScreen;
import com.project.automation.elements.ws.WsButton;

public class StarscapesNonlinearWelcomeBonusPopUpView extends BaseScreen {

    private static final WsButton BTN_CLAIM = new WsButton(getFormattedIdForButton("Button_Basic9Sclice_Green_WithShine"), "Check Button");

    public StarscapesNonlinearWelcomeBonusPopUpView() {
        super(BTN_CLAIM, "Starscapes Welcome Bonus Pop-up");
    }

    public void clickOnClaimBtn() {
        BTN_CLAIM.click();
    }
}
