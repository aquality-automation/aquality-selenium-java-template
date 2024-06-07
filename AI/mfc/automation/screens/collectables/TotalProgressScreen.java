package com.myproject.automation.screens.collectables;

import com.myproject.automation.base.BaseScreen;
import com.myproject.automation.base.DataHolder;
import com.myproject.automation.constants.RegexExpressions;
import com.myproject.automation.elements.ws.WsButton;
import com.myproject.automation.elements.ws.WsImage;
import com.myproject.automation.elements.ws.WsText;
import com.myproject.automation.enums.config.Timeout;
import com.myproject.automation.exceptions.PMException;
import com.myproject.automation.utils.ParseUtil;
import com.myproject.automation.utils.RegexUtils;

import static com.myproject.automation.constants.CollectablesConstants.CONTINUE;
import static com.myproject.automation.constants.CollectablesConstants.UPGRADE;

public class TotalProgressScreen extends BaseScreen {

    private static final WsText TXT_TOTAL_PROGRESS_HEADER = new WsText("CollectablesCardsSummaryView>Header>SummaryViewHeader_Text TextMeshProUGUI",
            "Total progress header Text");
    private final WsButton btnContinueClaim = new WsButton("ClaimButton Button", "Continue Claim Button");
    private final WsButton btnContinueClose = new WsButton("CloseButton Button", "Continue Close Button");
    private final WsText txtCollectedTotalPoints = new WsText("TradePoints_Text",
            "Collected Total points Text");
    private final WsText txtTradePoints = new WsText("TradePointsContainer>TradePointsCount_Text",
            "Trade points Text");
    private final WsImage imgJackpotCard = new WsImage("CollectablesJackpotCardSummary(Clone)>Container>AssetContainer Image",
            "Jackpot Card Image");

    public TotalProgressScreen() {
        super(TXT_TOTAL_PROGRESS_HEADER, "Total Progress screen");
    }

    public void clickContinueBtn() {
        if (isContinueClaimPresent()) {
            btnContinueClaim.click();
        } else {
            throw new PMException("Continue button is not present");
        }
    }

    public void clickUpgradeBtn() {
        if (isUpgradeClaimPresent()) {
            btnContinueClaim.click();
        } else if (isUpgradeClosePresent()) {
            btnContinueClose.click();
        } else {
            throw new PMException("Upgrade button is not present");
        }
    }

    public int getCountOfCurrentPoints() {
        return Integer.parseInt(RegexUtils.getMatch(RegexExpressions.COUNT_OF_CARDS_OR_POINTS, txtCollectedTotalPoints.getText(), 1));
    }

    public void spamContinue(int countOfTouches) {
        if (isContinueClaimPresent()) {
            btnContinueClaim.spamElement(countOfTouches, 1);
        } else {
            throw new PMException("Continue button is not present");
        }
    }

    public boolean isUpgradeClaimPresent() {
        return isButtonPresent(btnContinueClaim, UPGRADE);
    }

    public boolean isContinueClaimPresent() {
        return isButtonPresent(btnContinueClaim, CONTINUE);
    }

    public boolean isUpgradeClosePresent() {
        return isButtonPresent(btnContinueClose, UPGRADE);
    }

    public double getTradePointsAmount() {
        return ParseUtil.getDoubleValueWithComma(txtTradePoints.getText());
    }

    public boolean isTradePointsNotPresent() {
        return txtTradePoints.waitForDoesNotExist();
    }

    public boolean isJackpotCardPresent() {
        return imgJackpotCard.waitForIsPresent();
    }

    private boolean isButtonPresent(WsButton btn, String text) {
        if (btn.waitForIsPresent(DataHolder.getTimeout(Timeout.SHORT_CONDITION_SECONDS))) {
            return btn.getText().equals(text);
        } else {
            return false;
        }
    }
}
