package com.appsoftcorp.automation.tests.thebestgame.weber.rebuildreact.embeddedcoinshop;

import com.appsoftcorp.automation.enums.MenuButton;
import com.appsoftcorp.automation.steps.BrowserSteps;
import com.appsoftcorp.automation.steps.thebestgame.weberMenuSteps;
import com.appsoftcorp.framework.base.BaseUITest;
import com.appsoftcorp.framework.base.DataHolder;
import com.appsoftcorp.framework.enums.config.Url;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;

import org.testng.annotations.Test;

public class VerifyShopButtonIsDisplayedInHamburgerMenuTest extends BaseUITest {

    @TmsLinks({@TmsLink("9760605"), @TmsLink("9760603")})
    @Test(groups = {"thebestgame_externalcatalog", "base_webui"})
    public void verifyShopButtonIsDisplayedInHamburgerMenuTest() {
        BrowserSteps.get().openPage(DataHolder.getUrl(Url.thebestgame_ENDPOINT));
        weberMenuSteps.get().assertThatweberMenuPageIsOpened();
        weberMenuSteps.get().assertThatMenuButtonIsPresent(MenuButton.SHOP);
        weberMenuSteps.get().assertThatBurgerMenuButtonIsPresent(MenuButton.SHOP);
    }
}
