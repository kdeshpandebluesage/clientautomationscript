package com.pages;

public class LoginPage extends BaseClass {
    public LoginPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void login(String username, String password) {

//        String  loannumber = "3000000588";
        page.waitForTimeout(6000);
      //  clearAndFillText("input[name='userName']", username);
        clearAndFillText("input[name='email']", username);
      //  clearAndFillText("input[name='password']", password);
        clearAndFillText("input[name='password']", password);
       // click("ion-button[id='loginButton']");
        click("ion-button[id='loginButton']");
        //click("#btnLogin");
        System.out.println("LION Login attempt complete.");
    }
}
//        click("#btnLoans");
//        page.waitForTimeout(6000);
//        clearAndFillText("input[name='searchLoans-inputEl']", loannumber);
//        page.keyboard().press("Enter");
//        page.waitForTimeout(6000);
//        System.out.println("Searched for loan number: " + loannumber);