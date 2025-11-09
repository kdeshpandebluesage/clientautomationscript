package com.pages;

public class LoginLOPPage extends BasePage {
    public LoginLOPPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void login(String UsernameLOP, String PasswordLOP) {
        page.fill("input[name='userName-inputEl']", UsernameLOP);
        page.fill("input[name='password-inputEl']", PasswordLOP);
        page.click("span[id='btnLogin-btnIconEl']");
//        page.fill("#age", String.valueOf(age)); // Numeric field
//        page.click("#submit");

        System.out.println("Login attempt complete.");
    }
}
