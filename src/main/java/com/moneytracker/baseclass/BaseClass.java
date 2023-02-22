package com.moneytracker.baseclass;

import com.moneytracker.utils.ExtentManager;
import com.moneytracker.utils.SychronizedWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    public static Properties properties;
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public SychronizedWait sychronizedWait = new SychronizedWait();

    @BeforeSuite(groups = {"Regression"})
    public void loadConfiguration() {

        ExtentManager.setExtent();

        try {
            properties = new Properties();
            FileInputStream inputStream = new FileInputStream(
                    System.getProperty("user.dir") + "/Configuration/Config.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public void launchApp(String browser) throws InterruptedException {

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                threadLocalDriver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                threadLocalDriver.set(new FirefoxDriver());
                break;
            case "ie":
                WebDriverManager.iedriver();
                threadLocalDriver.set(new InternetExplorerDriver());
                break;
            default:
                //TODO: Insert new report message
                break;
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(properties.getProperty("url"));
        new SychronizedWait().waitHere(1);
    }

    @AfterSuite(groups = {"Regression"})
    public void suiteTearDown(){
        ExtentManager.endReport();
        threadLocalDriver.remove();
    }
}
