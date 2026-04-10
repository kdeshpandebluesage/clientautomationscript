

package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class LOSPropertyInsuranceInfoPage extends BaseClass {

       // private final Page page;
//       public LosPropertyInsuranceInfoPage(Page page) {
//           this.page = page;
        private final Random rand = new Random();
        // --- Locators ---
//        private final Locator propertyInsuranceInfoLink;
//        private final Locator checkPropertyInsuranceDetails;
//        private final Locator propertyInsuranceTypeDropdown;
//        private final Locator addBtn;
//        private final Locator checkAddInsuranceType;
//        private final Locator companyNameInput;
//        private final Locator companyNameDisabledInput;
//        private final Locator annualPremiumInput;
//        private final Locator agentNameInput;
//        private final Locator contactPersonInput;
//        private final Locator contactEmailAddressInput;
//        private final Locator contactTelephoneNumberInput;
//        private final Locator contactFaxNumberInput;
//        private final Locator companyFaxNumberInput;
//        private final Locator issueDateInput;
//        private final Locator expirationDateInput;
//        private final Locator policyNumberInput;
//        private final Locator coverageAmtInput;
//        private final Locator saveBtn;
//        private final Locator savingMask;
//        private final Locator typeOfInsurerDropdown;
//        private final Locator successPopup;
//        private final Locator successPopupOkBtn;
//
//        // Locators for the "Select Insurer" modal
//        private final Locator carrierPayeeSearchIcon;
//        private final Locator searchInsurerCompanyNameInput;
//        private final Locator searchInsurerSearchBtn;
//        private final Locator searchInsurerResultsGrid;
//        private final Locator searchInsurerOkBtn;

    private final String propertyInsuranceInfoLink = "//span[text()='Property Insurance Information']";
    private final String checkPropertyInsuranceDetails = "//span[text()='Property Insurance Details']";
    private final String propertyInsuranceTypeDropdown = "//input[@name='insuranceTypeId']/../following-sibling::td/div";
    private final String addBtn = "//a[@data-selenium-id='addInsuranceType']";
    private final String checkAddInsuranceType = "//span[text()='Add Insurance Type']";
    private final String companyNameInput = "//input[@name='companyName']";
    private final String companyNameDisabledInput = "//input[@name='companyName'][@readonly='readonly']";
    private final String annualPremiumInput = "//input[@name='annualPremiumAmt']";
    private final String agentNameInput ="//input[@name='agentName']";
    private final String contactPersonInput = "//input[@name='contactPerson']";
    private final String contactEmailAddressInput = "//input[@name='contactEmailAddress']";
    private final String contactTelephoneNumberInput = "//input[@name='contactTelephoneNumber']";
    private final String contactFaxNumberInput = "//input[@name='contactFaxNumber']";
    private final String companyFaxNumberInput = "//input[@name='faxNumber']";
    private final String issueDateInput = "//input[@name='issueDate']";
    private final String expirationDateInput ="//input[@name='expirationDate']";
    private final String policyNumberInput = "//input[@name='policyNumber']";
    private final String coverageAmtInput = "//input[@name='coverageAmt']";
    private final String saveBtn = "//a[@data-selenium-id='btnSave']";
    private final String savingMask = "//div[text()='Saving...']";
    private final String typeOfInsurerDropdown ="//input[@name='floodInsurerTypeId']/../following-sibling::td/div";
    private final String successPopup = "//span[text()='Success']";
    private final String successPopupOkBtn = "//div[@class='x-css-shadow'][contains(@style,'display: block')]//a[@data-selenium-id='ok']";

    // Locators for the "Select Insurer" modal
    private final String carrierPayeeSearchIcon = "//div[@data-selenium-id='CarrierPayeeContainer']//img";
    private final String searchInsurerCompanyNameInput = "(//input[@name='companyName'])[2]";
    private final String searchInsurerSearchBtn = "//a[@data-selenium-id='btnSearchInsuranceCarrier']";
    private final String searchInsurerResultsGrid = "//div[@data-selenium-id='searchInsurerCarrierGrid']//tr[contains(@class,'x-grid-data-row')]";
    private final String searchInsurerOkBtn = "//a[@data-selenium-id='btnOk']";
    // Add these locators to the top of your LOSPropertyInsuranceInfoPage class
    private final String agencyAddressLineOneInput = "//input[@name='agencyAddressLineOne']";
    private final String agencyPostalCodeInput = "//input[@name='agencyPostalCode']";
    private final String agencyCityInput = "//input[@name='agencyCity']";
            // --- Initializing Locators ---
//            this.propertyInsuranceInfoLink = page.locator("//span[text()='Property Insurance Information']");
//            this.checkPropertyInsuranceDetails = page.locator("//span[text()='Property Insurance Details']");
          // this.propertyInsuranceTypeDropdown = page.locator("//input[@name='insuranceTypeId']/../following-sibling::td/div");
          //  this.addBtn = page.locator("//a[@data-selenium-id='addInsuranceType']");
           // this.checkAddInsuranceType = page.locator("//span[text()='Add Insurance Type']");
          //  this.companyNameInput = page.locator("//input[@name='companyName']").first(); // Use .first() to be safe
           // this.companyNameDisabledInput = page.locator("//input[@name='companyName'][@readonly='readonly']");
          //  this.annualPremiumInput = page.locator("//input[@name='annualPremiumAmt']");
          //  this.agentNameInput = page.locator("//input[@name='agentName']");
         //   this.contactPersonInput = page.locator("//input[@name='contactPerson']");
          //  this.contactEmailAddressInput = page.locator("//input[@name='contactEmailAddress']");
          //  this.contactTelephoneNumberInput = page.locator("//input[@name='contactTelephoneNumber']");
          //  this.contactFaxNumberInput = page.locator("//input[@name='contactFaxNumber']");
          //  this.companyFaxNumberInput = page.locator("//input[@name='faxNumber']");
          //  this.issueDateInput = page.locator("//input[@name='issueDate']");
        //    this.expirationDateInput = page.locator("//input[@name='expirationDate']");
//            this.policyNumberInput = page.locator("//input[@name='policyNumber']");
//            this.coverageAmtInput = page.locator("//input[@name='coverageAmt']");
//            this.saveBtn = page.locator("//a[@data-selenium-id='btnSave']");
//            this.savingMask = page.locator("//div[text()='Saving...']");
//            this.typeOfInsurerDropdown = page.locator("//input[@name='floodInsurerTypeId']/../following-sibling::td/div");
//            this.successPopup = page.locator("//span[text()='Success']");
//            this.successPopupOkBtn = page.locator("//div[@class='x-css-shadow'][contains(@style,'display: block')]//a[@data-selenium-id='ok']");

            // "Select Insurer" modal locators
//            this.carrierPayeeSearchIcon = page.locator("//div[@data-selenium-id='CarrierPayeeContainer']//img");
//            this.searchInsurerCompanyNameInput = page.locator("(//input[@name='companyName'])[2]");
//            this.searchInsurerSearchBtn = page.locator("//a[@data-selenium-id='btnSearchInsuranceCarrier']");
//            this.searchInsurerResultsGrid = page.locator("//div[@data-selenium-id='searchInsurerCarrierGrid']//tr[contains(@class,'x-grid-data-row')]");
//            this.searchInsurerOkBtn = page.locator("//a[@data-selenium-id='btnOk']");
//        }

        // --- Constructor ---
        public LOSPropertyInsuranceInfoPage(Page page) {
            // FIX: Added super(page) to pass the page object to the BaseClass constructor.
            super(page);
        }
        /**
         * Navigates to the Property Insurance page. In a test, you would call this first.
         */
        public void navigateToPropertyInsurance() {

           page.locator(propertyInsuranceInfoLink).click();
            page.locator(checkPropertyInsuranceDetails).waitFor();
        }

        /**
         * Corresponds to the original `addInsuranceinfo` method.
         */
        public void addInsuranceInfo() {
            String[] propertyDetails = {"Windstorm Insurance"};
            addInsuranceDetails(propertyDetails, false);
        }

        /**
         * Corresponds to the original `addInsuranceDetailsMI` method.
         */
        public void addInsuranceDetailsMI() {
            String[] propertyDetails = {"Flood Insurance"};
            addInsuranceDetails(propertyDetails, true);
        }

        /**
         * Private helper method to handle the core logic for adding insurance details.
         * @param insuranceTypes Array of insurance types to add (e.g., "Windstorm Insurance").
         * @param expectSuccessPopup Whether to wait for a final "Success" pop-up.
         */
        private void addInsuranceDetails(String[] insuranceTypes, boolean expectSuccessPopup) {
            for (String insurance : insuranceTypes) {
                // Select the insurance type from the dropdown
                page.locator(propertyInsuranceTypeDropdown).click();
                page.locator(String.format("//li[text()='%s']", insurance)).click();

                page.locator(addBtn).click();
                page.locator(checkAddInsuranceType).waitFor();

                // Check if company name is disabled. If so, search for a carrier.
                if (page.locator(companyNameDisabledInput).isVisible()) {
                    page.locator(carrierPayeeSearchIcon).click();
                    page.locator(searchInsurerCompanyNameInput).fill("Prime");
                    page.locator(searchInsurerSearchBtn).click();

                    // Wait for results and click a random one
                    page.locator(searchInsurerResultsGrid).first().waitFor();
                    List<Locator> results =  page.locator(searchInsurerResultsGrid).all();
                    if (!results.isEmpty()) {
                        results.get(rand.nextInt(results.size())).click();
                    }
                    page.locator(searchInsurerOkBtn).click();
                } else {
                    page.locator(companyNameInput).fill("Prime");
                }

                if (insurance.equals("Flood Insurance")) {
                    page.locator(typeOfInsurerDropdown).click();
                    page.locator("//li[text()='Mutual Aid']").click();
                }

                // Fill form fields with random data
                page.locator(annualPremiumInput).fill(getRandomValueFromArray(new String[]{"1200", "1300", "1400", "1500"}));
                page.locator(agentNameInput).fill(getRandomValueFromArray(new String[]{"Agent1", "Agent2", "Agent3"}));
                page.locator(contactPersonInput).fill(getRandomValueFromArray(new String[]{"Hima", "Vegiraju", "Donald"}));
                page.locator(contactEmailAddressInput).fill("test@bluesage.com");
                page.locator(contactTelephoneNumberInput).fill("(852) 117-8965");
                page.locator(contactFaxNumberInput).fill("(511) 167-9962");
                page.locator(companyFaxNumberInput).fill("(511) 167-9962");

                // Fill address (logic inferred from original code)
                fillAddress("contactaddress", "123 Main St", "12345", "Anytown");

                // Handle dates
                page.locator(issueDateInput).fill(getFormattedDate(LocalDate.now()));
                page.locator(expirationDateInput).fill(getFormattedDate(LocalDate.now().plusDays(750)));

                page.locator(policyNumberInput).fill(getRandomValueFromArray(new String[]{"A120000", "B130000", "C140000"}));
                page.locator(coverageAmtInput).fill(getRandomValueFromArray(new String[]{"1200000", "1300000", "1400000"}));

                // Save and wait for the "Saving..." mask to disappear
                page.locator(saveBtn).click();
                page.locator(savingMask).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                page.locator(savingMask).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
            }

            if (expectSuccessPopup) {
                page.locator(successPopup).waitFor();
                page.locator(successPopupOkBtn).click();
            }
        }

        // --- Helper Methods ---

        public String getRandomValueFromArray(String[] array) {
            return array[rand.nextInt(array.length)];
        }

        private String getFormattedDate(LocalDate date) {
            return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        }

//        private void fillAddress(String addressType, String line1, String zip, String city) {
//            // This is a placeholder for your address filling logic.
//            // You'd have specific locators for each address type.
//            // For example:
//            // page.locator("//input[@name='agencyAddressLineOne']").fill(line1);
//            // page.locator("//input[@name='agencyPostalCode']").fill(zip);
//            // page.locator("//input[@name='agencyCity']").fill(city);
//            System.out.println("Filling address: " + line1 + ", " + city + ", " + zip);
//        }
    private void fillAddress(String addressType, String line1, String zip, String city) {
        // This now correctly fills the address fields on the web page
        page.locator(agencyAddressLineOneInput).fill(line1);
        page.locator(agencyPostalCodeInput).fill(zip);
        page.locator(agencyCityInput).fill(city);
    }
    }