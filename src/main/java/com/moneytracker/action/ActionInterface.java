package com.moneytracker.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface ActionInterface {

    void fluentWait(WebDriver driver, WebElement element, int timeOut);
    void click(WebDriver driver, WebElement element);
    void javaScriptClick(WebDriver driver, WebElement element);
    void moveToElement(WebDriver driver, WebElement element);
    WebElement findElementByLocator(WebDriver driver, String locator);
    WebElement findElementByContains(WebDriver driver, String text);
    void loseFocus(WebElement element);
    List<WebElement> findElementsByLocator(WebDriver driver, String locator);
    List<String> getElementsText(List<WebElement> elementList);
    String getText(WebDriver driver, String locator);
}
