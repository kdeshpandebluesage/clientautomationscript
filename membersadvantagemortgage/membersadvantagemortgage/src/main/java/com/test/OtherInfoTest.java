package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.pages.ExcelUtil;
import com.pages.OtherInfoPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OtherInfoTest {
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
        page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/purchase_property");

        /*below code is without excel//
        LoanPurposePage Lpform = new LoanPurposePage(page);
        Lpform.loanpurposeform("7894561236");*/
        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");

        for (Map<String, String> row : data) {
            new OtherInfoPage(page).OtherInfoform(row.get("RCompanyName"),row.get("RFirstName"),row.get("RLastName"),row.get("REmail"),row.get("Rphonenumber"));
        }

//        Assertions.assertTrue(page.locator("text=Thank you").isVisible());
    }

   @AfterClass
    static void teardown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        extent.flush();
    }
}
