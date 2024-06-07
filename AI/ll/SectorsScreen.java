package com.project.automation.screens.starscapes;

import com.project.automation.base.BaseScreen;
import com.project.automation.elements.ws.WsButton;

public class SectorsScreen extends BaseScreen {

    private final static WsButton BTN_SEASONAL_STARS = new WsButton(getFormattedIdForButton("SectorIcon_Seasonal(Clone)>Button"), "Seasonal Stars");
    private final WsButton btnHomeSector = new WsButton(getFormattedIdForButton("SectorPlaceholder_Beginner>SectorIconBase(Clone)>Button"), "Home Sector");
    private final WsButton btnCoinSector = new WsButton(getFormattedIdForButton("SectorPlaceholder_2>SectorIconBase(Clone)>Button"), "Coin Sector");
    private final WsButton btnOrbsSector = new WsButton(getFormattedIdForButton("SectorPlaceholder_3>SectorIconBase(Clone)>Button"), "Orb Sector");
    private final WsButton btnrrSector = new WsButton(getFormattedIdForButton("SectorPlaceholder_3>SectorIconBase(Clone)>Button"), "rr Sector");

    public SectorsScreen() {
        super(BTN_SEASONAL_STARS, "Starscapes Sectors Screen");
    }

    public void clickOnSeasonalStarsBtn() {
        BTN_SEASONAL_STARS.click();
    }

    public void clickOnHomeSectorBtn() {
        btnHomeSector.click();
    }

    public void clickOnCoinSectorBtn() {
        btnCoinSector.click();
    }
}
