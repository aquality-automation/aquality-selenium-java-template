package com.project.automation.screens.menus;

import com.project.automation.base.BaseScreen;
import com.project.automation.elements.ws.WsButton;

public class StarscapesNonlinearTopBarMenu extends BaseScreen {

    private final static WsButton BTN_INFO = new WsButton(getFormattedIdForButton("InfoButton>InfoIcon"), "Info Button");
    private final WsButton btnHome = new WsButton(getFormattedIdForButton("HomeButton>HomeIcon"), "Home Button");
    private final WsButton btnRankWidget = new WsButton(getFormattedIdForButton("RankWidget>Button"), "Rank Widget");
    private final WsButton btnGalaxy = new WsButton(getFormattedIdForButton("GalaxyButton"), "Galaxy Button");

    public StarscapesNonlinearTopBarMenu() {
        super(BTN_INFO, "Starscapes Frame Screen");
    }

    public void clickOnInfoBtn() {
        BTN_INFO.click();
    }

    public void clickOnHomeBtn() {
        btnHome.click();
    }

    public void clickOnRankWidgetBtn() {
        btnRankWidget.click();
    }

    public void clickOnGalaxyBtn() {
        btnGalaxy.click();
    }

    public boolean isRankWidgetPresent() {
        return btnRankWidget.waitForIsPresent();
    }
}
