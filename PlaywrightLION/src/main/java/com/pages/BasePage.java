package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class BasePage {
    protected Page page;
    public static boolean elemFound;
    public String checkMaskLoad = "//div[not(contains(@style,'display: none;'))]/div[contains(@class,'x-mask-loading')]/div[text()='Loading...']";
    public List<WebElement> listElems;
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

    public void pageUp (){
        // Simulate Page Down
        page.keyboard().press("PageUp");
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
    public void elementFound(String selector) {

        page.setDefaultTimeout(2000);
        elemFound = page.locator(selector).count() > 0;
    }
    public void listWebElements(String selector,String logLabel){
        if((listElems != null) && (listElems.size()>0) ) {
            listElems.clear();
        }
//        listElems = driver.findElements(By.xpath(xPath));
        Locator listElems = page.locator("selctor =" + selector);;
//		return listElems;

    }


    public void checkElementLoadMask(String selector ,String text, String logLabel) throws InterruptedException {
        elementFound(selector);
        try {
            if (elemFound == true) {
                page.setDefaultTimeout(2000);
                page.waitForSelector(selector,
                        new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
                Thread.sleep(5000);
                System.out.println(logLabel + " is invisible");
            } else {
                Exception error = new Exception(logLabel + " : Element not found");
//				getErrorMethod(metaDataKey, error);
            }
        } catch (Exception e) {
//			try {
//				getErrorMethod(metaDataKey, error);
//			} catch (IOException e) {
            e.printStackTrace();
//			}
        }
    }
}
