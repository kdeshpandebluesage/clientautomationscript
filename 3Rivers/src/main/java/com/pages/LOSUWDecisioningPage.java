package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LOSUWDecisioningPage extends BaseClass {

    // --- Locators ---
    private final String underwritingLink = "//span[text()='Underwriting']";
    private final String decisioningLink = "//span[text()='Decisioning']";
    private final String loadingMask = "//div[not(contains(@style,'display: none'))]//div[contains(@class,'x-mask-msg-text') and text()='Loading...']";
    private final String decisionTypeDropdown = "(//div[contains(@class,'x-form-arrow-trigger')])[2]";
    private final String addButton = "//span[text()='Add']";

    // Targeting the first two checkboxes in the decision grid for 'Approved' and 'Send'
    private final String approvedCheckbox = "(//td[contains(@class,'x-grid-cell-checkcolumn')])[1]";
    private final String approvedSendCheckbox = "(//td[contains(@class,'x-grid-cell-checkcolumn')])[2]";

    private final String saveButton = "//span[text()='Save']";
    private final String savingMask = "//div[text()='Saving...']";

    /**
     * Constructor for the LOSUWDecisioningPage.
     * @param page The Playwright Page object.
     */
    public LOSUWDecisioningPage(Page page) {
        super(page);
    }

    /**
     * Executes the full workflow for making an underwriting decision.
     */
    public void makeDecision() {
        // Step: LOSUnderwritingThird
        click(underwritingLink);

        // Step: LOSCredit Decisioning
        click(decisioningLink);

        // Step: LOSDecisioningLoading
        waitForLoadingMaskToDisappear(loadingMask);

        // Step: LOSDecisioningType
        selectFirstDropdownOption(decisionTypeDropdown);

        // Step: LOSAdd Decision Type
        click(addButton);

        // Step: LOSApprovedDecision & LOSApprovedDecisionScnd
        // Clicks the 'Approved' and 'Send' checkboxes for the new decision row.
        click(approvedCheckbox);
        click(approvedSendCheckbox);

        // Step: LOSSaveDecisioning
        click(saveButton);

        // Step: LOSCheckSaveDecisioning & LOSCheckSaveDecisioning111
        waitForLoadingMaskToDisappear(savingMask);

        // Step: Decisioning Page (writetoreport)
        System.out.println("✅ Successfully saved underwriting decision. Now on page: " + getText(decisioningLink));
        //================================================================================
        // NEW STEPS ADDED
        //================================================================================

        // Create an instance of the Loan Status page and call the new verification method.
        LOSLoanStatusPage loanStatusPage = new LOSLoanStatusPage(page);
        loanStatusPage.verifyLoanStatusIs("0520 - Approved - Clear to Close");
    }
}