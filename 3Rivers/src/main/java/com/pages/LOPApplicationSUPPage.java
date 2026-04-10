package com.pages;

public class LOPApplicationSUPPage extends BaseClass {
    public LOPApplicationSUPPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void LopApplication() throws InterruptedException {

        page.click("//span[text()='Application']");
        page.click("//span[text()='Loan Application - URLA']");

        page.waitForTimeout(3000);

        page.click("//span[text()='OK']");
        System.out.println("Loan Application Navigation is done .");
    }
}
