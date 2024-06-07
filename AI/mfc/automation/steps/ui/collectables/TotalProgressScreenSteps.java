package com.myproject.automation.steps.ui.collectables;

import com.myproject.automation.base.BaseSteps;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.constants.CommonTestConstants;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.screens.collectables.TotalProgressScreen;
import com.myproject.automation.utils.waiters.AwaitilityWrapper;
import io.qameta.allure.Step;

public class TotalProgressScreenSteps extends BaseSteps {

    private static ThreadLocal<BaseSteps> stepsInstanceHolder = new InheritableThreadLocal<>();

    public static synchronized TotalProgressScreenSteps get() {
        return (TotalProgressScreenSteps) get(stepsInstanceHolder, TotalProgressScreenSteps.class).get();
    }

    // action steps

    @Step("Clicking 'Continue'")
    public void clickContinue() {
        new TotalProgressScreen().clickContinueBtn();
    }

    @Step("Spamming Continue button")
    public void spamContinue() {
        new TotalProgressScreen().spamContinue(CommonTestConstants.FIVE_COUNT_OF_TAPS);
    }

    @Step("Clicking 'Upgrade'")
    public void clickUpgrade() {
        new TotalProgressScreen().clickUpgradeBtn();
    }

    @Step("Getting Trade points amount")
    public double getTradePointsAmount() {
        return new TotalProgressScreen().getTradePointsAmount();
    }

    // assertion steps

    @Step("Asserting that 'Total progress' screen is present")
    public void assertThatTotalProgressScreenIsPresent() {
        assertion.assertTrue(new TotalProgressScreen().isScreenPresent(), "'Total progress' screen is not present");
    }

    @Step("Asserting that Collected Total points amount is equal to {expectedTotalPoints} on Total Progress screen")
    public void assertThatCollectedTotalPointsIsEqualTo(int expectedTotalPoints) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new TotalProgressScreen().getCountOfCurrentPoints() == expectedTotalPoints,
                DataHolder.getTimeout(Timeout.DEFAULT_ELEMENT_GET_SECONDS)), "Collected Total points amount is not correct");
    }

    @Step("Asserting that 'Total progress' screen is not present")
    public void assertThatTotalProgressScreenIsNotPresent() {
        assertion.assertTrue(new TotalProgressScreen().isScreenNotPresent(), "'Total progress' screen is present");
    }

    @Step("Asserting that Continue button is present")
    public void assertThatContinueBtnIsPresent() {
        assertion.assertTrue(new TotalProgressScreen().isContinueClaimPresent(), "Continue button is not present");
    }

    @Step("Asserting that Upgrade button is present")
    public void assertThatUpgradeBtnIsPresent() {
        assertion.assertTrue(new TotalProgressScreen().isUpgradeClaimPresent(), "Upgrade button is not present");
    }

    @Step("Asserting that trade points amount is equal to {tradePointsAmount}")
    public void assertThatTradePointsAmountIsEqualTo(double tradePointsAmount) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new TotalProgressScreen().getTradePointsAmount() == tradePointsAmount,
                DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS)), "Trade points amount is not equal to expected");
    }

    @Step("Asserting that trade points are not present")
    public void assertThatTradePointsAreNotPresent() {
        assertion.assertTrue(new TotalProgressScreen().isTradePointsNotPresent(), "Trade points amount are present");
    }

    @Step("Asserting that Jackpot Card is present")
    public void assertThatJackpotCardIsPresent() {
        assertion.assertTrue(new TotalProgressScreen().isJackpotCardPresent(), "Jackpot Card is not present");
    }
}
