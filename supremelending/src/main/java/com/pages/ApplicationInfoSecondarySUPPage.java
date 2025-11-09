package com.pages;

import com.microsoft.playwright.Page;

import java.io.IOException;

public class ApplicationInfoSecondarySUPPage extends BaseClass {
//    public ApplicationInfoSecondarySUPPage(com.microsoft.playwright.Page page) {
//        super(page);
//    }
public ApplicationInfoSecondarySUPPage(Page page) {
    super(page);
}

    public void applicationSecondaryInformation(String ApplicationInfo2BrSecondaryApplicantFirstName,String ApplicationInfo2BrSecondaryApplicantLastName,String ApplicationInfo2BrSecondaryApplicantBOD,String ApplicationInfo2BrSecondaryApplicantMobNumber, String ApplicationInfo2BrSecondaryApplicantEmail,String ApplicationInfo2BrSecondaryApplicantAddressline ,String ApplicationInfo2BrSecondaryApplicantCityName ,String ApplicationInfo2BrSecondaryApplicantStateName,String ApplicationInfo2BrSecondaryApplicantzipcode, String ApplicationInfo2BrSecondaryApplicantRent ) throws IOException {
        page.waitForTimeout(3000);
        page.click("(//ion-segment-button[text()='Yes'])[1]");
//        page.locator("ion-segment-button:has-text('Yes')").click();
        page.click("(//ion-segment-button[text()='Yes'])[1]");
//        clearAndclearAndFillText("input[name='firstName']",ApplicationInfo2BrSecondaryApplicantFirstName);
//        clearAndclearAndFillText("input[name='lastName']",ApplicationInfo2BrSecondaryApplicantLastName);
        // 3. FIX: Renamed 'clearAndEnterText' to 'clearAndFillText' to match the method in BaseClass
        clearAndFillText("input[name='firstName']", ApplicationInfo2BrSecondaryApplicantFirstName);
        clearAndFillText("input[name='lastName']", ApplicationInfo2BrSecondaryApplicantLastName);


//        page.fill("input[name='lastName']", AppLastBrName);
//        page.fill("input[name='lastName']", AppLastBrName);
       // clearAndclearAndFillText("input[name='birthDate']",ApplicationInfo2BrSecondaryApplicantBOD);
        // Assuming you want to fill the birth date as well
        fillText("input[name='birthDate']", ApplicationInfo2BrSecondaryApplicantBOD);
//        page.fill("input[name='birthDate']", AppFirstBRBOD);

//        click("ion-segment-button[value='M']");
//        page.locator("ion-segment-button[value='M']").click();
        page.locator("//ion-segment-button[text()=' US Citizen ']").click();
        page.keyboard().press("PageDown");
        page.click("(//ion-segment-button[text()='No'])[4]");

        page.locator("//ion-segment[@name='militaryDutyType']//ion-segment-button[@value='NONE']").click();

        page.locator("//ion-input[@name='mobileNumber']").click();
        page.fill("input[name='mobileNumber']", ApplicationInfo2BrSecondaryApplicantMobNumber);

        pageDown();
//        page.locator("//input[@name='email']").clear();
//        page.fill("input[name='email']", ApplicationInfo2BrSecondaryApplicantEmail);

//        click("//ion-segment-button[@value='WORK']");
//        click("//ion-segment-button[@value='TELEPHONE']");
//        click("//ion-segment-button[@value='RENT']");
        // FIX: Removed the unnecessary 'page' parameter from the method calls below.
        click("//ion-segment-button[@value='WORK']");
        click("//ion-segment-button[@value='TELEPHONE']");
        click("//ion-segment-button[@value='RENT']");
        pageDown();

        //click("input[name='rent']");

        click("input[name='rent']");
      //  page.fill("input[name='rent']",ApplicationInfo2BrSecondaryApplicantRent);
        fillText("input[name='rent']",ApplicationInfo2BrSecondaryApplicantRent);

        //State selection //
        page.locator("input[name='formattedAddress']").click();
//        clearAndFillText("input[name='addressLine']", ApplicationInfo2BrSecondaryApplicantAddressline);
//        clearAndFillText("input[name='city']", ApplicationInfo2BrSecondaryApplicantCityName);
        enterText("input[name='addressLine']", ApplicationInfo2BrSecondaryApplicantAddressline);
        enterText("input[name='city']", ApplicationInfo2BrSecondaryApplicantCityName);
        String statedropdownToggleLocator = "//ion-select[@name='state']";
        String hoverTargetSelector = "//div[text()='NJ - New Jersey ']";
        page.click(statedropdownToggleLocator);
        selectAngularDropdownOption(page, statedropdownToggleLocator, hoverTargetSelector);
        System.out.println("click dropdown");
       // clearAndFillText("input[name='zip']", ApplicationInfo2BrSecondaryApplicantzipcode);
        enterText("input[name='zip']", ApplicationInfo2BrSecondaryApplicantzipcode);
//        click("//ion-button[contains(@class,'autocomplete-okay')]");
//        page.click("(//ion-segment-button[text()='Yes'])[4]");
        click("//ion-button[contains(@class,'autocomplete-okay')]");
        click("(//ion-segment-button[text()='Yes'])[4]");
       // clearAndFillText("input[name='movedMonthYear']", "05/2020");
        enterText("input[name='movedMonthYear']", "05/2020");
        System.out.println("Application Info screen is done.");
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        System.out.println("Other Info selection is done.");
    }
}
