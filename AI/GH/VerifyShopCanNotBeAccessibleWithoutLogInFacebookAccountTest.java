package com.appsoftcorp.automation.tests.thebestgame.weber.rebuildreact.embeddedcoinshop;

import com.appsoftcorp.automation.enums.MenuButton;
import com.appsoftcorp.automation.steps.BrowserSteps;
import com.appsoftcorp.automation.steps.FacebookLoginSteps;
import com.appsoftcorp.automation.steps.thebestgame.weberMenuSteps;
import com.appsoftcorp.automation.steps.thebestgame.SignInSteps;
import com.appsoftcorp.framework.base.BaseUITest;
import com.appsoftcorp.framework.base.DataHolder;
import com.appsoftcorp.framework.enums.config.Url;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class VerifyShopCanNotBeAccessibleWithoutLogInFacebookAccountTest extends BaseUITest {

    private static final int COUNT = 3;

    @TmsLink("9760659")
    @Test(groups = {"thebestgame_externalcatalog", "base_webui"})
    public void verifyShopCanNotBeAccessibleWithoutLogInFacebookAccountTest() {
        BrowserSteps.get().openPage(DataHolder.getUrl(Url.thebestgame_ENDPOINT));
        weberMenuSteps.get().assertThatweberMenuPageIsOpened();
        weberMenuSteps.get().assertThatMenuButtonIsPresent(MenuButton.SHOP);
        int countOfWindowHandles = BrowserSteps.get().getCountOfWindowHandles();
        IntStream.range(0, COUNT).forEach(i -> {
            weberMenuSteps.get().clickMenuButton(MenuButton.SHOP);
            BrowserSteps.get().assertThatWindowHandleCountEqualTo(countOfWindowHandles + 1, true);
            BrowserSteps.get().switchToLastWindowHandle();
            FacebookLoginSteps.get().assertThatLoginPageIsOpened();
            BrowserSteps.get().closeCurrentWindowHandleAndRemoveItFromWindowHandles();
            BrowserSteps.get().switchToFirstWindowHandle();
            SignInSteps.get().clickOkButton();
            weberMenuSteps.get().assertThatweberMenuPageIsOpened();
        });
    }
}
