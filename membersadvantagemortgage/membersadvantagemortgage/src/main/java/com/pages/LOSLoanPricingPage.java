package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class LOSLoanPricingPage extends BaseClass {

    // --- Locators from the provided steps ---
    private final String pricingPanelLink = "//span[text()='Product / Pricing / Fees']";
    private final String loanPricingLink = "//span[text()='Loan Pricing']";
   // private final String pricingLoadMask = "//div[not(contains(@style,'display: none'))]//div[contains(@class,'x-mask-msg-text') and text()='Loading...']";
    private final String pricingLoadMask = "//div[not(contains(@style,'display: none'))]//div[contains(@class,'x-mask-msg-text') and text()='Loading Loan Pricing...']";
    private final String checkPricingScreen = "//span[text()='Select Rate']";
    private final String lockPeriodDropdown = "//input[@name='rateLockTypeJd']"; // Using input name for stability
    private final String selectRateButton = "//span[text()='Select Rate']";
    private final String pricingGridLoadMask = "//div[not(contains(@style,'display: none'))]//div[contains(@class,'x-mask-msg-text') and text()='Loading Pricing...']";
    private final String checkSelectRateScreen = "//span[text()='Select Pricing']";
    private final String selectRateLowestPrice = "//td[contains(@class,'span_link')]";
    private final String requestPricingConcessionButton = "//span[text()='Request Pricing Concession']";
    private final String checkTotalRateAndPoints = "//input[@name='changeReasonTypeId']";
    private final String cocReasonsDropdown = "//input[@name='changeReasonTypeId']";
    private final String cocReasonsButton = "//a[@data-selenium-id='cocReasons_btn']";
    private final String cocLoadMask = "//div[not(contains(@style,'display: none'))]//div[contains(@class,'x-mask-msg-text') and text()='Loading...']";
    private final String checkCocWindow = "(//span[text()='COC Reasons'])[2]";
    private final String cocFirstReasonCheckbox = "(//div[@data-selenium-id='cocReasonGrid']//tr[contains(@class,'x-grid-row')])[1]";
    private final String cocOkButton = "//a[@data-selenium-id='btnOK']";
    private final String checkSelectedCocReason = "//label[text()='Application Date']";
    private final String saveLoanPricingButton = "//span[text()='Save']";
    private final String saveLoanPricingMask = "//div[not(contains(@style,'display: none'))]//div[contains(@class,'x-mask-msg-text') and text()='Saving...']";
    private final String finalLoanPricingPage = "//span[text()='Loan Pricing']";

    /**
     * Constructor for the LOSLoanPricingPage.
     * @param page The Playwright Page object.
     */
    public LOSLoanPricingPage(Page page) {
        super(page);
    }

    /**
     * Executes the full loan pricing and COC workflow.
     */
    public void priceLoanAndHandleCOC() {
        // Step: LOSPricingpanel
        click(pricingPanelLink);

        // Step: LOSLoanPricing
        click(loanPricingLink);

        // Step: CheckLoanPricingLoad
        waitForLoadingMaskToDisappear(pricingLoadMask);

        // Step: LOSCheckPricingScreen
        page.waitForSelector(checkPricingScreen);

        // Step: LOSLockPeriod (Selects the first available option)
       // selectFirstDropdownOption(lockPeriodDropdown);

        // Step: LOSClickSelctRate
        click(selectRateButton);

        // Step: ChecklosLoanpricingGrid
        waitForLoadingMaskToDisappear(pricingGridLoadMask);

        // Step: LOCCheckSelectRate
        page.waitForSelector(checkSelectRateScreen);

        // Step: LOSSelectRate (Custom logic to select the lowest rate)
        selectLowestRate();

        // Step: RequestPricingConcessionBtn
        click(requestPricingConcessionButton);

        // Step: CheckTotalRateandPoints
        page.waitForSelector(checkTotalRateAndPoints);

        // Step: COCReasons (Selects the first available reason)
        selectFirstDropdownOption(cocReasonsDropdown);

        // Step: COCReasonsbtn
        click(cocReasonsButton);

        // Step: CheckCOCLoad
        waitForLoadingMaskToDisappear(cocLoadMask);

        // Step: CheckCOCWindow
        page.waitForSelector(checkCocWindow);

        // Step: COCFirstReason
        click(cocFirstReasonCheckbox);

        // Step: COCOKbtn
        click(cocOkButton);

        // Step: CheckSelectedCOCReason
        page.waitForSelector(checkSelectedCocReason);

        // Step: SaveLoanPricing
        click(saveLoanPricingButton);

        // Step: CheckSaveLoanPricing
        waitForLoadingMaskToDisappear(saveLoanPricingMask);

        // Step: LOSPricing Page (Writetoreport)
        System.out.println("✅ Successfully completed Loan Pricing workflow. Now on page: " + getText(finalLoanPricingPage));
    }

    /**
     * Finds all available rates, identifies the one with the lowest numerical value, and clicks it.
     * This corresponds to the 'selectpricinglowrate' step.
     */
    private void selectLowestRate() {
        page.waitForSelector(selectRateLowestPrice);
        Locator rateOptions = page.locator(selectRateLowestPrice);
        int count = rateOptions.count();

        if (count == 0) {
            throw new PlaywrightException("No pricing rates found to select from.", null);
        }

        double lowestRate = Double.MAX_VALUE;
        int indexOfLowestRate = -1;

        for (int i = 0; i < count; i++) {
            try {
                String rateText = rateOptions.nth(i).innerText().replace("%", "").trim();
                double currentRate = Double.parseDouble(rateText);

                if (currentRate < lowestRate) {
                    lowestRate = currentRate;
                    indexOfLowestRate = i;
                }
            } catch (NumberFormatException e) {
                System.out.println("Could not parse rate text: " + rateOptions.nth(i).innerText());
            }
        }

        if (indexOfLowestRate != -1) {
            System.out.println("Lowest rate found: " + lowestRate + "%. Selecting it now.");
            rateOptions.nth(indexOfLowestRate).click();
        } else {
            throw new PlaywrightException("Could not determine the lowest rate to select.", null);
        }
    }
}