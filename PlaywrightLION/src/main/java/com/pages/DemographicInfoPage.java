package com.pages;

import java.io.IOException;

public class DemographicInfoPage extends BasePage {
    public DemographicInfoPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void DemographicInfoform1Borr() throws IOException {
        page.waitForTimeout(3000);

        page.click("//yes-no[@name='isNotHispanic']//ion-toggle");
        page.click("//yes-no[@name='isMale']//ion-toggle");
        page.click("//yes-no[@name='isWhite']//ion-toggle");
        page.click("//yes-no[@name='isEnglishLang']//ion-toggle");
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        System.out.println("Demographic selection is done.");
    }
    public void DemographicInfoform2Borr() throws IOException {
        page.waitForTimeout(3000);

        page.click("//yes-no[@name='isNotHispanic']//ion-toggle");
        page.click("//yes-no[@name='isMale']//ion-toggle");
        page.click("//yes-no[@name='isWhite']//ion-toggle");
        page.click("//yes-no[@name='isEnglishLang']//ion-toggle");
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        page.waitForTimeout(3000);

        page.click("//yes-no[@name='isNotHispanic']//ion-toggle");
        page.click("//yes-no[@name='isFemale']//ion-toggle");
        page.click("//yes-no[@name='isWhite']//ion-toggle");
        page.click("//yes-no[@name='isEnglishLang']//ion-toggle");
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        System.out.println("Demographic selection is done.");
    }
}
