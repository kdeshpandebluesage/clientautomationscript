package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPLoanSearchPage extends BasePage {
    public LOPLoanSearchPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void LopLoanSearch(String loanNumber) throws InterruptedException {

        page.click("//div[text()='Loans']");
        page.click("input[placeholder='Last Name or Loan Number']");
        page.fill("input[placeholder='Last Name or Loan Number']", loanNumber);


//        enterText(page ,"//input[placeholder='Last Name or Loan Number']",loanNumber);
        page.click("//span[text()='Search']");
//        page.locator("(//tr[contains(@class, 'x-grid-data-row')])[1]//td[contains(@class, 'loanList_LenderLoanNumber')]/div").waitFor(new Locator.WaitForOptions()
//                .setState(WaitForSelectorState.VISIBLE));


        page.waitForSelector("//div[@data-selenium-id='searchResultsGrid']//td[contains(@class, 'loanList_LenderLoanNumber')]/div", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("//div[@data-selenium-id='searchResultsGrid']//td[contains(@class, 'loanList_LenderLoanNumber')]/div");
        System.out.println("Loan Search is done .");
    }
}
