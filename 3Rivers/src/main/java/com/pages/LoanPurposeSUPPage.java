package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.io.IOException;

public class LoanPurposeSUPPage extends BaseClass {
    public LoanPurposeSUPPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void loanpurposeform(String LoanPurposePhoneNumber) throws IOException {
        page.waitForTimeout(6000);
        page.locator("ion-segment-button[value='Purchase']").click();
        page.click("(//ion-segment-button[text()='Yes'])");
        page.locator("//input[@name='phoneNumber']").click();
        page.fill("input[name='phoneNumber']",  LoanPurposePhoneNumber);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Purchase LoanPurpose Screen is done.");
    }
    public void refinanceloanpurposeform(String LoanPurposePhoneNumber) throws IOException {
        page.waitForTimeout(3000);
        page.locator("ion-segment-button[value='Refinance']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.locator("ion-segment-button[value='Refinance']").click();
        page.click("(//ion-segment-button[text()='Yes'])");
        page.locator("//ion-input[@name='phoneNumber']").click();
        page.fill("input[name='phoneNumber']",LoanPurposePhoneNumber);
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("1 - Refinance LoanPurpose Screen is done.");
    }

}
