package com.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class LOSDocumentPackagePage extends BaseClass {

    // --- Locators ---
    private final String documentPackageLink = "//span[text()='Document Package']";
    private final String retrievePackageListButton = "//span[text()='Retrieve Package List']";
    private final String documentTypeDropdown = "(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[2]";
    private final String processingRequestMask = "//div[text()='Processing Request ...']";
    private final String generatingDocumentsMask = "//div[text()='Generating documents ...']";
    private final String selectAllCheckbox = "//div[contains(@class,'x-column-header-inner-empty')]";
    private final String documentDestinationDropdown = "(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[3]";
    private final String sendButton = "//span[text()='Send']";
    private final String docResultWindowCloseButton = "//div[@data-selenium-id='docResultWindow']//img[contains(@class,'x-tool-close')]";
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

    /**
     * Constructor for the LOSDocumentPackagePage.
     * @param page The Playwright Page object.
     */
    public LOSDocumentPackagePage(Page page) {
        super(page);
    }

    // --- Methods ---

    /**
     * Executes the full workflow for generating a document package.
     * @param packageType The type of package to generate (e.g., "Initial Disclosure").
     * @param destination The destination for the package (e.g., "E-Sign").
     */
    public void generateDocumentPackage(String packageType, String destination) {
        click(homeLink);
        click(documentPackageLink);
        page.locator(retrievePackageListButton).waitFor();

        selectDropdownByText(documentTypeDropdown, packageType);
        click(retrievePackageListButton);
        waitForLoadingMaskToDisappear(processingRequestMask);

        handleUnexpectedPopups();
        handleAdhocDetailsIfNeeded();

        click(selectAllCheckbox);
        selectDropdownByText(documentDestinationDropdown, destination);

        click(sendButton);
        waitForLoadingMaskToDisappear(generatingDocumentsMask);

        handleUnexpectedPopups();

        System.out.println("✅ Successfully completed '" + packageType + "' Document Package workflow.");
    }

    /**
     * Executes the specific workflow for generating the "Closing" document package.
     * This is a convenience method that calls the generic generateDocumentPackage method
     * with predefined parameters for closing documents.
     */
    public void generateClosingDocumentPackage() {
        System.out.println("Generating the 'Closing' document package...");

        // This reuses the existing logic with specific parameters for the closing package.
        // We assume the package type is "Closing" and destination is "Print to PDF".
        generateDocumentPackage("Closing", "Print to PDF");
    }

    /**
     * Checks for and handles the "Adhoc Data" pop-up if it appears.
     */
    private void handleAdhocDetailsIfNeeded() {
        if (isElementVisible(displayAdhocButton)) {
            System.out.println("Adhoc Details button found, processing...");
            click(displayAdhocButton);
            page.locator(adhocWindow).waitFor();

            if (isElementVisible(ackOfCashDropdown)) {
                selectDropdownByText(ackOfCashDropdown, "Yes");
                selectDropdownByText(oweltyOfPartitionDropdown, "Yes");
                selectDropdownByText(texasPurchaseMoneyDropdown, "Yes");
            }

            click(adhocSaveButton);
            waitForLoadingMaskToDisappear(adhocSavingMask);
            click(adhocCloseButton);
        }
    }

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
}







//package com.pages;
//
//import com.microsoft.playwright.Page;
//import com.microsoft.playwright.Locator;
//
//public class LOSDocumentPackagePage extends BaseClass {
//
//    // --- Locators ---
//    private final String homeLink = "//span[text()='Home']";
//    private final String documentPackageLink = "//span[text()='Document Package']";
//    private final String retrievePackageListButton = "//span[text()='Retrieve Package List']";
//    private final String documentTypeDropdown = "(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[2]";
//    private final String processingRequestMask = "//div[text()='Processing Request ...']";
//    private final String generatingDocumentsMask = "//div[text()='Generating documents ...']";
//    private final String selectAllCheckbox = "//div[contains(@class,'x-column-header-inner-empty')]";
//    private final String documentDestinationDropdown = "(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[3]";
//    private final String sendButton = "//span[text()='Send']";
//    private final String docResultWindowCloseButton = "//div[@data-selenium-id='docResultWindow']//img[contains(@class,'x-tool-close')]";
//    private final String unexpectedPopupOkButton = "//div[@class='x-css-shadow'][contains(@style,'display: block')]//a[@data-selenium-id='ok']";
//
//    // Adhoc Details Locators
//    private final String displayAdhocButton = "//a[@data-selenium-id='allDocsAdocScreen']";
//    private final String adhocWindow = "//span[text()='Adhoc Data for Generated Documents']";
//    private final String ackOfCashDropdown = "//input[contains(@name,'AckOfCashAdvanced')]/../following-sibling::td/div";
//    private final String oweltyOfPartitionDropdown = "//input[contains(@name,'OweltyOfPartition')]/../following-sibling::td/div";
//    private final String texasPurchaseMoneyDropdown = "//input[contains(@name,'TexasPurchaseMoney')]/../following-sibling::td/div";
//    private final String adhocSaveButton = "//a[@data-selenium-id='btnSave']";
//    private final String adhocSavingMask = "//div[text()='Saving...']";
//    private final String adhocCloseButton = "//div[contains(@id,'adhocdialog')]//img";
//
//    /**
//     * Constructor for the LOSDocumentPackagePage.
//     * @param page The Playwright Page object.
//     */
//    public LOSDocumentPackagePage(Page page) {
//        super(page);
//    }
//
//    // --- Methods ---
//
//    /**
//     * Executes the full workflow for generating a document package.
//     * @param packageType The type of package to generate (e.g., "Initial Disclosure").
//     * @param destination The destination for the package (e.g., "E-Sign").
//     */
//    public void generateDocumentPackage(String packageType, String destination) {
//        // Step: DocumentPackage & CheckDocumentPackageScree
//        click(documentPackageLink);
//        page.locator(retrievePackageListButton).waitFor();
//
//        // Step: DocumentPackageType
//        selectDropdownByText(documentTypeDropdown, packageType);
//
//        // Step: RetrievePackageList & CheckRetrievePackageList
//        click(retrievePackageListButton);
//        waitForLoadingMaskToDisappear(processingRequestMask);
//
//        // This step handles unexpected popups that might appear after retrieving the list
//        handleUnexpectedPopups();
//
//        // This handles the conditional Adhoc Details sub-workflow
//        handleAdhocDetailsIfNeeded();
//
//        // Step: DocumentPackage SelecRetrie
//        click(selectAllCheckbox);
//
//        // Step: DocumentPackageDocDestina
//        selectDropdownByText(documentDestinationDropdown, destination);
//
//        // Step: DocumentPackage PrintDocu & DocumentPackage WaitForDo
//        click(sendButton);
//        waitForLoadingMaskToDisappear(generatingDocumentsMask);
//
//        // Steps: Docpackageunexppopup, checkDocumentPackagePopU, DocumentPackage CloseDocu
//        handleUnexpectedPopups();
//
//        // Step: LOS Document Package Page
//        System.out.println("✅ Successfully completed Document Package workflow. Now on page: " + getText(documentPackageLink));
//    }
//
//    /**
//     * Checks for and handles the "Adhoc Data" pop-up if it appears.
//     */
//    private void handleAdhocDetailsIfNeeded() {
//        if (isElementVisible(displayAdhocButton)) {
//            System.out.println("Adhoc Details button found, processing...");
//            click(displayAdhocButton);
//            page.locator(adhocWindow).waitFor();
//
//            // Check if the specific Texas fields are present before trying to fill them
//            if (isElementVisible(ackOfCashDropdown)) {
//                selectDropdownByText(ackOfCashDropdown, "Yes");
//                selectDropdownByText(oweltyOfPartitionDropdown, "Yes");
//                selectDropdownByText(texasPurchaseMoneyDropdown, "Yes");
//            }
//
//            click(adhocSaveButton);
//            waitForLoadingMaskToDisappear(adhocSavingMask);
//            click(adhocCloseButton);
//        }
//    }
//
//    /**
//     * A general handler to close various pop-ups that can appear during the workflow.
//     */
//    private void handleUnexpectedPopups() {
//        // Handles the generic "OK" pop-up
//        if (isElementVisible(unexpectedPopupOkButton)) {
//            click(unexpectedPopupOkButton);
//        }
//        // Handles the final "Document Result" window
//        if (isElementVisible(docResultWindowCloseButton)) {
//            click(docResultWindowCloseButton);
//        }
//    }
//    /**
//     * Executes the specific workflow for generating the "Closing" document package.
//     * This is a convenience method that calls the generic generateDocumentPackage method
//     * with predefined parameters for closing documents.
//     */
//    public void generateClosingDocumentPackage() {
//        System.out.println("Generating the 'Closing' document package...");
//
//        // This reuses the existing logic with specific parameters for the closing package.
//        // We assume the package type is "Closing" and destination is "Print to PDF".
//        generateDocumentPackage("Closing", "Print to PDF");
//    }
//}