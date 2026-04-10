package com.pages;

import java.io.IOException;

public class ApplicationInfoPage extends BasePage_Old {
    public ApplicationInfoPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void TwoBorrApplicationform(String AppFirstBrName,String AppLastBrName,String AppFirstBRBOD,String Remail,
                              String RPhonenumber, String  movedMonthYear, String SecBorrFirstBrName,
                              String SecBorrLastBrName, String SecBorrFirstBRBOD, String SecBorrEmail, String SecBorrMobNumber ) throws IOException {
        page.waitForTimeout(3000);

  //      page.click("(//ion-segment-button[text()='Yes'])[1]");
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

    public void OneBorrApplicationform(String AppFirstBrName,String AppLastBrName,String AppFirstBRBOD,String Remail,
                              String RPhonenumber, String  movedMonthYear) throws IOException {
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
        //marital status
        page.locator("//ion-segment[@name='maritalStatusId']//ion-segment-button[@value='U']").click();
        // page.locator("ion-segment-button[value='Unmarried']").click();
        //citizenship status
        //  page.locator("//ion-segment[@name='citizenStatus']//ion-segment-button[@value=]").click();
        page.locator("//yes-no-bool[@name='unmarriedSelectedYn']//ion-segment//ion-segment-button[2]").click();
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
        System.out.println("One Borrower Info is done.");



    }



}
