package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPApplicationPage extends BasePage_Old {
    public LOPApplicationPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void LopApplication() throws InterruptedException {

        page.click("//span[text()='Application']");
        page.click("//span[text()='Loan Application - URLA']");

        page.waitForTimeout(300);
        page.locator("//span[text()='OK']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//span[text()='OK']");
        System.out.println("Loan Application Navigation is done .");
    }
}
