
//
//import com.microsoft.playwright.Locator;
//import com.microsoft.playwright.options.WaitForSelectorState;
//
//public class LOSLoanFoldersPage extends BasePage {
//    public LOSLoanFoldersPage(com.microsoft.playwright.Page page) {
//        super(page);
//    }
//    public void LopApplication() throws InterruptedException {
//
//        page.click("//span[text()='Application']");
//        page.click("//span[text()='Loan Application - URLA']");
//
//        page.waitForTimeout(300);
//        page.locator("//span[text()='OK']").waitFor(new Locator.WaitForOptions()
//                .setState(WaitForSelectorState.VISIBLE));
//        page.click("//span[text()='OK']");
//        System.out.println("Loan Application Navigation is done .");
//    }
//}
package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class LOSLoanFoldersPage_Old extends BaseClass {

   // private final Page page;
    private final Random rand = new Random();

    // --- Locators ---
    private final String loanFoldersLink = "//tr[contains(@id,'main.loan.section.loanFolders')]";
    private final String uploadDocButton = "//span[text()='Upload Document']";
   // private final String uploadDocButton = "(//span[text()='Upload Document'])[1]";
    private final String uploadDialog = "//div[contains(@class,'x-window') and not(contains(@style,'display: none')) and .//span[text()='Upload a Document']]";
    private final String docTagInput = "//input[@name='docTag']";
    private final String tagsLabel = "//label[text()='Tags']";
    private final String uploadFinalButton = "//span[text()='Upload']";
    private final String entitledFolderDropdown = "//input[@name='filefolder']/../following-sibling::td/div";
    private final String inputFolder = "//input[@name='filefolder']";
    private final String categoryDropdown = "//input[@name='filecategory']/../following-sibling::td/div";
    private final String dropdownListItems = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
    private final String docTypeDropdown = "//input[@name='filetype']/../following-sibling::td/div";
    private final String fileDescInput = "//input[@name='filedesc']";
    private final String fileInput = "//input[@name='file']";
    private final String tagDropdown = "(//input[@name='docTag']/../following-sibling::td/div)[2]";
    private final String borrowerDropdown = "//input[@name='borrowername']/../following-sibling::td/div";
    private final String conditionsGrid = "//div[@data-selenium-id='conditionGrid']//table//tr";
    private final String acceptPopupOkButton = "//div[@class='x-css-shadow'][contains(@style,'display: block')]/../form/following-sibling::div[contains(@class,'x-closable x-window-closable ')]//a[@data-selenium-id='ok']";
    private final String successMessage = "//span[text()='Success']";
    private final String okButton = "//span[text()='OK']";
    // --- NEW LOCATOR for the rows in the conditions grid ---
    private final String conditionsGridRows = "//div[@id='fieldset-2141-body']//tr[contains(@class, 'x-grid-row')]";


    // --- Constructor ---
    // --- Constructor ---
    public LOSLoanFoldersPage_Old(Page page) {
        // FIX: Added super(page) to pass the page object to the BaseClass constructor.
        super(page);
    }

    // --- Methods ---

    /**
     * This method corresponds to the original 'addLoanFolderDetails' and includes
     * the more complex logic for handling popups and retries in the dropdown.
     */
    public void addLoanFolderDetails(String filePath) {
        page.locator(loanFoldersLink).click();
        page.locator(uploadDocButton).click();
        // FIX: Create a locator for the dialog to scope all subsequent searches.
       // Locator dialog = page.locator(uploadDialog);
       // dialog.waitFor(); // Ensure the dialog is visible before proceeding.

       // page.locator(docTagInput).fill("Investor");
      //  page.locator(tagsLabel).click();
        // FIX: Use the 'dialog' locator to find elements ONLY within the dialog.
       // dialog.locator(docTagInput).fill("Investor");
       // dialog.locator(tagsLabel).click();

     //   page.locator(uploadDocButton).click();

        // --- Entitled Folder Dropdown Logic with Popup Handling ---
        // The original code had a complex loop to handle a potential popup.
        // This logic attempts to find a selection that doesn't trigger the popup.
        for (int i = 0; i < 5; i++) { // Limit retries to prevent infinite loops
            page.locator(entitledFolderDropdown).click();
            Locator folderOptions = page.locator(dropdownListItems);
            folderOptions.first().waitFor(); // Wait for options to be visible
            int count = folderOptions.count();
            if (count > 0) {
                folderOptions.nth(rand.nextInt(count)).click();
            }

            // Check if the popup appeared after selection
           // Locator popupOkButton = page.locator(acceptPopupOkButton);
            // Use a short timeout because we don't expect it to be there always
         //   if (popupOkButton.isVisible(new Locator.IsVisibleOptions().setTimeout(1000))) {
          //      String folderValue = page.locator(inputFolder).getAttribute("value");
            //    if ("Conditions".equalsIgnoreCase(folderValue)) {
            //        popupOkButton.click();
                    // If popup appears, loop again to try a different option
         //       }
         //   } else {
                // No popup, we can break the loop and continue
                break;
          //  }
        }

        // --- Chained Category and Document Type Dropdown Logic ---
        selectRandomChainedDropdowns();
        // FIX: Overload or modify helper methods to accept the dialog scope
        /*private void selectRandomChainedDropdowns(Locator scope) {
            scope.locator(categoryDropdown).click();
            Locator categoryOptions = page.locator(dropdownListItems);
            categoryOptions.first().waitFor();
            int categoryCount = categoryOptions.count();
            if (categoryCount > 0) {
                categoryOptions.nth(rand.nextInt(categoryCount)).click();

                scope.locator(docTypeDropdown).click();
                Locator docTypeOptions = page.locator(dropdownListItems);
                docTypeOptions.first().waitFor();
                int docTypeCount = docTypeOptions.count();
                if (docTypeCount > 0) {
                    docTypeOptions.nth(rand.nextInt(docTypeCount)).click();
                }
            }
        }*/
      /*  private void selectFirstDropdownOption() {
            Locator options = page.locator(dropdownListItems);
            if (options.count() > 0) {
                options.first().click();
            }
        }*/

        // --- Fill remaining fields and upload file ---
        page.locator(fileDescInput).fill("Description");

        // Use Playwright's modern and reliable method for file uploads
        page.locator(fileInput).setInputFiles(Paths.get(filePath));

        // Select a random tag
        page.locator(tagDropdown).click();
        selectFirstDropdownOption();

        // --- Conditional Logic based on Folder Value ---
        String folderValue = page.locator(inputFolder).getAttribute("value");
        if ("borrower".equalsIgnoreCase(folderValue)) {
            page.locator(borrowerDropdown).click();
            selectFirstDropdownOption();
        } else if ("conditions".equalsIgnoreCase(folderValue)) {
            page.locator(conditionsGrid).click();
        }
        // --- NEW LOGIC: Select a random row from the conditions grid ---
        String selectedFolder = page.locator(inputFolder).getAttribute("value");
        if ("Conditions".equalsIgnoreCase(selectedFolder)) {
            Locator rows = page.locator(conditionsGridRows);
            // Wait for at least one row to be present
            rows.first().waitFor();
            List<Locator> allRows = rows.all();
            if (!allRows.isEmpty()) {
                // Select a random row and click it
                int randomIndex = rand.nextInt(allRows.size());
                allRows.get(randomIndex).click();
                System.out.println("Clicked on condition row index: " + randomIndex);
            } else {
//                Locator rows = page.locator(conditionsGridRows);
//                // Wait for at least one row to be present
//                rows.first().waitFor();
//                List<Locator> allRows = rows.all();
//                if (!allRows.isEmpty()) {
//                    // Select a random row and click it
//                    int randomIndex = rand.nextInt(allRows.size());
//                    allRows.get(randomIndex).click();
//                    System.out.println("Clicked on condition row index: " + randomIndex);
                System.out.println("No condition rows found to select.");
            }
        }

        // --- Finalize and Confirm Upload ---
        page.locator(uploadFinalButton).click();
        page.locator(successMessage).waitFor(); // Wait for success confirmation
        page.locator(okButton).click();
    }

    /**
     * Helper method to handle the chained category -> doctype dropdowns.
     */
    private void selectRandomChainedDropdowns() {
        page.locator(categoryDropdown).click();
        Locator categoryOptions = page.locator(dropdownListItems);
        categoryOptions.first().waitFor();
        int categoryCount = categoryOptions.count();
        if (categoryCount > 0) {
            categoryOptions.nth(rand.nextInt(categoryCount)).click();

            // Now handle the dependent dropdown
            page.locator(docTypeDropdown).click();
            Locator docTypeOptions = page.locator(dropdownListItems);
            docTypeOptions.first().waitFor();
            int docTypeCount = docTypeOptions.count();
            if (docTypeCount > 0) {
                docTypeOptions.nth(rand.nextInt(docTypeCount)).click();
            }
        }
    }

    /**
     * Replaces the original 'clickFirstElement' helper method.
     */
    private void selectFirstDropdownOption() {
        Locator options = page.locator(dropdownListItems);
        if (options.count() > 0) {
            options.first().click();
        }
    }
}