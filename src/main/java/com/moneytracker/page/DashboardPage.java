package com.moneytracker.page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.moneytracker.utils.ExtentManager.testLogger;

public class DashboardPage extends BasePage{


    /*---------- WebElements locators ----------*/
        private final String DASHBOARD_PAGE_TITLE_LOCATOR = "//h2[contains(text(),'Dashboard')]";
        private String NET_WORTH_GROUP_NAMES_LOCATOR = "//div[@class='account-widget-group']//a";
        private String NET_WORTH_GROUP_AMMOUNT_LOCATOR =
                "//a[contains(text(),'%s')]//parent::div//following-sibling::div/span";
        private final String NET_WORTH_GROUP_LOCATOR =
                "//a[contains(text(),'%s')]//parent::div/parent::div//preceding-sibling::div/span[1]";

        private final String NEW_TRANSACTION_FROM_LOCATOR = "//div[@class='ui selection dropdown']";
        private String NEW_TRANSACTION_FROM_OPTION_LOCATOR = "//div[@role='option']/span[contains(text(),'%s')]";
        private final String TRANSACTION_AMMOUNT_INPUT_LOCATOR = "//input[@type='number']";
        private final String ADD_EXPENSE_BUTTON_LOCATOR = "//button[contains(text(),'Add')]";

    /*-------------- WebElements --------------*/

    private WebElement dashboardPageTitle(){
        return driverAction.findElementByLocator(getDriver(), DASHBOARD_PAGE_TITLE_LOCATOR);
    }

    private WebElement accountNameSelector(){
        return driverAction.findElementByLocator(getDriver(), NEW_TRANSACTION_FROM_LOCATOR);
    }

    private WebElement transactionAmmountInput(){
        return driverAction.findElementByLocator(getDriver(), TRANSACTION_AMMOUNT_INPUT_LOCATOR);
    }

    private WebElement addExpenseButton(){
        return driverAction.findElementByLocator(getDriver(), ADD_EXPENSE_BUTTON_LOCATOR);
    };


    /*-------------- Actions --------------*/

    public boolean validateDashboardTitle(){
        return dashboardPageTitle().isDisplayed();
    }

    public List<String> getGroupAccountData(String accountName) {

        List groupData = new ArrayList<>();

        List<WebElement> accounts = driverAction.findElementsByLocator(getDriver(), NET_WORTH_GROUP_NAMES_LOCATOR);

        boolean isPresent = false;
        for (WebElement acount: accounts) {
            if (acount.getText().equals(accountName)){
                groupData.add( driverAction.findElementByLocator(getDriver(),
                        String.format(NET_WORTH_GROUP_LOCATOR, accountName)).getText());
                groupData.add(accountName);
                groupData.add(driverAction.findElementByLocator(getDriver(),
                        String.format(NET_WORTH_GROUP_AMMOUNT_LOCATOR, accountName)).getText().split(",")[0]);
            isPresent = true;
            break;
            }
        }
        if(isPresent == false) {
            testLogger.fail("Element with account " + accountName + "isn't present in Net Worth");
            throw new NoSuchElementException("");
        }
        return groupData;
    }

    public void insertExpense(String accountName, String ammount){
        accountNameSelector().click();
        String locator = String.format(NEW_TRANSACTION_FROM_OPTION_LOCATOR, accountName);

        driverAction.findElementByLocator(getDriver(), String.format(NEW_TRANSACTION_FROM_OPTION_LOCATOR, accountName)).click();
        transactionAmmountInput().click();
        transactionAmmountInput().sendKeys(ammount);
        driverAction.loseFocus(transactionAmmountInput());
        addExpenseButton().click();
    }

}
