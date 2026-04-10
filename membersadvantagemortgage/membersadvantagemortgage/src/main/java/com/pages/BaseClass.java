package com.pages;

import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// Assuming these are your existing Excel utilities, they can be used as-is.
// import com.wtc.excelutil.XlsxExcelReaderUtil;
// import com.wtc.excelutil.XlsxMultiAppReaderUtil;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseClass {

    // --- Playwright Setup ---
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    protected Page page;
    public List<WebElement> listElems;

    // --- Framework Data Members (from original classes) ---
    public static Map<String, String> metaDataMap = new LinkedHashMap<>();
    public static LinkedHashMap<String, String> fieldDataValueMap = new LinkedHashMap<>();
    public static List<HashMap<String, String>> dataList = new ArrayList<>(); // To be populated by your Excel reader
    private static String metaDataKey;
    private final Random rand = new Random();

    // --- Test Configuration ---
    // These would be set by your test runner or a properties file
    private final String URL1 = "http://your-crm-url.com";
    private final String URL2 = "http://your-lending-platform-url.com";

    //================================================================================
    // SECTION 1: LOCATORS MOVED FROM MiscellaneousPage
    //================================================================================

    // --- Miscellaneous Page Locators ---
    private final String administrationLink = "//a[@data-selenium-id='btnAdministration']";
    private final String menuOverrideLoanReqAction = "//div[@data-selenium-id='menuOverrideLoanReqAction']";

    // Action Items
    private final String actionsButton = "//a[@data-selenium-id='actions']";
    private final String checkActionsPopUp = "//div[contains(@id,'loanrequiredactionsdialog')]";
    private final String macocAction = "//div[@data-selenium-id='requiredLoanActionsPanel']//div[text()='MANCOC']";
    private final String auRequiredAction = "//div[@data-selenium-id='requiredLoanActionsPanel']//div[text()='AUS Required']";
    private final String pricingRequiredAction = "//div[@data-selenium-id='requiredLoanActionsPanel']//div[text()='Pricing Required']";
    private final String governmentLoanTermAction = "//div[@data-selenium-id='requiredLoanActionsPanel']//div[text()='Government Loan Terms Required']";
    private final String complianceRequiredAction = "//div[@data-selenium-id='requiredLoanActionsPanel']//div[text()='Compliance Required']";
    private final String miQuoteRequiredAction = "//div[@data-selenium-id='requiredLoanActionsPanel']//div[text()='MI Quote Required']";
    private final String closeActionWindowButton = "//div[contains(@id,'loanrequiredactionsdialog')]//img[@class='x-tool-img x-tool-close']";

    // Change of Circumstance (COC)
    private final String cocHistoryButton = "//a[@data-qtip='Change of Circumstance History']";
    private final String addCocButton = "//a[@data-selenium-id='btnAdd']";
    private final String cocReasonDropdown = "//div[contains(@id,'changecircumstanceaddeditdialog')]//input[@name='changeReasonTypeId']";
    private final String cocReasonCheckbox1 = "(//div[@class='x-grid-row-checker'])[1]";
    private final String cocReasonCheckbox2 = "(//div[@class='x-grid-row-checker'])[2]";
    private final String saveCocButton = "//div[contains(@id,'changecircumstanceaddeditdialog')]//a[@data-selenium-id='saveButton']";
    private final String cancelCocButton = "//div[contains(@id,'changecircumstanceaddeditdialog')]//a[@data-selenium-id='cancelButton']";

    // Exceptions
    private final String exceptionsButton = "//a[@data-selenium-id='exceptions']";
    private final String exceptionsCountZero = "//a[@data-selenium-id='exceptions']/div[text()='0']";
    private final String exceptionGrid = "//div[@data-selenium-id='exceptionGrid']//tr[contains(@class,'x-grid-row')]";
   // private final String exceptionOverrideCheckbox = "//div[contains(@id,'loanexceptiondetaildialog')]//label[text()='Override']";
   //private final String exceptionOverrideCheckbox = "//div[contains(@id,'loanexceptiondetaildialog')]//label[text()='Override']/../input";
   // New, correct locator
   private final String exceptionOverrideCheckbox = "//input[contains(@id, 'checkboxcustom-') and @name='isOverriden']";
    //private final String exceptionNoteTextArea = "//textarea[@name='noteText']";

    // This new locator finds the input with a specific ID pattern, which is more stable.
    //private final String exceptionOverrideCheckbox = "//input[contains(@id, 'checkboxcustom-') and @name='isOverriden']";
    // This corrects the typo in the 'name' attribute.
  //  private final String exceptionNoteTextArea = "//textarea[@name='noteTextarea']";

    // New, correct locator
    private final String exceptionNoteTextArea = "//textarea[@name='noteTextarea']";
    private final String exceptionSaveCloseButton = "//span[text()='Save & Close']";
    private final String exceptionDialogCloseButton = "//div[contains(@id,'retailloanexceptionsdialog')]//img[contains(@class,'x-tool-close')]";

    // Alerts
    private final String alertsButton = "//a[@data-selenium-id='alerts']";
    private final String alertsCountZero = "//a[@data-selenium-id='alerts']/div[text()='0']";
    private final String losAlertsGridCheckboxes = "//div[@data-selenium-id='alertsGrid']//tr//td[6]//div[contains(@class,'checkcolumncustom')]";
    private final String saveAlertButton = "//a[@data-selenium-id='btnSaveAlert']";
    private final String closeAlertDialogButton = "//div[contains(@id,'loanalertservicedialog')]//img[contains(@class,'x-tool-close')]";

    // Navigation Panels
    private final String borrowerPropertyCollapsedPanel = "//div[contains(@class,'x-panel-header-collapsed')]//span[text()='Borrower / Property']";
    private final String borrowerPropertyLink = "//span[text()='Borrower / Property']";


    //    public BaseClass(Page page) {
//    }
    public BaseClass(Page page) {
        this.page = page;
    }

    //================================================================================
    // SECTION 1: TEST EXECUTION & LIFECYCLE (Replaces old BaseClass test runner)
    //================================================================================

    @BeforeClass
    void launchBrowser() {
        playwright = Playwright.create();
        // Use headless(false) to watch the execution. Use true for CI/CD environments.
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
    }

   @AfterClass
    void closeBrowser() {
        playwright.close();
    }

    @BeforeMethod
    void createContextAndPage() {
        context = browser.newContext();
        // Start tracing for debugging. The trace can be viewed with the Playwright CLI.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();
    }

    @AfterMethod
    void closeContext(ITestResult result) {
        // Stop tracing and save the file
        String testName = result.getMethod().getMethodName();
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("traces/" + testName + ".zip")));
        context.close();
    }

    /**
     * This is the main entry point for a test run.
     * It simulates the data-driven approach of the original framework.
     */

    @Test(description = "ExecuteLoanProcessingFlow")
    void runFullFlow() throws IOException {
        // In a real scenario, you would populate `dataList` from your Excel file here.
        // For demonstration, I'll create a dummy data map.
        // dataList = XlsxMultiAppReaderUtil.readExcelData("path/to/your/data.xlsx");

        // Example of running the test for each row of data from your Excel file
        for (int i = 0; i < dataList.size(); i++) {
            enterDataIntoApp(i);
        }
    }

    /**
     * Orchestrates the test flow for a single set of test data.
     * @param dataRowIndex The index from the main data list to use for this run.
     */
    public void enterDataIntoApp(int dataRowIndex) throws IOException {
        fieldDataValueMap.clear();
        fieldDataValueMap.putAll(dataList.get(dataRowIndex));

        // Example logic to decide which application part to run
        boolean crmFlag = true; // These flags would be controlled by your test data
        boolean lpFlag = false;

        if (crmFlag) {
            page.navigate(URL1);
            // Example Login - replace with actual locators and data keys
            fillText("input[name='userName']", fieldDataValueMap.get("CRMUsername"));
            fillText("input[name='password']", fieldDataValueMap.get("CRMPassword"));
            click("#btnLogin");
            actionMethods();
        }

        if (lpFlag) {
            page.navigate(URL2);
            // ... LP login and actionMethods call
        }
    }


    //================================================================================
    // SECTION 2: LOW-LEVEL UI HELPERS (Replaces UIElementMethods)
    //================================================================================

    public static void selectAngularDropdownOption(Page page, String dropdownToggleSelector, String optionText) {

        page.hover(optionText);
        page.click(optionText);

        System.out.println("hoverTargetSelector: " + optionText + "is displayed"   );}
    public boolean isElementVisible(String selector) {
        return page.locator(selector).isVisible();
    }


    // ADDED: New method as requested. It's an alias for fillText.
    public void enterText(String selector, String text) {
        fillText(selector, text);
    }
    public void listWebElements(String selector,String logLabel){
        if((listElems != null) && (listElems.size()>0) ) {
            listElems.clear();
        }
//        listElems = driver.findElements(By.xpath(xPath));
        Locator listElems = page.locator("selctor =" + selector);;
//     return listElems;

    }
    public static void enterText(Page page, String selector, String newText) {
        Locator field = page.locator(selector);
        field.click();
        field.press("Control+A");    // Select all text
        field.press("Delete");       // Clear it
        field.type(newText);  // Enter new text
    }
    public static void clearAndEnterText(Page page, String selector, String newText) {
        Locator field = page.locator(selector);
        field.click();                // Focus on the field
        field.press("Meta+A");  // Select all text
        field.press("Delete");       // Clear it
        field.type(newText);         // Enter new text
    }
    // ADDED: New method to scroll the page down.
    public void pageDown() {

        page.keyboard().press("PageDown");
    }

    public void click(String selector) {

        page.locator(selector).click();
    }
    public static void click(Page page, String selector) {
        Locator field = page.locator(selector);
        field.click();                // Focus on the field

    }
    public void jsClick(String selector) {
        page.locator(selector).dispatchEvent("click");
    }

    public void fillText(String selector, String text) {
        page.locator(selector).fill(text);
    }

    public void clearAndFillText(String selector, String text) {
        Locator element = page.locator(selector);
        element.clear();
        element.fill(text);
    }

    public void uploadFile(String selector, String filePath) {
        page.locator(selector).setInputFiles(Paths.get(filePath));
    }

    public String getText(String selector) {
        return page.locator(selector).innerText();
    }

    public String getAttribute(String selector, String attributeName) {
        return page.locator(selector).getAttribute(attributeName);
    }

    public void doubleClick(String selector) {
        page.locator(selector).dblclick();
    }

    public void pressEnter(String selector) {
        page.locator(selector).press("Enter");
    }

    public void pressTab(String selector) {
        page.locator(selector).press("Tab");
    }

    public Path takeScreenshot(String name) {
        Path path = Paths.get("screenshots/" + name + "_" + System.currentTimeMillis() + ".png");
        page.screenshot(new Page.ScreenshotOptions().setPath(path).setFullPage(true));
        return path;
    }

    public void waitForLoadingMaskToDisappear(String selector) {
        page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(60000));
    }

//    public void selectDropdownByText(String triggerSelector, String text) {
//        click(triggerSelector);
//        page.getByRole(AriaRole.LISTBOX).locator("li", new Locator.LocatorOptions().setHasText(text)).click();
//    }
    public void selectDropdownByText(String triggerSelector, String text) {
        // 1. Click the dropdown trigger to open the list of options
        click(triggerSelector);

        // 2. Locate the specific option by its text
        // We target the list item (li) which is the standard for these dropdowns
        Locator optionToSelect = page.locator(String.format("//li[text()='%s']", text));

        // 3. Scroll that specific option into the dropdown's viewport
        optionToSelect.scrollIntoViewIfNeeded();

        // 4. Now that it's visible, click it
        optionToSelect.click();
    }

    public void selectFirstDropdownOption(String triggerSelector) {
        click(triggerSelector);
        page.getByRole(AriaRole.LISTBOX).locator("li").first().click();
    }

    public void selectRandomDropdownOption(String triggerSelector) {
        click(triggerSelector);
        Locator options = page.getByRole(AriaRole.LISTBOX).locator("li");
        options.first().waitFor(); // Ensure list is populated
        int count = options.count();
        if (count > 0) {
            options.nth(rand.nextInt(count)).click();
        }
    }

    public String todayDate() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(new Date());
    }

    public String addDaysToToday(int daysToAdd) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, daysToAdd);
        return df.format(c.getTime());
    }

    //================================================================================
    // SECTION 3: HIGH-LEVEL BUSINESS LOGIC (Replaces DriverMethods - Workflows)
    //================================================================================

    public void addDemographicDetails() {
        String demographicPage = "//div[text()='Demographic Info']";
        click(demographicPage);

        String borrowersCheckbox = "//table[contains(@id,'info_borrower')][not(contains(@style,'display: none;'))]//input";
        Locator borrowers = page.locator(borrowersCheckbox);
        borrowers.first().waitFor();
        int borrowerCount = borrowers.count();

        for (int i = 0; i < borrowerCount; i++) {
            borrowers.nth(i).click();

            // Select random options for each section
            selectRandomDropdownOption("//table[@data-selenium-id='appTakenByTypeGroupId']//input");

            // Check if Yes/No options are enabled before clicking
            if (isElementVisible("//label[text()='Yes']")) {
                click("//label[text()='Yes']");
                click("(//label[text()='Yes'])[2]");
                click("(//label[text()='Yes'])[3]");
            }

            selectRandomDropdownOption("//table[@data-selenium-id='ethnicityGroup']//input[@role='checkbox']");
            selectRandomDropdownOption("//table[@data-selenium-id='genderGroup']//input[@role='checkbox']");
            selectRandomDropdownOption("//table[@data-selenium-id='raceGroup']//input[@role='checkbox']");

            click("//a[@data-selenium-id='btnLoanAppSave']");
            page.waitForSelector("//div[text()='Demographic Info updated']");
        }
    }

    public void addEscrowDetails() {
        String[] fundDetails = {
                "1024 - School Tax Impounds",
                "1027 - Windstorm Insurance Impounds",
                "1002 - Hazard Insurance Impounds"
        };

        click("//a[@data-selenium-id='btnNewEscrow']");
        page.waitForSelector("//span[text()='Add Escrow Information']");

        for (String fund : fundDetails) {
            selectDropdownByText("//input[@name='escrowItemId']/../following-sibling::td/div", fund);

            fillText("//input[@name='annualEscrowAmt']", "1500");
            fillText("//input[@name='payeeName']", "Payee1");

            // Assuming enterAddressAndZip is another helper method
            // enterAddressAndZip("//input[@name='addressLineOne']/&///input[@name='postalCodeId']/&///input[@name='city']");

            fillText("//input[@name='payeeTelephone']", "(872) 217-9962");
            fillText("//input[@name='taxAgencyId']", "10000");

            selectDropdownByText("//input[@name='escrow-paymentcycle-inputEl']/../following-sibling::td/div", "Annual");

            fillText("//input[@name='firstPaymentDate']", addDaysToToday(365));

            click("//span[@id='escrow-generate-schedule-btnEl']");

            // Simplified insurance details section for brevity
            if (isElementVisible("//img[@data-selenium-id='addInsuranceTypeId'][not(contains(@style,'display: none;'))]")) {
                click("//img[@data-selenium-id='addInsuranceTypeId']");
                page.waitForSelector("//span[text()='Add Insurance Type']");
                selectFirstDropdownOption("//input[@name='hazardInsuranceTypeId']/../following-sibling::td/div");
                fillText("//input[@name='companyName']", "Bluesage");
                fillText("(//input[@name='telephoneNumber'])[2]", "(852) 117-8965");
                fillText("//input[@name='policyNumber']", "A120000");
                fillText("//input[@name='coverageAmt']", "10000");
                fillText("//input[@name='annualPremiumAmt']", "1500");
                jsClick("//a[@data-selenium-id='btnSave']");
                waitForLoadingMaskToDisappear("//div[text()='Saving...']");
            }

            if (!isElementVisible("//a[@data-selenium-id='saveAddButton'][contains(@class,'x-btn-disabled')]")) {
                jsClick("//a[@data-selenium-id='saveAddButton']");
                waitForLoadingMaskToDisappear("//div[text()='Saving...']");
            } else {
                jsClick("//div[contains(@id,'escrowinformationdialog')]//img[contains(@class,'x-tool-close')]");
                break;
            }
        }
        jsClick("//a[@data-selenium-id='saveAddButton']/following-sibling::a//span[text()='Cancel']");
    }

    public void enterProductMortgageDetails() {
        click("//span[text()='Loan Terms']");
        waitForLoadingMaskToDisappear("//div[contains(@class,'x-mask-loading')]");

        String mortgageType = getAttribute("//input[@name='mortgageTypeId']", "value");

        if (mortgageType.contains("FHA")) {
            click("//a[@data-selenium-id='btnFHADetail']");
            fillText("//input[@name='agencyCaseNumber2']", "111");
            click("//a[@data-selenium-id='calculate_btn']");
            waitForLoadingMaskToDisappear("//div[text()='Processing']");
        }

        click("//a[@data-selenium-id='savebutton']");
    }


    //================================================================================
    // SECTION 4: KEYWORD-DRIVEN DISPATCHER (Replaces actionMethods())
    //================================================================================

    /**
     * This method acts as the central engine, reading keywords from the test data
     * and executing the corresponding actions. This preserves your original framework design.
     */
    public void actionMethods() throws IOException {
        for (Map.Entry<String, String> entry : fieldDataValueMap.entrySet()) {
            metaDataKey = entry.getKey();
            String testData = entry.getValue();
            String metaDataVal = metaDataMap.get(metaDataKey); // From your metadata sheet

            if (metaDataVal == null || "null".equalsIgnoreCase(testData) || testData.isEmpty()) {
                System.out.println(metaDataKey + " value is null or not found in metadata. Skipping.");
                continue;
            }

            String[] methParts = metaDataVal.split("&&");
            String methodName = methParts[0];
            String xPath = "";
            for (String part : methParts) {
                if (part.startsWith("//") || part.startsWith("input") || part.startsWith("#")) {
                    xPath = part;
                    break;
                }
            }

            try {
                // --- The main dispatcher logic ---
                if ("nodes".equals(methodName) || "button".equals(methodName) || "click".equals(methodName)) {
                    click(xPath);
                } else if ("textbox".equals(methodName) || "enterval".equals(methodName)) {
                    fillText(xPath, testData);
                } else if ("dropdown".equals(methodName)) {
                    selectDropdownByText(xPath, testData);
                } else if ("clickdropdownfirstvalue".equals(methodName)) {
                    selectFirstDropdownOption(xPath);
                } else if ("clickrandomvalfromdropdown".equals(methodName)) {
                    selectRandomDropdownOption(xPath);
                } else if ("invisible".equals(methodName)) {
                    waitForLoadingMaskToDisappear(xPath);
                } else if ("path".equals(methodName)) {
                    // Assuming 'testData' is a key to find the real file path from properties
                    // String filePath = Utils.properties.getProperty(testData);
                    // uploadFile(xPath, filePath);
                } else if ("javascript".equals(methodName)) {
                    jsClick(xPath);
                } else if ("keyenter".equals(methodName)) {
                    pressEnter(xPath);
                } else if ("altertab".equals(methodName)) {
                    pressTab(xPath);
                } else if ("waitforelemlocator".equals(methodName)) {
                    page.waitForSelector(xPath);
                } else if ("doubleclick".equals(methodName)) {
                    doubleClick(xPath);
                }
                // --- Business Logic Keywords ---
                else if ("enterdemographhicinfo".equals(methodName)) {
                    addDemographicDetails();
                } else if ("entercomescrowdetails".equals(methodName)) {
                    addEscrowDetails();
                } else if ("enterbluesageproductmortgagedetails".equals(methodName)) {
                    enterProductMortgageDetails();
                }
                // ... Add all other 'else if' blocks for your keywords here ...
                // Example for a method that is not yet translated:
                else if ("enterloanfolderinfo".equals(methodName)) {
                    System.out.println("Executing 'enterloanfolderinfo'. NOTE: This method needs to be translated to Playwright.");
                    // String path =  Utils.properties.getProperty(testData);
                    // String FilePath = System.getProperty("user.dir") + "\\src\\resources\\"+path;
                    // LoanFoldersPage loanFoldersPage = new LoanFoldersPage(page); // Using POM
                    // loanFoldersPage.addLoanFolder(FilePath);
                }

                else {
                    System.out.println("Keyword '" + methodName + "' not recognized.");
                }

            } catch (PlaywrightException e) {
                System.err.println("Error processing keyword: " + metaDataKey + " (" + methodName + ")");
                System.err.println("Playwright Error: " + e.getMessage());
                takeScreenshot("ERROR_" + metaDataKey);
                // Optionally re-throw or handle the error to stop the test
                throw e;
            }
        }
    }
    /**
     * Checks for various required actions and triggers the appropriate workflows.
     * This is a complex orchestrator method.
     */
    public void processRequiredActionItems() {
        click(actionsButton);
        page.locator(checkActionsPopUp).waitFor();

        // Check which actions are required before closing the popup
        boolean isMacocRequired = isElementVisible(macocAction);
        boolean isMiQuoteRequired = isElementVisible(miQuoteRequiredAction);
        boolean isPricingRequired = isElementVisible(pricingRequiredAction);
        boolean isAusRequired = isElementVisible(auRequiredAction);
        boolean isComplianceRequired = isElementVisible(complianceRequiredAction);
        boolean isGovLoanTermRequired = isElementVisible(governmentLoanTermAction);

        click(closeActionWindowButton);

        // Instantiate other page objects to delegate workflows
        LOSComplEasePage compliancePage = new LOSComplEasePage(page);
        LOSLoanPricingPage loanPricingPage = new LOSLoanPricingPage(page);
        // Assuming other page objects like MIQuotePage, AutomatedUnderwritingPage exist...

        // Execute workflows based on required actions
        if (isMacocRequired) {
            addChangeOfCircumstanceDetails("Fee Change");
            compliancePage.runComplianceEaseWorkflow();
            isComplianceRequired = false; // Avoid running compliance twice
        }
        if (isMiQuoteRequired) {
            System.out.println("Placeholder for MI Quote Details workflow.");
        }
        if (isPricingRequired) {
            loanPricingPage.priceLoanAndHandleCOC();
        }
        if (isAusRequired) {
            System.out.println("Placeholder for Automated Underwriting workflow.");
        }
        if (isComplianceRequired) {
            compliancePage.runComplianceEaseWorkflow();
        }
        if (isGovLoanTermRequired) {
            System.out.println("Placeholder for Government Loan Terms workflow.");
        }
    }

    /**
     * Adds Change of Circumstance (COC) details.
     * @param reason The reason text to select from the dropdown.
     */
    public void addChangeOfCircumstanceDetails(String reason) {
        click(cocHistoryButton);
        page.locator(addCocButton).waitFor();
        click(addCocButton);
        page.locator(cocReasonDropdown).waitFor();

        selectDropdownByText(cocReasonDropdown, reason);
        click(cocReasonCheckbox1);
        click(cocReasonCheckbox2);
        jsClick(saveCocButton); // Use JS click for reliability on complex elements
        page.locator(cancelCocButton).waitFor();
        jsClick(cancelCocButton);
    }

    /**
     * Clears all outstanding loan exceptions by overriding them.
     */
//    public void clearAllExceptions() {
//        if (isElementVisible(exceptionsCountZero)) {
//            System.out.println("No exceptions to clear.");
//            return;
//        }
//
//        click(exceptionsButton);
//        Locator exceptionRows = page.locator(exceptionGrid);
//        exceptionRows.first().waitFor();
//
//        // Loop through each exception and override it
//        while (exceptionRows.count() > 0) {
//            exceptionRows.first().dblclick();
//            page.locator(exceptionOverrideCheckbox).waitFor();
//            click(exceptionOverrideCheckbox);
//
//            if (!isElementVisible(exceptionSaveCloseButton + "[contains(@class,'x-item-disabled')]")) {
//                fillText(exceptionNoteTextArea, "Clearing loan exception via automation.");
//            }
//            click(exceptionSaveCloseButton);
//            // Wait for the dialog to disappear before continuing
//            page.locator("//div[contains(@id,'loanexceptiondetaildialog')]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
//        }
//        click(exceptionDialogCloseButton);
//    }
// In BaseClass.java

    /**
     * Clears all overridable loan exceptions. It now specifically targets only the
     * rows that contain a checkbox, ignoring those that do not.
     */
    /*public void clearAllExceptions() {
        if (isElementVisible(exceptionsCountZero)) {
            System.out.println("No exceptions to clear.");
            return;
        }

        click(exceptionsButton);
        page.locator(exceptionGrid).first().waitFor(); // Wait for the grid to load

        // --- FIX ---
        // New, more specific locator that ONLY finds rows with a checkbox column
        String overridableExceptionRows = "//div[@data-selenium-id='exceptionGrid']//tr[contains(@class,'x-grid-row') and .//td[contains(@class,'x-grid-cell-checkcolumn')]]";
        Locator exceptionRows = page.locator(overridableExceptionRows);

        // Check if there are any overridable exceptions before starting the loop
        if (exceptionRows.count() == 0) {
            System.out.println("No overridable exceptions found.");
            click(exceptionDialogCloseButton);
            return;
        }
        // --- END OF FIX ---

        // Loop through each overridable exception and process it
        while (exceptionRows.count() > 0) {
            exceptionRows.first().dblclick();

            page.locator("//div[contains(@id,'loanexceptiondetaildialog')]").waitFor();

            // Use the more reliable checkbox locator from our previous fix
            String overrideCheckbox = "//div[contains(@id,'loanexceptiondetaildialog')]//label[text()='Override']/preceding-sibling::div[contains(@class,'x-form-checkbox')]";
            click(overrideCheckbox);

            if (isElementVisible(exceptionNoteTextArea)) {
                fillText(exceptionNoteTextArea, "Clearing loan exception via automation.");
            }
            click(exceptionSaveCloseButton);
            page.locator("//div[contains(@id,'loanexceptiondetaildialog')]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
        }
        click(exceptionDialogCloseButton);
    }*/



    // In BaseClass.java

    /**
     * Clears all overridable loan exceptions. It now uses a more resilient locator
     * for the override checkbox and includes better waiting and debugging.
     */
  /*  public void clearAllExceptions() {
        if (isElementVisible(exceptionsCountZero)) {
            System.out.println("No exceptions to clear.");
            return;
        }

        click(exceptionsButton);
        // Use a short wait to see if the grid has any rows at all.
        page.waitForTimeout(1000);

        String overridableExceptionRows = "//div[@data-selenium-id='exceptionGrid']//tr[contains(@class,'x-grid-row') and .//td[contains(@class,'x-grid-cell-checkcolumn')]]";
        Locator exceptionRows = page.locator(overridableExceptionRows);

        if (exceptionRows.count() == 0) {
            System.out.println("No overridable exceptions found.");
            click(exceptionDialogCloseButton);
            return;
        }

        while (exceptionRows.count() > 0) {
           // exceptionRows.first().dblclick();
            exceptionRows.first().dispatchEvent("dblclick");

            String detailDialog = "//div[contains(@id,'loanexceptiondetaildialog')]";
            page.locator(detailDialog).waitFor(); // Wait for the dialog container to be visible

            // --- NEW ROBUST LOCATOR ---
            // This finds the form item containing the "Override" label, then finds the clickable checkbox within it.
            String overrideCheckbox = detailDialog + "//div[contains(@class,'x-form-item') and .//label[text()='Override']]//input";
            click(overrideCheckbox);

            if (isElementVisible(exceptionNoteTextArea)) {
                fillText(exceptionNoteTextArea, "Clearing loan exception via automation.");
            }
            click(exceptionSaveCloseButton);

            page.locator(detailDialog).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
        }
        click(exceptionDialogCloseButton);
    }
    */

// In BaseClass.java

    public void clearAllExceptions() {
        if (isElementVisible(exceptionsCountZero)) {
            System.out.println("No exceptions to clear.");
            return;
        }

        click(exceptionsButton);
        page.waitForTimeout(1000);

        String overridableExceptionRows = "//div[@data-selenium-id='exceptionGrid']//tr[contains(@class,'x-grid-row') and .//td[contains(@class,'x-grid-cell-checkcolumn')]]";
        Locator exceptionRows = page.locator(overridableExceptionRows);
        String overrideCheckboxes = "//td[contains(@class,'x-grid-td x-grid-cell-headerId-checkcolumn')]/div/div[not(contains(@class,'x-grid-checkcolumncustom x-grid-checkcolumncustom-checked'))]";
        Locator checkBoxes= page.locator(overrideCheckboxes);
        if (exceptionRows.count() == 0) {
            System.out.println("No overridable exceptions found.");
            click(exceptionDialogCloseButton);
            return;
        }
        if (exceptionRows.count() > 0) {
            page.waitForTimeout(1000);
            // Click every checkbox available
            for (int i = 0; i <= exceptionRows.count(); i++) {
                checkBoxes.nth(i).click();
            }
        }
        page.click("//span[text()='Save']");
        System.out.println("Exceptions Overrided");
    }

    /**
     * Clears all outstanding alerts.
     */
    public void clearAllAlerts() {
        if (isElementVisible(alertsCountZero)) {
            System.out.println("No alerts to clear.");
            return;
        }
        click(alertsButton);
        Locator alertCheckboxes = page.locator(losAlertsGridCheckboxes);
        alertCheckboxes.first().waitFor();

        // Click every checkbox available
        for (int i = 0; i < alertCheckboxes.count(); i++) {
            alertCheckboxes.nth(i).click();
        }

        click(saveAlertButton);
        waitForLoadingMaskToDisappear("//div[text()='Loading...']");
        click(closeAlertDialogButton);
    }

    /**
     * Opens the Borrower/Property panel if it is collapsed.
     */
    public void openBorrowerAndPropertyPanel() {
        if (isElementVisible(borrowerPropertyCollapsedPanel)) {
            click(borrowerPropertyLink);
        }
    }
    /**
     * Enters text into an element without clearing its existing content.
     * Useful for appending text.
     * @param selector The locator for the element.
     * @param text The text to type.
     */
    public void enterTextWithoutClearing(String selector, String text) {
        page.locator(selector).pressSequentially(text);
    }

    /**
     * Scrolls an element into the visible area of the browser window.
     * @param selector The locator for the element to scroll to.
     */
    public void scrollIntoView(String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    /**
     * Hovers the mouse cursor over a given element.
     * @param selector The locator for the element to hover over.
     */
    public void hover(String selector) {
        page.locator(selector).hover();
    }

    /**
     * Clicks a dropdown trigger and selects an option by the text it contains.
     * This is useful for partial text matches.
     * @param triggerSelector The locator for the dropdown trigger (e.g., the input field or arrow button).
     * @param text The partial text of the option to select.
     */
    public void selectDropdownByContainingText(String triggerSelector, String text) {
        click(triggerSelector);
        page.getByRole(AriaRole.LISTBOX).locator("li:has-text('" + text + "')").first().click();
    }

    /**
     * Clicks a dropdown trigger and selects an option by its zero-based index.
     * @param triggerSelector The locator for the dropdown trigger.
     * @param index The index of the option to select (0 for the first, 1 for the second, etc.).
     */
    public void selectDropdownByIndex(String triggerSelector, int index) {
        click(triggerSelector);
        Locator options = page.getByRole(AriaRole.LISTBOX).locator("li");
        options.first().waitFor(); // Ensure the list is populated
        if (options.count() > index) {
            options.nth(index).click();
        }
    }

    /**
     * A utility to get the current timestamp as a formatted string.
     * Useful for creating unique filenames or identifiers.
     * @return A string representing the current timestamp (e.g., "2025.09.17.11.20.12").
     */
    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.sql.Timestamp(System.currentTimeMillis()));
    }

    /**
     * Extracts the first numerical value from a string.
     * @param text The string to parse (e.g., "Loan Number 12345 is pending").
     * @return The number as a String (e.g., "12345"), or null if no number is found.
     */
    public String getNumberFromText(String text) {
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(text);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    /**
     * Clicks an element that opens a new browser tab or window and returns the new Page object.
     * This is the standard Playwright way to handle multi-page scenarios.
     *
     * @param triggerActionSelector The locator of the element to click that opens the new window (e.g., a "Help" link).
     * @return The new Page object that was opened.
     */
    public Page switchToNewWindow(String triggerActionSelector) {
        return page.context().waitForPage(() -> {
            click(triggerActionSelector);
        });
    }

    /**
     * Injects and executes JavaScript to get a specific value from an ExtJS data store.
     * This is a powerful utility for web applications built with the ExtJS framework.
     *
     * @param storeId The ID of the ExtJS store.
     * @param searchField The field to search within the store's records (e.g., 'name').
     * @param searchValue The value to match in the searchField (e.g., 'John Doe').
     * @param pluckField The field whose value you want to retrieve from the matched record (e.g., 'id').
     * @return The plucked value as a String, or null if not found.
     */
    @SuppressWarnings("unchecked")
    public String pluckExtJsValue(String storeId, String searchField, String searchValue, String pluckField) {
        String script = "return Ext.pluck("
                + "Ext.Array.filter(Ext.pluck(Ext.getStore('" + storeId + "').data.items,'data'), "
                + "function(item) {"
                + "return item." + searchField + " === '" + searchValue + "';"
                + "}), "
                + "'" + pluckField + "');";

        ArrayList<String> result = (ArrayList<String>) page.evaluate(script);
        return result.size() > 0 ? result.get(0) : null;
    }
    /**
     * Enters a random address and zip code, automatically populating the city.
     * This is a utility for filling out address forms quickly.
     * @param addressInputSelector The locator for the street address input field.
     * @param zipInputSelector The locator for the postal code/zip input field.
     * @param cityInputSelector The locator for the city input field (used for verification).
     */
    public void enterAddressAndZip(String addressInputSelector, String zipInputSelector, String cityInputSelector) {
        // A list of sample addresses in "ZIP-Street Address" format
        String[] completeAddresses = { "99508-1715 Birchwood St", "35811-240 Grasslands Rd", "71922-2504 Hickory Grove Rd", "85234-2472 E Gemini St", "93535-45704 Palm Ln", "80113-4125 S Grant St", "06370-4 Texas Dr", "20032-800 Southern Ave Se", "32738-2245 Dumas Dr", "66441-2619 Paige Ln" };

        String randomAddress = completeAddresses[rand.nextInt(completeAddresses.length)];
        String[] addressParts = randomAddress.split("-", 2);
        String zipCode = addressParts[0];
        String streetAddress = addressParts[1];

        fillText(addressInputSelector, streetAddress);
        fillText(zipInputSelector, zipCode);

        // Wait for the city to be auto-populated by the system after entering the zip
      // page.waitForFunction("selector => document.querySelector(selector).value.length > 0", cityInputSelector);
    }
    /**
     * Clears the text from an input field.
     * @param selector The locator of the input field to clear.
     */
    public void clearField(String selector) {
        page.locator(selector).clear();
    }

    /**
     * A flexible method to get a future or past date formatted as "MM/dd/yyyy".
     * @param days The number of days to add to the current date (can be negative).
     * @param months The number of months to add to the current date (can be negative).
     * @param years The number of years to add to the current date (can be negative).
     * @return A formatted date string.
     */
    public String addDate(int days, int months, int years) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, days);
        c.add(Calendar.MONTH, months);
        c.add(Calendar.YEAR, years);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(c.getTime());
    }

    /**
     * Sets an element's value using JavaScript, which can be useful for inputs
     * that are difficult to interact with normally.
     * @param selector The locator of the element.
     * @param text The text to set as the element's value.
     */
    public void jsSetText(String selector, String text) {
        page.locator(selector).evaluate("(node, text) => node.value = text", text);
    }

    /**
     * Explicitly waits for an element to contain a specific text. This is more robust
     * than just waiting for visibility.
     * @param selector The locator of the element.
     * @param text The text expected to be inside the element.
     */
    public void waitForTextInElement(String selector, String text) {

        PlaywrightAssertions.assertThat(page.locator(selector))
                .containsText(text, new LocatorAssertions.ContainsTextOptions().setTimeout(15000));
    }

    /**
     * Closes all browser tabs and windows except for the original (main) window.
     */
    public void closeAllWindowsExceptMain() {
        // The first page in the context is considered the main page.
        Page mainPage = page.context().pages().get(0);

        // Iterate through all other pages and close them.
        for (int i = 1; i < page.context().pages().size(); i++) {
            page.context().pages().get(i).close();
        }

        // Ensure the main page is brought back into focus.
        mainPage.bringToFront();
    }

    /**
     * Selects a random string from a given array of strings.
     * @param array The array of strings to choose from.
     * @return A randomly selected string from the array.
     */
    public String getRandomValueFromArray(String[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[rand.nextInt(array.length)];
    }
}
