package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPDocumentPage extends BasePage {
    public LOPDocumentPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopInitial() {
        page.click("//span[text()='Documents']");
        page.locator("//span[text()='Packages']").waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        page.click("//span[text()='Packages']");
        page.click("//div[@data-selenium-id='applicantOrder']//input[@role='checkbox']");


        String DocumentTypeLocator = "(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[1]";
        String hoverTargetSelector = "//li[text()='itial Disclosure (Sales)']";
        page.click(DocumentTypeLocator);
        selectAngularDropdownOption(page, DocumentTypeLocator, hoverTargetSelector);


        String creditReportTypeLocator = "//input[@name='creditReportTypeId']/../following-sibling::td/div";
        String hoverTargetSelector1 =  "//li[text()='Instant Merge - 3 File']";
        page.click(creditReportTypeLocator);
        selectAngularDropdownOption(page, creditReportTypeLocator, hoverTargetSelector1);

        page.click("//span[text()='Submit Order']");
        System.out.println("Credit screen is done .");
    }
}
