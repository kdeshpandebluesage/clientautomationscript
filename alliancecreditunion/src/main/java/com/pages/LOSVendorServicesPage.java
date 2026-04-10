package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.List;

public class LOSVendorServicesPage extends BaseClass {

    // --- Main Locators ---
    private final String vendorServicesLink = "//span[text()='Vendor Services']";
    private final String orderNewServiceButton = "//span[text()='Order New Service']";
    private final String selectServiceDropdown = "//input[@placeholder='--Select a Service--']";
    private final String networkProviderDropdown = "//input[@name='networkProviderId']";
    private final String requestTypeDropdown = "//input[@name='requestTypeId']";
    private final String serviceProviderInput = "//input[@name='serviceProviderId']";
    private final String serviceProviderDropdownTrigger = "//input[@name='serviceProviderId']/../following-sibling::td/div";
    private final String productTypeDropdown = "//input[@name='productTypeId']";
    private final String productType = "//input[@name='productTypeId']";
    private final String emplStatusTypeDropdown = "//input[@name='employmentStatusTypeId']";
   private final  String emplStatusTypeText = "Current";
    private final String borrowerCheckboxGrid = "(//div[@data-selenium-id='borrowersGrid']//div[contains(@class,'checkcolumn')])[1]";
    private final String borrowerCheckboxGroup = "(//table[@data-selenium-id='borrowersGroup']//input)[1]";
    private final String submitOrderButton = "//a[@data-selenium-id='submitOrderBtn']";
    private final String infoOkButton = "//span[text()='OK']";
    private final String cancelButton = "//a[@data-selenium-id='cancelBtn']";
    private final String dropdownListItems = "//div[contains(@class,'x-boundlist-floating')][not(contains(@style,'display: none'))]//li";

    // --- Freeform Service Provider Window Locators ---
    private final String freeformSPButton = "//a[@data-selenium-id='freeformSPBtn']";
    private final String companyNameInput = "(//input[@name='companyName'])[2]";
    private final String addressLineOneInput = "//input[@name='addressLineOne']";
    private final String postalCodeInput = "//input[@name='postalCodeId']";
    private final String cityInput = "//input[@name='city']";
    private final String freeformPhoneInput = "//input[@name='telephoneNumber']";
    private final String freeformFaxInput = "//input[@name='faxNumber']";
    private final String freeformEmailInput = "//input[@name='emailAddress']";
    private final String serviceProviderOkButton = "//a[@data-selenium-id='btnOk']";

    /**
     * Constructor for the LOSVendorServicesPage.
     * @param page The Playwright Page object.
     */
    public LOSVendorServicesPage(Page page) {
        super(page);
    }

    /**
     * Orders a vendor service with complex, conditional logic.
     * @param serviceType The service to order (e.g., "Title Insurance").
     * @param networkProvider The network provider (e.g., "Manual" or "Real EC").
     */
    public void orderVendorService(String serviceType, String networkProvider, String serviceProviderText) throws InterruptedException {
        click(vendorServicesLink);
        page.locator(orderNewServiceButton).waitFor();
        if(serviceType == "Verification of Asset" || serviceType == "Verification of Employment" || serviceType == "Verification of Income") {
            selectDropdownByText(selectServiceDropdown, serviceType);//Verification of Asset || Employment
            click(orderNewServiceButton);
            page.locator(productTypeDropdown).waitFor();
            page.locator(networkProviderDropdown).waitFor();
            selectDropdownByText(networkProviderDropdown, networkProvider);//Manual
            // selectFirstDropdownOption(requestTypeDropdown);//New Order
            selectDropdownByText(serviceProviderDropdownTrigger, serviceProviderText);

            // Handle the freeform provider pop-up if the button is present
            handleFreeformServiceProviderIfNeeded();
            handleEmploymentStatusIfNeeded();
            //  selectFirstDropdownOption(productTypeDropdown);
            // Select the appropriate borrower checkbox depending on which one is visible
            if (isElementVisible(borrowerCheckboxGrid)) {
                click(borrowerCheckboxGrid);
            } else if (isElementVisible(borrowerCheckboxGroup)) {
                page.locator(borrowerCheckboxGroup).waitFor();
                click(borrowerCheckboxGroup);
            }
            page.locator(submitOrderButton).waitFor();
            click(submitOrderButton);
            page.locator(infoOkButton).waitFor();
            click(infoOkButton);
            click(cancelButton);
        }
        if(serviceType == "Flood Certification") {
            String productType = "Life of Loan Cert";
            selectDropdownByText(selectServiceDropdown, serviceType); //Flood Certification
            page.locator(orderNewServiceButton).waitFor();
            click(orderNewServiceButton);
            page.locator(productTypeDropdown).waitFor();

            selectDropdownByText(networkProviderDropdown, networkProvider);//Manual
            // selectFirstDropdownOption(requestTypeDropdown);//New Order
            selectDropdownByText(serviceProviderDropdownTrigger, serviceProviderText);

            // Handle the freeform provider pop-up if the button is present
            handleFreeformServiceProviderIfNeeded();
            //     page.locator(productTypeDropdown).waitFor();
            // 1. Get the 'value' attribute from the input field.
            String productTypeValue = getAttribute(productTypeDropdown, "value");
            System.out.println("Product Type Value: " + productTypeValue);
            if (productTypeValue == null || productTypeValue.isBlank()) {
                System.out.println("Product Type is blank. Selecting the first option.");
                selectDropdownByText(productTypeDropdown, productType);
            }

            //  selectFirstDropdownOption(productTypeDropdown);
            // Select the appropriate borrower checkbox depending on which one is visible
            if (isElementVisible(borrowerCheckboxGrid)) {
                click(borrowerCheckboxGrid);
            } else if (isElementVisible(borrowerCheckboxGroup)) {
                click(borrowerCheckboxGroup);
            }
            click(submitOrderButton);
            page.locator(infoOkButton).waitFor();
            click(infoOkButton);
        }

     if(serviceType == "Title Insurance") {
         String productType = "Commitment/Prelim Rpt";
         selectDropdownByText(selectServiceDropdown, serviceType); //Title Insurance
         page.locator(orderNewServiceButton).waitFor();
         click(orderNewServiceButton);
         page.locator(productTypeDropdown).waitFor();

         selectDropdownByText(networkProviderDropdown, networkProvider);//Manual
         // selectFirstDropdownOption(requestTypeDropdown);//New Order
         //selectDropdownByText(serviceProviderDropdownTrigger, serviceProviderText);

         // Handle the freeform provider pop-up if the button is present
         handleFreeformServiceProviderIfNeeded();
         //     page.locator(productTypeDropdown).waitFor();
         // 1. Get the 'value' attribute from the input field.
         String productTypeValue = getAttribute(productTypeDropdown, "value");
         System.out.println("Product Type Value: " + productTypeValue);
         if (productTypeValue == null || productTypeValue.isBlank()) {
             System.out.println("Product Type is blank. Selecting the first option.");
             selectDropdownByText(productTypeDropdown, productType);
         }

         //  selectFirstDropdownOption(productTypeDropdown);
         // Select the appropriate borrower checkbox depending on which one is visible
         if (isElementVisible(borrowerCheckboxGrid)) {
             click(borrowerCheckboxGrid);
         } else if (isElementVisible(borrowerCheckboxGroup)) {
             click(borrowerCheckboxGroup);
         }

         click(submitOrderButton);
         page.locator(infoOkButton).waitFor();
         click(infoOkButton);
         click(cancelButton);
     }
        if(serviceType == "Property Appraisals") {
            String productType = "1004 Hybrid Appraisal";
            selectDropdownByText(selectServiceDropdown, serviceType); //Title Insurance
            page.locator(orderNewServiceButton).waitFor();
            click(orderNewServiceButton);
            page.locator(productTypeDropdown).waitFor();

            selectDropdownByText(networkProviderDropdown, networkProvider);//Manual
            // selectFirstDropdownOption(requestTypeDropdown);//New Order
            //selectDropdownByText(serviceProviderDropdownTrigger, serviceProviderText);

            // Handle the freeform provider pop-up if the button is present
            handleFreeformServiceProviderIfNeeded();
            //     page.locator(productTypeDropdown).waitFor();
            // 1. Get the 'value' attribute from the input field.
            String productTypeValue = getAttribute(productTypeDropdown, "value");
            System.out.println("Product Type Value: " + productTypeValue);
            if (productTypeValue == null || productTypeValue.isBlank()) {
                System.out.println("Product Type is blank. Selecting the first option.");
                selectDropdownByText(productTypeDropdown, productType);
            }

            //  selectFirstDropdownOption(productTypeDropdown);
            // Select the appropriate borrower checkbox depending on which one is visible
            if (isElementVisible(borrowerCheckboxGrid)) {
                click(borrowerCheckboxGrid);
            } else if (isElementVisible(borrowerCheckboxGroup)) {
                click(borrowerCheckboxGroup);
            }

            click(submitOrderButton);
            page.locator(infoOkButton).waitFor();
            click(infoOkButton);
            click(cancelButton);
        }
        if(serviceType == "Signing Appointment") {
            String productType = "Document Signing - 1 Set";
            selectDropdownByText(selectServiceDropdown, serviceType); //Title Insurance
            page.locator(orderNewServiceButton).waitFor();
            click(orderNewServiceButton);
            page.locator(productTypeDropdown).waitFor();

            selectDropdownByText(networkProviderDropdown, networkProvider);//Manual
            // selectFirstDropdownOption(requestTypeDropdown);//New Order
            //selectDropdownByText(serviceProviderDropdownTrigger, serviceProviderText);

            // Handle the freeform provider pop-up if the button is present
            handleFreeformServiceProviderIfNeeded();
            //     page.locator(productTypeDropdown).waitFor();
            // 1. Get the 'value' attribute from the input field.
            String productTypeValue = getAttribute(productTypeDropdown, "value");
            System.out.println("Product Type Value: " + productTypeValue);
            if (productTypeValue == null || productTypeValue.isBlank()) {
                System.out.println("Product Type is blank. Selecting the first option.");
                selectDropdownByText(productTypeDropdown, productType);
            }

            //  selectFirstDropdownOption(productTypeDropdown);
            // Select the appropriate borrower checkbox depending on which one is visible
            if (isElementVisible(borrowerCheckboxGrid)) {
                click(borrowerCheckboxGrid);
            } else if (isElementVisible(borrowerCheckboxGroup)) {
                click(borrowerCheckboxGroup);
            }

            click(submitOrderButton);
            page.locator(infoOkButton).waitFor();
            click(infoOkButton);
            click(cancelButton);
        }
//        if(serviceType == "Verification of Employment") {
//           // String productType = "Document Signing - 1 Set";
//            selectDropdownByText(selectServiceDropdown, serviceType);//Verification of Employment
//            click(orderNewServiceButton);
//            page.locator(productTypeDropdown).waitFor();
//
//            selectDropdownByText(networkProviderDropdown, networkProvider);//Manual
//            // selectFirstDropdownOption(requestTypeDropdown);//New Order
//            selectDropdownByText(serviceProviderDropdownTrigger, serviceProviderText);
//
//            // Handle the freeform provider pop-up if the button is present
//            handleFreeformServiceProviderIfNeeded();
//            //  selectFirstDropdownOption(productTypeDropdown);
//            // Select the appropriate borrower checkbox depending on which one is visible
//            if (isElementVisible(borrowerCheckboxGrid)) {
//                click(borrowerCheckboxGrid);
//            } else if (isElementVisible(borrowerCheckboxGroup)) {
//                click(borrowerCheckboxGroup);
//            }
//
//            click(submitOrderButton);
//            page.locator(infoOkButton).waitFor();
//            click(infoOkButton);
//            click(cancelButton);
//        }


}


    /**
     * Checks for the 'Freeform' button and fills out the provider details if it appears.
     */
    private void handleFreeformServiceProviderIfNeeded() {
        if (isElementVisible(freeformSPButton)) {
            System.out.println("Freeform Service Provider button found. Entering details...");
            click(freeformSPButton);
            page.locator(companyNameInput).waitFor();

            fillText(companyNameInput, "BlueSage Automation Test Provider");
            // Use the new BaseClass helper to fill the address
            enterAddressAndZip(addressLineOneInput, postalCodeInput, cityInput);
            fillText(freeformPhoneInput, "(555) 123-4567");
            fillText(freeformFaxInput, "(555) 987-6543");
            fillText(freeformEmailInput, "test@bluesageusa.com");

            click(serviceProviderOkButton);
        }
    }
    private void handleProductTypeIfNeeded() {
        if (isElementVisible(freeformSPButton)) {
            System.out.println("Freeform Service Provider button found. Entering details...");
            click(freeformSPButton);
            page.locator(companyNameInput).waitFor();

            fillText(companyNameInput, "BlueSage Automation Test Provider");
            // Use the new BaseClass helper to fill the address
            enterAddressAndZip(addressLineOneInput, postalCodeInput, cityInput);
            fillText(freeformPhoneInput, "(555) 123-4567");
            fillText(freeformFaxInput, "(555) 987-6543");
            fillText(freeformEmailInput, "test@bluesageusa.com");

            click(serviceProviderOkButton);
        }
    }
    private void handleEmploymentStatusIfNeeded() {
        if (isElementVisible(emplStatusTypeDropdown)) {
            if(emplStatusTypeDropdown.isEmpty()) {
                System.out.println("Employment Status is empty");
                //    click(emplStatusTypeDropdown);
            }else {
                selectDropdownByText(emplStatusTypeDropdown, emplStatusTypeText);
            }
        }
    }
    //================================================================================
    // NEW METHOD ADDED
    //================================================================================
    /**
     * Executes prerequisite steps before ordering a service by clearing
     * required actions and exceptions.
     */
    public void clearPrerequisitesBeforeOrdering() {
        System.out.println("Clearing prerequisites before ordering services...");

        // Step: ClearingPricingBeforeLoanSta
        // This method clicks the 'Actions' button and processes required items.
        processRequiredActionItems();
        System.out.println("Processed required action items.");

        // Step: ClearLOSExceptionBeforeLoa
        // This method clicks the 'Exceptions' button and clears all exceptions.
        clearAllExceptions();
        System.out.println("Cleared all exceptions.");
    }
}