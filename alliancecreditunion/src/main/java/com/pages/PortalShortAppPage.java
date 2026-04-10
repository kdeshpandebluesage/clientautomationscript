package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.io.IOException;

public class PortalShortAppPage extends BaseClass {
    public PortalShortAppPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void shortApp(String creditscore, String DTI, String PropertyType, String LeinType) throws IOException {
        page.waitForTimeout(3000);

        page.fill("input[name='loanClosedDate']", addDaysToToday(25));
//        enterText(page, "input[name='loanClosedDate']", todayDate());
        enterText(page, "input[name='creditAuthorizationDate']", todayDate());
        page.click("//input[@name='creditScore']");
        enterText("(//input[@name='creditScore'])", creditscore);


        page.click("//input[@name='totalDebtRatioPct']");
        enterText("(//input[@name='totalDebtRatioPct'])", DTI);
        page.click("//input[@name='buildingTypeId']");
        clearAndFillText("(//input[@name='buildingTypeId'])", PropertyType);
        page.click("//input[@name='lienTypeId']");
        enterText("(//input[@name='lienTypeId'])", LeinType);


        page.click("//a[@data-selenium-id='btnLoanProdSearch']");
        System.out.println("Loan Product screen is done .");
        // --- Select Radio Buttons ---
//        productSearchDialog.getByLabel("Conventional").check();
        page.click("//label[text()='Conventional Mortgage']");
        page.click("//label[text()='Conforming']");
        page.click("//label[text()='Fixed Rate']");
        page.click("  //label[text()='Adjustable Rate']");


        // --- Select Checkboxes ---
        // Loan Terms
//        productSearchDialog.locator("fieldset:has-text('Loan Terms')").getByLabel("All").check();
        page.waitForTimeout(3000);
        page.click("(//label[text()='All'])[1]");
        // Product Groups
//        productSearchDialog.locator("fieldset:has-text('Product Groups')").getByLabel("All").check();
        page.click("//table[@data-selenium-id='chkProductTypes_ALL']//input");
        // --- Select from Dropdown ---
        String RateTypeLocator = "(//input[@name='rateLockTypeId']/../following-sibling::td/div)[2]";
        String hoverTargetSelector2 = "//li[text()='60 Day Rate Lock']";
        page.click(RateTypeLocator);
        selectAngularDropdownOption(page, RateTypeLocator, hoverTargetSelector2);


        // The 'Search' button specifically inside the dialog.
        page.locator("//a[@data-selenium-id='btnSearch']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//a[@data-selenium-id='btnSearch']");
        page.waitForTimeout(8000);
        System.out.println("Product Search dialog is open.");
        page.waitForSelector("//td[contains(@class,'span_link')]", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));

        // Find all data rows and click the one at the specified index.
//    page.click("(//td[contains(@class,'span_link')])[3]");
        page.click("//div[text()='Conforming FNMA 30 Year Fixed Wholesale']");
        System.out.println("Loan Term screen is done .");
        page.waitForSelector("//input[@name='documentationTypeId']/../following-sibling::td/div", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        String DocumentTypeLocator = "//input[@name='documentationTypeId']/../following-sibling::td/div";
        String hoverTargetSelector3 = "//li[text()='Full Documentation']";
        page.click(DocumentTypeLocator);
        selectAngularDropdownOption(page, DocumentTypeLocator, hoverTargetSelector3);
        page.click("//span[text()='Register']");

    }
}
