package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LOSAssetVerificationPage extends BaseClass {

    // --- Navigation Locators ---
    private final String underwritingLink = "//span[text()='Underwriting']";
    private final String assetVerificationLink = "//span[text()='Asset Verification']";

    // --- Main Page Locators ---
    private final String assetVerificationHeader = "//span[text()='Asset Verification']";
    private final String copyAmountsIcon = "//img[@data-qtip='Copy value to verified and used']";
    private final String saveButton = "//span[text()='Save']";
    private final String savingMask = "//div[text()='Saving...']";

    // --- Editable Grid Locators ---
    // This locator finds each main row in the verification grid.
    private final String assetGridRows = "//div[contains(@id,'assetverificationgrid')]//tr[contains(@class,'x-grid-row')]";

    // These are relative locators to find specific cells WITHIN a row.
    private final String mannerVerifiedCell = "td:nth-child(5)"; // 5th column
    private final String dateVerifiedCell = "td:nth-child(7)";   // 7th column
    private final String firstCellInRow = "td:nth-child(1)";     // Used to click away and save an edit

    // This is the generic locator for the input field that appears when a cell is edited.
    private final String activeGridInput = "//input[contains(@class,'x-form-focus')]";

    /**
     * Constructor for the LOSAssetVerificationPage.
     * @param page The Playwright Page object.
     */
    public LOSAssetVerificationPage(Page page) {
        super(page);
    }

    /**
     * Completes the verification workflow for all asset records found on the page.
     */
    public void verifyAllAssets() {
        // Prerequisite: Navigate to the Underwriting section
        click(underwritingLink);

        // Step: LOSAssetVerification
        click(assetVerificationLink);

        // Step: VerifyAssetRecords - Wait for the page to load
        page.locator(assetVerificationHeader).waitFor();

        // Step: ClickAllAssetsCopyAmounts
        Locator copyIcons = page.locator(copyAmountsIcon);
        copyIcons.first().waitFor();
        int icon_count = copyIcons.count();
        for (int i = 0; i < icon_count; i++) {
            copyIcons.nth(i).click();
        }

        // Step: LOSAssetsVerificationManner
        // This helper method edits the "Manner Verified" column for all rows
        editAllGridRows(mannerVerifiedCell, "Statement");

        // Step: LOSAssetsVerificationDates
        // This helper method edits the "Date" column for all rows
        editAllGridRows(dateVerifiedCell, todayDate());

        // Step: LOSSaveAssetsAmountVerified & checkSaveAssetVerification
        click(saveButton);
        waitForLoadingMaskToDisappear(savingMask);

        // Step: Asset Verification Page (writetoreport)
        System.out.println("✅ Successfully saved all asset verifications. Now on page: " + getText(assetVerificationLink));
    }

    /**
     * A private helper method to edit a specific column for all rows in the grid.
     * It simulates clicking a cell, entering a value, and clicking away.
     *
     * @param cellToClickSelector The CSS selector for the cell/column to edit within a row.
     * @param valueToFill The text value to enter into the cell's input field.
     */
    private void editAllGridRows(String cellToClickSelector, String valueToFill) {
        Locator rows = page.locator(assetGridRows);
        rows.first().waitFor();
        int rowCount = rows.count();

        for (int i = 0; i < rowCount; i++) {
            Locator currentRow = rows.nth(i);

            // Click the specific cell in the current row to activate the input editor
            currentRow.locator(cellToClickSelector).click();

            // Fill the input field that has now appeared and has focus
            fillText(activeGridInput, valueToFill);

            // Click on the first cell of the row to de-focus the input and save the change
            currentRow.locator(firstCellInRow).click();
        }
    }
}