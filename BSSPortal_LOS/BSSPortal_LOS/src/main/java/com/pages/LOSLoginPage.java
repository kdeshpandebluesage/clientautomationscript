package com.pages;

import com.microsoft.playwright.options.LoadState;

public class LOSLoginPage extends BaseClass {
    public LOSLoginPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void login(String username, String password) {
        // 1. Wait for the username field to be ready (replaces static sleep)
        page.waitForSelector("input[name='userName']");

        // 2. Fill Username
        // We use the name attribute because it is stable
        page.fill("input[name='userName']", username);

        // 3. Fill Password
        page.fill("input[name='password']", password);

        // 4. Click Login
        // Using the ID containing 'btnLogin' is usually safe in BlueSage
        page.click("#btnLogin");

        System.out.println("Login attempt submitted for user: " + username);

        // 5. Wait for navigation to complete (ensures the next page is loaded)
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}