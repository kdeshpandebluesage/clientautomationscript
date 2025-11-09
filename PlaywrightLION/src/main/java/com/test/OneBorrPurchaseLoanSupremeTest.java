package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.pages.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OneBorrPurchaseLoanSupremeTest {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    static ExtentReports extent;
    static ExtentTest test;


    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        ExtentHtmlReporter reporter = new ExtentHtmlReporter("test-output/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Test
    void testFormFill() throws IOException {
        //page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
       // page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
//        page.navigate("https://move-dev-lion.bluesageusa.com/apply/login");
        page.navigate("https://sup-qa-lion.bluesageusa.com/apply/login");

        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/MovementData.xlsx");
     //   page.click("//ion-button[contains(@class, 'login') and contains(@class, 'button')]");

        for (Map<String, String> row : data) {
            new LoginPage(page).login(row.get("Username"),row.get("Password"));
        }

        for (Map<String, String> row : data) {
            new LoanPurposePage(page).loanpurposeform(row.get("LoanPurposePhoneNumber"));
        }
//
//        for (Map<String, String> row : data) {
//            new OtherInfoPage(page).OtherInfoform(row.get("RealtorCompanyName"),row.get("RealtorFirstName"),row.get("RealtorLastName"),row.get("RealtorEmail"),row.get("Realtorphonenumber") );
//        }

        for (Map<String, String> row : data) {
            new PurchaseLoanPage(page).purchaseInfoform(row.get("Purchasezipcode"),row.get("PurchasepropertyType"),row.get("PurchaseLoanpurchasePrice"),row.get("Purchasedownpayment"));
        }

        for (Map<String, String> row : data) {
            new ApplicationInfoPage(page).OneBorrApplicationform(row.get("ApplicationInfo1BrPrimaryApplicantFirstName"), row.get("ApplicationInfo2BrPrimaryApplicantLastName"),
                    row.get("ApplicationInfo1BrPrimaryApplicantBOD"), row .get ("ApplicationInfo1BrPrimaryApplicantMobNumber"),row .get ("ApplicationInfo1BrPrimaryApplicantEmail"),row.get("ApplicationInfo1BrPrimaryApplicantAddressline"), row.get("ApplicationInfo1BrPrimaryApplicantCityName"),
                    row.get("ApplicationInfo1BrPrimaryApplicantStateName"), row.get("ApplicationInfo1BrPrimaryApplicantzipcode"), row.get("ApplicationInfo1BrPrimaryApplicantRent"),row .get ("ApplicationInfo1BrPrimaryApplicantMovedMonthYear")
            );
        }
        for (Map<String, String> row : data) {
            new EmplIncomePage(page).EmplIncomeform1Borr( row.get("onlineID"), row.get("passcode"),
                    row.get("EmplName"), row.get("EmpPhoneNumber"), row.get("EmpExpYears"), row.get("EmpMonthYear"), row.get("baseSalary"), row.get("overtime"),
                    row.get("bonus"), row.get("commissions"));
        }
        for (Map<String, String> row : data) {
            new OtherIncomePage(page).OtherIncomeform1Borr(row.get("incomeAmount"));
        }
        for (Map<String, String> row : data) {
            new AssetsPage(page).AssetInfoform1Borr(row.get("accountName"),row.get("currentValue") );
        }
        for (Map<String, String> row : data) {
            new REOPage(page).REOInfoformPurchase();
        }
        for (Map<String, String> row : data) {
            new DeclarationsPage(page).DeclarationsInfoform1Borr();
        }
        for (Map<String, String> row : data) {
            new DemographicInfoPage(page).DemographicInfoform1Borr();
        }
        for (Map<String, String> row : data) {
            new ConsentPage(page).ConsentInfoform1Borr(row.get("borrowerSSN"));
        }
        for (Map<String, String> row : data) {
            new SubmitPage(page).SubmitInfoform();
        }
//        Assertions.assertTrue(page.locator("text=Thank you").isVisible());
    }

    @AfterAll
    static void teardown() {
 //       if (page != null) page.close();
  //      if (browser != null) browser.close();
   //     if (playwright != null) playwright.close();
  //      extent.flush();
    }
}
