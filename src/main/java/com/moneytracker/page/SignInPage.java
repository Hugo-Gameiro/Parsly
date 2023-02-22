package com.moneytracker.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignInPage extends BasePage {

    public SignInPage() {
        PageFactory.initElements(getDriver(), this);
    }

    /*------------ WebElements Text ------------*/

    /*---------- WebElements locators ----------*/

    private final String EMAIL_INPUT = "//input[@name='email']";
    private final String SEND_CODE_BUTTON = "//button[contains(text(),'Send Code')]";
    private final String VALIDATION_CODE_INPUT = "//button[contains(text(),'Your Code')]";
    /*-------------- WebElements --------------*/

    @FindBy(xpath = EMAIL_INPUT)
    private WebElement emailInput;

    @FindBy(xpath = SEND_CODE_BUTTON)
    private WebElement sendCodeButton;

    @FindBy(xpath = VALIDATION_CODE_INPUT)
    private WebElement validationCodeInput;

    public void userValidation(String email) {
        emailInput.click();
        emailInput.sendKeys(email);
        driverAction.loseFocus(emailInput);
        sendCodeButton.click();
        Assert.assertTrue(validationCodeInput.isDisplayed());
    }
}
