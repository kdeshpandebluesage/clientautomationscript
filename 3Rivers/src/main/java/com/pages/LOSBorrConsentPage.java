package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LOSBorrConsentPage extends BaseClass {

    // --- Locators ---
    private final String borrowerPropertyLink = "//span[text()='Borrower / Property']";
    private final String borrowerConsentInfoLink = "//span[text()='Borrower Consent Info']";
    private final String borrowerConsentHeader = "//span[text()='Borrower Consent Information']";
    private final String saveButton = "//span[text()='Save']";

    // "Intent to Proceed" Grid Locators
    private final String intentToProceedCheckboxes = "//div[contains(@id,'IntenttoProceed')]//td[2]//img";
    private final String gridCellToClickForEditing = "//div[contains(@id,'IntenttoProceed')]//tr[contains(@class,'x-grid-data-row')]/td[3]";
    private final String consentTypeInput = "//input[@name='consentAuthCommTypeId']";
    private final String firstCellInGrid = "//div[contains(@id,'IntenttoProceed')]//tr[contains(@class,'x-grid-data-row')]//td[1]";

    /**
     * Constructor for the LOSBorrConsentPage.
     * @param page The Playwright Page object.
     */
    public LOSBorrConsentPage(Page page) {
        super(page);
    }

    /**
     * Executes the full workflow for providing borrower consent.
     */
    public void provideBorrowerConsent() {
        // Step: LOSBCProcessingPage
        click(borrowerPropertyLink);

        // Step: LOSBCInfoTreepanel
        click(borrowerConsentInfoLink);
        page.locator(borrowerConsentHeader).waitFor();

        // Step: LOSBCIntentToProcededDetails
        enterIntentToProceedDetails();

        // Step: LOSSaveBCinfo
        click(saveButton);
        waitForLoadingMaskToDisappear("//div[text()='Saving...']");

        // Step: Borrower Consent Page (writetoreport)
        System.out.println("✅ Successfully completed Borrower Consent workflow. Now on page: " + getText(borrowerConsentHeader));
    }

    /**
     * This private helper method contains the logic for the "Intent to Proceed" section.
     * It checks all the checkboxes and then fills out the corresponding grid rows.
     */
    private void enterIntentToProceedDetails() {
        // Check all available checkboxes in the grid
        Locator checkboxes = page.locator(intentToProceedCheckboxes);
        checkboxes.first().waitFor();
        int checkboxCount = checkboxes.count();
        for (int i = 0; i < checkboxCount; i++) {
            checkboxes.nth(i).click();
        }

        // This loop replicates the original 'clickOnMaskAndEnterValue' method.
        // It clicks a cell to activate an input, fills the input, then clicks away to save the row.
        Locator editableCells = page.locator(gridCellToClickForEditing);
        int rowCount = editableCells.count();
        for (int i = 0; i < rowCount; i++) {
            editableCells.nth(i).click();

            // The input field becomes active after the click.
            // Assuming it's a dropdown, we select the option. If it's a text box, use fillText().
            selectDropdownByText(consentTypeInput, "Written");

            // Click on a different cell in the row to de-focus the input and save the change.
            page.locator(firstCellInGrid).nth(i).click();
        }
    }
}