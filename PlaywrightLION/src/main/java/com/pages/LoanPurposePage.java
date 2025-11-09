package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoanPurposePage extends BasePage {
    public LoanPurposePage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void loanpurposeform(String LoanPurposePhoneNumber) throws IOException {
        //if("purchase" column value equals "Yes")
//        Map<String,String> row = datalst.get(0);
        page.waitForTimeout(3000);
        page.locator("ion-segment-button[value='Purchase']").click();
        page.locator("//input[@name='phoneNumber']").click();
        page.fill("input[name='phoneNumber']",  LoanPurposePhoneNumber);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Loan Purpose screen is done.");
    }
    public void refinanceloanpurposeform(String LoanPurposePhoneNumber) throws IOException {
        page.waitForTimeout(3000);
        //  page.locator("ion-segment-button[value='Purchase']").click();
        page.locator("ion-segment-button[value='Refinance']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.locator("ion-segment-button[value='Refinance']").click();
        // page.locator("ion-segment-button:has-text('No')").click();

        page.locator("//ion-input[@name='phoneNumber']").click();
        page.fill("input[name='phoneNumber']",LoanPurposePhoneNumber);
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("1 - LoanPurpose Screen is done.");
    }
}
