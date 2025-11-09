package com.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Page;
import com.base.BaseTest; // Correct import for BaseTest

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    // ThreadLocal ensures that each test method running in parallel gets its own ExtentTest instance
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // Define the report path and file name
        String reportPath = "target/ExtentReports/Playwright_TestReport_" + timestamp + ".html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath); // Spark reporter for modern HTML report
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Add System information to the report
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chromium"); // Or dynamically get from BaseTest
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        System.out.println("Extent Report will be generated at: " + Paths.get(reportPath).toAbsolutePath().toString());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the report to write all test information
        if (extent != null) {
            extent.flush();
            System.out.println("Extent Report generation finished.");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test entry in the report for each @Test method
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
        System.out.println("--- Starting Test: " + result.getMethod().getMethodName() + " ---");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
        System.out.println("--- Test Passed: " + result.getMethod().getMethodName() + " ---");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed");
        test.get().fail(result.getThrowable()); // Log the exception/error

        // Capture screenshot on failure
        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest) {
            Page page = ((BaseTest) currentClass).page; // Access the Playwright Page object from BaseTest
            if (page != null) {
                try {
                    String screenshotPath = captureScreenshot(page, result.getMethod().getMethodName());
                    // Attach the screenshot to the Extent Report
                    test.get().addScreenCaptureFromPath(screenshotPath, "Screenshot on failure");
                    System.out.println("Screenshot captured for failed test: " + result.getMethod().getMethodName());
                } catch (IOException e) {
                    System.err.println("Failed to capture screenshot for " + result.getMethod().getMethodName() + ": " + e.getMessage());
                }
            }
        }
        System.out.println("--- Test Failed: " + result.getMethod().getMethodName() + " ---");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
        System.out.println("--- Test Skipped: " + result.getMethod().getMethodName() + " ---");
    }

    // Helper method to capture a screenshot
    private String captureScreenshot(Page page, String methodName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = methodName + "_" + timestamp + ".png";
        java.nio.file.Path screenshotDir = Paths.get("target/ExtentReports/screenshots/");
        Files.createDirectories(screenshotDir); // Ensure the screenshots directory exists
        java.nio.file.Path screenshotPath = screenshotDir.resolve(fileName);
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));
        // Return relative path for the report, or absolute if preferred
        return screenshotPath.toAbsolutePath().toString();
    }
}