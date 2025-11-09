package com.pages;

public class LOPLoanSourcePage extends BasePage {
    public LOPLoanSourcePage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopLoanSource() {

        page.click("//span[text()='Loan Source");
        String ediRegiondropdownToggleLocator = "//input[@name='salesRegionId']/../following-sibling::td/div";
        String hoverTargetSelector = "//li[text()='NOVA']";
        page.click(ediRegiondropdownToggleLocator);
        selectAngularDropdownOption(page, ediRegiondropdownToggleLocator, hoverTargetSelector);
        System.out.println("click dropdown");
        String MarketTypeLocator = "//input[@name='subSalesRegionId']/../following-sibling::td/div";
        String hoverTargetSelector1 = "//li[text()='NC Brais']";
        selectAngularDropdownOption(page, MarketTypeLocator, hoverTargetSelector1);

        String BranchTypeLocator = "//input[@name='salesBranchId']/../following-sibling::td/div";
        String hoverTargetSelector2 = "//li[text()='VA-Main-520015']";
        selectAngularDropdownOption(page, MarketTypeLocator, hoverTargetSelector2);
        page.click(BranchTypeLocator);

        String CommunityTypeLocator = "//input[@name='fulfillmentCenterId']/../following-sibling::td/div";
        String hoverTargetSelector3 = "//li[text()='Carolina Crew West']";
        selectAngularDropdownOption(page, CommunityTypeLocator, hoverTargetSelector3);
        page.click(CommunityTypeLocator);

        String LeadTypeLocator = "//input[@name='leadTypeId']/../following-sibling::td/div";
        String hoverTargetSelector4 = "//li[text()='Company Generated']";
        selectAngularDropdownOption(page, CommunityTypeLocator, hoverTargetSelector4);
        page.click(LeadTypeLocator);

        String LeadSourceTypeLocator = "//input[@name='leadSourceId']/../following-sibling::td/div";
        String hoverTargetSelector5 = "//li[text()='CG - Corporate Sponsored Events']";
        selectAngularDropdownOption(page, CommunityTypeLocator, hoverTargetSelector5);
        page.click(LeadSourceTypeLocator);

        page.click("(//span[text()='Add'])[1]");
        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']

        System.out.println("Loan Source screen is done .");
    }
}
