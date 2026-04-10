package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;


public class LOPLoanTermSUPPage extends BaseClass {
    public LOPLoanTermSUPPage(Page page) {
        super(page);
    }



    public void lopLoantermPurchase(String LOPAppraisedValue, String LOPCreditscore,String SettlmentDate) throws InterruptedException {
        // --- Locators for Product Search ---
        final Locator productSearchIcon;
        final Locator productSearchDialog;
        final Locator searchButtonInDialog;
        final Locator searchResultsGrid;

        // This locator finds the magnifying glass icon next to the 'Product Description' input.
        productSearchIcon = page.locator("//a[@data-selenium-id='btnLoanProdSearch']");

        // Locator for the 'Product Search' pop-up window.
        productSearchDialog = page.locator("div.x-window:has-text('Product Search')");
        page.waitForTimeout(6000);

        clearAndEnterText(page, "//input[@name='appraisedValueAmt']", LOPAppraisedValue);


        System.out.println("Step 1: Waiting for the product search icon to be ready.");;
//        page.locator("(//a[@data-selenium-id='btnLoanProdSearch']").waitFor(new Locator.WaitForOptions()
//                .setState(WaitForSelectorState.VISIBLE));
//         page.hover("//a[@data-selenium-id='btnLoanProdSearch']");
        page.locator("a[data-selenium-id='btnLoanProdSearch']")
                .click(new Locator.ClickOptions().setForce(true));


        // --- Select Radio Buttons ---
//        productSearchDialog.getByLabel("Conventional").check();
        page.click("//label[text()='Conventional Mortgage']");
        page.click("//label[text()='Conforming']");
        page.click("//label[text()='Fixed Rate']");


        // --- Select Checkboxes ---
        // Loan Terms
//        productSearchDialog.locator("fieldset:has-text('Loan Terms')").getByLabel("All").check();
        page.click("//label[text()='All']");
        // Product Groups
//        productSearchDialog.locator("fieldset:has-text('Product Groups')").getByLabel("All").check();
        page.click("//table[@data-selenium-id='chkProductTypes_ALL']//input");
        // --- Select from Dropdown ---
        String RateTypeLocator = "//input[@name='rateLockTypeId']/../following-sibling::td/div";
        String hoverTargetSelector2 = "//li[text()='60 Day Rate Lock']";
        page.click(RateTypeLocator);
        selectAngularDropdownOption(page, RateTypeLocator, hoverTargetSelector2);


        // The 'Search' button specifically inside the dialog.
        page.locator("//a[@data-selenium-id='btnSearch']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
      page.click("//a[@data-selenium-id='btnSearch']");
        page.waitForTimeout(8000);
        System.out.println("Product Search dialog is open.");

        // The grid that contains the product results.
//      page.locator("//td[contains(@class,'span_link')]");
      page.waitForSelector("//td[contains(@class,'span_link')]", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));



        // Find all data rows and click the one at the specified index.
//    page.click("(//td[contains(@class,'span_link')])[3]");
    page.click("//div[text()='C30COOP - FNMA Conforming 30 Yr Fixed']");

//         pageUp();

        clearAndEnterText(page, "//input[@name='settlementDate']", SettlmentDate);
//        pageDown();

        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']

        System.out.println("Loan Term screen is done .");
//        page.click("//span[text()='NEXT']");
    }

}
