

package com.pages;

import com.microsoft.playwright.Page;

// The class extends your BaseClass to inherit all the UI helper methods
public class LOSLoanTermsPage extends BaseClass {

    // --- Locators defined as String constants based on the screenshot ---
    private final String productPricingFeesPanel = "//span[text()='Product / Pricing / Fees']";
    private final String loanTermsLink = "//span[text()='Loan Terms']";
    private final String loadingMask = "//div[not(contains(@style,'display: none;'))]/div[contains(@class,'x-mask-loading')]";
    private final String mortgageTypeInput = "//input[@name='mortgageTypeId']";
    private final String saveButton = "//a[@data-selenium-id='btnLoanAppSave']";

    // --- Locators for the FHA Details modal (inferred from the action 'entercomfhadetails') ---
    private final String fhaDetailButton = "//a[@data-selenium-id='btnFHADetail']";
    private final String agencyCaseNumberInput = "//input[@name='agencyCaseNumber2']";
    private final String calculateButton = "//a[@data-selenium-id='calculate_btn']";
    private final String processingMask = "//div[text()='Processing']";


    /**
     * Constructor to initialize the Page object from the BaseClass.
     * @param page The Playwright Page object.
     */
    public LOSLoanTermsPage(Page page) {
        // Pass the page object to the BaseClass constructor
        super(page);
    }

    /**
     * Fills out the Loan Terms page by following the steps from the provided screenshot.
     */
    public void enterLoanTermsDetails() {
        // Step 1: Click on the 'Product / Pricing / Fees' panel
        click(productPricingFeesPanel);

        // Step 2: Click on the 'Loan Terms' link
        click(loanTermsLink);

        // Step 3: Wait for the initial page loading mask to disappear
        waitForLoadingMaskToDisappear(loadingMask);

        // Step 4: Get the mortgage type to check if it's FHA
        String mortgageType = getAttribute(mortgageTypeInput, "value");

        // Step 5: If the mortgage type is FHA, enter the FHA details
        if (mortgageType != null && mortgageType.contains("FHA")) {
            System.out.println("Mortgage Type is FHA. Entering FHA details...");
            enterFhaDetails();
        }

        // Step 6: Save the loan terms
        click(saveButton);

        // Step 7: Wait for the saving mask to disappear
        waitForLoadingMaskToDisappear(loadingMask);

        // Step 8: Report completion
        System.out.println("Successfully filled out and saved the Loan Terms page.");
    }

    /**
     * Private helper method to handle the FHA Details modal.
     */
    private void enterFhaDetails() {
        click(fhaDetailButton);
        // Wait for the modal to appear if necessary, e.g., page.waitForSelector("//span[text()='FHA Loan Detail']");
        fillText(agencyCaseNumberInput, "111");
        click(calculateButton);
        waitForLoadingMaskToDisappear(processingMask);
        // Assuming the FHA modal has its own save/close button, which might be needed here.
        // For this example, we assume it closes automatically or the main save button handles it.
    }
}