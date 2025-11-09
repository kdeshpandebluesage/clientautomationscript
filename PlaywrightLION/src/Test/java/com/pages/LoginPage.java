package com.pages;

public class LoginPage extends BasePage {
    public LoginPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void login(String username, String password) {
        page.fill("input[name='email']", username);
        page.fill("input[name='password']", password);
        page.click("ion-button[id='loginButton']");
//        page.fill("#age", String.valueOf(age)); // Numeric field
//        page.click("#submit");

        System.out.println("Login attempt complete.");
    }
}
