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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TwoBorr_2_sc1Purchase_Portal_BSS {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    static ExtentReports extent;
    static ExtentTest test;
    public static String LoanNumber;


    @BeforeClass
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setArgs(Arrays.asList("--start-maximized")));
        page = browser.newPage();


//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int width = (int) screenSize.getWidth();
//        int height = (int) screenSize.getHeight();
//
//        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
//                .setViewportSize(1920, 1080));

//        // 2. THIS IS THE FIX: Set ViewportSize to null
//// This removes the 1280x720 constraint and fills the gray space
//        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
//                .setViewportSize(null));

        ExtentHtmlReporter reporter = new ExtentHtmlReporter("test-output/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        test = extent.createTest("twoBorrPurchaseLoanTest", "Validating One Borrower Purchase Loan Application");

    }

    @Test
    void
    testFormFill() throws IOException, InterruptedException {
        String loanNumber = null;

        page.navigate("https://bluesage-qa.bluesageusa.com/crm/crm/#/login");
//        page.navigate("https://kind-qa.bluesageusa.com/portal/portal/#/login");

        // List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/SupremeLIONLOPLOSData.xlsx");
        List<Map<String, String>> data = ExcelUtil.readExcelWithTC("src/main/resources/BSSPortalLOSData.xlsx", "BSS_Sc2");
//        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");
        //   page.click("//ion-button[contains(@class, 'login') and contains(@class, 'button')]");

        for (Map<String, String> row : data) {
            new LoginPage(page).login(row.get("Username"), row.get("Password"));
        }

        for (Map<String, String> row : data) {
//
            new PortalImportPage(page).xmlimportfile(row.get("xmlfile"));
        }

        for (Map<String, String> row : data) {
            new LOPNavigationPage(page).lopNavigation();
        }

        //Consent Page Working
        for (Map<String, String> row : data) {
            new LOPConsentPage(page).handleConsentGrid();
        }


        for (Map<String, String> row : data) {
            new LOPCreditPage(page).lopOrderCredit();
        }
        for (Map<String, String> row : data) {
            new LOPLoanSourcePage(page).lopLoanSource();
        }

        for (Map<String, String> row : data) {
            new LOPLoanTermBSSPage(page).lopLoantermPurchase(row.get("LOPAppraisedValue"), row.get("LOPLoanAmt"), row.get("SettlmentDate"));
        }

        for (Map<String, String> row : data) {
            new LOPPricingPage(page).selectRateInRange(95.0, 100.0);
        }
        for (Map<String, String> row : data) {
            new LOPAUSPage(page).lopAUSDU();
        }
        for (Map<String, String> row : data) {
            new LOPComplianceEasePage(page).lopComplianceEase();
        }

        //LOS
        //  page.navigate("https://move-qa.bluesageusa.com/lp/#/login");
        page.navigate("https://move-uat.bluesageusa.com/lp/#/login");

//Working
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSUWSubmittedForAssign"));
        }
//Working
        for (Map<String, String> row : data) {
            new LOSBorrowerRelationshipsPage(page).addSpouseRelationship();
        }
//Working
        for (Map<String, String> row : data) {
            new LOSPropertyInsuranceInfoPage(page).navigateToPropertyInsurance();
            new LOSPropertyInsuranceInfoPage(page).addInsuranceInfo();
        }
//Working
       /* for (Map<String, String> row : data) {
            new LOSLegalDescriptionPage(page).addLegalDescriptionDetails(row.get("LOSLegalDescmannerTitleHeldIn"));
        }*/
        //Not needed
//        for (Map<String, String> row : data) {
//            new LOSLoanTermsPage(page).enterLoanTermsDetails();
//        }
        //Not needed
//        for (Map<String, String> row : data) {
//            new LOSLoanPricingPage(page).priceLoanAndHandleCOC();
//        }
        //Exceptions and Alerts check again for another loan
        //Working
        for (Map<String, String> row : data) {
            new LOSComplEasePage(page).runComplianceEaseWorkflow();
        }
//Working
        for (Map<String, String> row : data) {
            new LOSDocumentPackagePage(page).generateDocumentPackage(row.get("LOSDocPackageType"),row.get("LOSDocPackageDestination"));
        }
        //Working
        for (Map<String, String> row : data) {
            new LOSComplAuditPage(page).performComplianceAudit();
        }
        //Working
        for (Map<String, String> row : data) {
            new LOSBorrConsentPage(page).provideBorrowerConsent();
        }
        //Loan Status - Underwriting - Initial Underwrite
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSUWInitialUnderwrite"));
        }
//            for (Map<String, String> row : data) {
////            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSUWSubmittedForAssign"));
////        }

        for (Map<String, String> row : data) {
            new LOSVendorServicesPage(page).orderVendorService(row.get("serviceType"),row.get("networkProvider"),row.get("serviceProviderText"));
        }
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSUnderwritingSubmitted"));
        }
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSUnderwritingReview"));
        }

//        for (Map<String, String> row : data) {
//            new LOSCreditPage(page).orderCreditReport(row.get("ediProvider"),row.get("creditAgency"));
//        }
        for (Map<String, String> row : data) {
            new LOSChecklistsPage(page).completeAllChecklistItems();
        }
        for (Map<String, String> row : data) {
            new LOSEmploymentVerificationPage(page).verifyAllEmployments();
        }
        for (Map<String, String> row : data) {
            new LOSIncomeVerificationPage(page).verifyAllIncomes();
        }
        for (Map<String, String> row : data) {
            new LOSAssetVerificationPage(page).verifyAllAssets();
        }
        for (Map<String, String> row : data) {
            new LOSLiabilityVerificationPage(page).verifyAllLiabilities();
        }
        //Loan status Processing Approved with Conditions
        for (Map<String, String> row : data) {
            new LOSLoanConditionsPage(page).processLoanConditions();
        }
        for (Map<String, String> row : data) {
            new LOSAUSPage(page).runAutomatedUnderwriting();
        }
        for (Map<String, String> row : data) {
            new LOSUWDecisioningPage(page).makeDecision();
        }
        for (Map<String, String> row : data) {
            new LOSClosFundScheduleLoanForClosingPage(page).scheduleLoanForClosing();
        }
        for (Map<String, String> row : data) {
            new LOSDocumentPackagePage(page).generateDocumentPackage(row.get("LOSDocPackageType"),row.get("LOSDocPackageDestination"));
        }
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSClosingSubmitted"));
        }
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSClosingReview"));
        }
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSClosingDocOut"));
        }
        //Funding
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSFundingReview"));
        }

        //Shipping

        //Post Closing

        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSFundsDisbursed"));
        }
        // Post Closing - Docs Received
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSPostClosingDocsReceived"));
        }
        // Post Closing - In Process
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSPostClosingInProcess"));
        }
        // Post Closing - Shipped
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSPostClosingShipped"));
        }
        // Final Action - Purchased
        for (Map<String, String> row : data) {
            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LSFinalActionPurchased"));
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
