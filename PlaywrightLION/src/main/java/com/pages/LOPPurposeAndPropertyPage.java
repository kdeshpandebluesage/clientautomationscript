package com.pages;

public class LOPPurposeAndPropertyPage extends BasePage {
    public LOPPurposeAndPropertyPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopOrderCredit() {

        page.click("//span[text()='Order a Credit Report']");
        page.click("//div[@data-selenium-id='applicantOrder']//input[@role='checkbox']");
        page.click("//span[text()='Add to Order']");
        String ediProviderdropdownToggleLocator = "//input[@name='ediProviderTypeId']/../following-sibling::td/div";
        String hoverTargetSelector = "//li[text()='Xactus']";
        page.click(ediProviderdropdownToggleLocator);
        selectAngularDropdownOption(page, ediProviderdropdownToggleLocator, hoverTargetSelector);
        System.out.println("click dropdown");
        String creditReportTypeLocator = "//input[@name='creditReportTypeId']/../following-sibling::td/div";
        String hoverTargetSelector1 = "//li[text()='Xactus']";
        page.click(creditReportTypeLocator);
        System.out.println("Credit screen is done .");
    }
}
