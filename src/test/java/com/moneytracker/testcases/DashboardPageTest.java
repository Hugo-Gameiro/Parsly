package com.moneytracker.testcases;

import com.moneytracker.page.BasePage;
import com.moneytracker.page.DashboardPage;
import com.moneytracker.page.HomepagePage;
import com.moneytracker.utils.DataReader;
import org.testng.annotations.*;

import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class DashboardPageTest extends BasePage {

    private DashboardPage dashboardPage;
    private HomepagePage homepagePage;

    @Parameters("browser")
    @BeforeMethod(groups = "Regression")
    public void setup(String browser) throws InterruptedException {
        launchApp(browser);
    }

    @Test(groups = "Regression", dataProviderClass = DataReader.class, dataProvider = "userData",
            description = "Validates values update after register an expense")
    public void validateAddExpense(HashMap<String, String> userData) {
        //Precondition - userCreation
        // #if we use a sychronized account these steps are avoided, we only have to log in
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

        //Test register expenses
        dashboardPage.insertExpense(userData.get("BankAccountName"), userData.get("DefaultExpenseAmmount"));
        dashboardPage.insertExpense(userData.get("PocketName"), userData.get("DefaultExpenseAmmount"));

        //
        int bankExpectedAmmout =
                parseInt(userData.get("BankAmmount_EUR")) - parseInt(userData.get("DefaultExpenseAmmount"));
        int cashExpectedAmmout =
                parseInt(userData.get("DefaultCashAmmount")) - parseInt(userData.get("DefaultExpenseAmmount"));


        dashboardPage.validateaccoutDetails(
                userData.get("BankGroup"), userData.get("BankAccountName"), bankExpectedAmmout + "",
                dashboardPage.getGroupAccountData(userData.get("BankAccountName")));

        dashboardPage.validateaccoutDetails(
                userData.get("CashGroup"), userData.get("PocketName"), cashExpectedAmmout + "",
                dashboardPage.getGroupAccountData(userData.get("PocketName")));
    }

    @AfterMethod(groups = {"Regression"})
    public void testTearDown() {
        getDriver().quit();
    }
}
