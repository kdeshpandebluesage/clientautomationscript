package com.pages;

import com.microsoft.playwright.Locator;

public class OtherIncomePage extends BasePage {
    public OtherIncomePage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void OtherIncomeform2Borr(String incomeAmount) {
        page.waitForTimeout(3000);
        //ion-button[contains(@class,'income-edit')]
        page.click("//ion-button[contains(@class,'income-edit')]");
        //ion-select[@name='incomeType']
        Locator incomeTypeDropDown = page.locator("ion-select[name='incomeType']");
        incomeTypeDropDown.click();
        String incomeTypeSelector = "//div[text()='Dividends/Interest ']";
        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", incomeTypeSelector);
        page.locator("//ion-input[@name='incomeAmount']").click();
        page.fill("input[name='incomeAmount']", incomeAmount);
      //  page.click("(//ion-button[@class='income-edit-new-btn ios button button-small button-solid ion-activatable ion-focusable hydrated'])");
       //div[@class='list-add-button']//ion-button
        //Income Type
        //IncomeType	clickdropdownoptionbytext	//ion-select[@name='incomeType']
        //Amount
        //IncomeAmount	textbox	//input[@name='incomeAmount']
//        page.fill("input[name='formattedAddress']", Paddress);
//        page.keyboard().press("PageDown");
//        page.fill("input[name='city']", Pcity);
//   //     page.fill("input[name='state']", Pstate);
//        page.selectOption("div[class*='alert-radio-group']']", Pstate);
//        page.fill("input[name='zip']", Pzip);
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

       //SECOND BORROWER
        page.waitForTimeout(3000);
        //ion-button[contains(@class,'income-edit')]
        page.click("//ion-button[contains(@class,'income-edit')]");
        //ion-select[@name='incomeType']
       // Locator incomeTypeDropDown = page.locator("ion-select[name='incomeType']");
        incomeTypeDropDown.click();
     //   String incomeTypeSelector = "//div[text()='Dividends/Interest ']";
        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", incomeTypeSelector);
        page.locator("//ion-input[@name='incomeAmount']").click();
        page.fill("input[name='incomeAmount']", incomeAmount);
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Other Income is done.");
    }
    public void OtherIncomeform1Borr(String incomeAmount) {
        page.waitForTimeout(3000);
        //ion-button[contains(@class,'income-edit')]
        page.click("//ion-button[contains(@class,'income-edit')]");
        //ion-select[@name='incomeType']
        Locator incomeTypeDropDown = page.locator("ion-select[name='incomeType']");
        incomeTypeDropDown.click();
        String incomeTypeSelector = "//div[text()='Dividends/Interest ']";
        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", incomeTypeSelector);
        page.locator("//ion-input[@name='incomeAmount']").click();
        page.fill("input[name='incomeAmount']", incomeAmount);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        System.out.println("Other Income is done.");
    }

}
