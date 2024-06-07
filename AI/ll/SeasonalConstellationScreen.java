package com.project.automation.screens.starscapes;

import com.project.automation.base.BaseScreen;
import com.project.automation.elements.ws.WsButton;

public class SeasonalConstellationScreen extends BaseScreen {

    private static final WsButton BTN_PLANETS = new WsButton(getFormattedIdForButton("PlanetsButton"), "Planets Button");
    public SeasonalConstellationScreen() {
        super(BTN_PLANETS, "Seasonal Constellation Screen");
    }

    public void clickOnPlanetsBtn() {
        BTN_PLANETS.click();
    }
}
