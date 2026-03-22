package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPNavigationPage extends BaseClass {
    public LOPNavigationPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopNavigation() throws InterruptedException {
// 1. Identify the 'Application' tab
        waitForWorkToComplete();

        page.waitForTimeout(1000);
        Locator applicationMenu = page.locator("span:has-text('Application')").first();

        // 2. Wait for it to be visible
        applicationMenu.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));


        // 3. HOVER is critical here. It triggers the ExtJS dropdown to render in the DOM.
        applicationMenu.hover();
//        applicationMenu.click();
        applicationMenu.dispatchEvent("click");
        // 4. Wait for the submenu item to actually appear in the DOM before clicking
        Locator urlaLink = page.locator("span:has-text('Loan Application - URLA')").first();
        urlaLink.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(15000));

        // 5. Click the link
        urlaLink.click(new Locator.ClickOptions().setForce(true));

        // 6. Use your new generic method to handle any loading masks that appear after the click
        waitForWorkToComplete();

        System.out.println("URLA screen is loaded.");
    }
}
