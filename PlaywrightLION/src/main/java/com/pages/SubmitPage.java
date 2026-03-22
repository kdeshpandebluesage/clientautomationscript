package com.pages;

import java.io.IOException;

public class SubmitPage extends BasePage {
    public SubmitPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void SubmitInfoform() throws IOException {

        page.click("//ion-button[@id='fullSubmitNow']");
         page.waitForTimeout(3000);
        System.out.println("Loan Application is Submitted.");

    }


}
