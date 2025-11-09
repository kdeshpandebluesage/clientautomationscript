package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.pages.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BSSDSPToSup_TwoBorrPurchase_LOS{
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
    void testFormFill() throws IOException, InterruptedException {

        //page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
        // page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/loan_purpose");
        page.navigate("https://sup-uat.bluesageusa.com/lp/#/login");
        String filePath = "src/main/resources/W_2w.txt";

        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/SupremeData.xlsx");
//
        for (Map<String, String> row : data) {
            new LoginPage(page).login(row.get("Username"), row.get("Password"));
        }


//        for (Map<String, String> row : data) {
//            new LOSLoanFoldersPage(page).addLoanFolderDetails(filePath);
//        }


        // --- FIX ADDED HERE ---
        // Add a master wait for a stable element on the main loan page to appear.
        // This ensures the page is fully loaded before any other actions are taken.
        System.out.println("Waiting for main loan page to load...");
        page.locator("//span[text()='Home']").waitFor();
        System.out.println("Main loan page loaded.");
        // --- END OF FIX ---


        //Change Loan Status to "Processing In Process"
//        for (Map<String, String> row : data) {
//            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LOSProcessingInProcess"));
//        }


        // //Borr/Property - Realtionships
        for (Map<String, String> row : data) {
//
//            new LOSBorrowerRelationshipsPage(page).addSpouseRelationship();
//
//            //Borr/Property - Property Insurance Information
//            LOSPropertyInsuranceInfoPage insurancePage = new LOSPropertyInsuranceInfoPage(page);
//            insurancePage.navigateToPropertyInsurance();
//            //insurancePage.addInsuranceInfo();
//
//            //Borr/Property
//            // Legal Description Page
//            LOSLegalDescriptionPage legaldescpage = new LOSLegalDescriptionPage(page);
//            legaldescpage.addLegalDescriptionDetails();
//
//            //Product/Pricing/Fees
//            // Loan Terms Page
//            LOSLoanTermsPage loantermspage = new LOSLoanTermsPage(page);
//            loantermspage.enterLoanTermsDetails();
//
//            //Product/Pricing/Fees
//            // MI
//
//            //Product/Pricing/Fees
//            //Credit Review Page

            //Product/Pricing/Fees
            // Loan Pricing
//            LOSLoanPricingPage loanpricingpage = new LOSLoanPricingPage(page);
//            loanpricingpage.priceLoanAndHandleCOC();


            //Home - Compliance Ease
//            LOSComplEasePage compleasepage = new LOSComplEasePage(page);
//            compleasepage.runComplianceEaseWorkflow();

            //Clear Exceptions
//            BaseClass baseclass = new BaseClass(page);
//            baseclass.clearAllExceptions();

            //Clear Alerts
//            BaseClass baseclass = new BaseClass(page);
//            baseclass.clearAllAlerts();

            // complianceMethodOverride - Done in Compl Page

            //Home - Doc Packg - Initial Disclosure - Program Error
            LOSDocumentPackagePage DocPkg = new LOSDocumentPackagePage(page);
            //DocPkg.generateDocumentPackage("Initial Disclosure","esign");

            //Home - Compliance Audit - Since there is no Doc Pkg sent no disclosure dates
            //  LOSComplAuditPage complaudit = new LOSComplAuditPage(page);
            //  complaudit.performComplianceAudit();

            //Borr/Prop - Borrower Consent - No need since it is done in LOP and not active in LOS
//            LOSBorrConsentPage borrConsent = new LOSBorrConsentPage(page);
//            borrConsent.provideBorrowerConsent();

            //Home - Vendor Services
            LOSVendorServicesPage vendorserv = new LOSVendorServicesPage(page);
            //  vendorserv.orderVendorService("Verification of Asset","Manual","BankVOD");
          //  vendorserv.orderVendorService("Verification of Employment", "Manual", "Advantage Credit");
            vendorserv.orderVendorService("Verification of Income", "Manual", "Data Verify");
            //   vendorserv.orderVendorService("Flood Certification","CoreLogic","Corelogic Flood Services");
            //   vendorserv.orderVendorService("Title Insurance","Manual","Freeform");
            //    vendorserv.orderVendorService("Property Appraisals","Manual","Freeform");
            //   vendorserv.orderVendorService("Closing Service","Manual","Freeform");
            //   vendorserv.orderVendorService("Signing Appointment","Manual","Freeform");

        }
        //Clear Actions - compliancemethodoverride
        //Clear Exceptions

        //Home - Change Loan Status to "Underwriting Submitted"
//        for (Map<String, String> row : data) {
//            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LOSUnderwritingSubmitted"));
//        }
        //Home - Change Loan Status to "Underwriting In Review"
//        for (Map<String, String> row : data) {
//            new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LOSUnderwritingInReview"));
//        }

//        for (Map<String, String> row : data) {
//            //Borr/Prop - Order Credit Report
//            LOSCreditPage creditPage = new LOSCreditPage(page);
//            //creditPage.orderCreditReport(row.get("CreditEdiProvider"), row.get("CreditAgency"));
//            creditPage.orderCreditReport("Fannie Mae", "Fannie Mae Test Credit Agency");
//
//            //Home - Checklists page
//            LOSChecklistsPage checklistsPage = new LOSChecklistsPage(page);
//            checklistsPage.completeAllChecklistItems();
//
//            //Underwriting - Employment Verification
//            LOSEmploymentVerificationPage emplVerificationPage = new LOSEmploymentVerificationPage(page);
//            emplVerificationPage.verifyAllEmployments();
//
//            //Underwriting - Income Verification
//            LOSIncomeVerificationPage incomelVerificationPage = new LOSIncomeVerificationPage(page);
//            incomelVerificationPage.verifyAllIncomes();
//
//            //Underwriting - Asset Verification
//            LOSAssetVerificationPage assetVerificationPage = new LOSAssetVerificationPage(page);
//            assetVerificationPage.verifyAllAssets();
//
//            //Underwriting - Liability Verification
//            LOSLiabilityVerificationPage liabilityVerificationPage = new LOSLiabilityVerificationPage(page);
//            liabilityVerificationPage.verifyAllLiabilities();
//
//            //Home - Loan Conditions
//            LOSLoanConditionsPage conditionsPage = new LOSLoanConditionsPage(page);
//            conditionsPage.processLoanConditions();
//
//            //Home - Automated Underwriting
//            LOSAUSPage AUSPage = new LOSAUSPage(page);
//            AUSPage.runAutomatedUnderwriting();
//
//            //Underwriting - DU Early Check
//            LOSUWDUEarlyCheckPage losuwduEarlyCheckPage = new LOSUWDUEarlyCheckPage(page);
//            losuwduEarlyCheckPage.runDuEarlyCheck();
//
//            //Clear Actions - compliancemethodoverride
//
//            //Clear Exceptions
//
//            //Underwriting - Decisioning
//            LOSUWDecisioningPage losuwDecisioningPage = new LOSUWDecisioningPage(page);
//            losuwDecisioningPage.makeDecision();
//
//            //Home - Check Loan Status - Approved Clear to Close
//            LOSLoanStatusPage losLoanStatusPage = new LOSLoanStatusPage(page);
//            losLoanStatusPage.verifyLoanStatusIs("Approved Clear to Close");
//
//            //Clear Exceptions
//
//
//            //Closing/Funding - Schedule Loan For Closing
//            LOSClosFundScheduleLoanForClosingPage losClosFundScheduleLoanForClosingPage = new LOSClosFundScheduleLoanForClosingPage(page);
//            losClosFundScheduleLoanForClosingPage.scheduleLoanForClosing();
//
//            //Clear Actions - compliancemethodoverride
//
//            //Home - Document Package - Closing Docs
//            LOSDocumentPackagePage closingDocPkg = new LOSDocumentPackagePage(page);
//            closingDocPkg.generateDocumentPackage("Closing", "esign");
//
//            //Clear Actions - compliancemethodoverride
//            //Clear Exceptions
//        }
//
//            //Home - Change Loan Status to "Closing - Submitted"
//            for (Map<String, String> row : data) {
//                new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LOSClosingSubmitted"));
//            }
//            //Home - Change Loan Status to "Closing - In Process"
//            for (Map<String, String> row : data) {
//                new LOSLoanStatusPage(page).addLoanStatusDetails(row.get("LOSClosingInProcess"));
//            }
        }
   @AfterClass
    static void teardown() {
 //       if (page != null) page.close();
  //      if (browser != null) browser.close();
   //     if (playwright != null) playwright.close();
  //      extent.flush();
    }
}
