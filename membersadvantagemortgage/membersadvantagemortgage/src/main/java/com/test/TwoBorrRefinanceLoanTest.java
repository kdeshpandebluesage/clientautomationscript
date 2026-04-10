package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseTest;
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

public class TwoBorrRefinanceLoanTest extends BaseTest {

    private Playwright playwright;
    private Browser browser;
    private Page page;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create(); // Initialize Playwright
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Launch browser
        page = browser.newPage(); // Open a new Playwright page

        // Initialize Extent Reports
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("test-output/RefinanceReport2.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        test = extent.createTest("TwoBorrRefinanceLoanTest", "Validating Two Borrower Refinance Loan Application");
    }

    @Test
    public void twoBorrRefiLoan() throws IOException {
        test.info("Test Started");

        // Navigate to the application login page
        page.navigate("https://move-dev-lion.bluesageusa.com/apply/login");
        test.info("Navigated to login page");

        // Read Excel data
        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");

        // Perform login
        for (Map<String, String> row : data) {
            String username = row.get("TwoBorrUsername");
            test.info("Logging in for user: " + username);
            new LoginPage(page).login(username, row.get("Password"));
        }

        // Fill Loan Purpose form
        for (Map<String, String> row : data) {
            test.info("Loan Purpose form for phone: " + row.get("rphonenumber"));
            new LoanPurposePage(page).refinanceloanpurposeform(row.get("rphonenumber"));
        }

        // Fill Refinance Property Information
        for (Map<String, String> row : data) {
            new RefinancePropertyPage(page).RefiPropInfoform(row.get("Refinanceaddress"),row.get("Refinancecity"),
                    row.get("Refinancestate"),row.get("Refinancezipcode"),row.get("rphonenumber"),row.get("RefinancePropertyUsed"),row.get("RefinancePurpose"), row.get("RefinanceCashoutAmt"));
        }

        // Fill Other Info form
        for (Map<String, String> row : data) {
            new ApplicationInfoPage(page).TwoBorrApplicationform(row.get("FirstBorrFirstBrName"),row.get("FirstBorrLastBrName"),
                    row.get("FirstBorrFirstBRBOD"),row.get("Remail"),row.get("RPhonenumber"),row.get("movedMonthYear"),
                    row.get("SecBorrFirstBrName"),row.get("SecBorrLastBrName"),row.get("SecBorrFirstBRBOD"), row.get("SecBorrEmail"), row.get("SecBorrMobNumber"));
        }

        // Fill Employment Income Information
        for (Map<String, String> row : data) {
            test.info("Employment Income form by Applicant");
            new EmplIncomePage(page).EmplIncomeform2Borr(row.get("onlineID"), row.get("passcode"),
                    row.get("EmplName"), row.get("EmpPhoneNumber"), row.get("EmpExpYears"),
                    row.get("EmpMonthYear"), row.get("baseSalary"), row.get("overtime"),
                    row.get("bonus"), row.get("commissions"));
        }

        // Fill Other Income Information
        for (Map<String, String> row : data) {
            test.info("Other Income form");
            new OtherIncomePage(page).OtherIncomeform2Borr(row.get("incomeAmount"));
        }

        // Fill Assets Information
        for (Map<String, String> row : data) {
            test.info("Assets Information");
            new AssetsPage(page).AssetInfoform2Borr(row.get("accountName"), row.get("currentValue"));
        }

        // Fill Real Estate-Owned (REO) Information
        for (Map<String, String> row : data) {
            test.info("REO Information");
            new REOPage(page).REOInfoform(row.get("monthlyHomeownersInsurance"), row.get("monthlyRealEstateTaxes"),
                    row.get("monthlyFloodInsurance"), row.get("hoaDues"));
        }

        // Fill Declarations Information
        for (Map<String, String> row : data) {
            test.info("Declarations form");
            new DeclarationsPage(page).DeclarationsInfoform2Borr();
        }

        // Fill Demographics Information
        for (Map<String, String> row : data) {
            test.info("Demographics form");
            new DemographicInfoPage(page).DemographicInfoform2Borr();
        }

        // Fill Consent Information
        for (Map<String, String> row : data) {
            test.info("Consent Information");
            new ConsentPage(page).ConsentInfoform2Borr(row.get("FirstborrowerSSN"), row.get("SecondcoborrowerSSN"));
        }

        // Submit the form
        for (Map<String, String> row : data) {
            test.info("Submitting Loan Application");
            new SubmitPage(page).SubmitInfoform();
        }

        test.pass("All forms successfully submitted.");
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
