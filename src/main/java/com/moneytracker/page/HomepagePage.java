package com.moneytracker.page;

import com.moneytracker.enums.AccountsGroupEnum;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HomepagePage extends BasePage {


    /*------------ WebElements Text ------------*/

    private String UPDATE_RATE_TEXT = "Update exchange rate";

    /*---------- WebElements locators ----------*/

    private final String ACCOUNT_NAME_LOCATOR = "//input[@placeholder='Account name']";

    private final String BASE_CURRENCY_INPUT = "//label[text()='Base Currency']//parent::div//input";
    private final String ADDITIONAL_CURRENCY_INPUT_LOCATOR =
            "//label[text()='Additional Currencies (optional)']//parent::div//input";
    private final String CURRENCY_SELECTION_LOCATOR = "//div[@class='visible menu transition']//div";

    private final String GROUP_SELECTOR_INPUT_LOCATOR = "//label[text()='Group']//parent::div/div/div[@role='alert']";
    private final String GROUP_SELECTOR_OPTIONS_LOCATOR = "//label[text()='Group']//parent::div//div[@role='option']/span";
    private final String BALANCE_INPUT_LOCATOR = "//*[@placeholder='Balance']";

    private final String SAVE_ACCOUNT_BUTTON_LOCATOR = "//button[contains(text(),'Save Account')]";

    private final String FINISH_BUTTON_LOCATOR = "//button[contains(text(),'Finish')]";

    private final String SIGN_IN_BUTTON_LOCATOR = "//a[contains(text(),'Sign in')]";

    /*-------------- WebElements --------------*/

    private WebElement accountNameBox(){
        return driverAction.findElementByLocator(getDriver(), ACCOUNT_NAME_LOCATOR);
    }

    private WebElement baseCurrencyInput(){
        return driverAction.findElementByLocator(getDriver(), BASE_CURRENCY_INPUT);
    }

    private WebElement additionalCurrencyInput(){
        return driverAction.findElementByLocator(getDriver(), ADDITIONAL_CURRENCY_INPUT_LOCATOR);
    }

    private WebElement groupDropbox(){
        return driverAction.findElementByLocator(getDriver(), GROUP_SELECTOR_INPUT_LOCATOR);
    }

    private WebElement balanceInput(){
        return driverAction.findElementByLocator(getDriver(), BALANCE_INPUT_LOCATOR);
    }

    private WebElement saveAccountButton(){
        return driverAction.findElementByLocator(getDriver(), SAVE_ACCOUNT_BUTTON_LOCATOR);
    }

    private WebElement finishButton(){
        return driverAction.findElementByLocator(getDriver(), FINISH_BUTTON_LOCATOR);
    }

    private WebElement signInButton(){
        return driverAction.findElementByLocator(getDriver(), SIGN_IN_BUTTON_LOCATOR);
    }

    /*-------------- Actions --------------*/

    public void fillAccountName(String account) {
        accountNameBox().click();
        accountNameBox().sendKeys(account);
    }

    public void setBaseCurrency(String baseCurrency) {
        baseCurrencyInput().click();
        baseCurrencyInput().sendKeys(baseCurrency);
        WebElement currencySelection = driverAction.findElementByLocator(getDriver(), CURRENCY_SELECTION_LOCATOR);
        currencySelection.click();
        driverAction.loseFocus(baseCurrencyInput());
    }

    public void setAdditionalCurrencies(String currency) {
        additionalCurrencyInput().click();
        additionalCurrencyInput().sendKeys(currency);
        WebElement currencySelection = driverAction.findElementByLocator(getDriver(), CURRENCY_SELECTION_LOCATOR);
        currencySelection.click();
        WebElement updateChangeRate = driverAction.findElementByContains(getDriver(), UPDATE_RATE_TEXT);
        driverAction.loseFocus(additionalCurrencyInput());
        Assert.assertTrue(updateChangeRate.isDisplayed());
    }

    public void selectAccountGroup(String optionText) {
        groupDropbox().click();

        List<WebElement> optionsList = driverAction.findElementsByLocator(getDriver(), GROUP_SELECTOR_OPTIONS_LOCATOR);
        boolean isOptionPresent = false;
        for (WebElement option : optionsList) {
            if (option.getText().equals(optionText)) {
                isOptionPresent = true;
                option.click();
                break;
            }
        }
        if (!isOptionPresent) {
            throw new NoSuchElementException("Option " + optionText + " was not present");
        }
    }

    /**
     * Evaluates if all declared options in enum AccountsGroupEnum are available in the dropbox and guarantees and
     * ensures that the number of options in the enum and dropbox match
     */
    public void assertAccountsGroupOptions() {

        boolean isAccountGroupPresent = false;

        groupDropbox().click();
        List<WebElement> options = driverAction.findElementsByLocator(getDriver(), GROUP_SELECTOR_OPTIONS_LOCATOR);

        Assert.assertEquals(AccountsGroupEnum.values().length, options.size(),
                "Enum AccountsGroup and avaliable options should match");

        for (AccountsGroupEnum accountsGroup : AccountsGroupEnum.values()) {
            isAccountGroupPresent = false;

            for (WebElement option : options) {
                if (accountsGroup.getAccountOptionString().equals(option.getText())) {
                    isAccountGroupPresent = true;
                    break;
                }
            }
            if (!isAccountGroupPresent) {

                throw new NoSuchElementException("The value " + accountsGroup.toString() + " should exist in dropdown");
            }
        }
    }

    public void insertValue(String value) {
       balanceInput().click();
       balanceInput().sendKeys(value);
       driverAction.loseFocus(balanceInput());
    }

    public void saveAccount() {
        saveAccountButton().click();
    }

    public DashboardPage clickFinishButton(){
        finishButton().click();
        return new DashboardPage();
    }

    public SignInPage signIn(){
        signInButton().click();
        return new SignInPage();
    }
}

