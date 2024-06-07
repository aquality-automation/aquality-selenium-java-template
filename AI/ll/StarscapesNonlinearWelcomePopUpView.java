package com.project.automation.screens.views;

import com.project.automation.base.BaseScreen;
import com.project.automation.elements.ws.WsButton;

public class StarscapesNonlinearWelcomePopUpView extends BaseScreen {

    private static final WsButton btnCheck = new WsButton(getFormattedIdForButton("Button_Basic9Sclice_Green_WithShine"), "Check Button");

    public StarscapesNonlinearWelcomePopUpView() {
        super(btnCheck, "Starscapes Welcome Pop-up");
    }

    public void clickOnCheckBtn() {
        btnCheck.click();
    }
}
