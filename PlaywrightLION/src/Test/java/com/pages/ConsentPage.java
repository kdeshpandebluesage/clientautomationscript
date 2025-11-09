package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.WaitUntilState;

import java.io.IOException;

public class ConsentPage extends BasePage {
    public ConsentPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void ConsentInfoform1Borr(String borrowerSSN) throws IOException {
        page.waitForTimeout(5000);
        System.out.println("Entered Consent Page ");
        page.click("//yes-no-bool[@name='borrowerSMSConsent']//ion-segment-button[1]");
//        Locator consent = page.locator("//div[text()='Consent']");
//        consent.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        //  page.pause();
        //   page.reload();
        //   page.reload(new Page.ReloadOptions().setWaitUntil(WaitUntilState.LOAD));
        // page.click("//yes-no-bool[@name='borrowerSMSConsent']//ion-segment-button[1]");
        System.out.println("Yes clicked");
     /*   page.locator("//yes-no-bool[@name='borrowerSMSConsent']//ion-segment-button[1]").click(new Locator.ClickOptions().setForce(true));
//        page.waitForSelector(".loader", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));

        Locator element1 = page.locator("//yes-no-bool[@name='borrowerSMSConsent']//ion-segment-button[1]");
        element1.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
       // page.click("//yes-no-bool[@name='borrowerSMSConsent']//ion-segment-button[1]");
        element1.scrollIntoViewIfNeeded();
        element1.click();
        page.waitForTimeout(5000);*/
        // page.hover("(//ion-button[text()=' View Agreement '])");
        Locator element2 = page.locator("(//ion-button[text()=' View Agreement '])[1]");
        element2.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        element2.scrollIntoViewIfNeeded();
        element2.click();
        // page.click("(//ion-button[text()=' View Agreement '])[1]");
        page.hover("//bl-consent-agreement[@class='ion-page']");
        page.click("//bl-consent-agreement[@class='ion-page']");
        page.waitForTimeout(5000);
        int numberOfTimes = 8; // Set the number of times you want to press PageDown
        for (int i = 0; i < numberOfTimes; i++) {
            page.waitForTimeout(500);
            page.keyboard().press("PageDown");
            page.waitForTimeout(500); // Optional: Add a delay if needed
        }
        System.out.println("Scrolling completed");
//        String locator="//div[@class='consent-agreement-option']//ion-segment-button[1]";
//        locator.wait(new locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator element3 = page.locator("//div[@class='consent-agreement-option']//ion-segment-button[1]");
        element3.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        element3.scrollIntoViewIfNeeded();
        element3.click();
        System.out.println("Yes Clicked");
//        page.keyboard().press("PageDown");
//        page.hover("//div[@class='consent-agreement-option']//ion-segment-button[1]");
//        page.click("//div[@class='consent-agreement-option']//ion-segment-button[1]");

        page.keyboard().press("PageDown");
        // borrowerInsQuoteConsent
        page.click("//yes-no-bool[@name='borrowerInsQuoteConsent']//ion-segment-button[1]");

        //credit consent
        page.click("//yes-no-bool[@name='borrowerCreditConsent']//ion-segment-button[1]");
        System.out.println("BR consent");
        page.locator("//ion-input[@name='borrowerSSN']").click();
        page.fill("input[name='borrowerSSN']", borrowerSSN);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Consent selection is done.");

    }

    public void ConsentInfoform2Borr(String borrowerSSN, String coborrowerSSN ) throws IOException {
        page.waitForTimeout(5000);
        page.click("//yes-no-bool[@name='borrowerSMSConsent']//ion-segment-button[1]");
        page.click("//yes-no-bool[@name='coborrowerSMSConsent']//ion-segment-button[1]");
        // page.click("//ion-button[@name='borrowerEdocsConsent']");
        //  page.click("//div//div[@class='info']");
        page.click("(//ion-button[text()=' View Agreement '])[1]");
        page.hover("//bl-consent-agreement[@class='ion-page']");
        page.click("//bl-consent-agreement[@class='ion-page']");
        //bl-consent-agreement[@class='ion-page']
        page.waitForTimeout(5000);
        int numberOfTimes = 8; // Set the number of times you want to press PageDown
        for (int i = 0; i < numberOfTimes; i++) {
            page.waitForTimeout(500);
            page.keyboard().press("PageDown");
            page.waitForTimeout(500); // Optional: Add a delay if needed
        }
        System.out.println("Scrolling completed");

        Locator element = page.locator("//div[@class='consent-agreement-option']//ion-segment-button[1]");
        element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        element.scrollIntoViewIfNeeded();
        element.click();
        System.out.println("Yes Clicked");

//        page.hover("//div[@class='consent-agreement-option']//ion-segment-button[1]");
//        page.waitForTimeout(1000);
//        page.click("//div[@class='consent-agreement-option']//ion-segment-button[1]");
        System.out.println("First BR consent");


        page.click("(//ion-button[text()=' View Agreement '])[2]");
        page.hover("//bl-consent-agreement[@class='ion-page']");
        page.click("//bl-consent-agreement[@class='ion-page']");
        page.waitForTimeout(3000);
//        page.click("//ion-button[@name='coborrowerEdocsConsent']");
//        page.click("//div//div[@class='info']");
        int numberOfTimesBr = 8;
        for (int i = 0; i < numberOfTimes; i++) {
            page.waitForTimeout(500);
            page.keyboard().press("PageDown");
            page.waitForTimeout(500); // Optional: Add a delay if needed
        }
        page.waitForTimeout(1000);
        page.hover("//div[@class='consent-agreement-option']//ion-segment-button[1]");
        page.waitForTimeout(1000);
        page.click("//div[@class='consent-agreement-option']//ion-segment-button[1]");

        page.keyboard().press("PageDown");
        page.click("//yes-no-bool[@name='borrowerInsQuoteConsent']//ion-segment-button[2]");

        page.click("(//yes-no-bool[@name='borrowerCreditConsent']//ion-segment-button)[1]");
        page.click("(//yes-no-bool[@name='coborrowerCreditConsent']//ion-segment-button)[1]");
        //       page.click("(//yes-no-bool[@name='pullJoint']//ion-segment-button)[1]");

        page.locator("//ion-input[@name='borrowerSSN']").click();
        page.fill("input[name='borrowerSSN']", borrowerSSN);
        page.locator("//ion-input[@name='coborrowerSSN']").click();
        page.fill("input[name='coborrowerSSN']", coborrowerSSN);


        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Consent selection is done.");

    }


}

