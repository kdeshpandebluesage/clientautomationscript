package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

   public static void selectAngularDropdownOption(Page page, String dropdownToggleSelector, String optionText) {
//        String dropdownToggleLocator = "//ion-select[@role='combobox']";
//        String hoverTargetSelector = "//div[text()='Single Family ']";
//        page.click(dropdownToggleSelector);
//        String optionLocator = String.format("//ion-select//ion-select-option", optionText);
//        System.out.println("optionLocator: " + optionLocator + "is displayed");

//         Wait for the dropdown option to be visible and clickable

        page.hover(optionText);
       System.out.println("optionText: " + optionText + "is displayed");
        page.click(optionText);
    }

   /* public static void selectAngularDropdownOption(Page page, String dropdownToggleSelector, String optionText) {
        // Assuming default selectors, adjust as needed
        String dropdownToggleLocator = "//ion-select[@role='combobox']"; // Declaration added
        String hoverTargetSelector = String.format("//ion-select-option[normalize-space()='%s']", optionText); // Declaration added using optionText

        page.click(dropdownToggleLocator); // Use click to open the dropdown first
        page.waitForSelector(hoverTargetSelector); // Wait for the option to appear
        page.hover(hoverTargetSelector);
        page.click(hoverTargetSelector);
    }*/


    public void pageDown (){
        // Simulate Page Down
        page.keyboard().press("PageDown");

        // Optional: Pause for visual validation
        page.waitForTimeout(2000); // 2 seconds
    }
    public static void clearAndEnterText(Page page, String selector, String newText) {
        Locator field = page.locator(selector);
        field.click();                // Focus on the field
        field.press("Control+A");    // Select all text
        field.press("Delete");       // Clear it
        field.type(newText);         // Enter new text
    }
    public static void enterText(Page page, String selector, String newText) {
        Locator field = page.locator(selector);
        field.click();                // Focus on the field
        field.type(newText);         // Enter new text
    }
    public static void click(Page page, String selector) {
        Locator field = page.locator(selector);
        field.click();                // Focus on the field

    }
}
