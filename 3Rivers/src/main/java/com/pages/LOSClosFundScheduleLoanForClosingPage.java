package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LOSClosFundScheduleLoanForClosingPage extends BaseClass {

    // --- Navigation Locators ---
    private final String closingFundingLink = "//span[text()='Closing / Funding']";
    private final String closingInfoLink = "//span[text()='Closing Information']";
    private final String closingTitleProviderLink = "//span[text()='Closing/Title Provider']";
    private final String settlementMailingLink = "//span[text()='Settlement / Mailing location']";
    private final String closingLocationLink = "//span[text()='Closing Location']";
    private final String mainSaveButton = "(//a[@data-selenium-id='saveButton'])[1]";

    // --- Provider Setup Locators ---
    private final String addProviderButton = "//span[text()='Add Closing/Title Provider']";
    private final String providerTypeDropdown = "//input[@name='serviceProviderLicenseAuthorityName']";
    private final String searchProvidersButton = "//a[@data-selenium-id='searchProviders']";
    private final String providerGridRow = "//div[text()='Active']/ancestor::tr";
    private final String providerOkButton = "//a[@data-selenium-id='btnOK']";
    private final String licenseAuthDateInput = "//input[@name='serviceProviderLicenseAuthorityDate']";
    private final String contactPersonInput = "//input[@name='contactPersonName']";
    private final String providerContactTypeDropdown = "//input[@name='contactLicenseAuthorityName']";
    private final String contactLicenseDateInput = "//input[@name='contactLicenseDate']";
    private final String mobileInput = "//input[@name='phone']";
    private final String providerEmailInput = "//input[@name='emailAddress']";
    private final String providerFaxInput = "//input[@name='faxNumber']";
    private final String agentEmailInput = "//input[@name='contactAgentEmail']";
    private final String providerSaveButton = "(//a[@data-selenium-id='saveButton'])[2]";

    // --- Settlement & Mailing Locators ---
    private final String copyVendorDropdown = "//input[@name='copyVendorId']";
    private final String additionalInstructionsInput = "//textarea[@name='additionalInstructions']";
    private final String settlementSaveButton = "(//a[@data-selenium-id='saveButton'])[2]";

    // --- Closing Information & Dates Locators ---
    private final String settlementDateInput = "//input[@name='anticipatedSettlementDate']";
    private final String estimatedClosingDateInput = "//input[@name='estimatedClosingDate']";
    private final String requestDateInput = "//input[@name='clsPkgRequestedDate']";
    private final String approvalDateInput = "//input[@name='clsPkgApprovalDate']";
    private final String fundingRequestNumberInput = "//input[@name='fundingRequestNumber']";
    private final String mersDesignatedMortgageeInput = "//input[@name='MERSDesignatedMortgagee']";
    private final String registrationStatusDropdown = "//input[@name='mersRegistrationStatusId']";
    private final String actualExpirationDateInput = "//input[@name='actualRescissionDate']";
    private final String actualFundingDateInput = "//input[@name='actualFundingDate']";
    private final String closingStatusDropdown = "//input[@name='closingStatusTypeId']";
    private final String waitingPeriodWaivedRadio = "//label[text()='Initial Loan Estimate 7-day Waiting Period Waived']/../input";

    // --- Closing Location Locators ---
    private final String copyFromClosingCompanyButton = "//span[text()='Copy from Closing Company']";
    private final String copyClosingOkButton = "//span[text()='OK']";
    private final String savedPopupOkButton = "//a[@data-selenium-id='ok']";

    public LOSClosFundScheduleLoanForClosingPage(Page page) {
        super(page);
    }

    /**
     * Orchestrates the entire multi-step process for scheduling a loan for closing.
     */
    public void scheduleLoanForClosing() {
        navigateAndSetupProvider();
        configureSettlementAndMailing();
        enterClosingDatesAndDetails();
        copyClosingLocation();

        click(mainSaveButton);
        page.locator(savedPopupOkButton).waitFor();
        click(savedPopupOkButton);

        System.out.println("✅ Successfully scheduled loan for closing.");
    }

    /**
     * Part 1: Navigates and sets up the closing/title provider.
     */
    private void navigateAndSetupProvider() {
        System.out.println("Setting up Closing/Title provider...");
        click(closingFundingLink);
        click(closingInfoLink);
        click(closingTitleProviderLink);
        click(addProviderButton);

        page.locator(providerTypeDropdown).waitFor();
        selectFirstDropdownOption(providerTypeDropdown);
        click(searchProvidersButton);

        page.locator(providerGridRow).waitFor();
        doubleClick(providerGridRow);
        click(providerOkButton);

        // Fill out provider details form
        fillText(licenseAuthDateInput, todayDate());
        fillText(contactPersonInput, "John Doe");
        selectFirstDropdownOption(providerContactTypeDropdown);
        fillText(contactLicenseDateInput, todayDate());
        fillText(mobileInput, "(555) 555-1234");
        fillText(providerEmailInput, "provider@test.com");
        fillText(providerFaxInput, "(555) 555-5678");
        fillText(agentEmailInput, "agent@test.com");

        jsClick(providerSaveButton);
        waitForLoadingMaskToDisappear("//div[text()='Loading...']");
    }

    /**
     * Part 2: Configures the settlement and mailing instructions.
     */
    private void configureSettlementAndMailing() {
        System.out.println("Configuring settlement and mailing location...");
        click(settlementMailingLink);
        page.locator(copyVendorDropdown).waitFor();
        selectFirstDropdownOption(copyVendorDropdown);
        fillText(additionalInstructionsInput, "Automated test settlement instructions.");
        jsClick(settlementSaveButton);
    }

    /**
     * Part 3: Enters all necessary dates and details in the Closing Information section.
     */
    private void enterClosingDatesAndDetails() {
        System.out.println("Entering closing dates and details...");
        click(closingInfoLink);
        page.locator(settlementDateInput).waitFor();

        // Fill dates
        fillText(settlementDateInput, addDaysToToday(3));
        fillText(estimatedClosingDateInput, addDaysToToday(3));
        fillText(requestDateInput, todayDate());
        fillText(approvalDateInput, todayDate());
        fillText(actualExpirationDateInput, addDaysToToday(30));
        fillText(actualFundingDateInput, addDaysToToday(4));

        // Fill other details
        fillText(fundingRequestNumberInput, "FRN" + System.currentTimeMillis() / 1000);
        fillText(mersDesignatedMortgageeInput, "MERS-" + System.currentTimeMillis() / 1000);
        selectFirstDropdownOption(registrationStatusDropdown);
        selectFirstDropdownOption(closingStatusDropdown);

        click(waitingPeriodWaivedRadio);
    }

    /**
     * Part 4: Navigates to the Closing Location and copies details from the closing company.
     */
    private void copyClosingLocation() {
        System.out.println("Copying closing location details...");
        click(closingLocationLink);
        page.locator(copyFromClosingCompanyButton).waitFor();
        click(copyFromClosingCompanyButton);
        click(copyClosingOkButton);
    }
}