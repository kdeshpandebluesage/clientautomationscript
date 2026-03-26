package com.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.openqa.selenium.WebElement;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LOPPricingPage extends BaseClass {
    public LOPPricingPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void selectRateInRange(double min, double max) throws InterruptedException {
        // 1. Open the modal
        page.locator("span.x-btn-inner:text-is('Select Rate')").click();
        waitForWorkToComplete();

        // 2. Target ROWS first, then find the specific 60-day COLUMN (index 5)
        Locator rows = page.locator("tr.x-grid-row");
        rows.first().waitFor();

        int count = rows.count();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            // td.nth(5) targets the 6th column (60 Day Price)
            Locator cell45Day = rows.nth(i).locator("td").nth(4).locator("div.x-grid-cell-inner");
            String cellText = cell45Day.innerText().replace("%", "").trim();

            try {
                double priceValue = Double.parseDouble(cellText);

                if (priceValue >= min && priceValue <= max) {
                    System.out.println("Matching 60-day price found: " + priceValue + "% at row " + (i+1));
                    // Click to select the row
                    cell45Day.click();
                    found = true;
                    break;
                }
            } catch (NumberFormatException e) {
                continue; // Skip non-numeric cells
            }
        }

        // 3. Handle Modal Closure
        // If double-click isn't used, clicking CANCEL is fine as long as the row was clicked first
     /*   Locator modalCancel = page.locator("span.x-btn-inner:text-is('CANCEL')").last();
        if (found && modalCancel.isVisible()) {
            modalCancel.click();
            waitForWorkToComplete();
        }

        if (!found) {
            if (modalCancel.isVisible()) modalCancel.click();
            throw new RuntimeException("No rate found in 60-day column within range " + min + "-" + max);
        }*/

        // 4. Trigger Lock (Save -> Yes)
        waitForWorkToComplete();
        page.waitForTimeout(2000);
        Locator saveBtn = page.locator("div.x-toolbar-docked-bottom span:text-is('SAVE')").first();
        page.locator("div.x-toolbar-docked-bottom span:text-is('SAVE')").first();
        saveBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        saveBtn.dispatchEvent("click");

//        page.locator("div.x-toolbar-docked-bottom span:text-is('SAVE')").first().click();

        // 5. Handle "Yes" Confirmation
        Locator yesButton = page.locator("span.x-btn-inner:text-is('Yes')").first();
        try {
            yesButton.waitFor(new Locator.WaitForOptions().setTimeout(7000));
            yesButton.click();
            waitForWorkToComplete();
        } catch (Exception e) {
            System.out.println("Confirmation modal didn't appear (Loan may already be locked).");
        }

        // 6. Finalize
        page.locator("div.x-toolbar-docked-bottom span:text-is('NEXT')").first().click();
    }
}

