package com.pages;

import java.io.IOException;

public class ApplicationInfoSecondaryPage extends BasePage {
    public ApplicationInfoSecondaryPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void applicationSecondaryInformation(String ApplicationInfo2BrSecondaryApplicantFirstName,String ApplicationInfo2BrSecondaryApplicantLastName,String ApplicationInfo2BrSecondaryApplicantBOD,String ApplicationInfo2BrSecondaryApplicantMobNumber, String ApplicationInfo2BrSecondaryApplicantEmail,String ApplicationInfo2BrSecondaryApplicantAddressline ,String ApplicationInfo2BrSecondaryApplicantCityName ,String ApplicationInfo2BrSecondaryApplicantStateName,String ApplicationInfo2BrSecondaryApplicantzipcode, String ApplicationInfo2BrSecondaryApplicantRent ) throws IOException {
        page.waitForTimeout(3000);
        page.click("(//ion-segment-button[text()='Yes'])[1]");
//        page.locator("ion-segment-button:has-text('Yes')").click();
        page.click("(//ion-segment-button[text()='Yes'])[1]");
        clearAndEnterText(page,"input[name='firstName']",ApplicationInfo2BrSecondaryApplicantFirstName);
        clearAndEnterText(page,"input[name='lastName']",ApplicationInfo2BrSecondaryApplicantLastName);

//        page.fill("input[name='lastName']", AppLastBrName);
//        page.fill("input[name='lastName']", AppLastBrName);
        clearAndEnterText(page,"input[name='birthDate']",ApplicationInfo2BrSecondaryApplicantBOD);
//        page.fill("input[name='birthDate']", AppFirstBRBOD);

//        click(page,"ion-segment-button[value='M']");
//        page.locator("ion-segment-button[value='M']").click();
        page.locator("//ion-segment-button[text()=' US Citizen ']").click();
        page.keyboard().press("PageDown");
        page.click("(//ion-segment-button[text()='No'])[4]");

        page.locator("//ion-segment[@name='militaryDutyType']//ion-segment-button[@value='NONE']").click();

        page.locator("//ion-input[@name='mobileNumber']").click();
        page.fill("input[name='mobileNumber']", ApplicationInfo2BrSecondaryApplicantMobNumber);

        page.locator("//input[@name='email']").clear();
        page.fill("input[name='email']", ApplicationInfo2BrSecondaryApplicantEmail);

        click(page,"//ion-segment-button[@value='WORK']");
        click(page,"//ion-segment-button[@value='TELEPHONE']");
        click(page,"//ion-segment-button[@value='RENT']");
        pageDown();

        click(page,"input[name='rent']");
        page.fill("input[name='rent']",ApplicationInfo2BrSecondaryApplicantRent);
//State selection //
        page.locator("input[name='formattedAddress']").click();
        enterText(page,"input[name='addressLine']", ApplicationInfo2BrSecondaryApplicantAddressline);
        enterText(page,"input[name='city']", ApplicationInfo2BrSecondaryApplicantCityName);
        String statedropdownToggleLocator = "//ion-select[@name='state']";
        String hoverTargetSelector = "//div[text()='NJ - New Jersey ']";
        page.click(statedropdownToggleLocator);
        selectAngularDropdownOption(page, statedropdownToggleLocator, hoverTargetSelector);
        System.out.println("click dropdown");
        enterText(page,"input[name='zip']", ApplicationInfo2BrSecondaryApplicantzipcode);
        click(page,"//ion-button[contains(@class,'autocomplete-okay')]");
        page.click("(//ion-segment-button[text()='Yes'])[4]");
        enterText(page,"input[name='movedMonthYear']", "05/2020");
        System.out.println("Application Info screen is done.");
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        System.out.println("Other Info selection is done.");
    }
}
