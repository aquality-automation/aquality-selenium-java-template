package com.appsoftcorp.automation.pages.thebestgame;

import com.appsoftcorp.automation.pages.interfaces.BaseBrowserActions;

import static com.appsoftcorp.automation.constants.ElementsText.INVALID_URL_MSG_FIREFOX;

public class FireFoxSignInPage extends BaseSignInPage implements BaseBrowserActions {

    private static final String LOC_INVALID_URL_MSG = "//div[@class='container']/descendant::h1[text()='%s']";

    public FireFoxSignInPage(String urlLocator) {
        super(String.format(LOC_INVALID_URL_MSG, INVALID_URL_MSG_FIREFOX));
    }
}
