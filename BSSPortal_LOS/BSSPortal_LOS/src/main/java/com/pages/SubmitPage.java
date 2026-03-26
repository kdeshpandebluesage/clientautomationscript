package com.pages;

import java.io.IOException;

public class SubmitPage extends BaseClass {
    public SubmitPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void SubmitInfoform() throws IOException {

        page.click("//ion-button[@id='fullSubmitNow']");
         page.waitForTimeout(8000);
        System.out.println("Loan Application is Submitted.");
    }
}
