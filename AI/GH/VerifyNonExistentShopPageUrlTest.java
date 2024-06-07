package com.appsoftcorp.automation.tests.thebestgame.weber.rebuildreact.embeddedcoinshop;

import com.appsoftcorp.automation.annotations.TestDataKeys;
import com.appsoftcorp.automation.steps.BrowserSteps;
import com.appsoftcorp.automation.steps.thebestgame.weberMenuSteps;
import com.appsoftcorp.automation.steps.thebestgame.SignInSteps;
import com.appsoftcorp.framework.base.BaseUITest;
import com.appsoftcorp.framework.base.DataHolder;
import com.appsoftcorp.framework.enums.config.Url;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.Test;

public class VerifyNonExistentShopPageUrlTest extends BaseUITest {

    @TmsLinks({@TmsLink("9760644"), @TmsLink("9095058")})
    @Test(dataProvider = "TestDataFromJson", groups = {"thebestgame_externalcatalog", "base_webui"})
    @TestDataKeys({"support_page_url", "invalid_url"})
    public void verifyNonExistentShopPageUrlTest(String supportPageUrl, String invalidUrl) {
        BrowserSteps.get().navigateToInvalidUrl(invalidUrl);
        SignInSteps.get().asserThatErrorPageIsPresentForInvalidUrl();
        BrowserSteps.get().openPage(DataHolder.getUrl(Url.thebestgame_ENDPOINT));
        weberMenuSteps.get().assertThatweberMenuPageIsOpened();
        int countOfWindowHandles = BrowserSteps.get().getCountOfWindowHandles();
        SignInSteps.get().clickSupportButton();
        BrowserSteps.get().assertThatWindowHandleCountEqualTo(countOfWindowHandles + 1, true);
        BrowserSteps.get().switchToLastWindowHandle();
        SignInSteps.get().assertThatPrivacyPolicyTextIsPresent();
        SignInSteps.get().assertCurrentUrlEquals(supportPageUrl);
    }
}
