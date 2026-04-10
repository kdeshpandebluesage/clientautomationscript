package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPPricingSUPPage extends BaseClass { // Assuming a Page Object for the header

    // --- Locators for the Header and Exceptions Dialog ---
    private final Locator exceptionsButton;
   // private final Locator exceptionCountBadge;

    public LOPPricingSUPPage(Page page) {
        super(page);

        // A stable locator for the Exceptions icon/button area in the header
       this.exceptionsButton = page.locator("a[data-selenium-id='exceptions']"); // Adjust if needed

        // A locator for the red circle with the number
       // this.exceptionCountBadge = exceptionsButton.locator("span.x-btn-badge"); // Adjust if needed
    }

    /**
     * Checks for loan exceptions and, if any exist, opens the dialog,
     * overrides all items, and saves the changes.
     */
    public void handleExceptionsIfNeeded() {
        System.out.println("Checking for loan exceptions...");
        clearAllExceptions();
        int exceptionCount = 0;

       /*     exceptionsButton.click();

            // --- Interact with the Exceptions Dialog ---
            // A stable locator for the pop-up window
            Locator dialog = page.locator("div.x-window:has-text('Exceptions for Loan')");
            dialog.waitFor(); // Wait for the dialog to appear

            // Find all the "Override" checkboxes in the grid.
            // This targets the clickable div inside the checkbox cell.
            Locator overrideCheckboxes = dialog.locator("//div[@data-selenium-id='exceptionGrid']//tr//td[10]//div[contains(@class,'checkcolumncustom')]");
            System.out.println("Overriding all exceptions...");
            // Loop through all found checkboxes and click each one
            for (Locator checkbox : overrideCheckboxes.all()) {
                checkbox.click();
            }
            // Click the Save button within the dialog
        page.click("//div[@data-selenium-id='exceptionGrid']//tr//td[10]//div[contains(@class,'checkcolumncustom')]");
       // page.click("//span[text()='Save']");
        System.out.println("Exceptions Override Save button clicked.");
        System.out.println("Exceptions dialog closed successfully.");*/

       // } else {
        //    System.out.println("No exceptions to handle.");
      //  }
    }
//    public void selectPricingValue() {
//        // The XPath locator is the same as in Selenium
//        page.locator("//div[text()='Pricing']").waitFor(new Locator.WaitForOptions()
//                .setState(WaitForSelectorState.VISIBLE));
//        page.click("//div[text()='Pricing']");
//        String locator = "//td[contains(@class,'span_link')]/div";
//        Locator priceElements = page.locator(locator);
//
//        // Wait for at least one price element to be present before continuing
//        priceElements.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//
//        int count = priceElements.count();
//        if (count == 0) {
//            System.out.println("No pricing elements were found on the page.");
//            return; // Exit the method if there's nothing to click
//        }
//
//        boolean itemWasClicked = false;
//        // Loop through each pricing element found
//        for (int i = 0; i < count; i++) {
//            Locator currentElement = priceElements.nth(i);
//            String text = currentElement.textContent().trim(); // Use textContent() and trim whitespace
//
//            try {
//                // The parsing logic is standard Java and remains the same
//                String[] val = text.replace(".", " ").split(" ");
//                int num = Integer.parseInt(val[0]);
//
//                // --- LOGIC CORRECTION ---
//                // The original code used `||` (OR), which would always be true.
//                // This has been corrected to `&&` (AND) to correctly find a
//                // number between 85 and 95, inclusive.
//                if (num >= 85 && num <= 95) {
//                    System.out.println("Found a suitable price (" + num + "). Clicking it.");
//                    currentElement.click();
//                    itemWasClicked = true; // Mark that we clicked an item
//                    break; // Exit the loop since we found our target
//                }
//            } catch (NumberFormatException e) {
//                // This handles cases where the text isn't a valid number
//                System.err.println("Could not parse a number from: '" + text + "'. Skipping.");
//            }
//        }
//
//        // --- FALLBACK LOGIC ---
//        // If the loop finishes and we haven't clicked anything,
//        // execute the original code's fallback of clicking the first element.
//        if (!itemWasClicked) {
//            System.out.println("No price between 85-95 found. Clicking the first element as a fallback.");
//            priceElements.first().click();
//        }
//    }
    /**
     * (Original Method) Selects a pricing value based on a hardcoded range.
     * This is less flexible than the new grid-based method.
     */
    public void selectPricingValue() {
        String rateLockColumnName ="60 Day Price";
        double minPrice= 95.0;
        double maxPrice= 101.0;
        page.locator("//div[text()='Pricing']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//div[text()='Pricing']");
        page.click("//span[text()='Select Rate']");
        String locator = "//td[contains(@class,'span_link')]/div";
        Locator priceElements = page.locator(locator);
        priceElements.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        int count = priceElements.count();
        if (count == 0) {
            System.out.println("No pricing elements were found on the page.");
            return;
        }

        boolean itemWasClicked = false;
        for (int i = 0; i < count; i++) {
            Locator currentElement = priceElements.nth(i);
            String text = currentElement.textContent().trim();

            try {
                String[] val = text.replace(".", " ").split(" ");
                int num = Integer.parseInt(val[0]);

                if (num >= 95 && num <= 101) {
                    System.out.println("Found a suitable price (" + num + "). Clicking it.");
                    currentElement.click();
                    itemWasClicked = true;
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Could not parse a number from: '" + text + "'. Skipping.");
            }
        }

        if (!itemWasClicked) {
            System.out.println("No price between 85-95 found. Clicking the first element as a fallback.");
            priceElements.first().click();
        }


    // --- NEW METHOD BASED ON SCREENSHOTS ---

    /**
     * Clicks the 'Select Rate' button and selects the first available price
     * from a specific rate lock column (e.g., "30 Day Price") that falls
     * within a specified price range.
     *
     * @param rateLockColumnName The exact text of the header for the desired rate lock column (e.g., "30 Day Price").
     * @param minPrice The minimum acceptable price (e.g., 98.0).
     * @param maxPrice The maximum acceptable price (e.g., 101.0).
     */
    ////public void selectPriceFromGrid(String rateLockColumnName, double minPrice, double maxPrice) {

       // System.out.println("--- Starting Pricing Selection from Grid ---");

        // 1. Click the "Select Rate" button to open the pricing dialog
      //  System.out.println("Clicking 'Select Rate' button...");
      //  page.locator("//span[text()='Select Rate']").click();

        // 2. Wait for the pricing dialog window to appear
//        Locator dialog = page.locator("div.x-window:has-text('Select Pricing')");
//        dialog.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        System.out.println("Pricing dialog is visible.");
//
//        // 3. Find the column index for the desired rate lock period
//        Locator headers = dialog.locator("//div[contains(@class,'x-column-header-text-inner')]");
//        int columnIndex = -1;
//        List<String> headerTexts = headers.allTextContents();

//        for (int i = 0; i < headerTexts.size(); i++) {
//            if (headerTexts.get(i).trim().equals(rateLockColumnName)) {
//                // Column indices in XPath are 1-based
//                columnIndex = i + 1;
//                break;
//            }
//        }
//
//        if (columnIndex == -1) {
//            System.err.println("Could not find column with header: '" + rateLockColumnName + "'");
//            dialog.locator("//span[text()='CANCEL']").click(); // Close the dialog
//            return;
//        }
//        System.out.println("Found '" + rateLockColumnName + "' at column index: " + columnIndex);
//
//        // 4. Find all price elements in that specific column
//        Locator priceCells = dialog.locator(
//                String.format("//div[contains(@class,'x-grid-view')]//tr/td[%d]", columnIndex)
//        );
//
//        // 5. Iterate through the prices and click the first one in the desired range
//        boolean priceWasSelected = false;
//        for (int i = 0; i < priceCells.count(); i++) {
//            Locator cell = priceCells.nth(i);
//            String priceText = cell.textContent().trim();
//
//            try {
//                double priceValue = Double.parseDouble(priceText);
//                System.out.println("Evaluating price: " + priceValue);
//
//                if (priceValue >= minPrice && priceValue <= maxPrice) {
//                    System.out.println("Found a suitable price in range: " + priceValue + ". Clicking it.");
//                    cell.click();
//                    priceWasSelected = true;
//                    break; // Exit after clicking the first match
//                }
//            } catch (NumberFormatException e) {
//                System.err.println("Could not parse price from text: '" + priceText + "'");
//            }
//        }
//
//        if (!priceWasSelected) {
//            System.out.println("No price found in the range [" + minPrice + ", " + maxPrice + "]. Closing dialog.");
//            dialog.locator("//span[text()='CANCEL']").click();
//        } else {
//            System.out.println("--- Pricing Selection Complete ---");
//            // The dialog may close automatically after a selection. If not, add a click for a 'Save' or 'Select' button here.
//        }
    }
}