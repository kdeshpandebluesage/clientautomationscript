package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.SelectOption;

import java.io.IOException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RefinancePropertyPage extends BasePage {
    public RefinancePropertyPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void RefiPropInfoform(String Paddress, String Pcity, String Pstate, String Pzip, String rphonenumber, String PropUsed, String CashOutAmt) {
        String stateValueToSelect = "NJ - New Jersey ";
        page.waitForTimeout(3000);

        page.locator("(//span[@class='status'])[2]").click();

        page.locator("input[name='formattedAddress']").click();
        page.locator("input[name='addressLine']").click();
        page.fill("input[name='addressLine']", Paddress);
    //    page.keyboard().press("PageDown");
        page.locator("input[name='city']").click();
        page.fill("input[name='city']", Pcity);

      //  selectAngularDropdownOption(page, "//ion-select[@role='combobox']", "NJ - New Jersey ");
        String xpathByName = "//ion-select[@name='state']";
       // Locator stateDropdown = page.locator("xpath=" + xpathByName);
        Locator stateDropdown = page.locator("ion-select[name='state']");
        System.out.println("XPath: " + xpathByName);
        stateDropdown.click();
        page.waitForTimeout(500);
        System.out.println("Selecting by value using XPath locator: " + stateValueToSelect);
        String stateSelector = "//div[text()='NJ - New Jersey ']";
        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", stateSelector);

        page.waitForTimeout(500);
        Locator dropdownTrigger = page.locator(".dropdown-trigger:has(div.select-icon)");

        page.locator("input[name='zip']").click();
        page.fill("input[name='zip']", Pzip);
      //  String Saveaddress = "//ion-button[contains(@class,'autocomplete-okay')]";
        page.locator("//ion-button[contains(@class,'autocomplete-okay')]").click();

        page.locator("//ion-segment[@name='propertyUse']//ion-segment-button[@value='primaryResidence']").click();

        //Property Type
        Locator propertyDropdown = page.locator("ion-select[name='buildingType']");
        propertyDropdown.click();
        page.waitForTimeout(500);
        String properyTypeSelector = "//div[text()='Single Family ']";
        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", properyTypeSelector);

        //Purpose of Refinance
        Locator purposeDropdown = page.locator("ion-select[name='refiPurposeType']");
        purposeDropdown.click();
        page.waitForTimeout(500);
        String purposeSelector = "//div[text()='Other ']";
        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", purposeSelector);
        page.waitForTimeout(500);

        //Cashout Amount
        page.locator("//input[@name='cashOutAmount']").click();
        page.locator("//input[@name='cashOutAmount']").clear();
        page.fill("input[name='cashOutAmount']", CashOutAmt);


       // Locator propertyTypeDropdown = page.locator("ion-select[name='state']");
      //  page.locator("//ion-segment[@name='propertyUse']", PropUsed).click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Refinance Property selection is done.");
        //
        //  page.getByLabel("State").nth(1).selectOption("NJ - New Jersey ");
//        page.selectOption("//ion-select[@name='state']", new SelectOption().setIndex(2));
        //  stateDropdown.selectOption(stateValueToSelect);
        // System.out.println("New Jersey is selected");
        // stateDropdown.selectOption(stateValueToSelect);
        // assertThat(stateDropdown).hasValue(stateValueToSelect);
        //     assertThat(page.locator("#result")).hasText(stateValueToSelect);
        //   page.locator("input[name='state']").click();
        //    page.fill("input[name='state']", Pstate);
        // Locator stateDropdown = page.locator("#cars-dropdown");
        //      page.selectOption("div[class*='alert-radio-group']']", Pstate);
        //String value = page.locator("select#country option",
        //       new Page.LocatorOptions().setHasText("New Jersey")).getAttribute("value");
        //page.selectOption("#country", value);
    }

}
