package com.moneytracker.testcases;

import com.moneytracker.baseclass.BaseClass;
import com.moneytracker.page.HomepagePage;
import org.testng.annotations.*;

public class HomepageTest extends BaseClass {

    private HomepagePage homepagePage;

    @Parameters("browser")
    @BeforeMethod(groups = "Regression")
    public void setup(String browser) throws InterruptedException {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Regression"})
    public void testTearDown() {
        getDriver().manage().deleteAllCookies();
        getDriver().navigate().refresh();
    }

    @Test(groups = "Regression")
    public void validateHomepageFields() {
        System.out.println("first method!!!");
    }
}
