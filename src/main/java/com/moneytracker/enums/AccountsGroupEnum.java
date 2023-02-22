package com.moneytracker.enums;

public enum AccountsGroupEnum {

    CASH("Cash"),
    Bank_Account("Bank Account"),
    DEPOSIT("Deposit"),
    CREDIT("Credit"),
    ASSET("Asset");

    private String accountOption;

    AccountsGroupEnum(final String accountOption){
        this.accountOption = accountOption;
    }

    public String getAccountOptionString(){
        return accountOption;
    }
}
