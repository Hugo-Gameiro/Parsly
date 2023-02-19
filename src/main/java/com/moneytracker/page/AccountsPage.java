package com.moneytracker.page;

import org.openqa.selenium.support.PageFactory;

public class AccountsPage extends BasePage{

    public AccountsPage(){
        PageFactory.initElements(getDriver(), this);
    }
}
