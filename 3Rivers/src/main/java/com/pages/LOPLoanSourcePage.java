package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPLoanSourcePage extends BasePage_Old {
    public LOPLoanSourcePage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopLoanSource() throws InterruptedException {
        page.locator("//div[text()='Loan Source']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//div[text()='Loan Source']");
        String ediRegiondropdownToggleLocator = "//input[@name='salesRegionId']/../following-sibling::td/div";
        String hoverTargetSelector = "//li[text()='Carolinas']";
        page.click(ediRegiondropdownToggleLocator);
        selectAngularDropdownOption(page, ediRegiondropdownToggleLocator, hoverTargetSelector);
        System.out.println("click dropdown");
        String MarketTypeLocator = "//input[@name='subSalesRegionId']/../following-sibling::td/div";
        String hoverTargetSelector1 = "//li[text()='NC Brais']";
        page.click(MarketTypeLocator);
        selectAngularDropdownOption(page, MarketTypeLocator, hoverTargetSelector1);

        String BranchTypeLocator = "//input[@name='salesBranchId']/../following-sibling::td/div";
        String hoverTargetSelector2 = "//li[text()='NC-BallantyneCorporate-720140']";
        page.click(BranchTypeLocator);
        selectAngularDropdownOption(page, MarketTypeLocator, hoverTargetSelector2);


        String CommunityTypeLocator = "//input[@name='fulfillmentCenterId']/../following-sibling::td/div";
        String hoverTargetSelector3 = "//li[text()='Carolina Crew West']";
        page.click(CommunityTypeLocator);
        selectAngularDropdownOption(page, CommunityTypeLocator, hoverTargetSelector3);


        String LeadTypeLocator = "//input[@name='leadTypeId']/../following-sibling::td/div";
        String hoverTargetSelector4 = "//li[text()='Company Generated']";
        page.click(LeadTypeLocator);
        selectAngularDropdownOption(page, LeadTypeLocator, hoverTargetSelector4);


        String LeadSourceTypeLocator = "//input[@name='leadSourceId']/../following-sibling::td/div";
        String hoverTargetSelector5 = "//li[text()='CG - Corporate Sponsored Events']";
        page.click(LeadSourceTypeLocator);
        selectAngularDropdownOption(page, CommunityTypeLocator, hoverTargetSelector5);


//        page.click("(//span[text()='Add'])[1]");
        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']

        System.out.println("Loan Source screen is done .");
    }
}
