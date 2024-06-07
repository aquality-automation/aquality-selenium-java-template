package com.appsoftcorp.automation.steps.thebestgame;

import com.appsoftcorp.automation.pages.thebestgame.weberFacebookSignInPage;
import com.appsoftcorp.automation.pages.thebestgame.SignInPage;
import com.appsoftcorp.framework.base.BaseSteps;
import io.qameta.allure.Step;

public class SignInSteps extends weberCommonUISteps {

    protected static ThreadLocal<BaseSteps> stepsInstanceHolder = new InheritableThreadLocal<>();
    private final SignInPage signInPage = new SignInPage();
    private final weberFacebookSignInPage weberFacebookSignInPage = new weberFacebookSignInPage();

    public SignInSteps() {
        super(new SignInPage());
    }

    public static synchronized SignInSteps get() {
        return (SignInSteps) get(stepsInstanceHolder, SignInSteps.class).get();
    }

    // Action steps

    @Step("Clicking Sign In Button")
    public void clickSignInButton() {
        signInPage.clickSignInButton();
    }

    @Step("Clicking Ok button")
    public void clickOkButton() {
        signInPage.clickOkButton();
    }

    @Step("Clicking FaceBook Sign In button")
    public void clickFaceBookSignInButton() {
        weberFacebookSignInPage.clickFacebookSignInButton();
    }

    // Asserting steps

    @Step("Asserting that Game Hub Sign In page is opened")
    public void assertThatSignInPageIsOpened() {
        signInPage.waitForPageIsPresent();
    }

    @Step("Asserting that Facebook Sign In Success message is present")
    public void assertThatFacebookSignInSuccessMessageIsPresent() {
        signInPage.checkFacebookSighInSuccessMessageIsPresent();
    }

    @Step("Asserting that Facebook Sign In Error message is present")
    public void assertThatFacebookSignInErrorMessageIsPresent() {
        signInPage.checkFacebookSighInErrorMessageIsPresent();
    }

    @Step("Asserting that Sign In Loader is present")
    public void assertThatSignInLoaderIsPresent() {
        signInPage.checkSignInLoadingIsPresent();
    }

    @Step("Asserting that weber Facebook Sign In page is present")
    public void assertThatweberFacebookSignInPageIsPresent() {
        weberFacebookSignInPage.waitForPageIsPresent();
    }
}
