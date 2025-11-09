package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPLoanSourceSUPPage extends BasePage {
    public LOPLoanSourceSUPPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopLoanSource() throws InterruptedException {
        page.locator("//div[text()='Loan Source']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//div[text()='Loan Source']");

        String DivisiondropdownToggleLocator = "//input[@name='salesRegionId']/../following-sibling::td/div";
        String hoverTargetSelector = "//li[text()='D100 Supreme One']";
        page.click(DivisiondropdownToggleLocator);
        selectAngularDropdownOption(page, DivisiondropdownToggleLocator, hoverTargetSelector);
        System.out.println("click dropdown");

        String RegionTypeLocator = "//input[@name='subSalesRegionId']/../following-sibling::td/div";
        String hoverTargetSelector1 = "//li[text()='R10000 Supreme HQ']";
        page.click(RegionTypeLocator);
        selectAngularDropdownOption(page, RegionTypeLocator, hoverTargetSelector1);

        String MainBranchTypeLocator = "//input[@name='salesBranchId']/../following-sibling::td/div";
        String hoverTargetSelector2 = "//li[text()='1000165']";
        page.click(MainBranchTypeLocator);
        selectAngularDropdownOption(page, MainBranchTypeLocator, hoverTargetSelector2);


        /*Add button is missing in QA hence not adding lead type

        String LeadTypeLocator = "//input[@name='leadTypeId']/../following-sibling::td/div";
        String hoverTargetSelector4 = "//li[text()='In-House']";
        page.click(LeadTypeLocator);
        selectAngularDropdownOption(page, LeadTypeLocator, hoverTargetSelector4);

        String LeadSourceTypeLocator = "//input[@name='leadSourceId']/../following-sibling::td/div";
        String hoverTargetSelector5 = "//li[text()='In-House']";
        page.click(LeadSourceTypeLocator);
        selectAngularDropdownOption(page, LeadSourceTypeLocator, hoverTargetSelector5);

        page.click("(//span[text()='Add'])[1]"); */
//        page.click("(//span[text()='Add'])[1]");
        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']

        System.out.println("Loan Source screen is done .");
        page.click("//span[text()='NEXT']");
    }
}
