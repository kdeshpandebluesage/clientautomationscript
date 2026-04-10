package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LOSEmploymentVerificationPage extends BaseClass {

    // --- Navigation Locators ---
    private final String underwritingLink = "//span[text()='Underwriting']";
    private final String employmentVerificationLink = "//span[text()='Employment Verification']";

    // --- Main Page Locators ---
    private final String verificationRecordSections = "//div[contains(@id,'baseform')]/fieldset";
    private final String saveButton = "//a[@data-selenium-id='saveButton']";
    private final String savingMask = "//div[text()='Saving...']";

    // --- Editable Grid Cell Locators (to click and activate inputs) ---
    private final String startDateCell = "(.//td[contains(@class,'headerId-datecolumncustom')])[5]";
    private final String verifiedByNameCell = "(.//td[contains(@class,'x-grid-cell-headerId-gridcolumn')])[11]";
    private final String verifiedByTitleCell = "(.//td[contains(@class,'x-grid-cell-headerId-gridcolumn')])[12]";
    private final String telephoneCell = "(.//td[contains(@class,'x-grid-cell-headerId-gridcolumn')])[13]";
    private final String mannerVerifiedCell = "(.//td[contains(@class,'x-grid-cell-headerId-gridcolumn')])[14]";
    private final String documentDateCell = "(.//td[contains(@class,'x-grid-cell-headerId-datecolumncustom')])[2]";

    // --- Input Field Locators (appear after clicking a cell) ---
    private final String startDateInput = "//input[@name='employedFromDate']";
    private final String verifiedByNameInput = "//input[@name='employmentVerifiedByName']";
    private final String verifiedByTitleInput = "//input[@name='employmentVerifiedByTitle']";
    private final String telephoneInput = "//input[@name='telephoneNumber']";
    private final String mannerVerifiedInput = "//input[@name='verificationTypeId']";
    private final String documentDateInput = "//input[@name='verificationDocumentDate']";

    /**
     * Constructor for the LOSEmploymentVerificationPage.
     * @param page The Playwright Page object.
     */
    public LOSEmploymentVerificationPage(Page page) {
        super(page);
    }

    /**
     * Completes the verification workflow for all employment records found on the page.
     */
    public void verifyAllEmployments() {
        // Step: LOSUnderwritingSecond
        click(underwritingLink);

        // Step: LOSEmployment Verification
        click(employmentVerificationLink);

        // Step: VerifyEmpRecords - Wait for the page to load
        page.locator(verificationRecordSections).first().waitFor();

        Locator employmentRecords = page.locator(verificationRecordSections);
        int recordCount = employmentRecords.count();
        System.out.println("Found " + recordCount + " employment records to verify.");

        // Loop through each employment record section on the page
        for (int i = 0; i < recordCount; i++) {
            Locator currentRecord = employmentRecords.nth(i);
            System.out.println("Verifying record " + (i + 1) + "...");

            // Fill out the verification form for the current record
            // The pattern is: click a cell, then fill the input that appears.
            currentRecord.locator(startDateCell).click();
            currentRecord.locator(startDateInput).fill(todayDate());

            currentRecord.locator(verifiedByNameCell).click();
            currentRecord.locator(verifiedByNameInput).fill("Automation Verifier");

            currentRecord.locator(verifiedByTitleCell).click();
            currentRecord.locator(verifiedByTitleInput).fill("QA Analyst");

            currentRecord.locator(telephoneCell).click();
            currentRecord.locator(telephoneInput).fill("(555) 123-4567");

            currentRecord.locator(mannerVerifiedCell).click();
            currentRecord.locator(mannerVerifiedInput).fill("Phone");

            currentRecord.locator(documentDateCell).click();
            currentRecord.locator(documentDateInput).fill(todayDate());
        }

        // Step: SaveEmpVerification
        click(saveButton);
        waitForLoadingMaskToDisappear(savingMask);

        // Step: Employment Verification Page (writetoreport)
        System.out.println("✅ Successfully saved all employment verifications. Now on page: " + getText(employmentVerificationLink));
    }
}