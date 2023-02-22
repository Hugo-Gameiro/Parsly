package com.moneytracker.action;

import com.moneytracker.baseclass.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Action extends BaseClass implements ActionInterface {

    private final String containsLocator = "//*[contains(text(),'%s')]";

    /**
     *
     * @param driver
     * @param element
     * @param timeOut
     */
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

    /**
     *
     * @param driver
     * @param element
     */
    public void click(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    /**
     *
     * @param driver
     * @param element
     */
    public void javaScriptClick(WebDriver driver, WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     *
     * @param driver
     * @param element
     */
    public void moveToElement(WebDriver driver, WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    /**
     *
     * @param driver
     * @param locator
     * @return
     */
    public WebElement findElementByLocator(WebDriver driver, String locator){
        return driver.findElement(By.xpath(locator));
    }

    /**
     *
     * @param driver
     * @param text
     * @return
     */
    public WebElement findElementByContains(WebDriver driver, String text){
        return driver.findElement(By.xpath(String.format(containsLocator, text)));
    }

    /**
     *
     * @param element
     */
    public void loseFocus(WebElement element){
        element.sendKeys(Keys.TAB);
    }

    /**
     *
     * @param driver
     * @param locator
     * @return
     */
    public List<WebElement> findElementsByLocator(WebDriver driver, String locator){
        return driver.findElements(By.xpath(locator));
    }

    /**
     *
     * @param elementList
     * @return
     */
    public List<String> getElementsText(List<WebElement> elementList){
        List<String> elementsText = new ArrayList<>();
        for (WebElement element: elementList) {
            elementsText.add(element.getText());
        }
        return elementsText;
    }

    /**
     *
     * @param driver
     * @param locator
     * @return
     */
    public String getText(WebDriver driver, String locator){
        return findElementByLocator(getDriver(), locator).getText();
    }
}
