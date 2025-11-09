package com.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.io.IOException;

public class PurchaseLoanPage extends BasePage {
    public PurchaseLoanPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void purchaseInfoform( String Purchasezipcode,String PurchasepropertyType ,String PurchaseLoanpurchasePrice, String Purchasedownpayment) throws IOException {
                        page.waitForTimeout(3000);
            page.locator("input[name='searchZipcode']").click();
        page.fill("input[name='searchZipcode']",Purchasezipcode);
        page.locator("//ion-segment-button[@value='primaryResidence']").click();

        //Property Type selection //
        String dropdownToggleLocator = "//ion-select[@role='combobox']";
        String hoverTargetSelector = "//div[text()='Single Family ']";
        page.click("//ion-select[@role='combobox']");
        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", hoverTargetSelector);

        page.locator("//ion-segment[@name='numberOfUnits']").click();

        pageDown();
        System.out.println("Page Down is done.");


        page.locator("//input[@name='purchasePrice']").click();
        page.fill("input[name='purchasePrice']", PurchaseLoanpurchasePrice);

        page.locator("//input[@name='downPaymentAmount']").click();
        page.fill("input[name='downPaymentAmount']", Purchasedownpayment);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Purchase selection is done.");
    }
}
