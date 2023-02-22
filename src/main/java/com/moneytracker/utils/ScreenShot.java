package com.moneytracker.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShot {

    public String takeScreenShot(WebDriver driver, String filename) {

        TakesScreenshot screenShot = (TakesScreenshot) driver;
        File source = screenShot.getScreenshotAs(OutputType.FILE);
        String destination = ExtentManager.reportPathDirectory + "/screenshots/" + Timestamp.getTimeStamp() + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            e.getMessage();
        }
        return destination;
    }
}