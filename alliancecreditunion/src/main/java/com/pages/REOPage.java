package com.pages;

import com.microsoft.playwright.Locator;

import java.io.IOException;

public class REOPage extends BaseClass {
    public REOPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void REOInfoform(String monthlyHomeownersInsurance,String monthlyRealEstateTaxes,String monthlyFloodInsurance,String hoaDues) throws IOException {
        page.waitForTimeout(3000);

        Locator propertyDropdown = page.locator("ion-select[name='propertyType']");
        propertyDropdown.click();
        page.waitForTimeout(500);
        String properyTypeSelector = "//div[text()='Single Family ']";
        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", properyTypeSelector);

        // Simulate Page Down
        page.keyboard().press("PageDown");

        page.locator("//input[@name='monthlyHomeownersInsurance']").click();
        page.fill("input[name='monthlyHomeownersInsurance']", monthlyHomeownersInsurance);

        page.locator("//input[@name='monthlyRealEstateTaxes']").click();
        page.fill("input[name='monthlyRealEstateTaxes']", monthlyRealEstateTaxes);

        page.locator("//input[@name='monthlyFloodInsurance']").click();
        page.fill("input[name='monthlyFloodInsurance']", monthlyFloodInsurance);

        page.locator("//input[@name='hoaDues']").click();
        page.fill("input[name='hoaDues']", hoaDues);


        page .click ("(//ion-segment-button[text()='No'])[3]");


        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        page.waitForTimeout(3000);
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("REO completed");


    }
    public void REOInfoformPurchase() throws IOException {
        page.waitForTimeout(3000);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("REO completed");


    }
}
