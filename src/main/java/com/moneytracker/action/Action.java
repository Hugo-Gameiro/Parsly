package com.moneytracker.action;

import com.moneytracker.baseclass.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class Action extends BaseClass implements ActionInterface {

    @Override
    public void fluentWait(WebDriver driver, WebElement element, int timeOut) {
        Wait<WebDriver> wait = null;
        try {
            wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(timeOut))
                    .pollingEvery(Duration.ofMillis(5))
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void click(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void javaScriptClick(WebDriver driver, WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void moveToElement(WebDriver driver, WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
}
