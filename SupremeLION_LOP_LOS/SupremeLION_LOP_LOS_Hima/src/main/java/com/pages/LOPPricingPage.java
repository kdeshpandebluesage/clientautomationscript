package com.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.openqa.selenium.WebElement;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LOPPricingPage extends BaseClass {
    public LOPPricingPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopPricing(String logLabel) throws InterruptedException {
        String exceptionsModalTitle = "//span[text()='Exceptions for Loan']";
        String saveButton = "//div[@id='retailloanexceptionsdialog']//a[@data-selenium-id='saveButton']";
        String noExceptionsBadge = "//a[@data-selenium-id='exceptions']/div[contains(@class,'badgeText')][text()='0']";
        String exceptionButton = "//span[@id='btnExceptions-btnIconEl']";
        String exceptionfound = "//a[@data-selenium-id='exceptions']/div[contains(@class,'badgeText')][text()='0']";
        String exceptionbtn = "//span[@id='btnExceptions-btnIconEl']";
        String checkexceptions = "//span[text()='Exceptions for Loan']";
        String excpchkbox = "//div[@data-selenium-id='exceptionGrid']//tr//td[8]//div[contains(@class,'checkcolumncustom')]";
        String clickexcption = "//div[contains(@id,'loanexceptionsdialog')]//img";
        String uncheckedExceptions = "//td[contains(@class,'x-grid-td x-grid-cell-headerId-checkcolumn')]/div/div[not(contains(@class,'x-grid-checkcolumncustom x-grid-checkcolumncustom-checked'))]";
        String dissave = "(//div[@id='retailloanexceptionsdialog']//a[@data-selenium-id='saveButton'][contains(@class,'x-btn-disabled')])";
        String closeexception = "//div[contains(@id,'loanexceptionsdialog')]//img";
        String savebtn = "//div[@id='retailloanexceptionsdialog']//a[@data-selenium-id='saveButton']";
        String newexp = "//span[@style='background-image:url(resources/images/btn_exception.png);']";
        String loadingIndicator = "//div[text()='Loading...']";

//        elementFound(exceptionfound);
//        if (elemFound == false) {
//            Thread.sleep(1000);
            if (!isElementVisible(noExceptionsBadge)) {
                click(exceptionButton);
                page.click(exceptionbtn);
                // checkElementLoadMask(checkMaskLoad, "Loading...",logLabel);
                waitForLoadingMaskToDisappear(loadingIndicator);
                Thread.sleep(2000);
//            waitForElementWithLocator(checkexceptions, logLabel);
                page.locator(checkexceptions).waitFor(new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)   // or ATTACHED / HIDDEN / DETACHED
                        .setTimeout(5000)                         // optional timeout in milliseconds
                );


//            listWebElements(excpchkbox, logLabel);
//            if (listElems == null && listElems.isEmpty()) {
//                for (WebElement lem : listElems) {
//                    lem.click();
//
//                }
//
//            }
                // Wait for the exceptions modal to be visible before proceeding
                page.locator(exceptionsModalTitle).waitFor();

                // Find all unchecked exception checkboxes using a Playwright Locator
                Locator uncheckedItems = page.locator(uncheckedExceptions);
                int count = uncheckedItems.count();

                // Loop through and click each unchecked item
                for (int i = 0; i < count; i++) {
                    uncheckedItems.nth(i).click();
                }

                // After checking items, click the save button. Playwright will wait for it to be enabled.
                click(saveButton);
//            listWebElements(uncheckedexception, logLabel);
//            for (WebElement elem : listElems) {
//                System.out.println(elem);
//                elem.click();
//            }
//            elementFound(dissave);
//            if (elemFound == true) {
//                click(closeexception);
////                click(closeexception, metaDataKey);
//            } else {
////                click(savebtn, metaDataKey);
//                click(savebtn);
//                Thread.sleep(1000);
//            }
            }
            page.wait(7000);
//        page.frameLocator("#pollyIFrame").locator("input[name='conventional']").check();
            // Create a locator for the iframe
            FrameLocator frame = page.frameLocator("#pollyIFrame");

            // 1. Ensure 'Conventional' is checked
            frame.locator("input[name='conventional']").check();

            // 2. Check the '30 Yr' checkbox
            frame.getByLabel("30 Yr").check();

            // 3. Click the 'Get Pricing' button
            frame.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Get Pricing")).click();
            frame.getByTestId("product-header-C30-true").click();
            frame.locator("input[type='checkbox']").first().check();

            // 4. Find and click the "Request" button. Playwright will scroll to it automatically.
            frame.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Request")).click();

            page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Lock"));

            page.wait(10000);
            page.getByTestId("lock-request-request-button").click();

            assertThat(page.getByText("Operation completed SUCCESS")).isVisible();

            page.click("//span[text()='NEXT']");

            // Use this line after an action that triggers the loader
            //  Locator loadingIndicator = page.getByText("Loading...");

// This waits up to 30 seconds for the element to be hidden before continuing.
            //  assertThat(loadingIndicator).isHidden(new LocatorAssertions.IsHiddenOptions().setTimeout(30000));
            assertThat(page.locator(loadingIndicator)).isHidden();
        }

//    private void elementFound(String exceptionfound) {
//    }

}

