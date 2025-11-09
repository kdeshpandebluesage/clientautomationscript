package com.pages;

import com.microsoft.playwright.Locator;

import java.io.IOException;

public class AssetsSUPPage extends BasePage {
    public AssetsSUPPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void AssetInfoform1Borr(String accountName,String currentValue) throws IOException {
//        page.waitForTimeout(3000);
//        AddAsset	nodes	//ion-button[contains(@class,'asset-master-edit-new-btn')]
        page.click("(//ion-button[contains(@class,'asset-master-edit-new-btn')])[2]");
        Locator assetTypeDropDown = page.locator("ion-select[name='assetType']");
        assetTypeDropDown.click();
        String assetTypeSelector = "//div[text()='Checking ']";
        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", assetTypeSelector);

        page.locator("//ion-input[@name='accountName']").click();
        page.fill("input[name='accountName']", accountName);

        page.locator("//ion-input[@name='currentValue']").click();
        page.fill("input[name='currentValue']", currentValue);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        System.out.println("Asset Info is done.");
    }
    public void AssetInfoform2Borr(String accountName,String currentValue) throws IOException {
//        page.waitForTimeout(3000);
//        AddAsset	nodes	//ion-button[contains(@class,'asset-master-edit-new-btn')]
        page.click("(//ion-button[contains(@class,'asset-master-edit-new-btn')])[1]");
        Locator assetTypeDropDown = page.locator("ion-select[name='assetType']");
        assetTypeDropDown.click();
        String assetTypeSelector = "//div[text()='Checking Accounts ']";
        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", assetTypeSelector);

        page.locator("//ion-input[@name='accountName']").click();
        page.fill("input[name='accountName']", accountName);

        page.locator("//ion-input[@name='currentValue']").click();
        page.fill("input[name='currentValue']", currentValue);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        System.out.println("Asset Info is done.");
    }
}
