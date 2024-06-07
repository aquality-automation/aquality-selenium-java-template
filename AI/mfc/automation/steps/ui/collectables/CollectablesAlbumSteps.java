package com.myproject.automation.steps.ui.collectables;

import com.myproject.automation.base.BaseSteps;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.drivers.appium.AutomationDriver;
import com.myproject.automation.elements.ws.WsImage;
import com.myproject.automation.enums.Attribute;
import com.myproject.automation.enums.Orb;
import com.myproject.automation.enums.collectables.CollectablesItem;
import com.myproject.automation.enums.collectables.ItemsState;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.screens.collectables.CollectablesAlbumScreen;
import com.myproject.automation.screens.collectables.CollectablesLoadingScreen;
import com.myproject.automation.screens.collectables.ItemsDetailsScreen;
import com.myproject.automation.steps.api.ApiSteps;
import com.myproject.automation.utils.waiters.AwaitilityWrapper;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Point;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectablesAlbumSteps extends BaseSteps {

    private static ThreadLocal<BaseSteps> stepsInstanceHolder = new InheritableThreadLocal<>();

    public static synchronized CollectablesAlbumSteps get() {
        return (CollectablesAlbumSteps) get(stepsInstanceHolder, CollectablesAlbumSteps.class).get();
    }

    // action steps

    @Step("Clicking Close button")
    public void clickCloseBtn() {
        new CollectablesAlbumScreen().clickCloseBtn();
    }

    @Step("Clicking Skip button")
    public void clickSkipBtn() {
        new CollectablesAlbumScreen().clickSkipBtn();
    }

    @Step("Clicking Skip button")
    public void clickSkipBtnIfPresent() {
        new CollectablesAlbumScreen().clickSkipBtnIfPresent();
    }

    @Step("Clicking {position} item")
    public void clickItem(int position) {
        new CollectablesAlbumScreen().clickItem(position);
    }

    @Step("Opening completed item by tapping on tick")
    public void tapTick() {
        new CollectablesAlbumScreen().tapTick();
    }

    @Step("Tapping 'Piglet Bank' location")
    public void tapPigletBankLocation(Point pigletBankPoint) {
        AutomationDriver.tapByCoordinates(pigletBankPoint);
    }

    @Step("Getting item name")
    public String getItemNameOnItemDetailsScreen() {
        return new ItemsDetailsScreen().getItemName();
    }

    @Step("Go to next item")
    public void goToNextItemOnItemDetailsScreen() {
        new ItemsDetailsScreen().openNextItem();
    }

    @Step("Go to previous item")
    public void goToPreviousItemOnItemDetailsScreen() {
        new ItemsDetailsScreen().openPreviousItem();
    }

    @Step("Click Trade button")
    public void clickTradeButton() {
        new CollectablesAlbumScreen().clickTrade();
    }

    @Step("Getting Trade points")
    public double getTradePoints() {
        return new CollectablesAlbumScreen().getTradePoints();
    }

    @Step("Click Info button")
    public void clickInfoButton() {
        new CollectablesAlbumScreen().clickInfoBtn();
    }

    // assertion steps

    @Step("Asserting that 'Collectables Album' is present")
    public void assertThatCollectablesAlbumIsPresent() {
        assertion.assertTrue(new CollectablesAlbumScreen().isScreenPresent(), "'Collectables Album' is not present");
    }

    @Step("Asserting that 'Collectables Album' is not present")
    public void assertThatCollectablesAlbumIsNotPresent() {
        assertion.assertTrue(new CollectablesAlbumScreen().isScreenNotPresent(), "'Collectables Album' is present");
    }

    @Step("Asserting that 'Loading screen' is present")
    public void assertThatLoadingScreenIsPresent() {
        assertion.assertTrue(new CollectablesLoadingScreen().isScreenPresent(), "'Loading screen' is not present");
    }

    @Step("Asserting that Lobby decor item {itemNumber} progress value is correct on 'Collectables Album'")
    public void assertThatLobbyDecorItemProgressIsCorrect(int itemNumber, String progressValue) {
        assertion.assertTrue(AwaitilityWrapper.waitForCondition(() -> new CollectablesAlbumScreen().getProgressValue(itemNumber).equals(progressValue),
                        DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS)),
                "Lobby decor item progress value is not correct");
    }

    @Step("Asserting that Close button is present on the 'Collectables Album' screen")
    public void assertThatCloseBtnOnCollectablesAlbumIsPresent() {
        assertion.assertTrue(new CollectablesAlbumScreen().isCloseBtnPresent(),
                "Close button is not present on the 'Collectables Album' screen");
    }

    @Step("Asserting that Item name is not the same as expected on the Item details screen")
    public void assertItemsNameIsNotSameOnItemDetailsScreen(String itemName) {
        assertion.assertNotEquals(new ItemsDetailsScreen().getItemName(), itemName, "Item name is the same as expected on the Item details screen");
    }

    @Step("Asserting that Item name is the same as expected on the Item details screen")
    public void assertItemsNameIsSameOnItemDetailsScreen(String itemName) {
        assertion.assertEquals(new ItemsDetailsScreen().getItemName(), itemName, "Item name is not the same as expected on the Item details screen");
    }

    @Step("Asserting that Reward Set text is the same as expected on the Item details screen")
    public void assertThatRewardSetTextIsSameAsExpected(String rewardText) {
        assertion.assertEquals(new CollectablesAlbumScreen().getRewardText(), rewardText, "Reward Set text is not the same as expected on the Item details screen");
    }

    @Step("{countItems} lobby decor items of '{state}' state are displayed on the Collectables Album screen")
    public void assertThatDecorItemsAreDisplayed(int countItems, ItemsState state) {
        List<WsImage> listOfItemImages = new CollectablesAlbumScreen().getListOfItemImages();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(listOfItemImages.size(), countItems, "Count lobby decor items is not equal to expected");
        listOfItemImages.forEach(v -> softAssert.assertTrue(v.getAttribute(Attribute.SPRITE).contains(state.getSpriteAttr()),
                String.format("Items are not displayed in '%s' state", state.getSpriteAttr())));
        softAssert.assertAll("Lobby decor items are not displayed on the Collectables Album screen as expected");
    }

    @Step("Asserting that lobby decor items of bronze state are displayed on the Collectables Home Page")
    public void assertThatLobbyDecorItemsAreDisplayedInBronzeState() {
        SoftAssert softAssert = new SoftAssert();
        Stream.of(CollectablesItem.values()).forEach(x -> softAssert.assertTrue(new CollectablesAlbumScreen().isItemInStatePresent(x, x.getBronzeSprite()),
                String.format("Lobby decor item %s is not displayed in bronze state on the Collectables Home Page", x)));
        softAssert.assertAll();
    }

    @Step("Asserting that lobby decor items of silver state are displayed on the Collectables Home Page")
    public void assertThatLobbyDecorItemsAreDisplayedInSilverState() {
        SoftAssert softAssert = new SoftAssert();
        Stream.of(CollectablesItem.values()).forEach(x -> softAssert.assertTrue(new CollectablesAlbumScreen().isItemInStatePresent(x, x.getSilverSprite()),
                String.format("Lobby decor item %s is not displayed in silver state on the Collectables Home Page", x)));
        softAssert.assertAll();
    }

    @Step("Asserting that lobby decor items of base state are displayed on the Collectables Home Page")
    public void assertThatLobbyDecorItemsAreDisplayedInBaseState() {
        SoftAssert softAssert = new SoftAssert();
        Stream.of(CollectablesItem.values()).forEach(x -> {
            if (StringUtils.isEmpty(x.getBaseSprite())) {
                softAssert.assertTrue(new CollectablesAlbumScreen().isItemNotPresent(x),
                        String.format("Lobby decor item %s is not displayed in base state on the Collectables Home Page", x));
            } else {
                softAssert.assertTrue(new CollectablesAlbumScreen().isItemInStatePresent(x, x.getBaseSprite()),
                        String.format("Lobby decor item %s is not displayed in base state on the Collectables Home Page", x));
            }
        });
        softAssert.assertAll();
    }

    @Step("Asserting that complete checkmark is not present")
    public void assertThatCompleteTickIsNotPresent() {
        assertion.assertTrue(new CollectablesAlbumScreen().isCompleteTickNotPresent(), "Complete checkmark is present");
    }

    @Step("Asserting that complete checkmark is present")
    public void assertThatCompleteTickIsPresent() {
        assertion.assertTrue(new CollectablesAlbumScreen().isCompleteTickPresent(), "Complete checkmark is not present");
    }

    @Step("Asserting that lightning reward for completing set is present")
    public void assertThatLightningRewardIsPresent() {
        assertion.assertTrue(new CollectablesAlbumScreen().isLightningRewardPresent(), "Lightning reward is not present");
    }

    @Step("Asserting that coin reward for completing set is present")
    public void assertThatCoinRewardIsPresent() {
        assertion.assertTrue(new CollectablesAlbumScreen().isCoinRewardPresent(), "Coin reward is not present");
    }

    @Step("{itemsCount} lobby decor items with correct points are displayed on the Collectables Album screen")
    public void assertThatCorrectPointsAmountIsDisplayedUnderEachItem(String userApiToken, int itemsCount, int level) {
        SoftAssert softAssert = new SoftAssert();
        IntStream.range(0, itemsCount).forEach(item ->
                softAssert.assertEquals(new CollectablesAlbumScreen().getProgressValue(item + 1), ApiSteps.get().getCollectablesProgressForUser(userApiToken, item, level),
                        String.format("Lobby decor item %d progress value is not correct", item)));
        softAssert.assertAll();
    }

    @Step("Asserting that Orb reward for completing set is present")
    public void assertThatOrbRewardIsPresent() {
        assertion.assertTrue(new CollectablesAlbumScreen().isOrbRewardPresent(), "Orb reward is not present");
    }

    @Step("Asserting that Orb reward with type '{orbType}' is present")
    public void assertThatOrbRewardIsPresent(Orb orbType) {
        assertion.assertTrue(new CollectablesAlbumScreen().isOrbRewardPresent(orbType),
                String.format("Orb reward with type '%s' is not present ", orbType));
    }

    @Step("Asserting that lobby decor items of gold state are displayed on the Collectables Home Page")
    public void assertThatLobbyDecorItemsAreDisplayedInGoldState() {
        SoftAssert softAssert = new SoftAssert();
        Stream.of(CollectablesItem.values()).forEach(x -> softAssert.assertTrue(new CollectablesAlbumScreen().isItemInStatePresent(x, x.getGoldSprite()),
                String.format("Lobby decor item %s is not displayed in gold state on the Collectables Home Page", x)));
        softAssert.assertAll();
    }

    @Step("Asserting that amount of 'Complete ticks' is equal to expected: {expectedAmount}")
    public void assertThatAmountOfCompleteTicksIsEqualTo(int expectedAmount) {
        assertion.assertEquals(new CollectablesAlbumScreen().getAmountOfCompleteTickImages(), expectedAmount,
                "Amount of 'Complete ticks' is not equal to expected");
    }

    @Step("Asserting that Trade points are equal to expected: {tradePoints}")
    public void assertThatTradePointsAreEqualTo(double tradePoints) {
        assertion.assertEquals(new CollectablesAlbumScreen().getTradePoints(), tradePoints, "Trade points are not equal to expected");
    }

    @Step("Asserting that Info button is present on the 'Collectables Album' screen")
    public void assertThatInfoBtnOnCollectablesAlbumIsPresent() {
        assertion.assertTrue(new CollectablesAlbumScreen().isInfoBtnPresent(),
                "Info button is not present on the 'Collectables Album' screen");
    }
}
