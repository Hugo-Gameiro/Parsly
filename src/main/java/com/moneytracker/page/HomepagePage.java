package com.moneytracker.page;

import org.openqa.selenium.support.PageFactory;

public class HomepagePage extends BasePage{

    public HomepagePage(){
        PageFactory.initElements(getDriver(), this);
    }

    public void validateFields(){

    }
}
