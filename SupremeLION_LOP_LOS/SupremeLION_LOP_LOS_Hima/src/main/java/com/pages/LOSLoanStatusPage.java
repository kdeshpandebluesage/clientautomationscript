
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

//import com.microsoft.playwright.Locator;
//import com.microsoft.playwright.Page;
//
//import java.nio.file.Paths;
//import java.util.Random;
//
//public class LOSLoanStatusPage extends BaseClass {
//
//
//    // --- Constructor ---
//    public LOSLoanStatusPage(Page page) {
//        // FIX: Added super(page) to pass the page object to the BaseClass constructor.
//        super(page);
//    }
//
//    // --- Methods ---
//
//    /**
//     * This method corresponds to the original 'addLoanFolderDetails' and includes
//     * the more complex logic for handling popups and retries in the dropdown.
//     */
//    public void updateLoanStatus(String filePath) {
//
//    }
//
//}

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOSLoanStatusPage extends BaseClass {

  //  private final Page page;

    // --- Locators ---
    private final Locator loanStatusNav;
    private final Locator checkLoanStatusWindow;
    private final Locator processingMilestoneDropdown;
    private final Locator saveButton;
    private final Locator savingMask;
    // --- Add this new locator at the top with your other locators ---
    private final String homeLink = "//span[text()='Home']";

    /**
     * Constructor to initialize the Page object and locators.
     * In Playwright, we pass the 'Page' object to our page classes.
     * @param page The Playwright Page object.
     */
    public LOSLoanStatusPage(Page page) {
        //this.page = page;
        super(page);

        // It's a Playwright best practice to define locators in the constructor.
        this.loanStatusNav = page.locator("//*[text()='Loan Status']");

        // In the LOSLoanStatusPage constructor
       // this.loanStatusNav = page.locator("//span[@class='x-tree-node-text' and text()='Loan Status']");

       // this.loanStatusNav = page.locator("//span[@class='x-tree-node-text' and contains(text(),'Loan Status')]");
        // Clicks the element that is both visible and matches the text
       // page.locator("//span[contains(text(),'Loan Status')]").locator("visible=true").click();
        this.checkLoanStatusWindow = page.locator("//label[text()='Old Milestone / Status']");
        this.processingMilestoneDropdown = page.locator("//input[@name='newStatus']/../following-sibling::td/div");
        this.saveButton = page.locator("//span[text()='Save']");
        this.savingMask = page.locator("//div[text()='Saving...']");
    }

    /**
     * Finds and selects a loan status, handling fallbacks if the desired status isn't found.
     * * @param testData The desired loan status text (e.g., "Application - Application - In Process").
     */
    public void addLoanStatusDetails(String testData) {
      //  page.waitForTimeout(6000);
        loanStatusNav.click();
        // Playwright's actions have auto-waits, so we wait for the main window element to be visible first.
        checkLoanStatusWindow.waitFor();

        // --- FIX ADDED HERE ---
        // Wait for the dropdown trigger to be ready before clicking
        processingMilestoneDropdown.waitFor();
        // --- END OF FIX ---
        // Click to open the status dropdown.
        page.waitForTimeout(8000);
        processingMilestoneDropdown.click();


        // --- FIX ADDED HERE ---
        // Wait for the dropdown list to appear by waiting for the first item to be visible.
        String dropdownItemsLocator = "//li[contains(@class, 'x-boundlist-item')]";
       // page.locator(dropdownItemsLocator).click();
        page.locator(dropdownItemsLocator).first().waitFor();
        // --- END OF FIX ---

        // Dynamically locate the desired status option from the test data.
//        Locator desiredStatusOption = page.locator(String.format("//span[contains(text(),'%s')]", testData));
//        Locator statusToClick = null;

        // Check if the desired status is visible in the dropdown.
//        if (desiredStatusOption.isVisible()) {
//
//            System.out.println("Loan status given in excel is found: " + testData);
//            statusToClick = desiredStatusOption;
//        } else {
//            // If not found, iterate through fallback options.
//            System.out.println("Loan status '" + testData + "' not found. Checking fallbacks.");
//            String[] fallbackStatusTypes = {"Application - Application - Activated", "Loan Status Message", "Loan Status"};
        // Check if at least one matching element exists in the DOM.

        // 3. Create a SPECIFIC locator for the item INSIDE the dropdown.
        //    Items in these dropdowns are often in an element with class 'x-boundlist-item'.
        //    This makes the locator unique and avoids matching other text on the page.
       // Locator statusOption = page.locator(String.format("//li[contains(@class, 'x-boundlist-item')]//span[text()=\"%s\"]", testData));
//This looks for an element that CONTAINS the text, which is more flexible.
        Locator statusOption = page.locator(String.format("//li[contains(@class, 'x-boundlist-item')]//span[contains(text(), \"%s\")]", testData));
        System.out.println("Status Option : " + statusOption);
        //      processingMilestoneDropdown.click();
//        if (desiredStatusOption.count() > 0) {
//            System.out.println("Loan status given in excel is found: " + testData);
//            // Select the FIRST matching element to avoid strict mode errors on click.
//            statusToClick = desiredStatusOption.first();
//        } else {
//            // If not found, iterate through fallback options.
//            System.out.println("Loan status '" + testData + "' not found. Checking fallbacks.");
//            // ... your existing fallback logic
//            String[] fallbackStatusTypes = {"Application - Application - Activated", "Loan Status Message", "Loan Status"};
//            for (String fallback : fallbackStatusTypes) {
//                Locator fallbackOption = page.locator(String.format("//span[contains(text(),'%s')]", fallback));
//                if (fallbackOption.isVisible()) {
//                    System.out.println("Found fallback status: " + fallback);
//                    statusToClick = fallbackOption;
//                    break; // Exit the loop once a fallback is found.
//                }
//            }
        // 4. Check if the desired option exists and click it.
        if (statusOption.count() > 0) {
    //        processingMilestoneDropdown.click();
            System.out.println("Loan status '" + testData + "' found in dropdown. Clicking it.");
            statusOption.first().click(); // Use .first() as a safeguard

            // 5. Save the changes and wait for the process to complete.
            saveButton.click();
  //          savingMask.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
  //          savingMask.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
   //     } else {
            // If the primary option isn't found, you can handle fallbacks here
            // or simply report the error.
            System.err.println("Error: Could not find loan status '" + testData + "' in the dropdown.");
            // You might want to throw an exception to fail the test clearly.
            throw new RuntimeException("Loan status not found: " + testData);
        }
    }

    //================================================================================
    // NEW METHODS ADDED
    //================================================================================

    /**
     * A private helper to select a status from the dropdown, save it, and verify the update.
     * This method assumes the user is already on the Loan Status page.
     * @param statusText The exact text of the status to select.
     */
    private void selectAndSaveStatus(String statusText) {
        processingMilestoneDropdown.click();

        // Locator for the specific option within the floating dropdown list
        Locator statusOption = page.locator(String.format("//li[contains(@class, 'x-boundlist-item')]//span[contains(text(), \"%s\")]", statusText));

        if (statusOption.count() > 0) {
            statusOption.first().click();
            saveButton.click();

            // Wait for the saving mask to appear and then disappear
            savingMask.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            savingMask.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));

            // Verify the new status is now displayed on the page
            Locator updatedStatusText = page.locator(String.format("//div[text()='%s']", statusText));
            updatedStatusText.waitFor();
            System.out.println("Successfully set and verified status: " + statusText);
        } else {
            // Throw an error if the status can't be found
            throw new RuntimeException("Loan status not found in dropdown: " + statusText);
        }
    }

    /**
     * Navigates to the Loan Status page and sets the status to 'Underwriting - Submitted'.
     */
    public void setStatusToUnderwritingSubmitted() {
        System.out.println("Setting status to 'Underwriting - Submitted'...");
        loanStatusNav.click();
        checkLoanStatusWindow.waitFor();
        selectAndSaveStatus("Underwriting - Submitted");
    }

    /**
     * Sets the loan status to 'Underwriting - In Review'.
     * This method should be called after the page is already on the Loan Status screen.
     */
    public void setStatusToUnderwritingInReview() {
        System.out.println("Setting status to 'Underwriting - In Review'...");
        selectAndSaveStatus("Underwriting - In Review");
    }
    //================================================================================
    // NEW METHOD ADDED
    //================================================================================

    /**
     * Navigates to the Loan Status page and verifies that the current status
     * matches the expected status text.
     * @param expectedStatus The full text of the status to verify.
     */
    public void verifyLoanStatusIs(String expectedStatus) {
        System.out.println("Verifying loan status is '" + expectedStatus + "'...");

        // Steps: LOSCheckClearToCloseNode & LOSHomeFifth
        click(homeLink);

        // Step: LOSClearToClosingLoanstatus
        loanStatusNav.click();

        // Step: CheckClearToClosingLoanStatu
        checkLoanStatusWindow.waitFor();

        // Verify that the expected status text is visible on the page
        Locator currentStatus = page.locator(String.format("//div[text()='%s']", expectedStatus));
        currentStatus.waitFor();
        System.out.println("Success! Loan status confirmed to be: " + expectedStatus);
    }
    /**
     * Navigates to the Loan Status page and sets the status to 'Closing - Submitted'.
     */
    public void setStatusToClosingSubmitted() {
        System.out.println("Setting status to 'Closing - Submitted'...");
        loanStatusNav.click();
        checkLoanStatusWindow.waitFor();
        selectAndSaveStatus("Closing - Submitted");
    }

    /**
     * Sets the loan status to 'Closing - In Review'.
     * This method should be called after the page is already on the Loan Status screen.
     */
    public void setStatusToClosingInReview() {
        System.out.println("Setting status to 'Closing - In Review'...");
        selectAndSaveStatus("Closing - In Review");
    }
}

//        // If a status (either desired or fallback) was found, click it and save.
//        if (statusToClick != null) {
//            statusToClick.click();
//            saveButton.click();
//
//            // This replicates 'checkElementLoadMask' by waiting for the "Saving..." mask to appear and then disappear.
//            savingMask.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//            savingMask.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
//        } else {
//            // It's good practice to handle the case where no option is found.
//            System.err.println("Error: No valid loan status option could be found for '" + testData + "' or any fallbacks.");
//            // You might want to throw an exception here in a real test case.
//            // throw new RuntimeException("No valid loan status option found.");
//        }
//    }
