package com.pages;

public class LOPLoanTermPage extends BasePage {
    public LOPLoanTermPage(com.microsoft.playwright.Page page) {
        super(page);
    }


    public void lopLoanSource(String LOPAppraisedValue, String LOPCreditscore,String SettlmentDate) {


        String propertyTypeLocator = "//input[@name='propertyTypeId']/../following-sibling::td/div";
        String hoverTargetSelector = "//li[text()='Condominium']";
        page.click(propertyTypeLocator);
        selectAngularDropdownOption(page, propertyTypeLocator, hoverTargetSelector);

        enterText(page, "//input[@name='appraisedValueAmt']", LOPAppraisedValue);

        String OverrideTypeLocator = "//input[@name='creditScoreOverrideTypeId']/../following-sibling::td/div";
        String hoverTargetSelector2 = "//li[text()='Non-Traditional Credit Established']";
        page.click(OverrideTypeLocator);
        selectAngularDropdownOption(page, OverrideTypeLocator, hoverTargetSelector2);

        String DocumentationTypeLocator = "//input[@name='documentationTypeId']/../following-sibling::td/div";
        String hoverTargetSelector3 = "//li[text()='Full Documentation']";
        page.click(DocumentationTypeLocator);
        selectAngularDropdownOption(page, DocumentationTypeLocator, hoverTargetSelector3);

        enterText(page, "//input[@name='representativeCreditScore']", LOPCreditscore);
        enterText(page, "//input[@name='appraisedValueAmt']", LOPAppraisedValue);

        page.click("//span[text()='SAVE']");
        //span[text()='SAVE']

        System.out.println("Loan Source screen is done .");
    }
}
