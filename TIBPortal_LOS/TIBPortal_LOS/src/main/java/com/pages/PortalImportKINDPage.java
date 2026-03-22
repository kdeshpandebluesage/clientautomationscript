package com.pages;

public class PortalImportKINDPage extends BaseClass {
    public PortalImportKINDPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void importfile() {

        page.click("//span[text()='Import Loan File']");
        page.click("(//input[@type='button'])[3]");

        String browseButtonSelector = "[id*='duThreeFourFile-browseButtonWrap']";
        // 2. Define your file path
        String myFile = "src/main/resources/DSP_Reg7.xml";

        // 3. Call the generic method
      uploadViaFileChooser(page, browseButtonSelector, myFile);
//      uploadFile(browseButtonSelector, myFile);
        page.click("//a[@data-selenium-id='newLoanIn_btnImport']");

        System.out.println("Import is done .");
    }
}
