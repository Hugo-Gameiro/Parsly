package com.moneytracker.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ActionInterface {

    void fluentWait(WebDriver driver, WebElement element, int timeOut);
    void click(WebDriver driver, WebElement element);
    void javaScriptClick(WebDriver driver, WebElement element);
    void moveToElement(WebDriver driver, WebElement element);
}
