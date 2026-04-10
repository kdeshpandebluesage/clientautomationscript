package com.pages;

import com.microsoft.playwright.Page;

public class LOSAUSPage extends BaseClass {

    // --- Navigation Locators ---
    private final String homeLink = "//span[text()='Home']";
    private final String automatedUnderwritingLink = "//span[text()='Automated Underwriting']";

    // --- Main AUS Page Locators ---
    private final String ausOnlyOrderHeader = "//label[text()='AUS Only Order']";
    private final String fannieMaeRadioButton = "//label[text()='Fannie Mae Desktop Underwriter (DU)']";
    private final String freddieMacRadioButton = "//label[text()='Freddie Mac Loan Product Advisor (LPA)']";
    private final String freddieMacProductInfo = "//div[contains(text(), 'FHLMC')]";
    private final String sendRequestButton = "//span[text()='Send Request']";
    private final String submittingRequestMask = "//div[contains(text(),'Submitting request')]";
    private final String errorPopupOkButton = "//div[@class='x-css-shadow'][contains(@style,'display: block')]//a[@data-selenium-id='ok']";

    // --- Manual/External AUS Dialog Locators ---
    private final String addManualDecisionButton = "//a[@data-selenium-id='addManualDecisionButton']";
    private final String ausTypeDropdown = "//input[@name='ausTypeId']";
    private final String ausResultDropdown = "//input[@name='ausResultCodeId']";
    private final String submissionDateInput = "//input[contains(@name,'datefieldcustom')]";
    private final String applicationIdInput = "(//input[contains(@name,'ApplicationId')])[2]";
    private final String saveAusButton = "//a[@data-selenium-id='saveButton']";
    private final String savingMask = "//div[text()='Saving...']";

    /**
     * Constructor for the LOSAUSPage.
     * @param page The Playwright Page object.
     */
    public LOSAUSPage(Page page) {
        super(page);
    }

    /**
     * Runs the Automated Underwriting Service. It dynamically chooses between
     * Fannie Mae and Freddie Mac and includes a fallback to manually add a
     * result if the automated submission fails.
     */
    public void runAutomatedUnderwriting() {
        click(homeLink);

        // Step: LOSAutomatdUnderwriting & CheckLOSAutomatdUnderwril
        click(automatedUnderwritingLink);
        page.locator(ausOnlyOrderHeader).waitFor();

        // Step: LOSSelectFanie (with conditional logic)
        // Check if there is information indicating a Freddie Mac loan, otherwise default to Fannie Mae.
        if (isElementVisible(freddieMacProductInfo)) {
            System.out.println("Freddie Mac product detected. Selecting LPA.");
            click(freddieMacRadioButton);
        } else {
            System.out.println("Defaulting to Fannie Mae DU.");
            click(fannieMaeRadioButton);
        }

        // Step: LOSFanSendRequest & WaitForFannieRecord
        click(sendRequestButton);
        waitForLoadingMaskToDisappear(submittingRequestMask);

        // Step: Sendrequesterror & AcceptAutomatedError (Fallback logic)
        // If an error pop-up appears, click OK and then manually add an external AUS result.
        if (isElementVisible(errorPopupOkButton)) {
            System.out.println("Automated submission failed. Clicking OK on error and falling back to manual entry.");
            click(errorPopupOkButton);
            manuallyAddExternalAUS("DU", "Accept / Eligible", "AutoEntry123");
        }

        // Step: AUS Page (writetoreport)
        System.out.println("✅ Successfully completed Automated Underwriting workflow. Now on page: " + getText(automatedUnderwritingLink));
    }

    /**
     * Manually adds an external AUS result to the system.
     * @param ausType The type of AUS (e.g., "DU" or "LPA").
     * @param ausResult The result (e.g., "Accept / Eligible").
     * @param appId A custom Application ID.
     */
    public void manuallyAddExternalAUS(String ausType, String ausResult, String appId) {
        System.out.println("Adding manual AUS result...");
        click(addManualDecisionButton);
        page.locator(ausTypeDropdown).waitFor(); // Wait for dialog to open

        selectDropdownByText(ausTypeDropdown, ausType);
        selectDropdownByText(ausResultDropdown, ausResult);
        fillText(submissionDateInput, todayDate());
        fillText(applicationIdInput, appId);

        click(saveAusButton);
        waitForLoadingMaskToDisappear(savingMask);
    }
}