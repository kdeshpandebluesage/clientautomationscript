package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.SelectOption;

import java.io.IOException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RefinancePropertyPage extends BasePage {
    public RefinancePropertyPage(com.microsoft.playwright.Page page) {
        super(page);
    }
/*
    public void RefiPropInfoform(String Refinanceaddress, String Refinancecity, String RefinanceState, String Refinancezipcode, String RefinancepropertyType,String RefinancePurpose, String RefinancePropertyUsed, String RefinanceCashoutAmt) {
//        String stateValueToSelect = "NJ - New Jersey ";
        page.waitForTimeout(3000);

//        page.locator("(//span[@class='status'])[2]").click();

        page.locator("input[name='formattedAddress']").click();
        page.locator("input[name='addressLine']").click();
        page.fill("input[name='addressLine']", Refinanceaddress);
    //    page.keyboard().press("PageDown");
        page.locator("input[name='city']").click();
        page.fill("input[name='city']", Refinancecity);

      //  selectAngularDropdownOption(page, "//ion-select[@role='combobox']", "NJ - New Jersey ");
        String xpathByName = "//ion-select[@name='state']";
       // Locator stateDropdown = page.locator("xpath=" + xpathByName);
        Locator stateDropdown = page.locator("ion-select[name='state']");
        System.out.println("XPath: " + xpathByName);
        stateDropdown.click();
        page.waitForTimeout(500);
//        System.out.println("Selecting by value using XPath locator: " + stateValueToSelect);
//        String stateSelector = "//div[text()='NJ - New Jersey ']";
        String stateSelector = "//div[normalize-space()='" + RefinanceState + "']";

        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", stateSelector);

        page.waitForTimeout(500);
        Locator dropdownTrigger = page.locator(".dropdown-trigger:has(div.select-icon)");

        page.locator("input[name='zip']").click();
        page.fill("input[name='zip']", Refinancezipcode );
      //  String Saveaddress = "//ion-button[contains(@class,'autocomplete-okay')]";
        page.locator("//ion-button[contains(@class,'autocomplete-okay')]").click();

        page.locator("//ion-segment[@name='propertyUse']//ion-segment-button[@value='primaryResidence']").click();

        //Property Type
        Locator propertyDropdown = page.locator("ion-select[name='buildingType']");
        propertyDropdown.click();
        page.waitForTimeout(500);
//        String properyTypeSelector = "//div[text()='Single Family ']";
        String properyTypeSelector = "//div[normalize-space()='" + RefinancepropertyType + "']";

        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", properyTypeSelector);

        //Purpose of Refinance
        Locator purposeDropdown = page.locator("ion-select[name='refiPurposeType']");
        purposeDropdown.click();
        page.waitForTimeout(500);
//        String purposeSelector = "//div[text()='Cash Out ']";
        String purposeSelector = "//div[normalize-space()='" + RefinancePurpose + "']";

        selectAngularDropdownOption(page, "//ion-select[@role='combobox']", purposeSelector);
        page.waitForTimeout(500);

        //Cashout Amount
        page.locator("//input[@name='cashOutAmount']").click();
        page.locator("//input[@name='cashOutAmount']").clear();
        page.fill("input[name='cashOutAmount']", RefinanceCashoutAmt);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Refinance Property selection is done.");

    }*/
public void RefiPropInfoform(String Refinanceaddress, String Refinancecity, String RefinanceState, String Refinancezipcode,String RefinancepropertyType,  String RefinancePropertyUsed, String RefinancePurpose, String RefinanceCashoutAmt) {
    page.locator("input[name='formattedAddress']").click();
    clearAndEnterText(page, "input[name='addressLine']", Refinanceaddress);
    //        clearAndEnterText(page,"input[name='addressLine']", ApplicationInfo2BrPrimaryApplicantAddressline);
    clearAndEnterText(page, "input[name='city']", Refinancecity);
    String statedropdownToggleLocator = "//ion-select[@name='state']";
    // String hoverTargetSelector = "//div[text()='NJ - New Jersey ']";
    String hoverTargetSelector = "//div[normalize-space()='" + RefinanceState +"']";
    page.click(statedropdownToggleLocator);
    selectAngularDropdownOption(page, statedropdownToggleLocator, hoverTargetSelector);
    System.out.println("click dropdown");
    clearAndEnterText(page, "input[name='zip']", Refinancezipcode);
    click(page, "//ion-button[contains(@class,'autocomplete-okay')]");
    page.locator("//ion-segment[@name='propertyUse']//ion-segment-button[@value='primaryResidence']").click();

    //Property Type
    Locator propertyDropdown = page.locator("ion-select[name='buildingType']");
    propertyDropdown.click();
    page.waitForTimeout(500);
    String propertyTypeSelector = "//div[normalize-space()='" + RefinancePropertyUsed +"']";
    selectAngularDropdownOption(page, "//ion-select[@role='combobox']", propertyTypeSelector);

    //Purpose of Refinance
    Locator purposeDropdown = page.locator("ion-select[name='refiPurposeType']");
    purposeDropdown.click();
    page.waitForTimeout(500);
    String purpOfRefiTypeSelector = "//div[normalize-space()='" + RefinancePurpose +"']";
    selectAngularDropdownOption(page, "//ion-select[@role='combobox']", purpOfRefiTypeSelector);
    if (RefinancePurpose != null && RefinancePurpose.trim().startsWith("Cash Out")) {

        page.locator("//input[@name='cashOutAmount']").click();

        page.locator("//input[@name='cashOutAmount']").clear();

        page.locator("//input[@name='cashOutAmount']").fill(RefinanceCashoutAmt);
    }
    page.locator("//ion-button[contains(@class,'save-continue')]").click();
    System.out.println("Refinance Property selection is done.");

}
}


