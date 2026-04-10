package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.pages.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TwoBorrRefinanceLoanTest_Old {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    static ExtentReports extent;
    static ExtentTest test;


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
    void testFormFill() throws IOException {
        //page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
       // page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
        page.navigate("https://move-dev-lion.bluesageusa.com/apply/login");

        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");
     //   page.click("//ion-button[contains(@class, 'login') and contains(@class, 'button')]");

        for (Map<String, String> row : data) {
            new LoginPage(page).login(row.get("TwoBorrUsername"),row.get("Password"));
        }

        for (Map<String, String> row : data) {
            new LoanPurposePage(page).refinanceloanpurposeform(row.get("rphonenumber"));
        }

        for (Map<String, String> row : data) {
            new RefinancePropertyPage(page).RefiPropInfoform(row.get("Refinanceaddress"),row.get("Refinancecity"),
                    row.get("Refinancestate"),row.get("Refinancezipcode"),row.get("rphonenumber"),row.get("RefinancePropertyUsed"),row.get("RefinancePurpose"), row.get("RefinanceCashoutAmt"));
        }
        for (Map<String, String> row : data) {
            new ApplicationInfoPage(page).TwoBorrApplicationform(row.get("FirstBorrFirstBrName"),row.get("FirstBorrLastBrName"),
                    row.get("FirstBorrFirstBRBOD"),row.get("Remail"),row.get("RPhonenumber"),row.get("movedMonthYear"),
                    row.get("SecBorrFirstBrName"),row.get("SecBorrLastBrName"),row.get("SecBorrFirstBRBOD"), row.get("SecBorrEmail"), row.get("SecBorrMobNumber") );
        }
        for (Map<String, String> row : data) {
            new EmplIncomePage(page).EmplIncomeform2Borr( row.get("onlineID"), row.get("passcode"),
                    row.get("EmplName"), row.get("EmpPhoneNumber"), row.get("EmpExpYears"), row.get("EmpMonthYear"), row.get("baseSalary"), row.get("overtime"),
                    row.get("bonus"), row.get("commissions"));
        }
        for (Map<String, String> row : data) {
            new OtherIncomePage(page).OtherIncomeform2Borr(row.get("incomeAmount"));
        }
        for (Map<String, String> row : data) {
            new AssetsPage(page).AssetInfoform2Borr(row.get("accountName"),row.get("currentValue") );
        }
        for (Map<String, String> row : data) {
            new REOPage(page).REOInfoform(row.get("monthlyHomeownersInsurance"),row.get("monthlyRealEstateTaxes"),row.get("monthlyFloodInsurance"),row.get("hoaDues") );
        }
        for (Map<String, String> row : data) {
            new DeclarationsPage(page).DeclarationsInfoform2Borr();
        }
        for (Map<String, String> row : data) {
            new DemographicInfoPage(page).DemographicInfoform2Borr();
        }
        for (Map<String, String> row : data) {
            new ConsentPage(page).ConsentInfoform2Borr(row.get("FirstborrowerSSN"),row.get("SecondcoborrowerSSN") );
        }
        for (Map<String, String> row : data) {
            new SubmitPage(page).SubmitInfoform();
        }
//        Assertions.assertTrue(page.locator("text=Thank you").isVisible());
    }

   @AfterClass
    static void teardown() {
 //       if (page != null) page.close();
  //      if (browser != null) browser.close();
   //     if (playwright != null) playwright.close();
  //      extent.flush();
    }
}
