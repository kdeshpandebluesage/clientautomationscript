package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.io.IOException;

public class PortalShortAppKindPage extends BaseClass {
    public PortalShortAppKindPage(Page page) {
        super(page);
    }

    public void shortApp(String creditscore,String AppraisedAmt,String PropertyType,String DTI ) throws IOException {
        page.waitForTimeout(3000);
//        AddAsset	nodes	//ion-button[contains(@class,'asset-master-edit-new-btn')]
//        page.click("(//ion-button[contains(@class,'asset-master-edit-new-btn')])[2]");
        page.fill("input[name='loanClosedDate']", addDaysToToday(25));
//        enterText(page, "input[name='loanClosedDate']", todayDate());
//        enterText(page, "input[name='creditAuthorizationDate']", todayDate());
        page.click("//input[@name='creditScore']");
        enterText("(//input[@name='creditScore'])",creditscore );

        enterText("(//input[@name='appraisedValueAmt'])",AppraisedAmt);

//        page.click("//input[@name='buildingTypeId']");
//        clearAndFillText("(//input[@name='buildingTypeId'])",PropertyType);

        String BuildingTypeLocator = "//input[@name='buildingTypeId']/../following-sibling::td/div";
        String hoverTargetSelector5 = "//li[text()='Attached']";
        page.click(BuildingTypeLocator);
        selectAngularDropdownOption(page, BuildingTypeLocator, hoverTargetSelector5);

        String DocumentTypeLocator = "//input[@name='documentationTypeId']/../following-sibling::td/div";
        String hoverTargetSelector3 = "//li[text()='Full Doc']";
        page.click(DocumentTypeLocator);
        selectAngularDropdownOption(page, DocumentTypeLocator, hoverTargetSelector3);




        String CompMethodLocator = "//input[@name='brokerCompensationTypeId']/../following-sibling::td/div";
        String hoverTargetSelector1 = "//li[text()='Lender Paid']";
        page.click(CompMethodLocator);
        selectAngularDropdownOption(page, CompMethodLocator, hoverTargetSelector1);

        String AdminFeeLocator = "//input[@name='feeBuyoutYn']/../following-sibling::td/div";
        String hoverTargetSelector2 = "//li[text()='Yes']";
        page.click(AdminFeeLocator);
        selectAngularDropdownOption(page, AdminFeeLocator, hoverTargetSelector2);

        String RateTypeLocator = "//input[@name='rateLockTypeId']/../following-sibling::td/div";
        String hoverTargetSelector4 = "//li[text()='60 Day Rate Lock']";
        page.click(RateTypeLocator);
        selectAngularDropdownOption(page, RateTypeLocator, hoverTargetSelector4);


        page.click("//input[@name='totalDebtRatioPct']");
        enterText("(//input[@name='totalDebtRatioPct'])",DTI );

        System.out.println("Loan Term screen is done .");

        page.click("//a[@data-selenium-id='btnRegister']");

        System.out.println("Loan Term screen is done .");

    }
}
