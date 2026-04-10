package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPCreditSUPPage extends BaseClass {
    public LOPCreditSUPPage(Page page) {
        super(page);
    }


    public void lopOrderCredit2BR() throws InterruptedException {
        page.click("//div[text()='Credit']");
        page.locator("//span[text()='Order a Credit Report']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//span[text()='Order a Credit Report']");
        page.click("//div[@data-selenium-id='applicantOrder']//input[@role='checkbox']");

        page.click("//span[text()='Add to Order']");

        String creditAgencydropdownToggleLocator = "//input[@name='creditAgencyId']/../following-sibling::td/div";
        String hoverTargetSelector = "//li[text()='Xactus NCRA/CSD']";
        page.click(creditAgencydropdownToggleLocator);
        selectAngularDropdownOption(page, creditAgencydropdownToggleLocator, hoverTargetSelector);


        String creditReportTypeLocator = "//input[@name='creditReportTypeId']/../following-sibling::td/div";
        String optionToSelectLocator  =  "//li[text()='Instant Merge - 3 File']";

        System.out.println("Clicking the Credit Report Type dropdown...");
        page.click(creditReportTypeLocator);
//        page.pause();
        System.out.println("Pausing script. Playwright Inspector should open...");
//        page.locator(optionToSelectLocator).click();
//        page.locator("li:has-text('Instant Merge - 3 File')").click();
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Instant Merge - 3 File")).click();
//        page.locator(optionToSelectLocator).click();
        page.click("//span[text()='Submit Order']");
        System.out.println("Credit screen is done .");

    }

    public void lopOrderCredit1BR(){
        page.click("//div[text()='Credit']");
        page.locator("//span[text()='Order a Credit Report']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//span[text()='Order a Credit Report']");
//        page.click("//div[@data-selenium-id='applicantOrder']//input[@role='checkbox']");

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

