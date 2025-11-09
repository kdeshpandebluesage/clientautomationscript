package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPLoanSourceSUPPage extends BaseClass {
    public LOPLoanSourceSUPPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopLoanSource() throws InterruptedException {
        page.locator("//div[text()='Loan Source']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//div[text()='Loan Source']");
        // Define a reusable selector for the first dropdown option
        String firstOptionSelector = "(//li)[1]";
        String DivisiondropdownToggleLocator = "//input[@name='salesRegionId']/../following-sibling::td/div";
      //  String hoverTargetSelector = "//li[text()='D100 Supreme One']";
        page.click(DivisiondropdownToggleLocator);
        //selectAngularDropdownOption(page, DivisiondropdownToggleLocator, hoverTargetSelector);
        selectAngularDropdownOption(page, DivisiondropdownToggleLocator, firstOptionSelector);
        System.out.println("click dropdown");

        String RegionTypeLocator = "//input[@name='subSalesRegionId']/../following-sibling::td/div";
       // String hoverTargetSelector1 = "//li[text()='R10000 Supreme HQ']";
        String firstOptionSelector1 = "(//li[contains(@class, 'x-boundlist-item')])[1]";
        page.click(RegionTypeLocator);
      //  selectAngularDropdownOption(page, RegionTypeLocator, hoverTargetSelector1);
        selectAngularDropdownOption(page, RegionTypeLocator, firstOptionSelector1);

        String MainBranchTypeLocator = "//input[@name='salesBranchId']/../following-sibling::td/div";
        String hoverTargetSelector2 = "//li[text()='1000165']";
        page.click(MainBranchTypeLocator);
       // selectAngularDropdownOption(page, MainBranchTypeLocator, hoverTargetSelector2);
       selectAngularDropdownOption(page, MainBranchTypeLocator, firstOptionSelector);


        //Add button is missing in QA hence not adding lead type

//        String LeadTypeLocator = "//input[@name='leadTypeId']/../following-sibling::td/div";
//        String hoverTargetSelector4 = "//li[text()='In-House']";
//        page.click(LeadTypeLocator);
//        selectAngularDropdownOption(page, LeadTypeLocator, hoverTargetSelector4);

//        String LeadSourceTypeLocator = "//input[@name='leadSourceId']/../following-sibling::td/div";
//        String hoverTargetSelector5 = "//li[text()='In-House']";
//        page.click(LeadSourceTypeLocator);
//        selectAngularDropdownOption(page, LeadSourceTypeLocator, hoverTargetSelector5);
//        page.waitForTimeout(6000);
//        page.click("(//span[text()='Add'])[1]");
//        page.click("(//span[text()='Add'])[1]");
        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']

        System.out.println("Loan Source screen is done .");
        page.click("//span[text()='NEXT']");
    }
}
