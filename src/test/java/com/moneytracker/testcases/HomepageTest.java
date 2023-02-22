package com.moneytracker.testcases;

import com.moneytracker.baseclass.BaseClass;
import com.moneytracker.page.DashboardPage;
import com.moneytracker.page.HomepagePage;
import com.moneytracker.utils.DataReader;
import org.testng.annotations.*;

import java.util.HashMap;

public class HomepageTest extends BaseClass {

    private HomepagePage homepagePage;
    private DashboardPage dashboardPage;

    @Parameters("browser")
    @BeforeMethod(groups = "Regression")
    public void setup(String browser) throws InterruptedException {
        launchApp(browser);
    }

   @Test(groups = "Regression", dataProviderClass = DataReader.class, dataProvider = "userData",
            description = "Creates an account and validates the inserted data in the Dashbord page")
    public void createAccount(HashMap<String, String> userData) {
        homepagePage = new HomepagePage();
        homepagePage.fillAccountName(userData.get("BankAccountName"));
        homepagePage.setBaseCurrency(userData.get("Base Currency"));
        homepagePage.setAdditionalCurrencies(userData.get("Additional Currency"));
        homepagePage.selectAccountGroup(userData.get("BankGroup"));
        homepagePage.insertValue(userData.get("DefaultCashAmmount"));
        homepagePage.saveAccount();
        homepagePage.fillAccountName(userData.get("PocketName"));
        homepagePage.selectAccountGroup(userData.get("CashGroup"));
        homepagePage.insertValue(userData.get("DefaultCashAmmount"));
        homepagePage.saveAccount();
        dashboardPage = homepagePage.clickFinishButton();
        dashboardPage.validateDashboardTitle();

        dashboardPage.validateaccoutDetails(
                userData.get("BankGroup"), userData.get("BankAccountName"), userData.get("DefaultCashAmmount"),
                dashboardPage.getGroupAccountData(userData.get("BankAccountName")));

        dashboardPage.validateaccoutDetails(
                userData.get("CashGroup"), userData.get("PocketName"),userData.get("DefaultCashAmmount"),
                dashboardPage.getGroupAccountData(userData.get("PocketName")));
    }

    @Test(groups = "Regression", description = "Validates existing Account Groups Options")
    public void validateAccountsGroupOptions() {
        homepagePage = new HomepagePage();
        homepagePage.assertAccountsGroupOptions();
    }

    @AfterMethod(groups = {"Regression"})
    public void testTearDown() {
        getDriver().quit();
    }
}
