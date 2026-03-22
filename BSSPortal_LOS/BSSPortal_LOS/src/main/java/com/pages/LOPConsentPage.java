package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.io.IOException;

public class LOPConsentPage extends BaseClass {
    public LOPConsentPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void handleConsentGrid() throws IOException {
        // 1. Navigate and wait for rows
        page.locator("//div[text()='Consent']").click();
        page.waitForSelector("tr.x-grid-data-row", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.waitForTimeout(2000);

        Locator rows = page.locator("tr.x-grid-data-row");
        int rowCount = rows.count();
        System.out.println("Processing " + rowCount + " consent rows.");

        // --- PASS 1: CLICK ALL CHECKBOXES ---
        for (int i = 0; i < rowCount; i++) {
            Locator rowCheckboxes = rows.nth(i).locator("div.x-grid-cell-inner-checkcolumn");
            int checkboxCount = rowCheckboxes.count();
            for (int j = 0; j < checkboxCount; j++) {
                try {
                    // Use a short timeout for checkboxes to keep the script moving
                    rowCheckboxes.nth(j).click(new Locator.ClickOptions().setForce(true).setTimeout(2000));
                } catch (Exception e) {
                    System.out.println("Skipping checkbox for row " + i + " column " + j);
                }
            }
        }
        page.waitForTimeout(1000);

        // --- PASS 2: SELECT ALL DROPDOWNS ---
        for (int k = 0; k < rowCount; k++) {
            // Target the 'Method of Communication' cell
            Locator dropdownCell = rows.nth(k).locator("td").nth(2).locator("div.x-grid-cell-inner");

            if (dropdownCell.isVisible()) {
                try {
                    System.out.println("Attempting row " + k);

                    // Use dblclick to activate, but set a short timeout
                    dropdownCell.dblclick(new Locator.DblclickOptions().setTimeout(2000));

                    // Immediately check if the active input appeared
                    Locator activeInput = page.locator("input.x-form-field.x-form-focus");

                    if (activeInput.isVisible()) {
                        activeInput.fill("Written");
                        page.keyboard().press("Enter");
                        page.waitForTimeout(400);
                    } else {
                        // Fallback to boundlist only if input isn't found
                        Locator firstOption = page.locator("li.x-boundlist-item:has-text('Written')").first();
                        firstOption.click(new Locator.ClickOptions().setTimeout(2000));
                        page.keyboard().press("Tab");
                    }
                    System.out.println("Successfully filled row " + k);
                } catch (Exception e) {
                    // This block catches timeouts for BOTH dblclick and the dropdown selection
                    System.out.println("Skipping dropdown for row " + k + ": Not accessible or timeout.");
                    page.keyboard().press("Escape"); // Ensure editor is closed before next row
                    page.waitForTimeout(200);
                }
            }
        }

        // --- PASS 3: SAVE ---
        // Target the stable data-selenium-id identified in the HTML
        try {
            page.locator("//a[@data-selenium-id='btnLoanAppSave']").click(new Locator.ClickOptions().setTimeout(5000));
            System.out.println("Consent grid saved.");
        } catch (Exception e) {
            System.out.println("Could not click SAVE button. Checking if already saved or blocked.");
        }
    }

}
//        click("#btnLoans");
//        page.waitForTimeout(6000);
//        clearAndFillText("input[name='searchLoans-inputEl']", loannumber);
//        page.keyboard().press("Enter");
//        page.waitForTimeout(6000);
//        System.out.println("Searched for loan number: " + loannumber);