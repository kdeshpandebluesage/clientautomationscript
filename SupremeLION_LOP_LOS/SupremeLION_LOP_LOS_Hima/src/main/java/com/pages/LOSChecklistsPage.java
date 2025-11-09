package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LOSChecklistsPage extends BaseClass {

    // --- Locators ---
    private final String homeLink = "//span[text()='Home']";
    private final String checklistsLink = "//span[text()='Checklists']";
    private final String addChecklistItemHeader = "//span[text()='Add Checklist Item']";

    // This locator is more robust as it finds all radio button cells in the grid
    private final String checklistItemRadios = "//div[@data-selenium-id='gridItems']//tr/td[5]";

    private final String saveButton = "//span[text()='Save']";

    /**
     * Constructor for the LOSChecklistsPage.
     * @param page The Playwright Page object.
     */
    public LOSChecklistsPage(Page page) {
        super(page);
    }

    /**
     * Navigates to the Checklists page and completes all available items.
     */
    public void completeAllChecklistItems() {
        // Step: LOSHomeThird
        click(homeLink);

        // Navigate to the Checklists page
        click(checklistsLink);

        // Step: LOSVerifychecklistsPage
        page.locator(addChecklistItemHeader).waitFor();

        // Steps: ClearChecklists through LOSChecklists36
        // This logic finds all radio buttons in the 5th column and clicks them all.
        Locator radioButtons = page.locator(checklistItemRadios);
        radioButtons.first().waitFor(); // Wait for the grid to load

        int itemCount = radioButtons.count();
        System.out.println("Found " + itemCount + " checklist items to complete.");

        for (int i = 0; i < itemCount; i++) {
            radioButtons.nth(i).click();
        }

        // Step: SaveChecklists
        click(saveButton);
        waitForLoadingMaskToDisappear("//div[text()='Saving...']");

        // Step: ChecklistsPage (writetoreport)
        System.out.println("✅ Successfully completed all checklist items. Now on page: " + getText(checklistsLink));
    }
}
