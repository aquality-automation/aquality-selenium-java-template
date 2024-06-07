package com.appsoftcorp.automation.pages.thebestgame;

import com.appsoftcorp.automation.pages.interfaces.BaseBrowserActions;

import static com.appsoftcorp.automation.constants.ElementsText.INVALID_URL_MSG_SAFARI;

public class SafariSignInPage extends BaseSignInPage implements BaseBrowserActions {

    private static final String LOC_INVALID_URL_MSG = "//div[@class='error-container']/descendant::p[text()='%s']";

    public SafariSignInPage(String urlLocator) {
        super(String.format(LOC_INVALID_URL_MSG, INVALID_URL_MSG_SAFARI));
    }
}
