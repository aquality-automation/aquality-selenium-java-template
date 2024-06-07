package com.myproject.automation.steps.ui.randomrewards;

import com.myproject.automation.base.BaseSteps;
import com.myproject.automation.drivers.websocket.SocketDriver;
import com.myproject.automation.enums.OrbReward;
import com.myproject.automation.screens.views.OrbTotalRewardsView;
import com.myproject.automation.steps.ui.AppSteps;
import io.qameta.allure.Step;

public class OrbTotalRewardsSteps extends BaseSteps {

    private static ThreadLocal<BaseSteps> stepsInstanceHolder = new InheritableThreadLocal<>();

    public static synchronized OrbTotalRewardsSteps get() {
        return (OrbTotalRewardsSteps) get(stepsInstanceHolder, OrbTotalRewardsSteps.class).get();
    }

    // action steps

    @Step("Clicking 'Finish'")
    public void clickFinish() {
        AppSteps.get().waitForStableResponseFromWebsocket();
        new OrbTotalRewardsView().clickFinish();
    }

    @Step("Tapping around finish button")
    public void tapAroundFinishBtn() {
        new OrbTotalRewardsView().tapAroundFinishBtn();
    }

    @Step("Getting coins reward")
    public double getCoinsReward() {
        SocketDriver.waitForResponseIsStable();
        return new OrbTotalRewardsView().getRewardValue(OrbReward.COINS);
    }

    @Step("Getting Lightning reward value")
    public int getLightningReward() {
        return new OrbTotalRewardsView().getRewardValue(OrbReward.LIGHTNING_ICON);
    }

    @Step("Getting Stellar points reward value")
    public int getStellarPointsReward() {
        return new OrbTotalRewardsView().getRewardValue(OrbReward.STELLAR_POINTS_ICON);
    }

    //assertion steps

    @Step("Asserting that Finish Button is present")
    public void assertThatFinishBtnIsPresent() {
        assertion.assertTrue(new OrbTotalRewardsView().isFinishBtnPresent(), "Finish Button is not present");
    }

    @Step("Asserting that Orb Total Rewards screen is present")
    public void assertThatOrbTotalRewardsScreenIsPresent() {
        assertion.assertTrue(new OrbTotalRewardsView().isScreenPresent(), "Collect Orb rewards screen is not opened");
    }

    @Step("Asserting that Orb Total Rewards screen is not present")
    public void assertThatOrbTotalRewardsScreenIsNotPresent() {
        assertion.assertTrue(new OrbTotalRewardsView().isScreenNotPresent(), "Collect Orb rewards screen is opened");
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

    @Step("Asserting that 'Orb total rewards' view is present")
    public void assertThatOrbTotalRewardsViewIsPresent() {
        assertion.assertTrue(new OrbTotalRewardsView().isScreenPresent(), "Orb total rewards view is not present");
    }

    @Step("Asserting that Collectables Card is present on Collect Orb Rewards screen")
    public void assertThatCardRewardIsPresentOnCollectOrbRewardsScreen() {
        assertion.assertTrue(new OrbTotalRewardsView().isCollectablesCardPresent(),
                "Collectables Card is not present on Collect Orb Rewards screen");
    }

    @Step("Asserting that Lightning reward is present on Collect Orb Rewards screen")
    public void assertThatLightningRewardIsPresentOnCollectOrbRewardsScreen() {
        assertion.assertTrue(new OrbTotalRewardsView().isLightningImgPresent(),
                "Lightning reward is not present on Collect Orb Rewards screen");
    }

    @Step("Asserting that Collectables boxes count on notification is correct on Orb Total Rewards screen")
    public void assertThatCollectablesBoxesCountOnNotificationIsCorrectOnCollectOrbRewardsView(String expectedText) {
        assertion.assertEquals(new OrbTotalRewardsView().getCollectablesBoxesCount(), expectedText,
                "Collectables boxes count on notification is not correct on Orb Total Rewards screen");
    }

    @Step("Asserting that cards amount on Orb Total reward view value is equal to '{expAmount}'")
    public void assertThatCardsAmountValueOnOrbTotalRewardsViewIsEqualTo(int expAmount) {
        AppSteps.get().waitForStableResponseFromWebsocket();
        assertion.assertEquals(new OrbTotalRewardsView().getRewardValue(OrbReward.COLLECTABLES_CARD), expAmount,
                String.format("Cards amount value is not equal to expected '%d'", expAmount));
    }

    @Step("Asserting that Finish button is present on Collect Orb Rewards screen")
    public void assertThatFinishBtnIsPresentOnCollectOrbRewardsScreen() {
        assertion.assertTrue(new OrbTotalRewardsView().isFinishBtnPresent(),
                "Finish button is not present on Collect Orb Rewards screen");
    }

    @Step("Asserting that Claim button is not present on Collect Orb Rewards screen")
    public void assertThatClaimBtnIsNotPresentOnCollectOrbRewardsScreen() {
        assertion.assertTrue(new OrbTotalRewardsView().isClaimBtnNotPresent(),
                "Claim button is present on Collect Orb Rewards screen");
    }

    @Step("Asserting that Collectables button is present on 'Orb total rewards' screen")
    public void assertThatCollectablesBtnIsPresentOnOrbTotalRewardsScreen() {
        assertion.assertTrue(new OrbTotalRewardsView().isCollectablesBtnPresent(),
                "Collectables button is not present on Collect Orb Rewards screen");
    }
}
