package com.appsoftcorp.automation.pages.thebestgame;

import static com.appsoftcorp.automation.constants.ElementsText.INVALID_URL_MSG_CHROME;

public class ChromeSignInPage extends BaseSignInPage {

    private static final String LOC_INVALID_URL_MSG = "//div[@id='main-frame-error']/descendant::span[text()='%s']";

    public ChromeSignInPage(String urlLocator) {
        super(String.format(LOC_INVALID_URL_MSG, INVALID_URL_MSG_CHROME));
    }
}
