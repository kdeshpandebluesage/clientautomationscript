package com.test;

import com.microsoft.playwright.*;
import java.util.HashMap;
import java.util.Map;

public class UserProfileCreation {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)); // Set to true for CI/CD

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            // 1. Navigate to the URL from your screenshot
            page.navigate("https://lion-bluesage-qa-angel.web.app/apply/create_profile");

            // 2. Fill out the "Create Profile" form
            page.fill("input[placeholder='Enter your first name']", "John");
            page.fill("input[placeholder='Enter your last name']", "Doe");
            page.fill("input[placeholder='Enter Email Address']", "john.doe@example.com");

            // Password meeting the "Requirements" in the screenshot
            String securePassword = "Password123!";
            page.fill("input[placeholder='Enter a password']", securePassword);
            page.fill("input[placeholder='Re-enter password']", securePassword);

            // 3. Click the Create Profile button
            // Using the text from the blue button in your image
            page.click("button:has-text('Create Profile')");

            // Optional: Wait for navigation or success message
            System.out.println("Profile creation submitted successfully.");

            // Keep browser open for a moment to see the result
            page.waitForTimeout(5000);

            browser.close();
        }
    }
}
