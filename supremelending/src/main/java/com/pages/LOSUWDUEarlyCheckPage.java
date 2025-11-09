package com.pages;

import com.microsoft.playwright.Page;

public class LOSUWDUEarlyCheckPage extends BaseClass {

    // --- Locators ---
    private final String duEarlyCheckLink = "//span[text()='DU Early Check']";
    private final String runEarlyCheckButton = "//span[text()='Run Early Check']";
    private final String okButton = "//a[@data-selenium-id='ok']";

    /**
     * Constructor for the LOSUWDUEarlyCheckPage.
     * @param page The Playwright Page object.
     */
    public LOSUWDUEarlyCheckPage(Page page) {
        super(page);
    }

    /**
     * Executes the full workflow for running a DU Early Check.
     */
    public void runDuEarlyCheck() {
        // Step: LOSDUEarlyCheck
        click(duEarlyCheckLink);

        // Step: CheckLosDUEarlyCheck & CloseOtherWindow
        // This clicks the button that opens a new window, then calls the BaseClass
        // method to close all new windows and return focus to the main one.
        System.out.println("Running Early Check, which will open a new window...");
        click(runEarlyCheckButton);
        closeAllWindowsExceptMain();
        System.out.println("New window closed and focus returned to main page.");

        // Step: DUEarlyCheckOKbtn
        // After returning, check for and click any confirmation pop-ups.
        if (isElementVisible(okButton)) {
            System.out.println("Confirmation pop-up found. Clicking OK.");
            click(okButton);
        }

        // Step: DUEarlycheckerror
        // Check for a second potential error/confirmation pop-up.
        if (isElementVisible(okButton)) {
            System.out.println("Secondary confirmation pop-up found. Clicking OK.");
            click(okButton);
        }
    }
}