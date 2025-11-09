package com.pages;

import java.io.IOException;

public class ApplicationInfoSUPPage extends BaseClass {
    public ApplicationInfoSUPPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void applicationInformation(String ApplicationInfo2BrPrimaryApplicantFirstName,String ApplicationInfo2BrPrimaryApplicantMiddleName,String ApplicationInfo2BrPrimaryApplicantLastName
            ,String ApplicationInfo2BrPrimaryApplicantBOD,String ApplicationInfo2BrPrimaryApplicantMobNumber,String ApplicationInfo2BrPrimaryApplicantEmail, String ApplicationInfo2BrPrimaryApplicantAddressline ,String ApplicationInfo2BrPrimaryApplicantCityName ,String ApplicationInfo2BrPrimaryApplicantStateName,String ApplicationInfo2BrPrimaryApplicantzipcode, String ApplicationInfo2BrPrimaryApplicantRent ,String ApplicationInfo2BrPrimaryApplicantMovedMonthYear,String ApplicationInfo2BrPrimaryMaritalStatus) throws IOException {
        page.waitForTimeout(5000);
        page.click("(//ion-segment-button[text()='Yes'])[1]");
//
        page.click("(//ion-segment-button[text()='Yes'])[1]");
        clearAndEnterText(page,"input[name='firstName']",ApplicationInfo2BrPrimaryApplicantFirstName);
        clearAndEnterText(page,"input[name='middleInitial']",ApplicationInfo2BrPrimaryApplicantMiddleName);
        clearAndEnterText(page,"input[name='lastName']",ApplicationInfo2BrPrimaryApplicantLastName
        );

        enterText(page,"input[name='birthDate']",ApplicationInfo2BrPrimaryApplicantBOD);
//        page.fill("input[name='birthDate']", AppFirstBRBOD);
        page.click("(//ion-segment-button[text()='No'])[2]");
        page.click("(//ion-segment-button[text()='No'])[3]");

        click(page,"ion-segment-button[value='M']");

       /* //marital status
        switch (ApplicationInfo2BrPrimaryMaritalStatus.toUpperCase()) {
            case "M":
                click(page,"ion-segment-button[value='M']");
                break;
            case "U":
                click(page,"ion-segment-button[value='U']");
                page.click("(//ion-segment-button[text()='No'])[4]")
                break;
            case "S":
                click(page,"ion-segment-button[value='S']");
                break;
        }
        */

        page.locator("//ion-segment-button[text()=' US Citizen ']").click();

        page.locator("//ion-segment[@name='militaryDutyType']//ion-segment-button[@value='NONE']").click();

        page.locator("//ion-input[@name='mobileNumber']").click();
        page.fill("input[name='mobileNumber']", ApplicationInfo2BrPrimaryApplicantMobNumber);

//        page.locator("//input[@name='email']").clear();
//        page.fill("input[name='email']", ApplicationInfo2BrPrimaryApplicantEmail);
        pageDown();
        click(page,"//ion-segment-button[@value='WORK']");
        click(page,"//ion-segment-button[@value='TELEPHONE']");
        click(page,"//ion-segment-button[@value='RENT']");
        pageDown();
//        page.fill("input[name='rent']",ApplicationInfo2BrPrimaryApplicantRent);

        clearAndEnterText(page,"input[name='rent']",ApplicationInfo2BrPrimaryApplicantRent);

//State selection //
        page.locator("input[name='formattedAddress']").click();
        clearAndEnterText(page,"input[name='addressLine']", ApplicationInfo2BrPrimaryApplicantAddressline);
//        clearAndEnterText(page,"input[name='addressLine']", ApplicationInfo2BrPrimaryApplicantAddressline);
        clearAndEnterText(page,"input[name='city']", ApplicationInfo2BrPrimaryApplicantCityName);
        String statedropdownToggleLocator = "//ion-select[@name='state']";
        String hoverTargetSelector = "//div[text()='NJ - New Jersey ']";
        page.click(statedropdownToggleLocator);
        selectAngularDropdownOption(page, statedropdownToggleLocator, hoverTargetSelector);
        System.out.println("click dropdown");
//        clearAndEnterText(page,"input[name='addressLine']", ApplicationInfo2BrPrimaryApplicantAddressline);
////        clearAndEnterText(page,"input[name='addressLine']", ApplicationInfo2BrPrimaryApplicantAddressline);
//        click(page,"//ion-button[contains(@class,'autocomplete-okay')]");
        clearAndEnterText(page,"input[name='zip']", ApplicationInfo2BrPrimaryApplicantzipcode);
        click(page,"//ion-button[contains(@class,'autocomplete-okay')]");
        page.click("(//ion-segment-button[text()='Yes'])[4]");

//        enterText(page,"input[name='movedMonthYear']", "05/2020");
        clearAndEnterText(page,"input[name='movedMonthYear']", "05/2020");
        System.out.println("Application Info screen is done.");
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        System.out.println("Appl First Borr selection is done.");
    }
    public void OneBorrApplicationform(String ApplicationInfo1BrPrimaryApplicantFirstName,String ApplicationInfo1BrPrimaryApplicantLastName,String ApplicationInfo1BrPrimaryApplicantBOD,String  ApplicationInfo1BrPrimaryApplicantMobNumber,String ApplicationInfo1BrPrimaryApplicantEmail,
                                        String ApplicationInfo1BrPrimaryApplicantAddressline,String ApplicationInfo1BrPrimaryApplicantCityName,String ApplicationInfo1BrPrimaryApplicantStateName,String ApplicationInfo1BrPrimaryApplicantzipcode, String ApplicationInfo1BrPrimaryApplicantRent,String  ApplicationInfo1BrPrimaryApplicantMovedMonthYear) throws IOException {
        page.waitForTimeout(300);

        // page.click("(//ion-segment-button[text()='Yes'])[1]");
        page.click("(//ion-segment-button[text()='No'])[1]");
        //firstname
        page.locator("//input[@name='firstName']").clear();
        page.fill("input[name='firstName']", ApplicationInfo1BrPrimaryApplicantFirstName);
        //lastname
        page.locator("//input[@name='lastName']").clear();
        page.fill("input[name='lastName']", ApplicationInfo1BrPrimaryApplicantLastName);
        //birthdate
        page.fill("input[name='birthDate']", ApplicationInfo1BrPrimaryApplicantBOD);
        //marital status
        page.locator("//ion-segment[@name='maritalStatusId']//ion-segment-button[@value='U']").click();

        //citizenship status
        //  page.locator("//ion-segment[@name='citizenStatus']//ion-segment-button[@value=]").click();
        page.locator("//yes-no-bool[@name='unmarriedSelectedYn']//ion-segment//ion-segment-button[2]").click();
        page.locator("ion-segment-button:has-text(' US Citizen ')").click();

        page.click("(//ion-segment-button[text()='No'])[4]");
        pageDown();

        page.locator("//ion-input[@name='mobileNumber']").click();
        page.fill("input[name='mobileNumber']", ApplicationInfo1BrPrimaryApplicantMobNumber);

        page.locator("//input[@name='email']").clear();
        page.fill("input[name='email']", ApplicationInfo1BrPrimaryApplicantEmail);

        // page.locator("ion-segment-button[value='US Citizen']").click();
        // armed forces


        page.locator("//ion-segment[@name='emailType']//ion-segment-button[@value='WORK']").click();
        // page.locator("ion-segment-button[value='WORK']").click();
        //contact
        page.locator("//ion-segment[@name='contactMethodType']//ion-segment-button[@value='TELEPHONE']").click();
        // page.locator("ion-segment-button[value='TELEPHONE']").click();
        //occupancy type
        click(page,"//ion-segment-button[@value='RENT']");
        pageDown();
        page.fill("input[name='rent']",ApplicationInfo1BrPrimaryApplicantRent);
        clearAndEnterText(page,"input[name='rent']",ApplicationInfo1BrPrimaryApplicantRent);

        page.locator("input[name='formattedAddress']").click();
        clearAndEnterText(page,"input[name='addressLine']", ApplicationInfo1BrPrimaryApplicantAddressline);
//        clearAndEnterText(page,"input[name='addressLine']", ApplicationInfo2BrPrimaryApplicantAddressline);
        clearAndEnterText(page,"input[name='city']", ApplicationInfo1BrPrimaryApplicantCityName);
        String statedropdownToggleLocator = "//ion-select[@name='state']";
        String hoverTargetSelector = "//div[text()='NJ - New Jersey ']";
        page.click(statedropdownToggleLocator);
        selectAngularDropdownOption(page, statedropdownToggleLocator, hoverTargetSelector);
        System.out.println("click dropdown");

        page.click("(//ion-segment-button[text()='Yes'])[4]");
        //moved in month and year
        page.fill("input[name='movedMonthYear']", ApplicationInfo1BrPrimaryApplicantMovedMonthYear);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        // page.click("(//ion-segment-button[text()=' Continue to Co-Applicant '])");
        System.out.println("One Borrower Info is done.");



    }
    public void OneBorrRefiApplicationform(String AppFirstBrName,String AppLastBrName,String AppFirstBRBOD,String Remail,
                                       String RPhonenumber, String  movedMonthYear,String maritalStatus) throws IOException {
        page.waitForTimeout(300);

        // page.click("(//ion-segment-button[text()='Yes'])[1]");
        page.click("(//ion-segment-button[text()='No'])[1]");
        //firstname
        page.locator("//input[@name='firstName']").clear();
        page.fill("input[name='firstName']", AppFirstBrName);
        //lastname
        page.locator("//input[@name='lastName']").clear();
        page.fill("input[name='lastName']", AppLastBrName);
        //birthdate
        page.fill("input[name='birthDate']", AppFirstBRBOD);

//        page.locator("//ion-segment[@name='maritalStatusId']//ion-segment-button[@value='U']").click();
        // page.locator("ion-segment-button[value='Unmarried']").click();

        //citizenship status

        page.locator("//yes-no-bool[@name='unmarriedSelectedYn']//ion-segment//ion-segment-button[2]").click();
        page.locator("ion-segment-button:has-text(' US Citizen ')").click();
        // page.locator("ion-segment-button[value='US Citizen']").click();
        // armed forces


        page.locator("//ion-segment[@name='emailType']//ion-segment-button[@value='WORK']").click();
        // page.locator("ion-segment-button[value='WORK']").click();
        //contact
        page.locator("//ion-segment[@name='contactMethodType']//ion-segment-button[@value='TELEPHONE']").click();
        // page.locator("ion-segment-button[value='TELEPHONE']").click();
        //occupancy type
        page.locator("//ion-segment[@name='ownRentOrFree']//ion-segment-button[@value='OWN']").click();

        //  page.click("(//ion-segment-button[text()='Yes'])[3]");
        // page.locator("ion-segment-button:has-text('Yes')").click();
        //moved in month and year
        page.fill("input[name='movedMonthYear']", movedMonthYear);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        // page.click("(//ion-segment-button[text()=' Continue to Co-Applicant '])");
        System.out.println("One Borrower Info is done.");



    }
}
