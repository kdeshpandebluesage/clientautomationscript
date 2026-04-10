package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPLoanSourcePage extends BaseClass {
    public LOPLoanSourcePage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopLoanSource() throws InterruptedException {
        page.locator("text=Loading...").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN));
        page.locator("//div[text()='Loan Source']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//div[text()='Loan Source']");

        String LeadTypeLocator = "//input[@name='leadTypeId']/../following-sibling::td/div";
        String hoverTargetSelector4 = "//li[text()='Family or Friend']";
        page.click(LeadTypeLocator);
        selectAngularDropdownOption(page, LeadTypeLocator, hoverTargetSelector4);


// Set zoom to 80% via JavaScript evaluation
        page.evaluate("document.body.style.zoom='0.9'");
        // Use a locator so we can wait for it to be 'Stable'
        Locator addBtn = page.locator("//a[@data-selenium-id='addLeadSourceBt']");
        addBtn.scrollIntoViewIfNeeded();
        addBtn.click(new Locator.ClickOptions().setForce(true));
        // Reset zoom back to 100% for the rest of the test
        page.evaluate("document.body.style.zoom='1.0'");


        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']
        page.click("//span[text()='NEXT']");
        System.out.println("Loan Source screen is done .");
    }
}



