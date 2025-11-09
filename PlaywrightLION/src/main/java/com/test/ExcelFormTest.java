package com.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import com.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Apache POI imports for Excel
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.util.Map;

import static com.base.ExcelReader.readExcel;

public class ExcelFormTest extends BaseTest {

    // Define how Excel columns map to Selenium locators
    private static final Map<String, By> FIELD_LOCATORS = Map.of(
            "First Name", By.id("firstName"),
            "Last Name",  By.id("lastName"),
            "Email",      By.name("email"),
            "DOB",        By.xpath("//input[@type='date']")
            // …add as needed
    );

    @Test
    void populateFormFromExcel() throws IOException {
        String url = "https://prime-dev-lion.bluesageusa.com/apply/login?userid=auto1_los";
        driver.get(url);

        List<Map<String,String>> records = readExcel("testdata.xlsx");
        for (Map<String,String> record : records) {
            // For each field in the row
            for (var entry : record.entrySet()) {
                String header = entry.getKey();
                String value  = entry.getValue();
                By locator    = FIELD_LOCATORS.get(header);
//AI selfhealing driver //
                WebElement input = driver.findElement(locator);
                input.clear();
                input.sendKeys(value);
            }
            // submit and reset for next row
            driver.findElement(By.cssSelector("button[type=submit]")).click();
            driver.navigate().refresh();
        }
    }
}
