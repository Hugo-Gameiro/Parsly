package com.moneytracker.testcases;

import com.moneytracker.page.BasePage;
import com.moneytracker.page.HomepagePage;
import com.moneytracker.page.SignInPage;
import com.moneytracker.utils.DataReader;
import org.testng.annotations.*;

import java.util.HashMap;

public class LoginPageTest extends BasePage {

    private SignInPage signInPage;
    private HomepagePage homepagePage;

    @Test(groups = "Regression", dataProviderClass = DataReader.class, dataProvider = "userData",
            description = "Validates Sign In")
    public void signIn(HashMap<String, String> userData) {
        homepagePage = new HomepagePage();
        signInPage = homepagePage.signIn();
        signInPage.userValidation(userData.get("Email"));
    }
}
