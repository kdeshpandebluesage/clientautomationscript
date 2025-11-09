package com.base;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static SelfHealingDriver driver;

    @BeforeAll
    public static void setUp() {
        // Clear driver cache and setup matching driver version
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().browserVersion("135.0.7049.85").clearDriverCache().setup();


        ChromeOptions options = new ChromeOptions();
        WebDriver raw = new ChromeDriver(options);

        driver = SelfHealingDriver.create(raw);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
