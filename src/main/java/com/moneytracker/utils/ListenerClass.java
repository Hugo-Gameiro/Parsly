package com.moneytracker.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.moneytracker.baseclass.BaseClass.getDriver;

public class ListenerClass extends ExtentManager implements ITestListener {

    private ScreenShot screenShot = new ScreenShot();

    public void onTestStart(ITestResult result) {
        testLogger = extentReport.createTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            testLogger.log(Status.PASS, result.getName() + " passed with success.");
            String imgPath = screenShot.takeScreenShot(getDriver(), result.getName());
            testLogger.pass(MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
        }
    }

    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                testLogger.log(Status.FAIL,
                        MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
                testLogger.log(Status.FAIL,
                        MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
                String imgPath = screenShot.takeScreenShot(getDriver(), result.getName());

                testLogger.fail(MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void OnTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            testLogger.log(Status.SKIP, result.getName() + " skipped test");
        }
    }
}
