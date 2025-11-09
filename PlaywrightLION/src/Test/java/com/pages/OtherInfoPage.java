package com.pages;

import java.io.IOException;

public class OtherInfoPage extends BasePage {
    public OtherInfoPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void OtherInfoform(String Realtorcompany,String RealtorFirstName,String RealtorLastName,String RealtorEmail,String RealtorPhonenumber) throws IOException {
        page.waitForTimeout(3000);
//        page.locator("ion-segment-button:has-text('Yes')").click();
        page  .click("(//ion-segment-button[text()='Yes'])[1]");
        page.locator("input[name='fullName']").click();
        page.fill("input[name='fullName']", Realtorcompany);
        page.locator("input[name='firstName']").click();
        page.fill("input[name='firstName']", RealtorFirstName);
        page.locator("input[name='lastName']").click();
        page.fill("input[name='lastName']", RealtorLastName);
        page.locator("input[name='emailAddress']").click();
        page.fill("input[name='emailAddress']", RealtorEmail);
        page.locator("input[name='phoneNumber']").click();
        page.fill("input[name='phoneNumber']", RealtorPhonenumber);
        page.locator("//ion-button[contains(@class,'save-continue')]").hover();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Other Info selection is done.");
    }
    public void OtherInfoform(String AppFirstBrName,String AppLastBrName,String AppFirstBRBOD,String Remail,
                              String RPhonenumber, String  movedMonthYear, String SecBorrFirstBrName,
                              String SecBorrLastBrName, String SecBorrFirstBRBOD, String SecBorrEmail, String SecBorrMobNumber ) throws IOException {
        page.waitForTimeout(300);

        page.click("(//ion-segment-button[text()='Yes'])[1]");
        //firstname
        page.locator("//input[@name='firstName']").clear();
        page.fill("input[name='firstName']", AppFirstBrName);
        //lastname
        page.locator("//input[@name='lastName']").clear();
        page.fill("input[name='lastName']", AppLastBrName);
        //birthdate
        page.fill("input[name='birthDate']", AppFirstBRBOD);
        //marital status
        page.locator("//ion-segment[@name='maritalStatusId']//ion-segment-button[@value='M']").click();
        // page.locator("ion-segment-button[value='Unmarried']").click();
        //citizenship status
        //  page.locator("//ion-segment[@name='citizenStatus']//ion-segment-button[@value=]").click();
        page.locator("ion-segment-button:has-text(' US Citizen ')").click();
        // page.locator("ion-segment-button[value='US Citizen']").click();
        // armed forces
        page.locator("//ion-segment[@name='militaryDutyType']//ion-segment-button[@value='NONE']").click();

        page.locator("//ion-segment[@name='emailType']//ion-segment-button[@value='WORK']").click();
        // page.locator("ion-segment-button[value='WORK']").click();
        //contact
        page.locator("//ion-segment[@name='contactMethodType']//ion-segment-button[@value='TELEPHONE']").click();
        // page.locator("ion-segment-button[value='TELEPHONE']").click();
        //occupancy type
        page.locator("//ion-segment[@name='ownRentOrFree']//ion-segment-button[@value='OWN']").click();

        page.click("(//ion-segment-button[text()='Yes'])[3]");
        // page.locator("ion-segment-button:has-text('Yes')").click();
        //moved in month and year
        page.fill("input[name='movedMonthYear']", movedMonthYear);

        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        // page.click("(//ion-segment-button[text()=' Continue to Co-Applicant '])");

        //second applicant
        page.waitForTimeout(300);

        page.click("(//ion-segment-button[text()='Yes'])[1]");
        //firstname
        page.locator("//input[@name='firstName']").clear();
        page.fill("input[name='firstName']", SecBorrFirstBrName);
        //lastname
        page.locator("//input[@name='lastName']").clear();
        page.fill("input[name='lastName']", SecBorrLastBrName);
        //birthdate
        page.fill("input[name='birthDate']", SecBorrFirstBRBOD);
        //marital status
        //   page.locator("//ion-segment[@name='maritalStatusId']//ion-segment-button[@value='M']").click();
        // page.locator("ion-segment-button[value='Unmarried']").click();
        //citizenship status
        //  page.locator("//ion-segment[@name='citizenStatus']//ion-segment-button[@value=]").click();
        page.locator("ion-segment-button:has-text(' US Citizen ')").click();
        // page.locator("ion-segment-button[value='US Citizen']").click();
        // armed forces
        page.locator("//ion-segment[@name='militaryDutyType']//ion-segment-button[@value='NONE']").click();

        page.locator("//ion-input[@name='mobileNumber']").click();
        page.fill("input[name='mobileNumber']", SecBorrMobNumber);

        page.locator("//input[@name='email']").clear();
        page.fill("input[name='email']", SecBorrEmail);

        page.locator("//ion-segment[@name='emailType']//ion-segment-button[@value='WORK']").click();
        // page.locator("ion-segment-button[value='WORK']").click();
        //contact
        page.locator("//ion-segment[@name='contactMethodType']//ion-segment-button[@value='TELEPHONE']").click();
        // page.locator("ion-segment-button[value='TELEPHONE']").click();
        //occupancy type
        page.locator("//ion-segment[@name='ownRentOrFree']//ion-segment-button[@value='OWN']").click();

        page.click("(//ion-segment-button[text()='No'])[3]");
        page.click("(//ion-segment-button[text()='Yes'])[4]");
        // page.locator("ion-segment-button:has-text('Yes')").click();
        //moved in month and year
        page.fill("input[name='movedMonthYear']", movedMonthYear);
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Two Borrowers Info is done.");

    }

}
