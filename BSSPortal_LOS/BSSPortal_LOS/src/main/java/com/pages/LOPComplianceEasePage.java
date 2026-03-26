package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPComplianceEasePage extends BaseClass {
    public LOPComplianceEasePage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopCE() throws InterruptedException {
        page.click("//div[text()='ComplianceEase']");
        page.wait(2000);


        page.click("span[text()='Send Request']");
        page.wait(2000);

;        System.out.println("AUS screen is done .");
    }

    public void lopComplianceEase() {
        // 1. Navigate to ComplianceEase tab
        page.locator("//div[text()='ComplianceEase']").click();
        waitForWorkToComplete();

        // 2. Click the 'Send Request' button
        // Using text-is ensures we target the exact button text
        page.locator("span:text-is('Send Request')").click();

        // 3. Handle the 'ComplianceEase' Confirmation Pop-up
        // Selector for the OK button inside the BlueSage modal
        Locator okButton = page.locator("span.x-btn-inner:text-is('OK')").first();

        // Wait for the modal to be visible before clicking
        okButton.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(5000));
        okButton.click();
        System.out.println("ComplianceEase modal confirmed.");

        // 4. THE CRITICAL WAIT: Wait for the submission spinner to clear
        // As seen in your 3rd screenshot: "Submitting loan to ComplianceEase..."
        Locator submissionSpinner = page.locator("text=Submitting loan to ComplianceEase...");

        try {
            // Wait for it to appear
            submissionSpinner.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(3000));

            // Wait for it to disappear (Compliance audits can take 30-60 seconds)
            submissionSpinner.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.HIDDEN)
                    .setTimeout(90000));
            System.out.println("ComplianceEase audit complete.");
        } catch (Exception e) {
            // Fallback to generic waiter if specific spinner was too fast to catch
            waitForWorkToComplete();
        }

        // 5. Finalize and move to the NEXT screen
        // We target the blue 'NEXT' button in the bottom docked toolbar
        page.locator("div.x-toolbar-docked-bottom span:text-is('NEXT')").first().click();
        waitForWorkToComplete();

        System.out.println("ComplianceEase screen finished.");
    }
}
