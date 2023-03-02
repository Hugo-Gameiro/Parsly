package com.moneytracker.page;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SignInPage extends BasePage {


    /*------------ WebElements Text ------------*/

    /*---------- WebElements locators ----------*/

    private final String EMAIL_INPUT_LOCATOR = "//input[@name='email']";
    private final String SEND_CODE_BUTTON_LOCATOR = "//button[contains(text(),'Send Code')]";
    private final String VALIDATION_CODE_INPUT_LOCATOR = "//button[contains(text(),'Your Code')]";
    /*-------------- WebElements --------------*/

    private WebElement emailInput(){
        return driverAction.findElementByLocator(getDriver(),EMAIL_INPUT_LOCATOR );
    }

    private WebElement sendCodeButton(){
        return driverAction.findElementByLocator(getDriver(),SEND_CODE_BUTTON_LOCATOR);
    }

    private WebElement validationCodeInput() {
       return driverAction.findElementByLocator(getDriver(), VALIDATION_CODE_INPUT_LOCATOR);
    }

    /*-------------- Actions --------------*/

    public void userValidation(String email) {
        emailInput().click();
        emailInput().sendKeys(email);
        driverAction.loseFocus(emailInput());
        sendCodeButton().click();
        Assert.assertTrue(validationCodeInput().isDisplayed());
    }
}
