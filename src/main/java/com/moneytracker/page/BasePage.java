package com.moneytracker.page;

import com.moneytracker.baseclass.BaseClass;
import com.moneytracker.action.Action;
import org.testng.Assert;

import java.util.List;

public class BasePage extends BaseClass {

    Action driverAction = new Action();

    /*---------- WebElements locators ----------*/

    /**
     * Asserts account details in Dashbord Page - is mandatory to maintain the order
     * @param expectedGroup
     * @param expectedName
     * @param expectedAmmount
     * @param actualData
     */
    public void validateaccoutDetails(
            String expectedGroup, String expectedName, String expectedAmmount, List<String> actualData){
        Assert.assertEquals(actualData.size(), 3,
                "There should be 3 WebElements to verify: Group, Account Name and Ammount");

        Assert.assertEquals(actualData.get(0), expectedGroup,
                "Expected group: " + expectedGroup + " obtained" + actualData.get(0));

        Assert.assertEquals(actualData.get(1), expectedName,
                "Expected group: " + expectedName + " obtained" + actualData.get(1));

        Assert.assertEquals(actualData.get(2), expectedAmmount,
                "Expected group: " + expectedAmmount + " obtained" + actualData.get(2));
    }
}
