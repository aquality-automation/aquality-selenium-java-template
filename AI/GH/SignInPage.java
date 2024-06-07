package com.appsoftcorp.automation.pages.thebestgame;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.appsoftcorp.automation.constants.InfoViewMessages.*;

public class SignInPage extends weberCommonUIPage {

    private static final SelenideElement BUTTON_SIGN_IN = $x("//button[contains(@class, 'LoginButton')]");
    private static final String headerTemplate = "//h1[text()='%s']";
    private static final SelenideElement txtFacebookSignInErrorMessage = $x(String.format(headerTemplate, FAILED_SIGN_IN_TEXT));
    private static final SelenideElement txtFacebookSignInSuccessMessage = $x(String.format(headerTemplate, SIGN_IN_TEXT));
    private static final SelenideElement txtSignInLoader = $x(String.format(headerTemplate, SIGNING_YOU_IN_TEXT));
    private static final SelenideElement btnOk = $x("//button[contains(@data-testid, 'sign-in')]");

    public SignInPage() {
        super(BUTTON_SIGN_IN, "Game Hub Sign In Page");
    }

    public void clickSignInButton() {
        BUTTON_SIGN_IN.click();
    }

    public void clickOkButton() {
        btnOk.click();
    }

    public void checkFacebookSighInSuccessMessageIsPresent() {
        txtFacebookSignInSuccessMessage.should(Condition.visible);
    }

    public void checkFacebookSighInErrorMessageIsPresent() {
        txtFacebookSignInErrorMessage.should(Condition.visible);
    }

    public void checkSignInLoadingIsPresent() {
        txtSignInLoader.should(Condition.visible);
    }
}
