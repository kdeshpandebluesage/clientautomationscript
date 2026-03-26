package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPLoanTermPage extends BaseClass {
    public LOPLoanTermPage(com.microsoft.playwright.Page page) {
        super(page);
    }
    LOPLoanTermPage loanTermPageProductSearch = new LOPLoanTermPage(page);

    public void lopLoantermPurchase2Br(String PurchasepropertyType) throws InterruptedException {

        page.locator("//div[text()='Loan Terms']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//div[text()='Loan Terms']");
        //Property Type
        String propertyTypedropdownToggleLocator = "//input[@name='propertyTypeId']/../following-sibling::td/div";
        page.click(propertyTypedropdownToggleLocator);
        page.locator(".x-boundlist-item >> text=\"" + PurchasepropertyType + "\"").click();
        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']
        page.waitForTimeout(5000);
        System.out.println("Loan Term screen is done .");
    }

    public void lopXMLImportLoantermPurchase2Br(String SubjectPropertyType, String PurchasepropertyType, String PurchasePrice, String LOPAppraisedValue, String LoanAmount, String DocumentationType) throws InterruptedException {
        page.locator("//div[text()='Loan Terms']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//div[text()='Loan Terms']");

        //Subject Property Type
        String subpropertyTypedropdownToggleLocator = "//input[@name='buildingTypeId']/../following-sibling::td/div";
        page.click(subpropertyTypedropdownToggleLocator);
        page.locator(".x-boundlist-item >> text=\"" + SubjectPropertyType + "\"").click();
        //Purchase Property Type
        String propertyTypedropdownToggleLocator = "//input[@name='propertyTypeId']/../following-sibling::td/div";
        page.click(propertyTypedropdownToggleLocator);
        page.locator(".x-boundlist-item >> text=\"" + PurchasepropertyType + "\"").click();
        //PurchasePrice
        page.locator("//input[@name='purchasePriceAmt']").clear();
        page.fill("//input[@name='purchasePriceAmt']",PurchasePrice);
        //LOPAppraisedValue
        page.locator("//input[@name='appraisedValueAmt']").clear();
        page.fill("//input[@name='appraisedValueAmt']", LOPAppraisedValue);
        //LoanAmount
        page.fill("input[name='loanAmt']", LoanAmount);
        page.waitForTimeout(3000);
        pageDown();

        // --- Documentation Type Section ---

// 1. Define the input locator for scrolling/visibility
        Locator docTypeInput = page.locator("input[name='documentationTypeId']");

// 2. Ensure the element is scrolled into view so it is 'visible' to Playwright's click engine
        docTypeInput.scrollIntoViewIfNeeded();

// 3. Click the dropdown trigger
// We target the trigger arrow (the div next to the input) for a more reliable click
        String docTypeTrigger = "//input[@name='documentationTypeId']/../following-sibling::td/div";
        page.locator(docTypeTrigger).click();

// 4. Wait for the list to be visible and click 'Full Documentation'
// We use a specific locator that targets only the visible boundlist to avoid strict mode violations
        Locator docOption = page.locator("//div[contains(@class, 'x-boundlist') and not(contains(@style, 'display: none'))]//li[text()='Full Documentation']");

// Explicitly wait for the option to be visible before clicking
        docOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        docOption.click();

// 5. Verification: Optional but recommended to ensure the selection stuck
        String actualDocType = docTypeInput.inputValue();
        if (!actualDocType.equals("Full Documentation")) {
            // If click failed, try a force click or dispatching the event
            docOption.click(new Locator.ClickOptions().setForce(true));
        }

        //product search
//        loanTermPageProductSearch.lopXMLImportLoantermProductSearch();

        //SAVE
        page.waitForTimeout(5000);
        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']
        page.waitForTimeout(5000);

        //TRID Accept
        page.locator("(//span[text()='Yes'])[1]").click();
        page.waitForTimeout(8000);
        System.out.println("Loan Terms screen is done .");
    }
}

// This locator finds the magnifying glass icon next to the 'Product Description' input.
//        productSearchIcon = page.locator("//a[@data-selenium-id='btnLoanProdSearch']");
//
////     Old   page.locator("a[data-selenium-id='btnLoanProdSearch']")
////                .click(new Locator.ClickOptions().setForce(true));
//        //Old  page.locator("a[data-selenium-id='btnLoanProdSearch']").click();
//        // 1. Ensure the element is present in the DOM
//        Locator searchIcon = page.locator("a[data-selenium-id='btnLoanProdSearch']");
//        searchIcon.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//
//// 2. Try to scroll it into view just in case
//        searchIcon.scrollIntoViewIfNeeded();
//
//// 3. Attempt a Force Click (bypasses intercept check)
//        //   searchIcon.click(new Locator.ClickOptions().setForce(true));
//        System.out.println("Executing JS click on Product Search icon...");
//        page.evaluate("selector => document.querySelector(selector).click()", "a[data-selenium-id='btnLoanProdSearch']");
//        // Locator for the 'Product Search' pop-up window.
//        System.out.println("Step 1: Waiting for the product search icon to be ready.");;
//        page.waitForTimeout(6000);
//        // --- Select Radio Buttons ---
////        productSearchDialog.getByLabel("Conventional").check();
//        page.click("//label[text()='Conventional Mortgage']");
//        page.click("//label[text()='Conforming']");
//        page.click("//label[text()='Fixed Rate']");
//
//        // --- Select Checkboxes ---
//        // Loan Terms
////        productSearchDialog.locator("fieldset:has-text('Loan Terms')").getByLabel("All").check();
//        page.click("//label[text()='All']");
//        // Product Groups
////        productSearchDialog.locator("fieldset:has-text('Product Groups')").getByLabel("All").check();
//        //     page.click("//table[@data-selenium-id='chkProductTypes_ALL']//input");
//        // --- Select from Dropdown ---
//        String RateTypeLocator = "//input[@name='rateLockTypeId']/../following-sibling::td/div";
//        String hoverTargetSelector2 = "//li[text()='60 Day Rate Lock']";
//        page.click(RateTypeLocator);
//        selectAngularDropdownOption(page, RateTypeLocator, hoverTargetSelector2);
//
//
//        // The 'Search' button specifically inside the dialog.
//        page.locator("//a[@data-selenium-id='btnSearch']").waitFor(new Locator.WaitForOptions()
//                .setState(WaitForSelectorState.VISIBLE));
//        page.click("//a[@data-selenium-id='btnSearch']");
//        page.waitForTimeout(8000);
//        System.out.println("Product Search dialog is open.");
//
//        // The grid that contains the product results.
////      page.locator("//td[contains(@class,'span_link')]");
//        page.waitForSelector("//td[contains(@class,'span_link')]", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
//
//
//
//        // Find all data rows and click the one at the specified index.
////    page.click("(//td[contains(@class,'span_link')])[3]");
//        // page.click("//div[text()='C30'");
//        page.locator("//div[contains(@class, 'x-grid-cell-inner') and text()='C30']").click();
//
////         pageUp();
//
//        ///clearAndEnterText(page, "//input[@name='settlementDate']", SettlmentDate);
////        pageDown();



//        page.click("//span[text()='NEXT']");