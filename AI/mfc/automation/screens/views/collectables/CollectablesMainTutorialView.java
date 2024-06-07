package com.myproject.automation.screens.views.collectables;

import com.myproject.automation.base.BaseScreen;
import com.myproject.automation.elements.ws.WsButton;
import com.myproject.automation.elements.ws.WsText;

public class CollectablesMainTutorialView extends BaseScreen {

    private static final String LOC_FIRST_TUTORIAL_CARD = "Page0%s>Container>TutorialCard01>Avatar_Frame_Left>Text_Headline TextMeshProUGUI";
    private static final WsButton BTN_CLOSE = new WsButton("FTUE_CollectablesMainPopUp(Clone)>CloseButton Button", "Close Button");
    private final WsButton btnNextArrow = new WsButton("ButtonArrowNext Button", "Next Arrow Button");
    private final WsButton btnPreviousArrow = new WsButton("ButtonArrowPrevious Button", "Previous Arrow Button");

    public CollectablesMainTutorialView() {
        super(BTN_CLOSE, "Collectables Main Tutorial view");
    }

    public boolean isCloseBtnPresent() {
        return BTN_CLOSE.waitForIsPresent();
    }

    public void clickCloseBtn() {
        BTN_CLOSE.click();
    }

    public boolean isNextArrowBtnPresent() {
        return btnNextArrow.waitForIsPresent();
    }

    public boolean isPreviousArrowBtnPresent() {
        return btnPreviousArrow.waitForIsPresent();
    }

    public boolean isNextArrowBtnNotPresent() {
        return btnNextArrow.waitForDoesNotExist();
    }

    public boolean isPreviousArrowBtnNotPresent() {
        return btnPreviousArrow.waitForDoesNotExist();
    }

    public void clickNextArrow() {
        btnNextArrow.click();
    }

    public void clickPreviousArrow() {
        btnPreviousArrow.click();
    }

    public boolean isCorrectPage(int numberPage) {
        return new WsText(String.format(LOC_FIRST_TUTORIAL_CARD, numberPage + 1), String.format("Page %d", numberPage)).waitForIsPresent();
    }

    public void spamCloseBtn(int spamCount) {
        BTN_CLOSE.spamElement(spamCount, 1);
    }
}
