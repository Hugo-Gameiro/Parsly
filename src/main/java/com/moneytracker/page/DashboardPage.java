package com.moneytracker.page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.moneytracker.utils.ExtentManager.testLogger;

public class DashboardPage extends BasePage{

    public DashboardPage() {
        PageFactory.initElements(getDriver(), this);
    }


    /*---------- WebElements locators ----------*/
        private final String DASHBOARD_PAGE_TITLE = "//h2[contains(text(),'Dashboard')]";
        private final String NET_WORTH_GROUP_NAMES = "//div[@class='account-widget-group']//a";
        private final String NET_WORTH_GROUP_AMMOUNT =
                "//a[contains(text(),'%s')]//parent::div//following-sibling::div/span";
        private final String NET_WORTH_GROUP =
                "//a[contains(text(),'%s')]//parent::div/parent::div//preceding-sibling::div/span[1]";

        private final String NEW_TRANSACTION_FROM = "//div[@class='ui selection dropdown']";
        private String NEW_TRANSACTION_FROM_OPTION = "//div[@role='option']/span[contains(text(),'%s')]";
        private final String TRANSACTION_AMMOUNT_INPUT = "//input[@type='number']";
        private final String ADD_EXPENSE_BUTTON = "//button[contains(text(),'Add')]";

    /*-------------- WebElements --------------*/

    @FindBy(xpath = DASHBOARD_PAGE_TITLE)
    private WebElement dashboardPageTitle;

    @FindBy(xpath = NEW_TRANSACTION_FROM)
    private WebElement accountNameSelector;

    @FindBy(xpath = TRANSACTION_AMMOUNT_INPUT)
    private WebElement transactionAmmountInput;

    @FindBy(xpath = ADD_EXPENSE_BUTTON)
    private WebElement addExpenseButton;

    public boolean validateDashboardTitle(){
        return dashboardPageTitle.isDisplayed();
    }

    public List<String> getGroupAccountData(String accountName) {

        List groupData = new ArrayList<>();

        List<WebElement> accounts = driverAction.findElementsByLocator(getDriver(), NET_WORTH_GROUP_NAMES);

        boolean isPresent = false;
        for (WebElement acount: accounts) {
            if (acount.getText().equals(accountName)){
                groupData.add( driverAction.findElementByLocator(getDriver(),
                        String.format(NET_WORTH_GROUP, accountName)).getText());
                groupData.add(accountName);
                groupData.add(driverAction.findElementByLocator(getDriver(),
                        String.format(NET_WORTH_GROUP_AMMOUNT, accountName)).getText().split(",")[0]);
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
        accountNameSelector.click();
        String locator = String.format(NEW_TRANSACTION_FROM_OPTION, accountName);
        driverAction.findElementByLocator(getDriver(), String.format(NEW_TRANSACTION_FROM_OPTION, accountName)).click();
        transactionAmmountInput.click();
        transactionAmmountInput.sendKeys(ammount);
        driverAction.loseFocus(transactionAmmountInput);
        addExpenseButton.click();
    }

}
