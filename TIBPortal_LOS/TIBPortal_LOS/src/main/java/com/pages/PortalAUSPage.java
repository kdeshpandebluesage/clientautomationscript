package com.pages;

public class PortalAUSPage extends BasePage_Old {
    public PortalAUSPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopAUSDU() throws InterruptedException {
        page.click("//div[text()='Automated Underwriting']");
        page.wait(2000);

        page.click("//label[text()='Fannie Mae Desktop Underwriter (DU)']");
        page.click("//span[text()='Credit Info']");

        page.fill("//input[@name='//input[@name='referenceNo_0']']", "504889");

        String creditAgencydropdownToggleLocator = "//input[@name='creditAgencyNameFannie']/../following-sibling::td/div";
        String hoverTargetSelector = "//li[text()='Xactus NCRA/CSD']";
        page.click(creditAgencydropdownToggleLocator);
        selectAngularDropdownOption(page, creditAgencydropdownToggleLocator, hoverTargetSelector);


        page.fill("//input[@name='brokerAccountId']", "123");
        page.fill("//input[@name='brokerAccountId']", "123");

        page.click("span[text()='Send Request']");
        page.wait(2000);

;        System.out.println("AUS screen is done .");
    }
}
