package com.pages;

import java.io.IOException;

public class DeclarationsPage extends BaseClass {
    public DeclarationsPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void DeclarationsInfoform2Borr() throws IOException {
        page.waitForTimeout(8000);
        //        PrimaryResidence	selectbuttonoptionbytext	//yes-no-bool[@name='a_occupyAsPrimary']
        page.click("(//ion-segment-button[text()='Yes'])[1]");
        //        DownBorrower	selectbuttonoptionbytext	//yes-no-bool[@name='c_isDownBorrow']
        page.click("(//ion-segment-button[text()='No'])[2]");
        page.click("(//ion-segment-button[text()='No'])[3]");
        page.click("(//ion-segment-button[text()='No'])[4]");
        page.click("(//ion-segment-button[text()='No'])[5]");
        page.click("(//ion-segment-button[text()='No'])[6]");
        page.click("(//ion-segment-button[text()='No'])[7]");
        page.click("(//ion-segment-button[text()='No'])[8]");
        page.click("(//ion-segment-button[text()='No'])[9]");
        page.click("(//ion-segment-button[text()='No'])[10]");
        for (int i = 0; i < 1; i++) {
            page.keyboard().press("PageDown");
//            page.waitForTimeout(1000); // Optional: Add a delay if needed
        }
        page.click("(//ion-segment-button[text()='No'])[11]");
        page.click("(//ion-segment-button[text()='No'])[12]");
        page.click("(//ion-segment-button[text()='No'])[13]");
        page.click("(//ion-segment-button[text()='No'])[14]");
        for (int i = 0; i < 2; i++) {
            page.keyboard().press("PageDown");
//            page.waitForTimeout(1000); // Optional: Add a delay if needed
        }
        page.click("(//ion-segment-button[text()='No'])[15]");
        page.click("(//ion-segment-button[text()='No'])[16]");
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        page.waitForTimeout(6000);

        //Second Borr Declarations
        page.click("(//ion-segment-button[text()='Yes'])[1]");
        //        DownBorrower	selectbuttonoptionbytext	//yes-no-bool[@name='c_isDownBorrow']
        page.click("(//ion-segment-button[text()='No'])[2]");
        page.click("(//ion-segment-button[text()='No'])[3]");
        page.click("(//ion-segment-button[text()='No'])[4]");
        page.click("(//ion-segment-button[text()='No'])[5]");
        page.click("(//ion-segment-button[text()='No'])[6]");
        page.click("(//ion-segment-button[text()='No'])[7]");
        page.click("(//ion-segment-button[text()='No'])[8]");
        page.click("(//ion-segment-button[text()='No'])[9]");
        page.click("(//ion-segment-button[text()='No'])[10]");
        for (int i = 0; i < 1; i++) {
            page.keyboard().press("PageDown");
//            page.waitForTimeout(1000); // Optional: Add a delay if needed
        }
        page.click("(//ion-segment-button[text()='No'])[11]");
        page.click("(//ion-segment-button[text()='No'])[12]");
        page.click("(//ion-segment-button[text()='No'])[13]");
        page.click("(//ion-segment-button[text()='No'])[14]");
        for (int i = 0; i < 2; i++) {
            page.keyboard().press("PageDown");
//            page.waitForTimeout(1000); // Optional: Add a delay if needed
        }
        page.click("(//ion-segment-button[text()='No'])[15]");
        page.waitForTimeout(3000);

        for (int i = 0; i < 1; i++) {
            page.keyboard().press("PageDown");
//            page.waitForTimeout(1000); // Optional: Add a delay if needed
        }

        page.click("(//ion-segment-button[text()='No'])[16]");
        page.locator("//ion-button[contains(@class,'save-continue')]").click();


        System.out.println("Declarations selection is done.");
    }

    public void DeclarationsInfoform1Borr() throws IOException {
        page.waitForTimeout(3000);
        //        PrimaryResidence	selectbuttonoptionbytext	//yes-no-bool[@name='a_occupyAsPrimary']
        page.click("(//ion-segment-button[text()='Yes'])[1]");
        //        DownBorrower	selectbuttonoptionbytext	//yes-no-bool[@name='c_isDownBorrow']
        page.click("(//ion-segment-button[text()='No'])[2]");
        page.click("(//ion-segment-button[text()='No'])[3]");
        page.click("(//ion-segment-button[text()='No'])[4]");
        page.click("(//ion-segment-button[text()='No'])[5]");
        page.click("(//ion-segment-button[text()='No'])[6]");
        page.click("(//ion-segment-button[text()='No'])[7]");
        page.click("(//ion-segment-button[text()='No'])[8]");
        page.click("(//ion-segment-button[text()='No'])[9]");
        page.click("(//ion-segment-button[text()='No'])[10]");
        page.click("(//ion-segment-button[text()='No'])[11]");
        page.click("(//ion-segment-button[text()='No'])[12]");
        page.click("(//ion-segment-button[text()='No'])[13]");
        page.click("(//ion-segment-button[text()='No'])[14]");
        page.click("(//ion-segment-button[text()='No'])[15]");

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
   //     page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Declarations selection is done.");
    }







}
