package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoanUtilsSUP {
    public static String getLoanNumber(String s, Page page) {
        System.out.println("Attempting to capture the loan number...");
        Locator loanNumberParagraph = page.locator("p:has-text('Reference Number:')");;
        // Within the paragraph we found, we now look for the <b> (bold) tag
        // which contains the actual number.
        Locator loanNumberElement = loanNumberParagraph.locator("b");

        // We get the text content from the <b> element.
        String loanNumber = loanNumberElement.textContent();

//        // It's a good practice to verify that we captured something.
//        Assert.assertNotNull(loanNumber, "Loan number element was found, but it was empty.");
//        Assert.assertFalse(loanNumber.trim().isEmpty(), "Captured loan number is empty or just whitespace.");

        System.out.println("Successfully captured Loan Number: " + loanNumber);

        return loanNumber;
    }
}
