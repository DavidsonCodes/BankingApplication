package com.example.BankingApplication.model;

import jakarta.persistence.*;

@Entity(name = "accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String accountNumber;

    private double accountBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountUser user;

    public BankAccount(){}

    public BankAccount(String accountNumber, double accountBalance, AccountUser user) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.user = user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public AccountUser getUser() {
        return user;
    }

    public void setUser(AccountUser user) {
        this.user = user;
    }
}
