package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOSLiabilityVerificationPage extends BaseClass {

    // --- Navigation Locators ---
    private final String underwritingLink = "//span[text()='Underwriting']";
    private final String liabilityVerificationLink = "//span[text()='Liability Verification']";

    // --- Main Page Locators ---
    private final String liabilityVerificationHeader = "//span[text()='Liability Verification']";
    private final String copyAmountsIcon = "//img[@data-qtip='Copy amounts to verified and used']";
    private final String saveButton = "//span[text()='Save']";
    private final String savingMask = "//div[text()='Saving...']";

    // --- Editable Grid Locators ---
    private final String liabilityGridRows = "//div[contains(@id,'liabilityverificationgrid')]//tr[contains(@class,'x-grid-row')]";
    // This locator finds the second column cell, which is the "Amount" to be double-clicked.
    private final String amountCellInRow = "td:nth-child(2)";

    // --- Verification Pop-up Dialog Locators ---
    private final String verificationDialog = "//div[contains(@id,'liabilityverificationdetaildialog')]";
    private final String verifiedBalanceInput = "//input[@name='verifiedBalAmt']";
    private final String verifiedPaymentInput = "//input[@name='verifiedPmtAmt']";
    private final String verifiedNumOfPaymentsInput = "//input[@name='verifiedNumOfPmts']";
    private final String mannerVerifiedInput = "//input[@name='mannerVerified']";
    private final String verificationDocumentDateInput = "//input[@name='verificationDocumentDate']";
    private final String dialogOkButton = "//div[contains(@id,'liabilityverificationdetaildialog')]//span[text()='Ok']";

    /**
     * Constructor for the LOSLiabilityVerificationPage.
     * @param page The Playwright Page object.
     */
    public LOSLiabilityVerificationPage(Page page) {
        super(page);
    }

    /**
     * Completes the verification workflow for all liability records found on the page.
     */
    public void verifyAllLiabilities() {
        // Prerequisite: Navigate to the Underwriting section
        click(underwritingLink);

        // Step: LOSLiabilityVerificationInfo
        click(liabilityVerificationLink);

        // Step: VerifyLiabilityRecords - Wait for the page to load
        page.locator(liabilityVerificationHeader).waitFor();

        // Step: ClickAllLiabilityCopyAmounts
        Locator copyIcons = page.locator(copyAmountsIcon);
        copyIcons.first().waitFor();
        int icon_count = copyIcons.count();
        for (int i = 0; i < icon_count; i++) {
            copyIcons.nth(i).click();
        }

        // --- Main Loop to edit each liability record ---
        Locator liabilityRows = page.locator(liabilityGridRows);
        int rowCount = liabilityRows.count();
        System.out.println("Found " + rowCount + " liability records to verify.");

        for (int i = 0; i < rowCount; i++) {
            Locator currentRow = liabilityRows.nth(i);

            // Step: LOSLiabityAmountID - Double-click the amount cell to open the edit dialog
            currentRow.locator(amountCellInRow).dblclick();

            // Wait for the verification dialog to appear
            page.locator(verificationDialog).waitFor();

            // Fill out the fields in the pop-up dialog
            fillText(verifiedBalanceInput, "1500.00");
            fillText(verifiedPaymentInput, "150.00");
            fillText(verifiedNumOfPaymentsInput, "10");
            fillText(mannerVerifiedInput, "Liability Statement");
            fillText(verificationDocumentDateInput, todayDate());

            // Step: LOSSaveLiabilityusedinfo - Click "Ok" to close the dialog
            click(dialogOkButton);

            // Wait for the dialog to disappear before continuing to the next record
            page.locator(verificationDialog).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
        }

        // Step: LOSsaveLiabiltyVerification
        click(saveButton);
        waitForLoadingMaskToDisappear(savingMask);

        // Step: Liability Verification Page (writetoreport)
        System.out.println("✅ Successfully saved all liability verifications. Now on page: " + getText(liabilityVerificationLink));
    }
}