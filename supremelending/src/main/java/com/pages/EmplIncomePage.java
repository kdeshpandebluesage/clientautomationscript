package com.pages;

public class EmplIncomePage extends BaseClass {
    public EmplIncomePage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void EmplIncomeform2Borr(String onlineID, String passcode,String EmplName, String EmpPhoneNumber,
                               String EmpExpYears, String EmpMonthYear, String baseSalary, String overtime, String bonus, String commissions ) {
        page.waitForTimeout(3000);
        page.click("(//ion-segment-button[text()='Yes'])");
        page.waitForTimeout(3000);
        //Empl Name
        page.locator("//ion-input[@name='employerName']").click();
        page.fill("input[name='employerName']", EmplName);

        //Emp Address

        //Emp Phone Number
        page.locator("//ion-input[@name='phoneNumber']").click();
        page.fill("input[name='phoneNumber']", EmpPhoneNumber);
        //No Of years Profession
        page.locator("//ion-input[@name='experienceYears']").click();
        page.fill("input[name='experienceYears']", EmpExpYears);
        //No Of months
        //Start Date
        page.locator("//ion-input[@name='employMonthYear']").click();
        page.fill("input[name='employMonthYear']", EmpMonthYear);
        //Empl Family
       // page.locator("//yes-no-bool[@name='isEmployedByPartyToTransaction'](//ion-segment-button[text()='No'])[1]").click();
        page.waitForTimeout(3000);
   //     page.click("(//ion-segment-button[text()='No'])[1]");
        page.click("((//ion-segment-button[text()='No'])[1])");
        //Self Employed
        page.click("((//ion-segment-button[text()='No'])[2])");
        //Still Employed
        page.click("((//ion-segment-button[text()='Yes'])[3])");
        //Salary
//        SecondBorrowerBaseSalary	textbox	//input[@name='baseSalary']
        page.locator("//ion-input[@name='baseSalary']").click();
        page.fill("input[name='baseSalary']", baseSalary);
//        SecondBorrowerAnnualOvertime	textbox	//input[@name='overtime']
        page.locator("//ion-input[@name='overtime']").click();
        page.fill("input[name='overtime']", overtime);
//        SecondBorrowerAnnualBonus	textbox	//input[@name='bonus']
        page.locator("//ion-input[@name='bonus']").click();
        page.fill("input[name='bonus']", bonus);
//        SecondBorrowerAnnualCommisions	textbox	//input[@name='commissions']
        page.locator("//ion-input[@name='commissions']").click();
        page.fill("input[name='commissions']", commissions);
//        SecondBorrowerAnnualMilitaryPay	textbox	//input[@name='militaryPay']
//        SecondBorrowerOtherIncome	textbox	//input[@name='otherIncome']
//        SecondBorrowerEmploymentSave	nodes	//ion-button[contains(@class,'save-continue')]
//        CheckSecondBorrowerEmploymentSave	waitforelemlocator	//form[contains(@class,'apply-form')]
//        EmploymentContinue	nodes	//ion-button[contains(@class,'save-continue')]

        //Save & Continue
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        //SECOND BORROWER
        page.click("(//ion-segment-button[text()='Yes'])");
        page.waitForTimeout(3000);
        //Empl Name
        page.locator("//ion-input[@name='employerName']").click();
        page.fill("input[name='employerName']", EmplName);

        //Emp Address

        //Emp Phone Number
        page.locator("//ion-input[@name='phoneNumber']").click();
        page.fill("input[name='phoneNumber']", EmpPhoneNumber);
        //No Of years Profession
        page.locator("//ion-input[@name='experienceYears']").click();
        page.fill("input[name='experienceYears']", EmpExpYears);
        //No Of months
        //Start Date
        page.locator("//ion-input[@name='employMonthYear']").click();
        page.fill("input[name='employMonthYear']", EmpMonthYear);
        //Empl Family
        // page.locator("//yes-no-bool[@name='isEmployedByPartyToTransaction'](//ion-segment-button[text()='No'])[1]").click();
        page.waitForTimeout(3000);
        //     page.click("(//ion-segment-button[text()='No'])[1]");
        page.click("((//ion-segment-button[text()='No'])[1])");
        //Self Employed
        page.click("((//ion-segment-button[text()='No'])[2])");
        //Still Employed
        page.click("((//ion-segment-button[text()='Yes'])[3])");
        //Salary
//        SecondBorrowerBaseSalary	textbox	//input[@name='baseSalary']
        page.locator("//ion-input[@name='baseSalary']").click();
        page.fill("input[name='baseSalary']", baseSalary);
//        SecondBorrowerAnnualOvertime	textbox	//input[@name='overtime']
        page.locator("//ion-input[@name='overtime']").click();
        page.fill("input[name='overtime']", overtime);
//        SecondBorrowerAnnualBonus	textbox	//input[@name='bonus']
        page.locator("//ion-input[@name='bonus']").click();
        page.fill("input[name='bonus']", bonus);
//        SecondBorrowerAnnualCommisions	textbox	//input[@name='commissions']
        page.locator("//ion-input[@name='commissions']").click();
        page.fill("input[name='commissions']", commissions);
        //Save & Continue
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        System.out.println("Emply and Income is done.");
    }
    public void EmplIncomeform1Borr(String onlineID, String passcode,String EmplName, String EmpPhoneNumber,
                                    String EmpExpYears, String EmpMonthYear, String baseSalary, String overtime, String bonus, String commissions ) {

        page.waitForTimeout(3000);
        page.click("(//ion-segment-button[text()='Yes'])");
        //Add Employment
       // page.locator("//ion-button[text()=' Add Employment ']").click();
        //Empl Name
        page.locator("//ion-input[@name='employerName']").click();
        page.fill("input[name='employerName']", EmplName);

        //Emp Address

        //Emp Phone Number
        page.locator("//ion-input[@name='phoneNumber']").click();
        page.fill("input[name='phoneNumber']", EmpPhoneNumber);
        //No Of years Profession
        page.locator("//ion-input[@name='experienceYears']").click();
        page.fill("input[name='experienceYears']", EmpExpYears);
        //No Of months
        //Start Date
        page.locator("//ion-input[@name='employMonthYear']").click();
        page.fill("input[name='employMonthYear']", EmpMonthYear);
        //Empl Family
        // page.locator("//yes-no-bool[@name='isEmployedByPartyToTransaction'](//ion-segment-button[text()='No'])[1]").click();
        page.waitForTimeout(3000);
        //     page.click("(//ion-segment-button[text()='No'])[1]");
        page.click("((//ion-segment-button[text()='No'])[1])");
        //Self Employed
        page.click("((//ion-segment-button[text()='No'])[2])");
        //Still Employed
        page.click("((//ion-segment-button[text()='Yes'])[3])");
        //Salary
//        SecondBorrowerBaseSalary	textbox	//input[@name='baseSalary']
        page.locator("//ion-input[@name='baseSalary']").click();
        page.fill("input[name='baseSalary']", baseSalary);
//        SecondBorrowerAnnualOvertime	textbox	//input[@name='overtime']
        page.locator("//ion-input[@name='overtime']").click();
        page.fill("input[name='overtime']", overtime);
//        SecondBorrowerAnnualBonus	textbox	//input[@name='bonus']
        page.locator("//ion-input[@name='bonus']").click();
        page.fill("input[name='bonus']", bonus);
//        SecondBorrowerAnnualCommisions	textbox	//input[@name='commissions']
        page.locator("//ion-input[@name='commissions']").click();
        page.fill("input[name='commissions']", commissions);
//        SecondBorrowerAnnualMilitaryPay	textbox	//input[@name='militaryPay']
//        SecondBorrowerOtherIncome	textbox	//input[@name='otherIncome']
//        SecondBorrowerEmploymentSave	nodes	//ion-button[contains(@class,'save-continue')]
//        CheckSecondBorrowerEmploymentSave	waitforelemlocator	//form[contains(@class,'apply-form')]
//        EmploymentContinue	nodes	//ion-button[contains(@class,'save-continue')]

        //Save & Continue
        page.locator("//ion-button[contains(@class,'save-continue')]").click();
        page.locator("//ion-button[contains(@class,'save-continue')]").click();

        System.out.println("Emply and Income is done.");
    }

}
