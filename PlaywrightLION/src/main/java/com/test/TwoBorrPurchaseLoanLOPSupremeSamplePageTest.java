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

public class TwoBorrPurchaseLoanLOPSupremeSamplePageTest {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    static ExtentReports extent;
    static ExtentTest test;
    public static String LoanNumber;



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
    void testFormFill() throws IOException, InterruptedException {
        String loanNumber = null;
        //page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
       // page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
        page.navigate("https://sup-uat.bluesageusa.com/crm/crm/#/login");

        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/MovementData.xlsx");
//        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");
     //   page.click("//ion-button[contains(@class, 'login') and contains(@class, 'button')]");

        for (Map<String, String> row : data) {
            new LoginLOPPage(page).login(row.get("UsernameLOP"),row.get("PasswordLOP"));
            System.out.println("LOP is launced");
        }

        for (Map<String, String> row : data) {
            new LOPPricingSUPPage(page).handleExceptionsIfNeeded();
        }
        for (Map<String, String> row : data) {
            new LOPAUSPage(page).lopAUSDU();
        }

        for (Map<String, String> row : data) {
            new LOPComplianceEasePage(page).lopCE();
        }
        for (Map<String, String> row : data) {
            new LOPDocumentPage(page).lopInitial();
        }

    }

    @AfterAll
    static void teardown() {
 //       if (page != null) page.close();
  //      if (browser != null) browser.close();
   //     if (playwright != null) playwright.close();
  //      extent.flush();
    }
}
