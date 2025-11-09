package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.pages.ExcelUtil;
import com.pages.LoanPurposePage;
import com.pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.pages.LoanPurposePage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanPurposeTest {
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

        ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Test
    void testFormFill() throws IOException {
        page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");

        /*below code is without excel//
        LoanPurposePage Lpform = new LoanPurposePage(page);
        Lpform.loanpurposeform("7894561236");*/
        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");




//        for (Map<String, String> row : data) {
//            new LoanPurposePage(page).loanpurposeform(data.toString());
//        }

//        Assertions.assertTrue(page.locator("text=Thank you").isVisible());
    }
/*
    @AfterAll
    static void teardown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        extent.flush();
    }*/
}
