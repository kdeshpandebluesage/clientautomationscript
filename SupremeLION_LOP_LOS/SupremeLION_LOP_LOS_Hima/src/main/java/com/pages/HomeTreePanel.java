package com.pages;

import com.microsoft.playwright.Page;

public class HomeTreePanel {
    protected Page page;

    public HomeTreePanel(Page page)
    {
        this.page = page;
    }
    public static void LoanFolders(Page page, String dropdownToggleSelector, String optionText) {

        page.waitForTimeout(3000);
        page.click("(//span[text()='Loan Folders']");
        System.out.println("Entered Loan Folders Page");
        // String dropdownToggleLocator = "//ion-select[@role='combobox']";
        // String stateSelector = "//div[text()='NJ - New Jersey ']";
        // page.click(dropdownToggleSelector);
        //       String optionLocator = String.format("//div[contains(@class, 'alert-wrapper sc-ion-alert-ios') and contains(@class, 'sc-ion-alert-ios')]", optionText);
        //       System.out.println("optionLocator: " + optionLocator + "is displayed");

        // Wait for the dropdown option to be visible and clickable

//        page.hover(hoverTargetSelector);
//        page.click(hoverTargetSelector);
        // Click on the desired option
 //       page.click(optionLocator);

    }
    public void RefiPropInfoform(String paddress, String pcity, String pstate, String pzip, String rphonenumber) {
    }
}
