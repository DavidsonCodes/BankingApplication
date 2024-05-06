package com.example.BankingApplication.service;

import com.example.BankingApplication.exception.TransactionException;
import com.example.BankingApplication.model.BankAccount;
import com.example.BankingApplication.model.TransactionType;
import com.example.BankingApplication.model.Transactions;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class BankingOperationService {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionService transactionService;

    public ResponseEntity<BankAccount> depositFund(String accountNumber, double amount){
        if( amount < 1 ){
            throw new TransactionException("You can not deposit negative amount");
        }
        BankAccount account = bankAccountService.getByAccountNumber(accountNumber).getBody();
        assert account != null;

        account.setAccountBalance(amount + account.getAccountBalance());

        Transactions transaction = new Transactions();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transactionService.postNewTransaction(transaction);
        return new ResponseEntity<>(bankAccountService.updateAccount(account).getBody(), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> withdrawFund(String accountNumber, double amount){
        if( amount < 1 ){
            throw new TransactionException("You can not deposit negative amount");
        }
        BankAccount account = bankAccountService.getByAccountNumber(accountNumber).getBody();
        assert account != null;

        if( account.getAccountBalance() < amount ){
            throw new TransactionException("Insufficient Balance");
        }
        account.setAccountBalance(account.getAccountBalance() - amount);

        Transactions transaction = new Transactions();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transactionService.postNewTransaction(transaction);
        return new ResponseEntity<>(bankAccountService.updateAccount(account).getBody(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> transferFunds(String accountFrom, String accountTo, double amount ){
        try{
            withdrawFund(accountFrom, amount);
            depositFund(accountTo, amount);
            return new ResponseEntity<>("Transaction Successful", HttpStatus.OK);
        } catch (TransactionException transactionException){
            System.out.println(transactionException.getMessage());
        }
        return new ResponseEntity<>("Transaction failed", HttpStatus.NOT_ACCEPTABLE);
    }
}
