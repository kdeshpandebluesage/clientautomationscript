package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LOSLoanConditionsPage extends BaseClass {

    // --- Navigation Locators ---
    private final String homeLink = "//span[text()='Home']";
    private final String loanConditionsLink = "//span[text()='Loan Conditions']";

    // --- Main Page Locators ---
    private final String addManualButton = "//a[@data-selenium-id='new']";
    private final String addStandardButton = "//a[@data-selenium-id='addStandard']";
    private final String satisfiedCheckboxes = "//div[contains(@class,'grid-with-col-lines')]//td[9]//div[contains(@class,'checkcolumn')]";
    private final String waiverCheckboxes = "//div[contains(@class,'grid-with-col-lines')]//td[10]//div[contains(@class,'checkcolumn')]";
    private final String saveButton = "//span[text()='Save']";
    private final String savingMask = "//div[text()='Saving...']";

    // --- Manual Condition Dialog Locators ---
    private final String appliesToHeader = "//span[text()='Add a Condition']";
    private final String conditionCategoryDropdown = "//input[@name='condCategoryTypeId']";
    private final String descriptionInput = "//input[@name='shortText']";
    private final String responsiblePartyDropdown = "//input[@name='condPartyTypeId']";
    private final String whenRequiredDropdown = "//input[@name='condRequiredTypeId']";
    private final String detailedDescriptionInput = "//textarea[@name='longText']";
    private final String priorityDropdown = "//input[@name='priorityLevel']";
    private final String saveAndAddButton = "//a[@data-selenium-id='saveAddBt']";
    private final String closeManualDialogButton = "//div[contains(@id,'retailloanconditioncreatedialog')]//img";

    // --- Standard Condition Dialog Locators ---
    private final String assetsTreeNode = "//span[text()='Assets']";
    private final String firstStandardConditionCheckbox = "(//div[contains(@id,'stdcondgrid')]//div[contains(@class,'checkcolumn')])[1]";
    private final String saveStandardConditionButton = "(//a[@data-selenium-id='saveButton'])[2]";


    /**
     * Constructor for the LOSLoanConditionsPage.
     * @param page The Playwright Page object.
     */
    public LOSLoanConditionsPage(Page page) {
        super(page);
    }

    /**
     * Executes the full workflow for Loan Conditions: adds a manual condition,
     * adds a standard condition, and then satisfies all open conditions.
     */
    public void processLoanConditions() {
        click(homeLink);
        click(loanConditionsLink);
        page.locator(addManualButton).waitFor();

    //    addManualCondition();
     //   addStandardCondition();
        satisfyAllOpenConditions();

        System.out.println("✅ Successfully processed all loan conditions. Now on page: " + getText(loanConditionsLink));
    }

    /**
     * Opens the dialog to add a new manual condition and fills it out.
     */
    private void addManualCondition() {
        System.out.println("Adding a manual condition...");
        click(addManualButton);
        page.locator(appliesToHeader).waitFor();

        selectFirstDropdownOption(conditionCategoryDropdown);
        fillText(descriptionInput, "Automated Condition Description");
        selectFirstDropdownOption(responsiblePartyDropdown);
        selectFirstDropdownOption(whenRequiredDropdown);
        fillText(detailedDescriptionInput, "This is a detailed description for the automated test condition.");
        selectFirstDropdownOption(priorityDropdown);

        jsClick(saveAndAddButton);
        waitForLoadingMaskToDisappear(savingMask);
        click(closeManualDialogButton);
    }

    /**
     * Opens the dialog to add a standard condition, expands 'Assets', and selects the first one.
     */
    private void addStandardCondition() {
        System.out.println("Adding a standard condition...");
        click(addStandardButton);
        page.locator(assetsTreeNode).waitFor();

        doubleClick(assetsTreeNode); // Double-click to expand the category
        click(firstStandardConditionCheckbox); // Select the first available condition under Assets

        jsClick(saveStandardConditionButton);
        waitForLoadingMaskToDisappear(savingMask);
    }

    /**
     * Clicks all available "Satisfied" and "Waived" checkboxes on the main grid and saves.
     */
    private void satisfyAllOpenConditions() {
        System.out.println("Satisfying all open conditions...");

        // Satisfy all conditions
        Locator satisfiedChecks = page.locator(satisfiedCheckboxes);
        satisfiedChecks.first().waitFor();
        for (int i = 0; i < satisfiedChecks.count(); i++) {
            satisfiedChecks.nth(i).click();
        }
        click(saveButton);
        waitForLoadingMaskToDisappear(savingMask);

        // Waive all conditions (if waiver boxes are present)
        if (isElementVisible(waiverCheckboxes)) {
            System.out.println("Waiver boxes found, checking all...");
            Locator waiverChecks = page.locator(waiverCheckboxes);
            for (int i = 0; i < waiverChecks.count(); i++) {
                waiverChecks.nth(i).click();
            }
            click(saveButton);
            waitForLoadingMaskToDisappear(savingMask);
        }
    }
}