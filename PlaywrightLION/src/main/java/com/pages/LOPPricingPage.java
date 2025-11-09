package com.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.openqa.selenium.WebElement;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LOPPricingPage extends BasePage {
    public LOPPricingPage(com.microsoft.playwright.Page page) {
        super(page);
    }
    public void lopPricing(String logLabel) throws InterruptedException {

        String exceptionfound = "//a[@data-selenium-id='exceptions']/div[contains(@class,'badgeText')]";
        String exceptionbtn = "//span[@id='btnExceptions-btnIconEl']";
        String checkexceptions = "//span[text()='Exceptions for Loan']";
        String excpchkbox = "//div[@data-selenium-id='exceptionGrid']//tr//td[8]//div[contains(@class,'checkcolumncustom')]";
        String clickexcption = "//div[contains(@id,'loanexceptionsdialog')]//img";
        String uncheckedexception = "//td[contains(@class,'x-grid-td x-grid-cell-headerId-checkcolumn')]/div/div[not(contains(@class,'x-grid-checkcolumncustom x-grid-checkcolumncustom-checked'))]";
        String dissave = "(//div[@id='retailloanexceptionsdialog']//a[@data-selenium-id='saveButton'][contains(@class,'x-btn-disabled')])";
        String closeexception = "//div[contains(@id,'loanexceptionsdialog')]//img";
        String savebtn = "//div[@id='retailloanexceptionsdialog']//a[@data-selenium-id='saveButton']";
        String newexp = "//span[@style='background-image:url(resources/images/btn_exception.png);']";

        elementFound(exceptionfound);
        if (elemFound == false) {
            Thread.sleep(1000);
         page.click(exceptionbtn);
            checkElementLoadMask(checkMaskLoad, "Loading...",logLabel);
            Thread.sleep(2000);
//            waitForElementWithLocator(checkexceptions, logLabel);
            page.locator(checkexceptions).waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)   // or ATTACHED / HIDDEN / DETACHED
                    .setTimeout(5000)                         // optional timeout in milliseconds
            );



            listWebElements(excpchkbox, logLabel);
            if (listElems == null && listElems.isEmpty()) {
                for (WebElement lem : listElems) {
                    lem.click();

                }

            }
            listWebElements(uncheckedexception, logLabel);
            for (WebElement elem : listElems) {
                System.out.println(elem);
                elem.click();
            }
            elementFound(dissave);
            if (elemFound == true) {
                click(page,closeexception);
//                click(closeexception, metaDataKey);
            } else {
//                click(savebtn, metaDataKey);
                click(page,savebtn);
                Thread.sleep(1000);
            }
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
        assertThat(page.getByText("Operation completed SUCCESS")).isVisible();

        page.click("//span[text()='NEXT']");

        // Use this line after an action that triggers the loader
        Locator loadingIndicator = page.getByText("Loading...");

// This waits up to 30 seconds for the element to be hidden before continuing.
        assertThat(loadingIndicator).isHidden(new LocatorAssertions.IsHiddenOptions().setTimeout(30000));



    }

}
