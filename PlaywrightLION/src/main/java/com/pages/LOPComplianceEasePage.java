package com.pages;

public class LOPComplianceEasePage extends BasePage {
    public LOPComplianceEasePage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopCE() throws InterruptedException {
        page.click("//div[text()='ComplianceEase']");
        page.wait(2000);


        page.click("span[text()='Send Request']");
        page.wait(2000);

;        System.out.println("AUS screen is done .");
    }
}
