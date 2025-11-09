//package com.pages;
//
//import com.microsoft.playwright.Locator;
//import com.microsoft.playwright.options.WaitForSelectorState;
//
//import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
//
//public class LOPLoanSearchPage extends BaseClass {
//    public LOPLoanSearchPage(com.microsoft.playwright.Page page) {
//        super(page);
//    }
//

//    public void LopLoanSearch(String loanNumber) throws InterruptedException {
//
//        page.click("//div[text()='Loans']");
//        page.click("input[placeholder='Last Name or Loan Number']");
//        page.fill("input[placeholder='Last Name or Loan Number']", loanNumber);
//
////        enterText(page ,"//input[placeholder='Last Name or Loan Number']",loanNumber);
//        page.click("//span[text()='Search']");
//        page.waitForTimeout(6000);
//        page.locator("(//tr[contains(@class, 'x-grid-data-row')])[1]//td[contains(@class,'loanList_LenderLoanNumber')]/div").waitFor(new Locator.WaitForOptions()
//                .setState(WaitForSelectorState.VISIBLE));
//        page.click("(//tr[contains(@class, 'x-grid-data-row')])[1]//td[contains(@class,'loanList_LenderLoanNumber')]/div");
//        Locator loanNumberLOP = page.locator ("(//tr[contains(@class, 'x-grid-data-row')])[1]//td[contains(@class,'loanList_LenderLoanNumber')]/div");
////        assertThat(loanNumberLOP).isEnabled();
////        System.out.println("Locator Enabled");
//      //  loanNumberLOP.hover();
//      //  loanNumberLOP.click();
//        System.out.println("Loan Search is done .");
//    }
//}
//public void LopLoanSearch(String loanNumber) {
//    System.out.println("Searching for loan number: " + loanNumber);
//
//    // Step 1: Navigate and perform the search as before.
//    page.click("//div[text()='Loans']");
//    page.locator("input[placeholder='Last Name or Loan Number']").fill(loanNumber);
//    page.click("//span[text()='Search']");
//    page.waitForTimeout(6000);
//    // Step 2: IMPORTANT - Wait for the loading mask/spinner to disappear.
//    // This is the correct way to wait for the grid to be ready.
//    // Note: The locator ".x-mask-loading" is a common example; replace it
//    // with the actual locator for your application's loading overlay.
//    page.locator(".x-mask-loading").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
//
//    // Step 3: Create a PRECISE locator that finds the cell with your specific loan number.
//    // This is much better than just getting the first row.
//    Locator loanNumberCell = page.locator("//div[@class='x-grid-cell-inner' and text()='" + loanNumber + "']");
//
//    // Step 4: Wait for THAT specific cell to be visible, then click it.
//    loanNumberCell.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//    loanNumberCell.click(); // Or .dblclick() if the app requires a double click to open the loan.
//
//    System.out.println("Successfully found and clicked loan: " + loanNumber);
//}
//}

package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPLoanSearchPage extends BaseClass {
    public LOPLoanSearchPage(Page page) {
        super(page);
    }

    public void LopLoanSearch(String loanNumber) {
        System.out.println("Searching for loan number: " + loanNumber);

        // Step 1: Perform the search
        page.click("//div[text()='Loans']");
        page.locator("input[placeholder='Last Name or Loan Number']").fill(loanNumber);
        page.click("//span[text()='Search']");
        page.waitForTimeout(8000);
        // Step 2: Wait for the loading mask to disappear
        page.locator(".x-mask-loading").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));

        // Step 3: Create the PRECISE locator using '.' to read nested text
        Locator loanNumberCell = page.locator("//div[contains(@class, 'x-grid-cell-inner') and .='" + loanNumber + "']");

        // Step 4: Wait for the cell to be visible and then click it
        loanNumberCell.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        loanNumberCell.click();

        System.out.println("Successfully found and clicked loan: " + loanNumber);
    }
}