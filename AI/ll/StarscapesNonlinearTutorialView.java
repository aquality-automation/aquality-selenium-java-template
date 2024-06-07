package com.project.automation.screens.views;

import com.project.automation.base.BaseScreen;
import com.project.automation.elements.ws.WsButton;

public class StarscapesNonlinearTutorialView extends BaseScreen {

    private static final WsButton BTN_CLOSE = new WsButton(getFormattedIdForButton("StarscapesNonLinear_InfoPopup(Clone)>CloseButton"), "Close Button");
    private final WsButton btnNextArrow = new WsButton(getFormattedIdForButton("ButtonArrowNext"), "Next Arrow");
    private final WsButton btnPreviousArrow = new WsButton(getFormattedIdForButton("ButtonArrowPrevious"), "Previous Arrow");

    public StarscapesNonlinearTutorialView() {
        super(BTN_CLOSE, "Starscapes Tutorial View");
    }

    public void clickOnNextArrow() {
        btnNextArrow.click();
    }

    public void clickOnPreviousArrow() {
        btnPreviousArrow.click();
    }

    public boolean isNextArrowPresent() {
        return btnNextArrow.waitForIsPresent();
    }

    public boolean isPreviousArrowPresent() {
        return btnPreviousArrow.waitForIsPresent();
    }
}
