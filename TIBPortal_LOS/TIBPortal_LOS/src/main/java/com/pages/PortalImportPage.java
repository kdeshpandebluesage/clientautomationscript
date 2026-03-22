package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PortalImportPage extends BaseClass {
    public PortalImportPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void importfile() {

        page.click("//span[text()='Import Loan File']");
        page.click("(//input[@type='button'])[3]");

        String browseButtonSelector = "[id*='duThreeFourFile-browseButtonWrap']";
        // 2. Define your file path
        String myFile = "src/main/resources/DU_2BR_Purchase_Fixed.xml";


        // 3. Call the generic method
      uploadViaFileChooser(page, browseButtonSelector, myFile);
//      uploadFile(browseButtonSelector, myFile);
        page.click("//a[@data-selenium-id='newLoanIn_btnImport']");
        System.out.println("Import is done .");
    }
    public void xmlimportfile(String fileName) {

        page.click("//span[text()='Import Loan File']");
        page.click("(//input[@type='button'])[3]");

        String browseButtonSelector = "[id*='duThreeFourFile-browseButtonWrap']";
        // 2. Define your file path
        String myFile = "src/main/resources/DU_2BR_Purchase_Fixed.xml";
        // Uses System.getProperty("user.dir") to ensure it works on any machine
        String filePath = System.getProperty("user.dir") + "/src/main/resources/" + fileName;


        // 3. Call the generic method
        uploadViaFileChooser(page, browseButtonSelector, filePath);
//      uploadFile(browseButtonSelector, myFile);
        page.click("//a[@data-selenium-id='newLoanIn_btnImport']");
        System.out.println("Import is done .");
    }

}
