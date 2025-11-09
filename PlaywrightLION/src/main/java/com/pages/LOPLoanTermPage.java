package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPLoanTermPage extends BasePage {
    public LOPLoanTermPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopLoantermPurchase(String LOPAppraisedValue, String LOPCreditscore,String SettlmentDate) {
//        page.locator("//div[text()='Loan Term']").waitFor(new Locator.WaitForOptions()
//                .setState(WaitForSelectorState.VISIBLE));
//        page.click("//div[text()='Loan Term']");
        page.waitForTimeout(2000);
        String propertyTypeLocator = "//input[@name='propertyTypeId']/../following-sibling::td/div";
        String hoverTargetSelector = "//li[text()='Condominium']";
        page.click(propertyTypeLocator);
        selectAngularDropdownOption(page, propertyTypeLocator, hoverTargetSelector);

//        enterText(page, "//input[@name='appraisedValueAmt']", LOPAppraisedValue);

//        String OverrideTypeLocator = "//input[@name='creditScoreOverrideTypeId']/../following-sibling::td/div";
//        String hoverTargetSelector2 = "//li[text()='Non-Traditional Credit Established']";
//        page.click(OverrideTypeLocator);
//        selectAngularDropdownOption(page, OverrideTypeLocator, hoverTargetSelector2);
        pageDown();
        String DocumentationTypeLocator = "//input[@name='documentationTypeId']/../following-sibling::td/div";
        String hoverTargetSelector3 = "//li[text()='Full Documentation']";
        page.click(DocumentationTypeLocator);
        System.out.println("Waiting for and clicking the 'Full Documentation' option using getByRole...");
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Full Documentation")).click();
//        selectAngularDropdownOption(page, DocumentationTypeLocator, hoverTargetSelector3);

//        enterText(page, "//input[@name='representativeCreditScore']", LOPCreditscore);
        enterText(page, "//input[@name='appraisedValueAmt']", LOPAppraisedValue);
         pageUp();
        enterText(page, "//input[@name='settlementDate']", SettlmentDate);

        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']

        System.out.println("Loan Term screen is done .");
        page.click("//span[text()='NEXT']");
    }

}
