package com.pages;

import java.io.IOException;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class PurchaseLoanPage extends BaseClass {
    public PurchaseLoanPage(Page page) {
        super(page);
    }

/*    public void purchaseInfoform(String Paddress, String Pcity, String Pstate, String Pzip, String rphonenumber) throws IOException {
        page.waitForTimeout(3000);
        page.fill("input[name='formattedAddress']", Paddress);
        page.keyboard().press("PageDown");
        page.fill("input[name='city']", Pcity);
//        page.fill("input[name='state']", Pstate);
        page.selectOption("div[class*='alert-radio-group']']", Pstate);
        page.fill("input[name='zip']", Pzip);
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Purchase selection is done.");
    }
}*/
public void purchaseInfoform( String Purchasezipcode,String PurchasepropertyType ,String PurchaseLoanpurchasePrice, String Purchasedownpayment) throws IOException {
    page.waitForTimeout(6000);
    page.locator("input[name='searchZipcode']").click();
    page.fill("input[name='searchZipcode']",Purchasezipcode);
    page.locator("//ion-segment-button[@value='primaryResidence']").click();

    //Property Type selection //
    String dropdownToggleLocator = "//ion-select[@role='combobox']";
   // String hoverTargetSelector = "//div[text()='Single Family ']";
   // String hoverTargetSelector = "//div[text()='"+ PurchasepropertyType +"']";
    String hoverTargetSelector = "//div[normalize-space()='" + PurchasepropertyType +"']";
   // Locator loanNumberCell = page.locator("//div[contains(@class, 'x-grid-cell-inner') and .='" + loanNumber + "']");
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

