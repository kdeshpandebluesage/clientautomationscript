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

public class SC4_TwoBorr_2_Purchase_Portal_TIB {
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

        page.navigate("https://tib-qa.bluesagedlp.com/portal/portal/#/login");



       // List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/SupremeLIONLOPLOSData.xlsx");
        List<Map<String, String>> data = ExcelUtil.readExcelWithTC("src/main/resources/TIBPortalLOSData.xlsx","TIB_Sc1");
//        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");
        //   page.click("//ion-button[contains(@class, 'login') and contains(@class, 'button')]");

        for (Map<String, String> row : data) {
            new LoginPage(page).login(row.get("Username"), row.get("Password"));
        }

//        for (Map<String, String> row : data) {
//            new LoanPurposeSUPPage(page).refinanceloanpurposeform(row.get("LoanPurposePhoneNumber"));
//        }
        for (Map<String, String> row : data) {
            new PortalImportPage(page).importfile();
        }
        for (Map<String, String> row : data) {
            new PortalShortAppPage(page).shortApp(row.get("Creditscore"),row.get("DTI"),row.get("PropertyType"),row.get("LeinType"));

            loanNumber = LoanUtils.getLoanNumber("//div[contains(text(), 'registered successfully')]", page);
            if (loanNumber == null || loanNumber == "") {
                throw new AssertionError("Loan number is null or empty");
            }
            System.out.println(LoanUtils.getLoanNumber("//div[contains(text(), 'registered successfully')]", page));
        }

    }

   @AfterClass
    static void teardown() {
        //       if (page != null) page.close();
        //      if (browser != null) browser.close();
        //     if (playwright != null) playwright.close();
        //      extent.flush();
    }
}
