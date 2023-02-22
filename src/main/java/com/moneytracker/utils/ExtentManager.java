package com.moneytracker.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {

    public static ExtentReports extentReport;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentTest testLogger;
    public static String reportPathDirectory;

    public static void setExtent() {

        reportPathDirectory = System.getProperty("user.dir") + "/Test Report/report_" + Timestamp.getTimeStamp() + "/";

        extentReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(
                new File(reportPathDirectory + "Report.html"));
        extentReport.attachReporter(sparkReporter);
    }

    public static void endReport() {
        extentReport.flush();
    }
}
