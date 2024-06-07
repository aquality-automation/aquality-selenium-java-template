package com.project.automation.steps.ui.starscapes;

import com.project.automation.base.BaseSteps;
import com.project.automation.screens.starscapes.SectorsScreen;
import com.project.automation.screens.menus.StarscapesNonlinearTopBarMenu;

public class StarscapesSteps extends BaseSteps {

    private static ThreadLocal<BaseSteps> stepsInstanceHolder = new InheritableThreadLocal<>();

    public static synchronized StarscapesSteps get() {
        return (StarscapesSteps) get(stepsInstanceHolder, StarscapesSteps.class).get();
    }

    //Action steps
    public void goToLobby() {
        new StarscapesNonlinearTopBarMenu().clickOnHomeBtn();
    }

    public void openTutorial() {
        new StarscapesNonlinearTopBarMenu().clickOnInfoBtn();
    }

    public void openHomeSector() {
        new SectorsScreen().clickOnHomeSectorBtn();
    }

    public void openSeasonalConstellation() {
        new SectorsScreen().clickOnSeasonalStarsBtn();
    }

    //Assertion steps
    public void assertThatStarscapesSectorsScreenIsOpen() {
        assertion.assertTrue(new SectorsScreen().isScreenPresent(), "Starscapes Sectors Screen is now present");
    }
}
