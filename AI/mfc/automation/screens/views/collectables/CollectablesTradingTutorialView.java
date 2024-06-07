package com.myproject.automation.screens.views.collectables;

import com.myproject.automation.base.BaseScreen;
import com.myproject.automation.elements.ws.WsButton;

public class CollectablesTradingTutorialView extends BaseScreen {

    private static final WsButton BTN_CLOSE = new WsButton("FTUE_CollectablesTradingPopUp(Clone)>CloseButton Button", "Close Button");

    public CollectablesTradingTutorialView() {
        super(BTN_CLOSE, "Collactables Trading Tutorial view");
    }

    public void spamClose(int spamCount) {
        BTN_CLOSE.spamElement(spamCount, 1);
    }

    public void clickClose() {
        BTN_CLOSE.click();
    }
}
