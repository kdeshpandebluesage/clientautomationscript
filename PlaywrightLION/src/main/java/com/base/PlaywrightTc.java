//package com.base;
//
//
//import com.microsoft.playwright.*;
//import com.pages.ExcelUtil;
//import com.pages.LoanPurposePage;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//public class PlaywrightTc {
//    public static void main(String[] args) throws IOException {
//        // Load Excel data
////        List<Map<String, String>> data = ExcelUtil.readExcel("src/main/resources/testdata.xlsx");
//        String excelPath = "src/main/resources/data.xlsx";
//        FileInputStream file = new FileInputStream(excelPath);
//        Workbook workbook = new XSSFWorkbook(file);
//        Sheet sheet = workbook.getSheetAt(0);
//        Row row = sheet.getRow(1); // Assuming first row is header
//        Map<String,String> row = data.get(0);
//        Map<String,String> row = data.get(0).toString();
//        String username = row.getCell(0).getStringCellValue();
//        String password = row.getCell(1).getStringCellValue();
//        String username = row.get("Username");
//        String password = row.get("Password");
////        String phonenumber = row.getCell(2).getStringCellValue();
//        String phonenumber = row.get("Phonenumber");
////        workbook.close();
//
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//            Page page = browser.newPage();
////            page.navigate("https://prime-dev-lion.bluesageusa.com/apply/login?userid=auto1_los"); // Replace with actual login page
//            page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/login?userid=auto1_los");
//
//            LoginScreen loginScreen = new LoginScreen();
//            loginScreen.performLogin(page, username, password);
//
//            LoanPurposePage loanPurposePage = new LoanPurposePage(page);
//            loanPurposePage.loanpurposeform(phonenumber);
//
////            page.waitForTimeout(3000); // Wait 3 seconds for page to respond
//            System.out.println("Login attempt complete.");
//            page.click("ion-segment[text=' Purchase ']");
//            page.locator("//ion-segment[@name='purchaseOrRefi']").hover();
//            System.out.println("Loan purpose is complete.");
//
//            page.locator("//input[@name='phoneNumber']").fill("7894561236");
//
//        }
//    }
//
//}
