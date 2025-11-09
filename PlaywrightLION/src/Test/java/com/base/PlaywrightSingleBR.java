//package com.base;
//
//import com.microsoft.playwright.Browser;
//import com.microsoft.playwright.BrowserType;
//import com.microsoft.playwright.Page;
//import com.microsoft.playwright.Playwright;
//import com.pages.*;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//public class PlaywrightSingleBR {
//    @Test
//    public static void main(String[] args) throws IOException {
//        // Load Excel data
//        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");
////        String excelPath = "src/main/resources/data.xlsx";
////        FileInputStream file = new FileInputStream(excelPath);
////        Workbook workbook = new XSSFWorkbook(file);
////        Sheet sheet = workbook.getSheetAt(0);
////        Row row = sheet.getRow(1); // Assuming first row is header
//        Map<String,String> row = data.get(0);
////        Map<String,String> row = data.get(0).toString();
////        String username = row.getCell(0).getStringCellValue();
////        String password = row.getCell(1).getStringCellValue();
//          String username = row.get("Username");
//          String password = row.get("Password");
////        String phonenumber = row.getCell(2).getStringCellValue();
//         String phonenumber = row.get("Rphonenumber");
//         String Rfirstname = row.get("RFirstName");
//        String RLastName = row.get("RLastName");
//        String RCompanyName = row.get("RCompanyName");
//        String Remail = row.get("REmail");
//        String RPhonenumber = row.get("Rphonenumber");
//        String Paddress = row.get("Paddress");
//        String Pcity = row.get("Pcity");
//        String Pstate = row.get("Pstate");
//        String Pzipcode = row.get("Pzipcode" );
//        String Pdownpayment = row.get("Pdownpayment");
//        String PpurchasePrice = row.get("PpurchasePrice" );
//        String PpropertyType = row.get("PpropertyType" );
//        String AppFirstBrName = row.get("AppFirstBrName");
//        String AppLastBrName = row.get("AppLastBrName" );
//        String AppFirstBRBOD = row.get("AppFirstBRBOD" );
//        String AppInoFirstBrAddressline = row.get("AppInoFirstBrAddressline");
//        String AppInfoFirtBRCityName = row.get("AppInfoFirtBRCityName");
//        String AppInfoFirstBrStateName = row.get("AppInfoFirstBrStateName");
//        String AppInfoFirstBrZipcode = row.get("AppInfoFirstBrZipcode");
//        String AppInfoFirstBRRent = row.get("AppInfoFirstBRRent");
//        String AppInoFirstBrzipcode = row.get("AppInoFirstBrzipcode");
//        // Add lines to read secondary applicant info
//        String AppSecondBrName = row.get("AppSecondBrName");
//        String AppSecondLastBrName = row.get("AppSecondLastBrName");
//        String AppSecondBRBOD = row.get("AppSecondBRBOD");
//
//        // Read Employee Income data
//        String onlineID = row.get("OnlineID"); // Assuming column name is "OnlineID" in Excel
//        String passcode = row.get("Passcode"); // Assuming column name is "Passcode" in Excel
//        String EmplName = row.get("EmplName"); // Assuming column name is "EmplName" in Excel
//        String EmpPhoneNumber = row.get("EmpPhoneNumber"); // Assuming column name is "EmpPhoneNumber" in Excel
//        String EmpExpYears = row.get("EmpExpYears"); // Assuming column name is "EmpExpYears" in Excel
//        String EmpMonthYear = row.get("EmpMonthYear"); // Assuming column name is "EmpMonthYear" in Excel
//        String baseSalary = row.get("BaseSalary"); // Assuming column name is "BaseSalary" in Excel
//        String overtime = row.get("Overtime"); // Assuming column name is "Overtime" in Excel
//        String bonus = row.get("Bonus"); // Assuming column name is "Bonus" in Excel
//        String commissions = row.get("Commissions"); // Assuming column name is "Commissions" in Excel
//
//
////        workbook.close();
//
//
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//            Page page = browser.newPage();
////            page.navigate("https://prime-dev-lion.bluesageusa.com/apply/login?userid=auto1_los"); // Replace with actual login page
////            page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/login?userid=auto1_los");
//             page.navigate("https://move-dev-lion.bluesageusa.com/apply/login");
//
//            LoginScreen loginScreen = new LoginScreen();
//            loginScreen.performLogin(page, username, password);
//
//            LoanPurposePage loanPurposePage = new LoanPurposePage(page);
//            loanPurposePage.loanpurposeform(phonenumber);
//
//
//
//            OtherInfoPage otherInfoPage =new OtherInfoPage(page);
//            otherInfoPage.OtherInfoform(RCompanyName, Rfirstname, RLastName, Remail, RPhonenumber);
//
//            PurchaseLoanPage purchaseLoanPage = new PurchaseLoanPage(page);
//            purchaseLoanPage.purchaseInfoform(Pzipcode,PpropertyType,PpurchasePrice,Pdownpayment);
//
//            ApplicationInfoPage applicationInfoPage = new ApplicationInfoPage(page);
//            applicationInfoPage.applicationInformation(AppFirstBrName,AppLastBrName,AppFirstBRBOD,AppInoFirstBrAddressline,AppInfoFirtBRCityName,AppInfoFirstBrStateName,AppInfoFirstBRRent,AppInoFirstBrzipcode,AppInfoFirstBrZipcode);
////
////            ApplicationInfoSecondaryPage secondaryPage = new ApplicationInfoSecondaryPage(page);
////            secondaryPage.applicationSecondaryInformation(AppSecondBrName,AppSecondLastBrName,AppSecondBRBOD,AppInoFirstBrAddressline,AppInfoFirtBRCityName,AppInfoFirstBrStateName,AppInfoFirstBRRent,AppInoFirstBrzipcode);
//
//
//          EmplIncomePage empIncomePage = new EmplIncomePage(page);
//          empIncomePage .EmplIncomeform(onlineID,passcode,EmplName,EmpPhoneNumber,EmpExpYears,EmpMonthYear,baseSalary,overtime,bonus,commissions);
//
//            page.waitForTimeout(3000); // Wait 3 seconds for page to respond
//
////            page.click("ion-segment[text=' Purchase ']");
////            page.locator("//ion-segment[@name='purchaseOrRefi']").hover();
////            System.out.println("Loan purpose is complete.");
//
////            page.locator("//input[@name='phoneNumber']").fill("7894561236");
//
////            System.out.println("Login attempt complete.");
//        }
//    }
//
//
//}
