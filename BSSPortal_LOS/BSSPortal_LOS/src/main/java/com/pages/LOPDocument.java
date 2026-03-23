package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LOPDocument extends BaseClass {
    // --- Locators ---
    private final String applicationLink ="//span[text()='Application']";
    private final String loanStatusLink = "//span[text()='Loan Status']";
    private final String documentsLink = "//span[text()='Documents']";
    private final String packages = "//span[text()='Packages']";
    private final String retrievePackageListButton = "//span[text()='Retrieve Package List']";
    private final String docPackageDropdown = "(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[1]";
    private final String docDestinationDropdown = "(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[2]";
    private final String docPackageType = "//li[text()='Initial Disclosure (Sales)']";
    private final String proceedToBypass = "//span[text()='Proceed to Bypass']";
    private final String newMileStoneStatusDropdown = "//input[@name='newStatus']/../following-sibling::td/div";
    private final String loanStatusSaveButton = "//span[text()='SAVE']";
    private final String processingRequestMask = "//div[text()='Processing Request ...']";
    private final String generatingDocumentsMask = "//div[text()='Generating documents ...']";
    private final String selectAllCheckbox = "//div[contains(@class,'x-column-header-inner-empty')]";
    private final String documentDestinationDropdown = "(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[3]";
    private final String sendButton = "//span[text()='Send']";
    private final String docResultWindowCloseButton = "//div[@data-selenium-id='docResultWindow']//img[contains(@class,'x-tool-close')]";
    //(//img[contains(@class,'x-tool-img x-tool-close')])[2]
    private final String unexpectedPopupOkButton = "//div[@class='x-css-shadow'][contains(@style,'display: block')]//a[@data-selenium-id='ok']";
    private final String homeLink = "//span[text()='Home']";

    // Adhoc Details Locators
    private final String displayAdhocButton = "//a[@data-selenium-id='allDocsAdocScreen']";
    private final String adhocWindow = "//span[text()='Adhoc Data for Generated Documents']";
    private final String ackOfCashDropdown = "//input[contains(@name,'AckOfCashAdvanced')]/../following-sibling::td/div";
    private final String oweltyOfPartitionDropdown = "//input[contains(@name,'OweltyOfPartition')]/../following-sibling::td/div";
    private final String texasPurchaseMoneyDropdown = "//input[contains(@name,'TexasPurchaseMoney')]/../following-sibling::td/div";
    private final String adhocSaveButton = "//a[@data-selenium-id='btnSave']";
    private final String adhocSavingMask = "//div[text()='Saving...']";
    private final String adhocCloseButton = "//div[contains(@id,'adhocdialog')]//img";

    public LOPDocument(com.microsoft.playwright.Page page) {
        super(page);
    }

    /**
     * Executes the full workflow for generating a document package.
     * @param packageType The type of package to generate (e.g., "Initial Disclosure").
     * @param destination The destination for the package (e.g., "E-Sign").
     */
    public void generateDocumentPackage(String packageType, String destination, String ApplStatus) {
        //Documents
        click(documentsLink);
        click(packages);

        page.click(docPackageDropdown);
        page.locator(".x-boundlist-item >> text=\"" + packageType + "\"").click();

        // page.click(docPackageDropdown);
        // selectAngularDropdownOption(page, docPackageDropdown, docPackageType);
        page.locator(retrievePackageListButton).waitFor();
        click(retrievePackageListButton);
//        page.waitForTimeout(2000);
//        click(proceedToBypass);

        //Doc Destination - All checkboxes are default selected
        page.click(docDestinationDropdown);
        page.locator(".x-boundlist-item >> text=\"" + destination + "\"").click();

        //Adhoc Details
        handleAdhocDetailsIfNeeded();



        System.out.println("✅ Successfully completed '" + packageType + "' Document Package workflow.");

        //Update Loan Status
        page.reload();
        click(applicationLink);
        page.waitForTimeout(2000);
        click(loanStatusLink);
        page.waitForTimeout(4000);

        //New MileStone/Status
        //Status
        String newStatusdropdownToggleLocator = "//input[@name='newStatus']/../following-sibling::td/div";
        page.click(newStatusdropdownToggleLocator);
        page.locator(".x-boundlist-item >> text=\"" + ApplStatus + "\"").click();

        System.out.println("✅ LOP Loan Status -- '" + ApplStatus);
        click(loanStatusSaveButton);
        page.waitForTimeout(2000);
        //Logout LOP
        page.locator("a[data-selenium-id='btnUser']").click();
        page.locator("//span[text()='Log Out']").click();
    }
    /**
     * Checks for and handles the "Adhoc Data" pop-up if it appears.
     */
    private void handleAdhocDetailsIfNeeded() {
        if (isElementVisible(displayAdhocButton)) {
            System.out.println("Adhoc Details button found, processing...");
            click(displayAdhocButton);
            click(adhocSaveButton);
            waitForLoadingMaskToDisappear(adhocSavingMask);
            click(sendButton);
            page.waitForTimeout(20000);
            click(docResultWindowCloseButton);
            System.out.println("Doc Pkg Initial Disclosure Sent");

        }
    }
    /**
     * Executes the specific workflow for generating the "Closing" document package.
     * This is a convenience method that calls the generic generateDocumentPackage method
     * with predefined parameters for closing documents.
     */
//    public void generateClosingDocumentPackage() {
//        System.out.println("Generating the 'Closing' document package...");
//
//        // This reuses the existing logic with specific parameters for the closing package.
//        // We assume the package type is "Closing" and destination is "Print to PDF".
//        generateDocumentPackage("Closing", "Print to PDF");
//    }


    /**
     * A general handler to close various pop-ups that can appear during the workflow.
     */
    private void handleUnexpectedPopups() {
        // Added a short wait to allow pop-ups to render before checking for them.
        page.waitForTimeout(1000);
        if (isElementVisible(unexpectedPopupOkButton)) {
            click(unexpectedPopupOkButton);
        }
        if (isElementVisible(docResultWindowCloseButton)) {
            click(docResultWindowCloseButton);
        }
    }

//    public void lopInitial() {
//        page.click("//span[text()='Documents']");
//        page.locator("//span[text()='Packages']").waitFor(new Locator.WaitForOptions()
//                .setState(WaitForSelectorState.VISIBLE));
//        page.click("//span[text()='Packages']");
//        String DocumentTypeLocator = "(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[1]";
//        String hoverTargetSelector = "//li[text()='Initial Disclosure (Sales)']";
//        page.click(DocumentTypeLocator);
//        selectAngularDropdownOption(page, DocumentTypeLocator, hoverTargetSelector);
//        page.click("//span[text()='Submit Order']");
//        System.out.println("Doc Pkg Initial Disclosure Sent");
//    }
}
