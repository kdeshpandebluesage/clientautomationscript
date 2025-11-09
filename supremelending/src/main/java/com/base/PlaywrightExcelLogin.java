package com.base;

import com.microsoft.playwright.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class PlaywrightExcelLogin {
    public static void main(String[] args) throws IOException {
        // Load Excel data
        String excelPath = "src/main/resources/data.xlsx";
        FileInputStream file = new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1); // Assuming first row is header
        String username = row.getCell(0).getStringCellValue();
        String password = row.getCell(1).getStringCellValue();
//        String phonenumber = row.getCell(2).getStringCellValue();
        workbook.close();

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
//            page.navigate("https://prime-dev-lion.bluesageusa.com/apply/login?userid=auto1_los"); // Replace with actual login page
            page.navigate("https://lion-bluesage-dev.firebaseapp.com/apply/login?userid=auto1_los");
            page.fill("input[name='email']", username);
            page.fill("input[name='password']", password);
            page.click("ion-button[id='loginButton']");
//            page.waitForTimeout(3000); // Wait 3 seconds for page to respond
            System.out.println("Login attempt complete.");
            page.click("ion-segment[text=' Purchase ']");
            page.locator("//ion-segment[@name='purchaseOrRefi']").hover();
            System.out.println("Loan purpose is complete.");

            page.locator("//input[@name='phoneNumber']").fill("7894561236");

        }
    }
}
