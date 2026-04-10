package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class LOSLoanFoldersPage extends BaseClass {

    private final Random rand = new Random();

    // --- Locators ---
    private final String loanFoldersLink = "//span[text()='Loan Folders']";
    private final String uploadDocButton = "//span[text()='Upload Document']";
    private final String fileInput = "//input[@name='file']";
    private final String fileDescInput = "//input[@name='filedesc']";
    private final String entitledFolderDropdown = "//input[@name='filefolder']/../following-sibling::td/div";
    private final String categoryDropdown = "//input[@name='filecategory']/../following-sibling::td/div";
    private final String docTypeDropdown = "//input[@name='filetype']/../following-sibling::td/div";
    private final String borrowerDropdown = "//input[@name='borrowername']/../following-sibling::td/div";
    private final String uploadFinalButton = "//span[text()='Upload']";
    private final String successMessage = "//span[text()='Success']";
    private final String okButton = "//span[text()='OK']";

    // --- Locators for dynamic elements ---
    private final String dropdownListItems = "//div[contains(@class,'x-boundlist-floating') and not(contains(@style,'display: none'))]//li";
    private final String conditionsGridRows = "//div[@id='fieldset-2141-body']//tr[contains(@class, 'x-grid-row')]";


    // --- Constructor ---
    public LOSLoanFoldersPage(Page page) {
        super(page);
    }

    // --- Methods ---

    public void addLoanFolderDetails(String filePath) {
        page.locator(loanFoldersLink).click();
        page.locator(uploadDocButton).click();

        // Use a short wait to ensure the dialog is ready
        page.waitForTimeout(1000);

        // --- Entitled Folder Dropdown Logic ---
        page.locator(entitledFolderDropdown).click();
        String selectedFolder = selectRandomDropdownOptionAndGetText();
        System.out.println("Randomly selected folder: " + selectedFolder);

        // --- Chained Category and Document Type Dropdown Logic ---
        selectRandomChainedDropdowns();

        page.locator(fileDescInput).fill("Test File Description");
        page.locator(fileInput).setInputFiles(Paths.get(filePath));

        // Use a switch statement to handle different cases
        switch (selectedFolder.toLowerCase()) {
            case "conditions":
                System.out.println("Handling 'Conditions' folder type...");
                Locator rows = page.locator(conditionsGridRows);
                rows.first().waitFor(); // Wait for the grid to load
                List<Locator> allRows = rows.all();
                if (!allRows.isEmpty()) {
                    int randomIndex = rand.nextInt(allRows.size());
                    allRows.get(randomIndex).click();
                    System.out.println("Clicked on condition row index: " + randomIndex);
                } else {
                    System.out.println("No condition rows found to select.");
                }
                break;

            case "borrower":
                System.out.println("Handling 'Borrower' folder type...");
                page.locator(borrowerDropdown).click();
                selectFirstDropdownOption();
                break;

            case "home loan":
                System.out.println("Handling 'Home Loan' folder type. No extra action needed.");
                break;

            default:
                System.out.println("No specific action defined for folder: " + selectedFolder);
                break;
        }

        // --- Finalize and Confirm Upload ---
        page.locator(uploadFinalButton).click();
        page.locator(successMessage).waitFor();
        page.locator(okButton).click();
    }

    // --- Helper Methods ---

    private void selectRandomChainedDropdowns() {
        page.locator(categoryDropdown).click();
        selectRandomDropdownOption();

        page.locator(docTypeDropdown).click();
        selectRandomDropdownOption();
    }

    private void selectFirstDropdownOption() {
        Locator options = page.locator(dropdownListItems);
        options.first().waitFor();
        if (options.count() > 0) {
            options.first().click();
        }
    }

    private void selectRandomDropdownOption() {
        Locator options = page.locator(dropdownListItems);
        options.first().waitFor();
        int count = options.count();
        if (count > 0) {
            options.nth(rand.nextInt(count)).click();
        }
    }

    private String selectRandomDropdownOptionAndGetText() {
        Locator options = page.locator(dropdownListItems);
        options.first().waitFor();
        int count = options.count();
        if (count > 0) {
            int randomIndex = rand.nextInt(count);
            Locator selectedOption = options.nth(randomIndex);
            String optionText = selectedOption.innerText();
            selectedOption.click();
            return optionText;
        }
        return "";
    }
}