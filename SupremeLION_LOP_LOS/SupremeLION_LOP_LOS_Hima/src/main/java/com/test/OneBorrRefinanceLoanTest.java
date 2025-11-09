package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.base.BaseTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.pages.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import com.pages.PurchaseLoanPage;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class OneBorrRefinanceLoanTest extends BaseTest {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    static ExtentReports extent;
   // static final ExtentReports extent = new ExtentReports();
    static ExtentTest test;


    @BeforeClass
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("test-output/RefinanceReport1.html");
//        reporter.config().setDocumentTitle("Automation Report");
//        reporter.config().setReportName("Functional Test Report");
        reporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        test = extent.createTest("OneBorrRefinanceLoanTest", "Validating One Borrower Refinance Loan Application");

    }

    @Test
    void oneBorrRefiLoan() throws IOException {
        //page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
       // page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
        page.navigate("https://move-dev-lion.bluesageusa.com/apply/login");

        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");
     //   page.click("//ion-button[contains(@class, 'login') and contains(@class, 'button')]");

        for (Map<String, String> row : data) {
            new LoginPage(page).login(row.get("SingleBorrUsername"),row.get("Password"));
        }

        for (Map<String, String> row : data) {
            new LoanPurposePage(page).refinanceloanpurposeform(row.get("rphonenumber"));
        }

        for (Map<String, String> row : data) {
            new RefinancePropertyPage(page).RefiPropInfoform(row.get("Refinanceaddress"),row.get("Refinancecity"),
                    row.get("Refinancestate"),row.get("Refinancezipcode"),row.get("rphonenumber"),row.get("RefinancePropertyUsed"),row.get("RefinancePurpose"), row.get("RefinanceCashoutAmt"));
        }
        for (Map<String, String> row : data) {
            new ApplicationInfoPage(page).OneBorrApplicationform(row.get("OneBorrAppFirstBrName"),row.get("OneBorrAppLastBrName"),
                    row.get("OneBorrAppFirstBRBOD"),row.get("Remail"),row.get("RPhonenumber"),row.get("movedMonthYear")
                   );
       }
//        for (Map<String, String> row : data) {
//            new ApplicationInfoPage(page).OneBorrApplicationform(row.get("AppFirstBrName"),row.get("AppLastBrName"),
//                    row.get("AppFirstBRBOD"),row.get("Remail"),row.get("RPhonenumber"),row.get("movedMonthYear")
//            );
//        }
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
            new REOPage(page).REOInfoform(row.get("monthlyHomeownersInsurance"),row.get("monthlyRealEstateTaxes"),row.get("monthlyFloodInsurance"),row.get("hoaDues") );
        }
        for (Map<String, String> row : data) {
            new DeclarationsPage(page).DeclarationsInfoform1Borr();
        }
        for (Map<String, String> row : data) {
            new DemographicInfoPage(page).DemographicInfoform1Borr();
        }
        for (Map<String, String> row : data) {

            new ConsentPage(page).ConsentInfoform1Borr(row.get("OneBorrowerSSN"));
        }
        for (Map<String, String> row : data) {
            new SubmitPage(page).SubmitInfoform();
        }
//        Assertions.assertTrue(page.locator("text=Thank you").isVisible());
    }

    @AfterClass
    public void teardown() {
        // Close resources
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
        extent.flush(); // Finalize Extent reports
    }
}
