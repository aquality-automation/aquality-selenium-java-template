package com.myproject.automation.steps.ui.randomrewards;

import com.myproject.automation.base.BaseSteps;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.enums.OrbReward;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.screens.views.OrbRewardsView;
import com.myproject.automation.screens.views.OrbTotalRewardsView;
import com.myproject.automation.steps.ui.AppSteps;
import io.qameta.allure.Step;

public class OrbRewardsSteps extends BaseSteps {

    private static ThreadLocal<BaseSteps> stepsInstanceHolder = new InheritableThreadLocal<>();

    public static synchronized OrbRewardsSteps get() {
        return (OrbRewardsSteps) get(stepsInstanceHolder, OrbRewardsSteps.class).get();
    }

    // action steps

    @Step("Clicking 'Open all'")
    public void clickOpenAll() {
        new OrbRewardsView().clickOpenAll();
    }

    @Step("Clicking 'Open Next'")
    public void clickOpenNext() {
        new OrbRewardsView().clickOpenNext();
    }

    @Step("Clicking 'Finish'")
    public void clickFinish() {
        AppSteps.get().waitForStableResponseFromWebsocket();
        new OrbRewardsView().clickFinish();
    }

    @Step("Clicking 'Collectables'")
    public void clickCollectables() {
        AppSteps.get().waitForStableResponseFromWebsocket();
        new OrbRewardsView().clickCollectables();
    }

    @Step("Opening Collectables view")
    public void openCollectables() {
        new OrbRewardsView().openCollectablesView();
    }

    @Step("Getting coins amount from Orb rewards")
    public double getCoinsAmountFromOrbRewards() {
        AppSteps.get().sleep(DataHolder.getTimeout(Timeout.DEFAULT_CONDITION_SECONDS), "Waiting for the coins update");
        return new OrbRewardsView().getAmountOfCoins();
    }

    @Step("Open all Orb Rewards if Finish isn't present")
    public void openAllOrbRewardsIfFinishIsNotPresent() {
        if (new OrbRewardsView().isFinishBtnNotPresent(DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS))) {
            clickOpenAll();
        }
    }

    @Step("Getting reward value from Orb rewards by sprite {sprite}")
    public double getRewardValueFromOrbRewards(OrbReward orbReward) {
        return new OrbRewardsView().getRewardValue(orbReward.getSprite());
    }

    //assertion steps

    @Step("Asserting that Orb rewards view is present")
    public void assertThatOrbRewardsViewIsPresent() {
        assertion.assertTrue(new OrbRewardsView().isScreenPresent(), "Orb rewards view is not present");
    }

    @Step("Asserting that Orb rewards view is not present")
    public void assertThatOrbRewardsViewIsNotPresent() {
        assertion.assertTrue(new OrbRewardsView().isScreenNotPresent(), "Orb rewards view is present");
    }

    @Step("Asserting that Collectables icon is present on Orb rewards view")
    public void assertThatCollectablesIconIsPresentOrbRewardsView() {
        assertion.assertTrue(new OrbRewardsView().isCollectablesIconPresent(),
                "Collectables icon is not present on Orb rewards view");
    }

    @Step("Asserting that cards amount value is present on Orb rewards view")
    public void assertThatCardsAmountValueIsPresentOrbRewardsView() {
        assertion.assertTrue(new OrbRewardsView().isRewardAmountPresent(OrbReward.COLLECTABLES_CARD.getSprite()),
                "Cards amount value is not present on Orb rewards view");
    }

    @Step("Asserting that cards amount value on Orb Reward Screen is equal to '{expAmount}'")
    public void assertThatCardsAmountValueOnOrbRewardScreenIsEqualTo(int expAmount) {
        AppSteps.get().waitForStableResponseFromWebsocket();
        assertion.assertEquals(new OrbRewardsView().getRewardValue(OrbReward.COLLECTABLES_CARD.getSprite()), expAmount,
                String.format("Cards amount value is not equal to expected '%d' on Orb Reward Screen", expAmount));
    }

    @Step("Asserting that Open Next button is present")
    public void assertThatOpenNextBtnIsPresent() {
        assertion.assertTrue(new OrbRewardsView().isOpenNextPresent(), "Open Next button is not present");
    }

    @Step("Asserting that Open Next button is not present")
    public void assertThatOpenNextBtnIsNotPresent() {
        assertion.assertTrue(new OrbRewardsView().isOpenNextBtnNotPresent(), "Open Next button is present");
    }

    @Step("Asserting that Open All button is present")
    public void assertThatOpenAllBtnIsPresent() {
        assertion.assertTrue(new OrbRewardsView().isOpenAllBtnPresent(), "Open All button is not present");
    }

    @Step("Asserting that total cards amount value in the top of Orb Reward Screen is equal to '{expAmount}'")
    public void assertThatTotalCardsAmountValueOnTopOrbScreenIsEqualTo(int expAmount) {
        AppSteps.get().waitForStableResponseFromWebsocket();
        assertion.assertEquals(new OrbRewardsView().getTotalRewardValue(OrbReward.COLLECTABLES_CARD.getSprite()), expAmount,
                String.format("Total cards amount value in the top of Orb Reward Screen is not equal to expected '%d'", expAmount));
    }

    @Step("Asserting that Finish Button is present")
    public void assertThatFinishBtnIsPresent() {
        assertion.assertTrue(new OrbRewardsView().isFinishBtnPresent(), "Finish Button is not present");
    }

    @Step("Asserting that Orbs counter is equal to '{expOrbCount}'")
    public void assertThatOrbsCounterIsEqualTo(int expOrbCount) {
        assertion.assertEquals(new OrbTotalRewardsView().getOrbCount(), expOrbCount,
                "Orbs counter is not equal to expected");
    }

    @Step("Asserting that Orbs counter is present")
    public void assertThatOrbsCounterIsPresent() {
        assertion.assertTrue(new OrbTotalRewardsView().isOrbsCounterPresent(), "Asserting that Orbs counter is not present");
    }
}
