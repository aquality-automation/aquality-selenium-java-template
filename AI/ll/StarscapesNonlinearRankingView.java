package com.project.automation.screens.views;

import com.project.automation.base.BaseScreen;
import com.project.automation.elements.ws.WsButton;

public class StarscapesNonlinearRankingView extends BaseScreen {

    public static final WsButton BTN_CLOSE = new WsButton(getFormattedIdForButton("Close_Button"), "Close Button");
    public StarscapesNonlinearRankingView() {
        super(BTN_CLOSE, "Ranking View");
    }

    public void clickOnCloseBtn() {
        BTN_CLOSE.click();
    }
}
