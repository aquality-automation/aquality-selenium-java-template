package com.myproject.automation.steps.ui.collectables;

import com.myproject.automation.base.BaseSteps;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.constants.CommonTestConstants;
import com.myproject.automation.enums.collectables.CardsFrontImageState;
import com.myproject.automation.enums.collectables.CollectablesItem;
import com.myproject.automation.enums.collectables.ItemsStateOnTopCardsScreen;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.screens.collectables.CollectablesCardsScreen;
import com.myproject.automation.steps.ui.AppSteps;
import com.myproject.automation.utils.waiters.AwaitilityWrapper;
import io.qameta.allure.Step;

public class CollectablesCardsSteps extends BaseSteps {

    private static ThreadLocal<BaseSteps> stepsInstanceHolder = new InheritableThreadLocal<>();

    public static synchronized CollectablesCardsSteps get() {
        return (CollectablesCardsSteps) get(stepsInstanceHolder, CollectablesCardsSteps.class).get();
    }

    // action steps

    @Step("Opening card {cardNumber}")
    public void openCard(int cardNumber) {
        new CollectablesCardsScreen().openCard(cardNumber);
    }

    @Step("Opening all cards")
    public void openAllCards() {
        AppSteps.get().waitForStableResponseFromWebsocket();
        new CollectablesCardsScreen().openAllCards();
    }

    @Step("Clicking continue")
    public void clickContinue() {
        new CollectablesCardsScreen().clickContinue();
    }

    @Step("Opening all cards")
    public void skipAll() {
        new CollectablesCardsScreen().skipAll();
    }

    @Step("Spamming 'Skip all' button")
    public void spamSkipAll() {
        new CollectablesCardsScreen().spamSkipAll(CommonTestConstants.FIVE_COUNT_OF_TAPS);
    }

    @Step("Clicking Next button")
    public void clickNextBtn() {
        new CollectablesCardsScreen().clickNext();
    }

    @Step("Tapping around Next button")
    public void tapAroundNextBtn() {
        new CollectablesCardsScreen().tapAroundNext();
    }

    @Step("Spamming Next button")
    public void spamNextBtn() {
        new CollectablesCardsScreen().spamNext(CommonTestConstants.FIVE_COUNT_OF_TAPS);
    }

    @Step("Tapping around 'Skip All' button")
    public void tapAroundSkipAllBtn() {
        new CollectablesCardsScreen().tapAroundSkipAllButton();
    }

    @Step("Getting points amount for {cardNumber} card")
    public double getCardPointsAmount(int cardNumber) {
        AppSteps.get().waitForStableResponseFromWebsocket();
        return new CollectablesCardsScreen().getCardPointsAmount(cardNumber);
    }

    @Step("Getting trade points amount from bottom bar")
    public double getTradePointsAmountFromBottomBar() {
        return new CollectablesCardsScreen().getBottomBarTradePointsAmount();
    }

    // assertion steps

    @Step("Asserting that 'Collectables Cards' screen is present")
    public void assertThatCollectableCardsScreenIsPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isScreenPresent(), "'Collectables Cards' screen is not present");
    }

    @Step("Asserting that 'Collectables Cards' screen is not present")
    public void assertThatCollectableCardsScreenIsNotPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isScreenNotPresent(), "'Collectables Cards' screen is present");
    }

    @Step("Asserting that 'Collectables Cards' screen is not present with waiting")
    public void assertThatCollectableCardsScreenIsNotPresentWithWaiting() {
        assertion.assertTrue(!new CollectablesCardsScreen().isScreenPresent(DataHolder.getTimeout(Timeout.SHORT_ELEMENT_APPEARANCE_SECONDS)),
                "'Collectables Cards' screen is present");
    }

    @Step("Asserting that 'Next' button is present")
    public void assertThatNextButtonIsPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isNextBtnPresent(), "'Next' button is not present");
    }

    @Step("Asserting that 'Continue' button is present")
    public void assertThatContinueButtonIsPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isContinueBtnPresent(), "'Continue' button is not present");
    }

    @Step("Asserting that 'Skip All' button is present")
    public void assertThatSkipAllButtonIsPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isSkipAllBtnPresent(DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS)),
                "'Skip All' button is not present");
    }

    @Step("Asserting that 'Skip All' button is present with small waiting")
    public void assertThatSkipAllButtonIsPresentWithSmallWaiting() {
        assertion.assertFalse(AwaitilityWrapper.waitForCondition(() -> !new CollectablesCardsScreen().
                        isSkipAllBtnPresent(DataHolder.getTimeout(Timeout.SHORT_ELEMENT_APPEARANCE_SECONDS)),
                DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS)), "'Skip All' button is not present");
    }

    @Step("Asserting that number of unopened cards equal to: {expectedNumberOfCards}")
    public void assertThatNumberOfUnopenedCardsIsEqual(int expectedNumberOfCards) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().getNumberOfUnopenedCards() == expectedNumberOfCards,
                DataHolder.getTimeout(Timeout.DEFAULT_ELEMENT_GET_SECONDS)), "Number of unopened cards is not correct");
    }

    @Step("Asserting that number of opened cards equal to: {expectedNumberOfCards}")
    public void assertThatNumberOfOpenedCardsIsEqual(int expectedNumberOfCards) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().getNumberOfOpenedCards() == expectedNumberOfCards,
                DataHolder.getTimeout(Timeout.DEFAULT_ELEMENT_GET_SECONDS)), "Number of opened cards is not correct");
    }

    @Step("Asserting that maximum number of cards equal to: {expectedNumberOfCards}")
    public void assertThatMaximumNumberOfCardsIsEqual(int expectedNumberOfCards) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().getTotalNumberOfCards() == expectedNumberOfCards,
                DataHolder.getTimeout(Timeout.DEFAULT_ELEMENT_GET_SECONDS)), "Maximum number of cards is not correct");
    }

    @Step("Asserting that maximum topbar assets equal to: {expectedNumberOfTopbarAssets}")
    public void assertThatMaximumNumberOfTopbarAssetsIsEqual(int expectedNumberOfTopbarAssets) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().getTotalNumberOfTopbarAssets() == expectedNumberOfTopbarAssets,
                DataHolder.getTimeout(Timeout.DEFAULT_ELEMENT_GET_SECONDS)), "Maximum number of topbar assets is not correct");
    }

    @Step("Asserting that Cards counter is not present")
    public void assertThatCardsCounterIsNotPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isCardsCounterNotPresent(), "Cards counter is present");
    }

    @Step("Asserting that Count of opened cards is {countOfOpenedCards}")
    public void assertThatCountOfOpenedCardsIs(int countOfOpenedCards) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().getCountOfOpenedCards() == countOfOpenedCards,
                        DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS)),
                "Count of opened cards is not correct");
    }

    @Step("Asserting that Count of unopened cards is '{countOfUnopenedCards}'")
    public void assertThatCountOfUnopenedCardsIs(int countOfUnopenedCards) {
        assertion.assertEquals(new CollectablesCardsScreen().getCountOfUnopenedCards(), countOfUnopenedCards, "Count of unopened cards is not correct");
    }

    @Step("Asserting that Item Asset of opened card is present")
    public void assertThatItemAssetOfOpenedCardsIsPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isOpenedItemAssetPresent(), "Item Asset of opened card is not present");
    }

    @Step("Asserting that Point amount of opened card is present")
    public void assertThatPointAmountOfOpenedCardsIsPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isOpenedPointsAmountPresent(), "Item Asset of opened card is not present");
    }

    @Step("Asserting that Point amount of opened card is not present")
    public void assertThatPointAmountOfOpenedCardsIsNotPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isOpenedPointsAmountNotPresent(), "Point amount of opened cards is present");
    }

    @Step("Asserting that Collectables Points at the Top Bar is present")
    public void assertThatCollectablesPointsAtTopBarIsPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isTopBarCollectablesPointsPresent(), "Collectables Points at the Top Bar is not present");
    }

    @Step("Asserting that Collectables Points at the Top Bar is not present")
    public void assertThatCollectablesPointsAtTopBarIsNotPresent() {
        assertion.assertTrue(new CollectablesCardsScreen().isTopBarCollectablesPointsNotPresent(), "Collectables Points at the Top Bar is present");
    }

    @Step("Asserting that Total points amount is equal to {totalPointsAmount}")
    public void assertThatTotalPointsAmountIsEqualTo(double totalPointsAmount) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().getTotalPointsAmount() == totalPointsAmount,
                DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS)), "Total points amount is not equal to expected");
    }

    @Step("Asserting that bottom bar trade points amount is equal to {totalPointsAmount}")
    public void assertThatBottomBarTradePointsAmountIsEqualTo(double totalPointsAmount) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().getBottomBarTradePointsAmount() == totalPointsAmount,
                DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS)), "Bottom bar trade points amount is not equal to expected");
    }

    @Step("Asserting that Collected Total points amount is equal to {totalPointsAmount} on Collectables cards screen")
    public void assertThatCollectedTotalPointsIsEqualTo(int expectedTotalPoints) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().getCountOfCollectedPoints() == expectedTotalPoints,
                DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS)), "Collected Total points amount is not correct");
    }

    @Step("Asserting that '{state}' card is present on Collectables cards screen")
    public void assertThatCardIsPresent(CardsFrontImageState state) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().isCardPresent(state),
                        DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS)),
                String.format("'%s' card is not present on Collectables cards screen", state));
    }

    @Step("Asserting that '{state}' item is present on top of the Collectables cards screen")
    public void assertThatItemIsPresentOnTopOfScreen(ItemsStateOnTopCardsScreen state) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().isItemPresentOnTopOfScreen(state),
                        DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS)),
                String.format("'%s' item is not present on top of the Collectables cards screen", state));
    }

    @Step("Asserting that '{state}' item is not present on top of the Collectables cards screen")
    public void assertThatItemIsNotPresentOnTopOfScreen(ItemsStateOnTopCardsScreen state) {
        assertion.assertFalse(AwaitilityWrapper.waitForCondition(() -> new CollectablesCardsScreen().isItemPresentOnTopOfScreen(state),
                        DataHolder.getTimeout(Timeout.SHORT_ELEMENT_APPEARANCE_SECONDS)),
                String.format("'%s' item is present on top of the Collectables cards screen", state));
    }

    @Step("Asserting that opened item asset is bronze on Collectables cards screen")
    public void assertThatOpenedItemAssetSpriteIsBronze(CollectablesItem collectablesItem) {
        assertion.assertEquals(new CollectablesCardsScreen().getOpenedItemSprite(collectablesItem), collectablesItem.getBronzeSprite(),
                "Collected item sprite is not bronze");
    }

    @Step("Asserting that Trade points '{tradePoints}' are not present")
    public void assertThatTradePointsAreNotPresent(int tradePoints) {
        assertion.assertTrue(new CollectablesCardsScreen().areTradePointsNotPresent(tradePoints), "Trade points are present");
    }
}
