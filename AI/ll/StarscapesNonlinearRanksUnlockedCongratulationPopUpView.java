package com.project.automation.screens.views;

import com.project.automation.base.BaseScreen;
import com.project.automation.elements.ws.WsButton;

public class StarscapesNonlinearRanksUnlockedCongratulationPopUpView extends BaseScreen {

    public static final WsButton BTN_GREAT = new WsButton(getFormattedIdForButton("UpgradeButton"), "Great Button");
    public StarscapesNonlinearRanksUnlockedCongratulationPopUpView() {
        super(BTN_GREAT, "'Ranks Unlocked' Congratulation Pop-up");
    }
}
