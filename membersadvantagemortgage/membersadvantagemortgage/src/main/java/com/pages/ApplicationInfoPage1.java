package com.pages;

import com.microsoft.playwright.Page;

import java.io.IOException;

public class ApplicationInfoPage1 extends BasePage_Old {
    public ApplicationInfoPage1(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void applicationInformation(String ApplicationInfo2BrPrimaryApplicantFirstName, String ApplicationInfo2BrPrimaryApplicantLastName
            , String ApplicationInfo2BrPrimaryApplicantBOD, String ApplicationInfo2BrPrimaryApplicantMobNumber, String ApplicationInfo2BrPrimaryApplicantEmail, String ApplicationInfo2BrPrimaryApplicantAddressline, String ApplicationInfo2BrPrimaryApplicantCityName, String ApplicationInfo2BrPrimaryApplicantStateName, String ApplicationInfo2BrPrimaryApplicantzipcode, String ApplicationInfo2BrPrimaryApplicantRent, String ApplicationInfo2BrPrimaryApplicantMovedMonthYear) throws IOException {

    }

    private void pageDown() {
    }

    private void enterText(Page page, String s, String applicationInfo2BrPrimaryApplicantBOD) {
    }

    private void clearAndEnterText(Page page, String s, String applicationInfo2BrPrimaryApplicantFirstName) {
    }

    private void click(Page page, String s) {
    }

    public void OneBorrApplicationPurchaseform(String ApplicationInfo1BrPrimaryApplicantFirstName, String ApplicationInfo1BrPrimaryApplicantLastName, String ApplicationInfo1BrPrimaryApplicantBOD, String ApplicationInfo1BrPrimaryApplicantMobNumber, String ApplicationInfo1BrPrimaryApplicantEmail,
                                               String ApplicationInfo1BrPrimaryApplicantAddressline, String ApplicationInfo1BrPrimaryApplicantCityName, String ApplicationInfo1BrPrimaryApplicantStateName, String ApplicationInfo1BrPrimaryApplicantzipcode, String ApplicationInfo1BrPrimaryApplicantRent, String ApplicationInfo1BrPrimaryApplicantMovedMonthYear) throws IOException {

    }
    public void OneBorrRefiApplicationform(String AppFirstBrName,String AppLastBrName,String AppFirstBRBOD,String Remail,
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
