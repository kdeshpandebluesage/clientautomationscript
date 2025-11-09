package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPAUSPage extends BasePage {
    public LOPAUSPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopAUSDU() throws InterruptedException {
        page.click("//div[text()='Automated Underwriting']");
        page.wait(2000);

        page.click("(//input[@type='button'])[6]");
        page.click("span[text()='Send Request']");
        page.wait(2000);

;        System.out.println("AUS screen is done .");
    }
}
