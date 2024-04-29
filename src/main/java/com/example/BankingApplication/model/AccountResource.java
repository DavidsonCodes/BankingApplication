package com.example.BankingApplication.model;

import org.springframework.hateoas.RepresentationModel;

public class AccountResource extends RepresentationModel<AccountResource> {

    private AccountUser accountUser;

    public void setAccountUser(AccountUser user){
        this.accountUser = user;
    }

    public AccountUser getAccountUser(){
        return accountUser;
    }

}
