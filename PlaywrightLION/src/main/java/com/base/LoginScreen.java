package com.base;

import com.microsoft.playwright.Page;

public class LoginScreen {
    public  void performLogin(Page page, String username, String password) {
        page.fill("input[name='email']", username);
        page.fill("input[name='password']", password);
        page.click("ion-button[id='loginButton']");
    }
}
