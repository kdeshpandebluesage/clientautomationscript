package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.pages.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TwoBorr_1_Refinance_WilliamSBrennan_LIONLOPLOS_Supreme {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    static ExtentReports extent;
    static ExtentTest test;
    public static String LoanNumber;


    @BeforeClass
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        ExtentHtmlReporter reporter = new ExtentHtmlReporter("test-output/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Test
    void
    testFormFill() throws IOException, InterruptedException {
        String loanNumber = null;
        //page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
        // page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
 //       page.navigate("https://sup-uat-lion.bluesageusa.com/apply/login");
        page.navigate("https://sup-qa-lion.bluesageusa.com/apply/login");
//        page.navigate("https://sup-dev-lion.bluesageusa.com/apply/login");


       // List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/SupremeLIONLOPLOSData.xlsx");
        List<Map<String, String>> data = ExcelUtil.readExcelWithTC("src/main/resources/SupremeLIONLOPLOSData.xlsx","SUP_DSP_Sc1");
//        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");
        //   page.click("//ion-button[contains(@class, 'login') and contains(@class, 'button')]");

        for (Map<String, String> row : data) {
            new LoginPage(page).login(row.get("Username"), row.get("Password"));
        }

        for (Map<String, String> row : data) {
            new LoanPurposeSUPPage(page).refinanceloanpurposeform(row.get("LoanPurposePhoneNumber"));
        }

//        for (Map<String, String> row : data) {
//            new OtherInfoPage(page).OtherInfoform(row.get("RealtorCompanyName"),row.get("RealtorFirstName"),row.get("RealtorLastName"),row.get("RealtorEmail"),row.get("Realtorphonenumber") );
//        }

//        for (Map<String, String> row : data) {
//            new PurchaseLoanPage(page).purchaseInfoform(row.get("Purchasezipcode"), row.get("PurchasepropertyType"), row.get("PurchaseLoanpurchasePrice"), row.get("Purchasedownpayment"));
//        }
        for (Map<String, String> row : data) {
            new RefinancePropertyPage(page).RefiPropInfoform(row.get("Refinanceaddress"),row.get("Refinancecity"),
                    row.get("Refinancestate"),row.get("Refinancezipcode"),row.get("rphonenumber"),row.get("RefinancePropertyUsed"),row.get("RefinancePurpose"), row.get("RefinanceCashoutAmt"));
        }

//        for (Map<String, String> row : data) {
//            new OtherInfoPage(page).OtherInfoform(row.get("AppFirstBrName"), row.get("AppLastBrName"),
//                    row.get("AppFirstBRBOD"), row.get("Remail"), row.get("RPhonenumber"), row.get("movedMonthYear"),
//                    row.get("SecBorrFirstBrName"), row.get("SecBorrLastBrName"), row.get("SecBorrFirstBRBOD"), row.get("SecBorrEmail"), row.get("SecBorrMobNumber"));
//
//        }
        for (Map<String, String> row : data) {
            new ApplicationInfoSUPPage(page).applicationInformation(row.get("ApplicationInfo2BrPrimaryApplicantFirstName"), row.get("ApplicationInfo2BrPrimaryApplicantMiddleName"),row.get("ApplicationInfo2BrPrimaryApplicantLastName"),
                    row.get("ApplicationInfo2BrPrimaryApplicantBOD"), row.get("ApplicationInfo2BrPrimaryApplicantMobNumber"), row.get("ApplicationInfo2BrPrimaryApplicantEmail"), row.get("ApplicationInfo2BrPrimaryApplicantAddressline"), row.get("ApplicationInfo2BrPrimaryApplicantCityName"),
                    row.get("ApplicationInfo2BrPrimaryApplicantStateName"), row.get("ApplicationInfo2BrPrimaryApplicantzipcode"), row.get("ApplicationInfo2BrPrimaryApplicantRent"), row.get("ApplicationInfo2BrPrimaryApplicantMovedMonthYear"), row.get("ApplicationInfo2BrPrimaryApplicantCountry")
            );
        }
        for (Map<String, String> row : data) {
            new ApplicationInfoSecondaryPage(page).applicationSecondaryInformation(row.get("ApplicationInfo2BrSecondaryApplicantFirstName"), row.get("ApplicationInfo2BrSecondaryApplicantMiddleName"), row.get("ApplicationInfo2BrSecondaryApplicantLastName"),
                    row.get("ApplicationInfo2BrSecondaryApplicantBOD"), row.get("ApplicationInfo2BrSecondaryApplicantMobNumber"), row.get("ApplicationInfo2BrSecondaryApplicantEmail"), row.get("ApplicationInfo2BrSecondaryApplicantAddressline"), row.get("ApplicationInfo2BrSecondaryApplicantCityName"),
                    row.get("ApplicationInfo2BrSecondaryApplicantStateName"), row.get("ApplicationInfo2BrSecondaryApplicantzipcode"), row.get("ApplicationInfo2BrSecondaryApplicantRent"));
        }


        for (Map<String, String> row : data) {
            new EmplIncomePage(page).EmplIncomeform2Borr(row.get("onlineID"), row.get("passcode"),
                    row.get("EmplName"), row.get("EmpPhoneNumber"), row.get("EmpExpYears"), row.get("EmpMonthYear"), row.get("baseSalary"), row.get("overtime"),
                    row.get("bonus"), row.get("commissions"));
        }
        for (Map<String, String> row : data) {
            new OtherIncomePage(page).OtherIncomeform2Borr(row.get("incomeAmount"));
        }
        for (Map<String, String> row : data) {
            new AssetsSUPPage(page).AssetInfoform2Borr(row.get("accountName"), row.get("currentValue"));
        }
        for (Map<String, String> row : data) {
            new REOPage(page).REOInfoformPurchase();
        }
        for (Map<String, String> row : data) {
            new DeclarationsPage(page).DeclarationsInfoform2Borr();
        }
        for (Map<String, String> row : data) {
            new DemographicInfoPage(page).DemographicInfoform2Borr();
        }

        for (Map<String, String> row : data) {
            new ConsentSUPPage(page).ConsentInfoform2Borr(row.get("borrowerSSN"), row.get("coborrowerSSN"));
        }
//        for (Map<String, String> row : data) {
//           new SubmitPage(page).SubmitInfoform();
//       }
        for (Map<String, String> row : data) {
            new SubmitPage(page).SubmitInfoform();
            loanNumber = LoanUtilsSUP.getLoanNumber("p:has-text('Reference Number:')", page);
            if (loanNumber == null || loanNumber == "") {
                throw new AssertionError("Loan number is null or empty");
            }
            System.out.println(LoanUtils.getLoanNumber("p:has-text('Reference Number:')", page));
        }
        page.navigate("https://sup-qa.bluesageusa.com/crm/crm/#/login");

//        Assertions.assertTrue(page.locator("text=Thank you").isVisible());
        for (Map<String, String> row : data) {
            new LoginLOPPage(page).login(row.get("UsernameLOP"), row.get("PasswordLOP"));
            System.out.println("LOP is launched");
        }
        for (Map<String, String> row : data) {
            new LOPLoanSearchPage(page).LopLoanSearch(loanNumber);
//            System.out.println(LoanUtils.getLoanNumber("p:has-text('Loan Number:')",page));
        }
//        for (Map<String, String> row : data) {
//            new LOPApplicationSUPPage(page).LopApplication();
//        }
//        for (Map<String, String> row : data) {
//            new LOPCreditSUPPage(page).lopOrderCredit2BR();
//        }
//        for (Map<String, String> row : data) {
//            new LOPLoanSourceSUPPage(page).lopLoanSource();
//        }
//
//        for (Map<String, String> row : data) {
//            new LOPLoanTermSUPPage(page).lopLoantermPurchase(row.get("LOPAppraisedValue"), row.get("LOPCreditscore"), row.get("SettlmentDate"));
//        }
//
//        for (Map<String, String> row : data) {
//            new LOPPricingSUPPage(page).handleExceptionsIfNeeded();
//           // new LOPPricingSUPPage(page).handleExceptionsIfNeeded(row.get("logLabel"));
//        }
//        for (Map<String, String> row : data) {
//            new LOPAUSPage(page).lopAUSDU();
//        }
//
//        for (Map<String, String> row : data) {
//            new LOPComplianceEasePage(page).lopCE();
//        }
//        for (Map<String, String> row : data) {
//            new LOPDocumentPage(page).lopInitial();
//        }

    }

   @AfterClass
    static void teardown() {
        //       if (page != null) page.close();
        //      if (browser != null) browser.close();
        //     if (playwright != null) playwright.close();
        //      extent.flush();
    }
}
