package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPAUSPage extends BaseClass {
    public LOPAUSPage(com.microsoft.playwright.Page page) {
        super(page);
    }


//    public void lopAUSDU() throws InterruptedException {
//        page.click("//div[text()='Automated Underwriting']");
//        page.wait(2000);
//
//        page.click("//label[text()='Fannie Mae Desktop Underwriter (DU)']");
//        page.click("span[text()='Send Request']");
//        page.wait(2000);
//
//        System.out.println("AUS screen is done .");
//    }
    public void lopAUSDU() {
        // 1. Navigate to AUS
        page.locator("//div[text()='Automated Underwriting']").click();
        waitForWorkToComplete(); // Wait for screen to load

        // 2. Select Fannie Mae DU
        page.locator("//label[text()='Fannie Mae Desktop Underwriter (DU)']").click();

        // 3. Click Send Request
        // Using span:text-is ensures we hit the exact button text
        page.locator("span:text-is('Send Request')").click();

        // 4. THE CRITICAL WAIT: AUS takes time
        // We wait for the "Submitting request..." mask to appear and THEN disappear
        System.out.println("AUS Request sent. Waiting for 'Submitting request' to clear...");

        Locator submissionSpinner = page.locator("text=Submitting request...");
        try {
            // Wait for it to show up (up to 3s)
            submissionSpinner.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(3000));

            // Wait for it to vanish (up to 90s - AUS is slow)
            submissionSpinner.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.HIDDEN)
                    .setTimeout(90000));
        } catch (Exception e) {
            // If it never showed up or already finished, just use the generic waiter
            waitForWorkToComplete();
        }

        // 5. Finalize
        System.out.println("AUS results received. Moving to NEXT.");
        page.locator("div.x-toolbar-docked-bottom span:text-is('NEXT')").first().click();
    }
}
