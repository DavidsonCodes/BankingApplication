package com.example.BankingApplication.controller;

import com.example.BankingApplication.exception.TransactionException;
import com.example.BankingApplication.model.*;
import com.example.BankingApplication.service.BankingOperationService;
import com.example.BankingApplication.service.TransactionService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    @Autowired
    private BankingOperationService operationService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<BankAccount> depositFund(@RequestBody OperationRequest request) throws MessagingException{
        return new ResponseEntity<>(operationService.depositFund(request.getAccountNumber(), request.getAmount(), transactionService.generateTxnId()).getBody(), HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankAccount> withdrawFund(@RequestBody OperationRequest request){
        return new ResponseEntity<>(operationService.withdrawFund(request.getAccountNumber(), request.getAmount(), transactionService.generateTxnId()).getBody(), HttpStatus.OK);
    }

    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity<String> transferFunds(@RequestBody TransferRequest request ) throws MessagingException {
        try{
            operationService.transferFunds(request.getAccountFrom(), request.getAccountTo(), request.getAmount());
            return new ResponseEntity<>("Transaction Successful", HttpStatus.ACCEPTED);
        } catch (TransactionException transactionException){
            System.out.println(transactionException.getMessage());
        }
        return new ResponseEntity<>("Transaction failed", HttpStatus.NOT_ACCEPTABLE);
    }
}

