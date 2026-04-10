

package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

// The class extends your BaseClass
public class LOSBorrowerRelationshipsPage extends BaseClass {

    // --- Locators defined as String constants ---
    private final String borrpropPanel = "//span[text()='Borrower / Property']";
    private final String firstBorrowerExpansion = "(//tr[contains(@data-recordid,'borrower.summary')]//img[contains(@class,'x-tree-expander')])[1]";
    private final String firstRelationshipLink = "//span[text()='Relationships']";
    private final String checkRelationshipsPage = "//span[text()='Borrower Name']";
    private final String relationshipTypeDropdown = "//div[text()='Not Associated']";
    private final String spouseOption = "//li[text()='Spouse']";
    private final String saveButton = "//a[@id='borrowerrelationship-savebutton']";
    private final String savingMask = "//div[not(contains(@style,'display: none;'))]/div[contains(@class,'x-mask-loading')]/div[text()='Saving...']";

    /**
     * Constructor to initialize the Page object from the BaseClass.
     * @param page The Playwright Page object.
     */
    public LOSBorrowerRelationshipsPage(Page page) {
        // Pass the page object to the BaseClass constructor
        super(page);
    }

    /**
     * Expands the first borrower section and sets their relationship to 'Spouse'.
     */
    public void addSpouseRelationship() {
        // All actions now use page.locator() with the string variables

        page.locator(borrpropPanel).click();
      //  page.waitForSelector("text=Loan Summary");
        page.locator(firstBorrowerExpansion).click();
        // --- SCROLL CODE ADDED HERE ---
        // 1. Define the locator for the element you need to scroll to.
        Locator relationshipLink = page.locator(firstRelationshipLink);
        // 2. Scroll the element into view.
        relationshipLink.scrollIntoViewIfNeeded();
        page.locator(firstRelationshipLink).click();
        page.locator(checkRelationshipsPage).waitFor(); // Wait for the page to load
      //  page.locator(relationshipTypeDropdown).click();
      //  page.locator(spouseOption).click();

      //  page.locator(saveButton).click();

        // Wait for the "Saving..." mask to appear and then disappear
      //  page.waitForSelector(".loader", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
     //   page.waitForSelector(".loader", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));

    }
}