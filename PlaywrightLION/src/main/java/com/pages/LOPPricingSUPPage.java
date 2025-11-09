package com.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.openqa.selenium.WebElement;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPPricingSUPPage extends BasePage { // Assuming a Page Object for the header

    // --- Locators for the Header and Exceptions Dialog ---
    private final Locator exceptionsButton;
    private final Locator exceptionCountBadge;

    public LOPPricingSUPPage(Page page) {
        super(page);

        // A stable locator for the Exceptions icon/button area in the header
        this.exceptionsButton = page.locator("a[data-selenium-id='exceptions']"); // Adjust if needed

        // A locator for the red circle with the number
        this.exceptionCountBadge = exceptionsButton.locator("span.x-btn-badge"); // Adjust if needed
    }

    /**
     * Checks for loan exceptions and, if any exist, opens the dialog,
     * overrides all items, and saves the changes.
     */
    public void handleExceptionsIfNeeded() {
        System.out.println("Checking for loan exceptions...");

        int exceptionCount = 0;
        try {
            // Get the text from the badge, e.g., "2"
            String countText = exceptionCountBadge.textContent();
            exceptionCount = Integer.parseInt(countText);
        } catch (NumberFormatException e) {
            System.out.println("Could not parse exception count, or no exceptions found.");
            exceptionCount = 0;
        }

        // --- Only proceed if there are exceptions to handle ---
        if (exceptionCount > 0) {
            System.out.println(exceptionCount + " exceptions found. Opening the exceptions dialog.");
            exceptionsButton.click();

            // --- Interact with the Exceptions Dialog ---
            // A stable locator for the pop-up window
            Locator dialog = page.locator("div.x-window:has-text('Exceptions for Loan')");
            dialog.waitFor(); // Wait for the dialog to appear

            // Find all the "Override" checkboxes in the grid.
            // This targets the clickable div inside the checkbox cell.
            Locator overrideCheckboxes = dialog.locator("td[class*='checkcolumncustom'] div.x-grid-checkcolumn");

            System.out.println("Overriding all exceptions...");
            // Loop through all found checkboxes and click each one
            for (Locator checkbox : overrideCheckboxes.all()) {
                checkbox.click();
            }

            // Click the Save button within the dialog
            dialog.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Save")).click();
            System.out.println("Save button clicked.");

            // Click the Close button
            dialog.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Close")).click();

            // A robust way to confirm the action is complete is to wait for the dialog to disappear
            dialog.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
            System.out.println("Exceptions dialog closed successfully.");

        } else {
            System.out.println("No exceptions to handle.");
        }
    }
}