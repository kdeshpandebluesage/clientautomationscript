package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOSComplAuditPage extends BaseClass {

    // --- Locators ---
    private final String complianceAuditLink = "//span[text()='Compliance Audit']";
    private final String criticalDatesHeader = "//span[text()='Critical Dates']";
    private final String disclosureDatesLink = "//span[text()='Disclosure Dates']";
    private final String initialDisclosureReceivedDateInput = "//input[@name='initialLED_DisclosureRecvDate']";
    private final String saveButton = "//span[text()='Save']";
    private final String savingMask = "//div[not(contains(@style,'display: none'))]//div[contains(@class,'x-mask-msg-text') and text()='Saving...']";

    // Locators for the Disclosure Dates Details Grid & Pop-up
    private final String editIcon = "//div[@data-selenium-id='homeLoanDestinationHistoryGrid']//img[contains(@src,'pencil.png')]";
    private final String sentDateInput = "//input[@name='sentDate']";
    private final String receivedDateInput = "//input[@name='receivedDate']";
    private final String exceptionsTextArea = "//textarea[@name='packageExceptionText']";
    private final String saveComplianceAuditButton = "//div[contains(@id,'disclosurehistoryeditdialog')]//a[@data-selenium-id='saveButton']";
    private final String closeDisclosureDialogButton = "//div[contains(@id,'disclosurehistoryeditdialog')]//img[@class='x-tool-img x-tool-close']";

    /**
     * Constructor for the LOSComplianceAuditPage.
     * @param page The Playwright Page object.
     */
    public LOSComplAuditPage(Page page) {
        super(page);
    }

    /**
     * Executes the full workflow for the Compliance Audit page.
     */
    public void performComplianceAudit() {
        // Step: LOSComplianceAuditLink
        click(complianceAuditLink);

        // Step: LOSVerifyComplianceAudit
        page.locator(criticalDatesHeader).waitFor();

        // Step: LOSDisclosureDateLink
        click(disclosureDatesLink);

        // Step: LOSAComplianceAuditCheck (Handles the complex grid editing logic)
        fillDisclosureDates();

        // Step: LOSEnterDisclosureDate
        fillText(initialDisclosureReceivedDateInput, todayDate());

        // Step: LOSComplianceAuditSave
        click(saveButton);

        // Step: LOSCheckSaveCompliance
        waitForLoadingMaskToDisappear(savingMask);

        // Step: Compliance Audit Page (writetoreport)
        System.out.println("✅ Successfully completed Compliance Audit workflow. Now on page: " + getText(complianceAuditLink));
    }

    /**
     * This private helper method contains the logic to iterate through the
     * disclosure dates grid, click each edit icon, and fill in the dates if the fields are enabled.
     */
    private void fillDisclosureDates() {
        Locator editIcons = page.locator(editIcon);
        editIcons.first().waitFor(); // Wait for the grid to be ready
        int count = editIcons.count();

        if (count == 0) {
            System.out.println("No disclosure date records found to edit.");
            return;
        }

        System.out.println("Found " + count + " disclosure records to process.");

        // Loop through each "edit" icon in the grid
        for (int i = 0; i < count; i++) {
            // Re-query the locator inside the loop to avoid stale element issues
            page.locator(editIcon).nth(i).click();
            page.locator(sentDateInput).waitFor(); // Wait for the dialog to open

            // Check if the 'sentDate' input is disabled
            if (page.locator(sentDateInput).isDisabled()) {
                System.out.println("Record " + (i + 1) + " is disabled, closing dialog.");
                click(closeDisclosureDialogButton);
            } else {
                System.out.println("Record " + (i + 1) + " is enabled, entering dates.");
                String today = todayDate();
                fillText(sentDateInput, today);
                fillText(receivedDateInput, today);
                fillText(exceptionsTextArea, "Exceptions noted by automation.");

                jsClick(saveComplianceAuditButton); // Use JS click for reliability
                waitForLoadingMaskToDisappear("//div[text()='Loading...']");
            }
            // Wait for the dialog to fully close before proceeding to the next iteration
            page.locator(closeDisclosureDialogButton).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
        }
    }
}