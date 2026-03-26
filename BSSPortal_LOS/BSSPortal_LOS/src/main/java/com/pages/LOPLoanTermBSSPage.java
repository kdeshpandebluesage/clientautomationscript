package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;


public class LOPLoanTermBSSPage extends BaseClass {
    public LOPLoanTermBSSPage(Page page) {
        super(page);}
    public void lopLoantermPurchase(String LOPAppraisedValue, String LOPLoanAmt,String SettlmentDate) throws InterruptedException {
        // --- Locators for Product Search ---
        final Locator productSearchIcon;
        final Locator productSearchDialog;
        final Locator searchButtonInDialog;
        final Locator searchResultsGrid;
        Locator dateInput = page.locator("input[name='settlementDate']");

        Locator settlementDateInput = page.locator("input[name='settlementDate']");

        // 2. SCRAPE: Get the current date from the screen (e.g., "03/30/2026")
        // Note: Use inputValue() for text fields
        String dateOnScreen = settlementDateInput.inputValue();

        // 3. CALCULATE: Use your BaseClass method to add 4 weeks
        // This will check your 2026 holiday list and weekends automatically
        String finalBusinessDate = getBusinessDateFromStart(dateOnScreen, 4);

        // 4. ENTER: Clear the old date and type the new one
        settlementDateInput.click();
        settlementDateInput.clear();
        settlementDateInput.fill(finalBusinessDate);

        // 5. TRIGGER: Press Enter to ensure the UI registers the change
        settlementDateInput.press("Enter");

        selectAngularDropdown(page, "//input[@name='buildingTypeId']/../following-sibling::td/div", "//li[text()='Attached']");
        clearAndEnterText(page, "//input[@name='appraisedValueAmt']", LOPAppraisedValue);
        clearAndEnterText(page, "//input[@name='loanAmt']", LOPLoanAmt);


        // This locator finds the magnifying glass icon next to the 'Product Description' input.

        productSearchIcon = page.locator("//a[@data-selenium-id='btnLoanProdSearch']");
        productSearchIcon.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        productSearchIcon.scrollIntoViewIfNeeded();

        page.evaluate("selector => document.querySelector(selector).click()", "a[data-selenium-id='btnLoanProdSearch']");

        System.out.println("Step 1: Waiting for the product search icon to be ready.");;
//
//        page.locator("//a[@data-selenium-id='btnLoanProdSearch']")
//                .click(new Locator.ClickOptions().setForce(true));


        // --- Select Radio Buttons ---
//        productSearchDialog.getByLabel("Conventional").check();
        page.click("//label[text()='Conventional Mortgage']");
        page.click("//label[text()='Conforming']");
        page.click("//label[text()='Fixed Rate']");
        page.click("//label[text()='Adjustable Rate']");


        // --- Select Checkboxes ---
        // Loan Terms
//        productSearchDialog.locator("fieldset:has-text('Loan Terms')").getByLabel("All").check();
        page.click("//label[text()='All']");
        // Product Groups
//        productSearchDialog.locator("fieldset:has-text('Product Groups')").getByLabel("All").check();
        page.click("//table[@data-selenium-id='chkProductTypes_ALL']//input");
        selectAngularDropdown(page, "//input[@name='rateLockTypeId']/../following-sibling::td/div", "//li[text()='45 Day Rate Lock']");
        page.locator("//tr[contains(., 'Borrower Paid MI')]//label[text()='No']").click();
        // --- Select from Dropdown ---
     /*   String RateTypeLocator = "//input[@name='rateLockTypeId']/../following-sibling::td/div";
        String hoverTargetSelector2 = "//li[text()='60 Day Rate Lock']";
        page.click(RateTypeLocator);
        selectAngularDropdownOption(page, RateTypeLocator, hoverTargetSelector2);*/


        // The 'Search' button specifically inside the dialog.
        page.locator("//a[@data-selenium-id='btnSearch']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//a[@data-selenium-id='btnSearch']");
        page.waitForTimeout(8000);
        System.out.println("Product Search dialog is open.");

        // The grid that contains the product results.
//      page.locator("//td[contains(@class,'span_link')]");
      page.waitForSelector("//td[contains(@class,'span_link')]", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
//      page.click("//div[text()='Conforming FNMA 30 Yr Fixed']");
//      page.click("//div[text()='FNMA Conforming 30 Yr Fixed']");
        page.click("//div[text()='FNMA Conforming 15 Yr Fixed']");
        waitForWorkToComplete();
        page.waitForLoadState(LoadState.NETWORKIDLE);

      selectAngularDropdown(page, "//input[@name='documentationTypeId']/../following-sibling::td/div", "//li[text()='Full Documentation']");
        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']

        System.out.println("Loan Term screen is done .");
        page.click("//span[text()='NEXT']");
    }

}
