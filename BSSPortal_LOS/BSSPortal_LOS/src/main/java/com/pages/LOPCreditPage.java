package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPCreditPage extends BaseClass {
    public LOPCreditPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopOrderCredit() {
        page.click("//div[text()='Credit']");
        page.locator("//span[text()='Order a Credit Report']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//span[text()='Order a Credit Report']");
        page.click("//div[@data-selenium-id='applicantOrder']//input[@role='checkbox']");

        page.click("//span[text()='Add to Order']");

        String ediProviderdropdownToggleLocator = "//input[@name='ediProviderTypeId']/../following-sibling::td/div";
        String hoverTargetSelector = "//li[text()='Xactus']";
        page.click(ediProviderdropdownToggleLocator);
        selectAngularDropdownOption(page, ediProviderdropdownToggleLocator, hoverTargetSelector);


        String creditReportTypeLocator = "//input[@name='creditReportTypeId']/../following-sibling::td/div";
        String hoverTargetSelector1 =  "//li[text()='Instant Merge - 3 File']";
        page.click(creditReportTypeLocator);
        selectAngularDropdownOption(page, creditReportTypeLocator, hoverTargetSelector1);

        page.click("//span[text()='Submit Order']");
        System.out.println("Credit screen is done .");
    }
}
