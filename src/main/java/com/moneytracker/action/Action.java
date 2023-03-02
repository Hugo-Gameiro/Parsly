package com.moneytracker.action;

import com.moneytracker.baseclass.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Action extends BaseClass implements ActionInterface {

    private final String containsLocator = "//*[contains(text(),'%s')]";
    private final int pollingDuration = 5;

    /**
     *
     * @param driver
     * @param element
     */
    @Override
    public void fluentWait(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait = null;
        try {
            wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(parseInt(properties.getProperty("webelementTimeout"))))
                    .pollingEvery(Duration.ofMillis(pollingDuration))
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param driver
     * @param locator
     */
    @Override
    public void fluentWaitByLocator(WebDriver driver, String locator) {
        Wait<WebDriver> wait = null;
        try {
            wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(parseInt(properties.getProperty("webelementTimeout"))))
                    .pollingEvery(Duration.ofMillis(pollingDuration))
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
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
        fluentWait(driver, element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    /**
     *
     * @param driver
     * @param element
     */
    public void javaScriptClick(WebDriver driver, WebElement element){
        fluentWait(driver, element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     *
     * @param driver
     * @param element
     */
    public void moveToElement(WebDriver driver, WebElement element){
        fluentWait(driver, element);
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
        fluentWaitByLocator(driver, locator);
        return driver.findElement(By.xpath(locator));
    }

    /**
     *
     * @param driver
     * @param text
     * @return
     */
    public WebElement findElementByContains(WebDriver driver, String text){
        String locator = String.format(containsLocator, text);
        fluentWaitByLocator(driver, locator);
        return driver.findElement(By.xpath(locator));
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
        fluentWaitByLocator(driver, locator);
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
        fluentWaitByLocator(driver, locator);
        return findElementByLocator(getDriver(), locator).getText();
    }
}
