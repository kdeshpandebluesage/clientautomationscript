package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pages.ExcelUtil;
import com.pages.LoginPage;
import com.microsoft.playwright.*;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginTest {
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
    void Login() throws IOException {
        test = extent.createTest("Playwright Data Driven Login Test");

        page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/login?userid=auto1_los");
//        page.navigate("https://movement-bluesage-dev.firebaseapp.com/apply/login?userid=auto1_los");
        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");

        for (Map<String, String> row : data) {
//            new LoginPage(page).login(row.get("Username"), row.get("Password"), Integer.parseInt(row.get("Age")));
            new LoginPage(page).login(row.get("Username"), row.get("Password"));

            test.info("Test executed for: " + row);
        }
    }

  /*  @AfterAll
    static void teardown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        extent.flush();
    }*/
}
