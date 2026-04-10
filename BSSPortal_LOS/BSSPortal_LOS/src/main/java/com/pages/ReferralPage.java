package com.pages;

import java.io.IOException;

public class ReferralPage extends BaseClass {
    public ReferralPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void OtherInfoform(String AppFirstBrName,String AppLastBrName,String AppFirstBRBOD,String Remail,String RPhonenumber) throws IOException {
        page.waitForTimeout(3000);
        page.locator("ion-segment-button:has-text('Yes')").click();
        page.fill("input[name='firstName']", AppFirstBrName);
        page.fill("input[name='lastName']", AppLastBrName);
        page.fill("input[name='birthDate']", AppFirstBRBOD);
        page.locator("ion-segment-button[value='Married']").click();
        page.locator("ion-segment-button[value='US Citizen']").click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Other Info selection is done.");
    }
}
