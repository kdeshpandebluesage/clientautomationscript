package com.pages;

public class LoginVerificationPage extends BaseClass {
    public LoginVerificationPage(com.microsoft.playwright.Page page) {
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

    public void verification(String phonenumbercode) {

//        String  loannumber = "3000000588";
        page.waitForTimeout(6000);

        clearAndFillText("input[name='code']", phonenumbercode);

        System.out.println("verification of code is done.");
    }
}
