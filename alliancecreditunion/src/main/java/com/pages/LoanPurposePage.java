package com.pages;

import java.io.IOException;

public class LoanPurposePage extends BasePage_Old {
    public LoanPurposePage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void purchaseloanpurposeform(String phoneNumber) throws IOException {
        page.waitForTimeout(3000);
        page.locator("ion-segment-button[value='Purchase']").click();
      //  page.locator("ion-segment-button[value='Refinance']").click();
       // page.locator("ion-segment-button:has-text('Yes')").click();
        page.locator("//ion-input[@name='phoneNumber']").click();
        page.fill("input[name='phoneNumber']",phoneNumber);
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("1 - LoanPurpose Screen is done.");
    }
    public void refinanceloanpurposeform(String phoneNumber) throws IOException {
        page.waitForTimeout(3000);
        //  page.locator("ion-segment-button[value='Purchase']").click();
        page.locator("ion-segment-button[value='Refinance']").click();
        // page.locator("ion-segment-button:has-text('No')").click();
        page.locator("//ion-input[@name='phoneNumber']").click();
        page.fill("input[name='phoneNumber']",phoneNumber);
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("1 - LoanPurpose Screen is done.");
    }
}
