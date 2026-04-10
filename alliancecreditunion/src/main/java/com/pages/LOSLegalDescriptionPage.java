

package com.pages;

import com.microsoft.playwright.Page;

// The class extends your BaseClass to inherit all the UI helper methods
public class LOSLegalDescriptionPage extends BaseClass {

    // --- Locators defined as String constants based on the screenshot ---
    private final String legalDescriptionLink = "//span[text()='Legal Description']";
    private final String checkLegalDescriptionPage = "//label[text()='Is the property Held in a revocable Trust']";
    private final String savePropertyChangesYesBtn = "//span[text()='Yes']";
    private final String mannerTitleHeldInDropdown = "//input[@name='mannerTitleHeldIn']/../following-sibling::td/div";
    private final String estateWillBeHeldRadio = "//label[text()='Fee Simple']";
    private final String namesWillBeHeldTextField = "//textarea[@name='namesTitleHeldIn']";
    private final String legalDescriptionMethodRadio = "//label[text()='Metes and Bounds']";
    private final String lotNumberTextField = "//input[@name='lotNumber']";
    private final String blockNumberTextField = "//input[@name='blockNumber']";
    private final String censusTractTextField = "//input[@name='censusTractNumber']";
    private final String taxParcelIDTextField = "//input[@name='taxParcelNumber']";
    private final String propertyHeldInTrustRadio = "//label[text()='No']";
    private final String mannerTitleHeldTextField = "//input[@name='titleMannerType']";
    private final String saveButton = "//span[text()='Save']";

    private final String addTaxParcelIdButton = "//span[text()='Add Tax Parcel ID']";
    private final String checkAddTaxParcelIdsModal = "//span[text()='Add Tax Parcel ID']";
    private final String addInModalButton = "(//span[text()='Add'])[2]";
    private final String taxIdGridCell = ".//td[contains(@class,'headerId-gridcolumn')]";
    private final String taxIdGridInput = "//input[@name='taxParcelID']";
    private final String okInModalButton = "//span[text()='Ok']";

    /**
     * Constructor to initialize the Page object from the BaseClass.
     * @param page The Playwright Page object.
     */
    public LOSLegalDescriptionPage(Page page) {
        // Pass the page object to the BaseClass constructor
        super(page);
    }

    /**
     * Fills out the Legal Description page by following the steps from the provided screenshot.
     * This method orchestrates calls to the helper methods in BaseClass.
     */
    public void addLegalDescriptionDetails() {
        // Step 1: Navigate to the Legal Description page
        click(legalDescriptionLink);

        // Step 2: Wait for the page to be loaded by checking for a unique element
     //   page.waitForSelector(savePropertyChangesYesBtn);

        // Step 3: Save property changes by clicking "Yes"
     //   click(savePropertyChangesYesBtn);

        // Step 4: Select an option from the "Manner Title Held In" dropdown
        // Assuming "Sample Language" is the text of the option to select
        selectDropdownByText(mannerTitleHeldInDropdown, "Joint Tenancy Other Than Spouse");

        // Step 5: Select the "Estate will be held" radio button
        click(estateWillBeHeldRadio);

        // Step 6: Enter text into the "Names will be held" text field
        fillText(namesWillBeHeldTextField, "John Doe and Jane Doe");

        // Step 7: Select the "Legal Description method" radio button
        click(legalDescriptionMethodRadio);

        // Step 8-11: Fill in the various identification number fields
        fillText(lotNumberTextField, "123");
        fillText(blockNumberTextField, "45B");
        fillText(censusTractTextField, "1270.23");
        // --- CORRECTED LOGIC for Add Tax Parcel ID ---
        // 1. Click the button to open the modal
        click(addTaxParcelIdButton);
        page.waitForSelector(addInModalButton); // Wait for modal to open

        // 2. Click "Add" inside the modal to create a new grid row
        click(addInModalButton);

        // 3. Click the first grid cell to make it editable

  //      click(taxIdGridCell);

        // 4. Fill the input field that appears in the grid
        page.waitForSelector(taxIdGridInput);
        fillText(taxIdGridInput, "1568-123-504");

        // 5. Click "Ok" to save and close the modal
        click(okInModalButton);
        // --- END OF CORRECTED LOGIC ---

        // Step 12: Select the "Property Held in revocable trust" radio button
    //    click(propertyHeldInTrustRadio);

        // Step 13: Enter text into the "Manner Title held" text field
        fillText(mannerTitleHeldTextField, "Tenants in Common");

        // Step 14: Save the Legal Description details
        click(saveButton);

        // This would typically be followed by waiting for a "Saving..." mask to disappear
        // For example: waitForLoadingMaskToDisappear("//div[text()='Saving...']");

        System.out.println("Successfully filled out and saved the Legal Description page.");
    }
}