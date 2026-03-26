package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPDocumentPageold extends BaseClass {
    public LOPDocumentPageold(com.microsoft.playwright.Page page) {
        super(page);
    }
    public void generateInitialDisclosures(String secondaryLienText) {
        // 1. Navigate: Documents -> Packages
        // 1. HARD SYNC: Ensure the page is fully loaded after the previous step
        waitForWorkToComplete();

        // 2. BROAD LOCATOR: Find the Documents tab by text in the top navigation bar
        // This looks for 'Documents' inside the tab list specifically
        Locator documentsMenu = page.locator("span.menu-button-inner:text-is('Documents')");

                  // 2. Hover over it to trigger the dropdown visibility
            documentsMenu.hover();

// 3. Optional: Add a small wait if there is a fade-in animation
            page.waitForTimeout(500);

// 4. Click 'Packages' only when visible
        page.locator("span.x-menu-item-text:text-is('Packages'):visible")
                .first()
                .click(new Locator.ClickOptions().setForce(true));


        // 2. Select 'Initial Disclosure' from the Document Package dropdown
        String packageDropdown = "//input[@name='documentPackageId']/../following-sibling::td/div";
        page.locator(packageDropdown).click();
        page.locator("//li[text()='Initial Disclosure']").click();

        // 3. Click 'Retrieve Package List' and wait for external vendor
        page.locator("span:text-is('Retrieve Package List')").click();

        // Wait for "Sending Request to External Doc Vendor" to finish
        Locator vendorSpinner = page.locator("text=Sending Request to External Doc Vendor");
        vendorSpinner.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(60000));
        waitForWorkToComplete();

        // 4. Select the 'Doc Index #' checkbox (Header checkbox to select all)
        page.locator("div.x-column-header-checkbox").first().click();

        // 5. Handle Adhoc Data (Image 7/8: Secondary Lien Holder)
        // Click the Adhoc icon (small document icon in the grid)
        page.locator("img.x-action-col-icon").first().click();

        Locator adhocInput = page.locator("input[name='secondaryLienHolder']");
        adhocInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        adhocInput.fill(secondaryLienText);

        // Click Save on the Adhoc pop-up
        page.locator("span.x-btn-inner:text-is('Save')").click();
        waitForWorkToComplete();

        // 6. Set Destination to 'eSign'
        String destDropdown = "//input[@name='destinationId']/../following-sibling::td/div";
        page.locator(destDropdown).click();
        page.locator("//li[text()='eSign']").click();

        // 7. Click 'Send' and handle the "Generating documents" mask
        page.locator("span:text-is('Send')").click();

        Locator genSpinner = page.locator("text=Generating documents ...");
        genSpinner.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(90000));

        waitForWorkToComplete();
        System.out.println("Document Package sent successfully via eSign.");
    }
}
