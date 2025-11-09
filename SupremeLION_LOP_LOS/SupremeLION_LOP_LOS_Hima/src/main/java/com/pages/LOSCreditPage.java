package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LOSCreditPage extends BaseClass {

    // --- Main Locators ---
    private final String borrowerPropertyLink = "//span[text()='Borrower / Property']";
    private final String creditReportLink = "//span[text()='Credit Report']";
    private final String orderCreditReportsHeader = "//span[text()='Order Credit Reports']";
    private final String orderCreditReportButton = "//span[text()='Order Credit Reports']";

    // --- Borrower Linking Locators ---
    private final String ungroupedBorrowerCheckboxes = "//div[@data-selenium-id='ct_ungrouped']//input[@role='checkbox']";
    private final String createLinkButton = "//span[text()='Create Link']";
    private final String saveLinkButton = "//span[text()='Save']";
    private final String ungroupButton = "//a[contains(@data-selenium-id,'ungroup')]";

    // --- Order Credit Pop-up Locators ---
    private final String orderCreditReportsPopup = "//span[text()='Order Credit Reports']";
    private final String applicantOrderCheckboxes = "//div[@data-selenium-id='applicantOrder']//input[@role='checkbox']";
    private final String addToOrderButton = "//span[text()='Add to Order']";
    private final String referenceNumberInput = "(//input[contains(@id,'textfieldcustom')])";
    private final String ediProviderDropdown = "//input[@name='ediProviderTypeId']";
    private final String creditAgencyDropdown = "//input[@name='creditAgencyId']";
    private final String creditReportTypeDropdown = "//input[@name='creditReportTypeId']";
    private final String submitOrderButton = "//span[text()='Submit Order']";
    private final String savingMask = "//div[text()='Saving...']";
    private final String nextButton = "//span[text()='NEXT']";

    /**
     * Constructor for the LOSCreditPage.
     * @param page The Playwright Page object.
     */
    public LOSCreditPage(Page page) {
        super(page);
    }

    /**
     * Executes the full workflow for ordering a credit report.
     * @param ediProvider The EDI Provider to select (e.g., "Real EC").
     * @param creditAgency The Credit Agency to select (e.g., "Factual Data").
     */
    public void orderCreditReport(String ediProvider, String creditAgency) {
        // Step: LOSBCProcessingPage1
        click(borrowerPropertyLink);

        // Step: LOSCreditScreen
        click(creditReportLink);

        // Step: LOSCheckCreditScreen
        page.locator(orderCreditReportsHeader).waitFor();

        // Handle linking multiple borrowers if necessary
        linkBorrowersIfNeeded();

        // Step: LOSOrderCreditReport & LOSCheckReportPopUP
        click(orderCreditReportButton);
        page.locator(orderCreditReportsPopup).waitFor();

        // Step: LOSClickFirstBorrowerOrder & LOSAddBorrowersToOrder
        // Select all available borrowers in the pop-up
        Locator borrowerCheckboxes = page.locator(applicantOrderCheckboxes);
        for (int i = 0; i < borrowerCheckboxes.count(); i++) {
            borrowerCheckboxes.nth(i).click();
        }
        click(addToOrderButton);

        // Step: LOSClearCredit
        clearField(referenceNumberInput);

        // Step: LOSCreditSelectEdiProvider
        selectDropdownByText(ediProviderDropdown, ediProvider);

        // Step: LOSCreditAgencyProvider
        selectDropdownByText(creditAgencyDropdown, creditAgency);

        // Step: LOSCreditReportType
        selectFirstDropdownOption(creditReportTypeDropdown);

        // Step: LOSSubmitCreditReport & LOSCheckSaveLoadReport
        click(submitOrderButton);
        waitForLoadingMaskToDisappear(savingMask);

        // Step: LOSWaitForCreditReport
        page.locator(nextButton).waitFor();

        // Step: LOSCredit Page (writetoreport)
        System.out.println("✅ Successfully ordered credit report. Now on page: " + getText(creditReportLink));
    }

    /**
     * Checks if there are multiple ungrouped borrowers and links them if needed.
     */
    private void linkBorrowersIfNeeded() {
        Locator ungroupedCheckboxes = page.locator(ungroupedBorrowerCheckboxes);
        ungroupedCheckboxes.first().waitFor(); // Ensure the elements are ready

        if (ungroupedCheckboxes.count() > 1) {
            System.out.println("Multiple ungrouped borrowers found. Linking them now.");
            ungroupedCheckboxes.nth(0).click();
            ungroupedCheckboxes.nth(1).click();
            click(createLinkButton);
            click(saveLinkButton);
            page.locator(ungroupButton).waitFor(); // Wait for confirmation that they are linked
        }
    }
}