package com.pages;

import java.io.IOException;

public class OtherInfoPage extends BasePage_Old {
    public OtherInfoPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void OtherInfoform(String Rcompany,String Rfirstname,String RLastname,String Remail,String RPhonenumber) throws IOException {
        page.waitForTimeout(3000);
        page.locator("ion-segment-button:has-text('Yes')").click();
        page.fill("input[name='fullName']", Rcompany);
        page.fill("input[name='firstName']", Rfirstname);
        page.fill("input[name='lastName']", RLastname);
        page.fill("input[name='emailAddress']", Remail);
        page.fill("input[name='phoneNumber']", RPhonenumber);
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Other Info selection is done.");
    }
}
