package com.example.BankingApplication.service;

import com.example.BankingApplication.model.Transactions;
import com.example.BankingApplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<List<Transactions>> getAllTransactions(){
        return new ResponseEntity<>(transactionRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Transactions> getById(long id){
        return new ResponseEntity<>(transactionRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<Transactions> getByTransactionId(String transactionId){
        return new ResponseEntity<>(transactionRepository.findByTransactionId(transactionId), HttpStatus.OK);
    }

    public ResponseEntity<Transactions> postNewTransaction(Transactions transactions){
        StringBuilder transId = new StringBuilder();
        transId.append("TXN");
        for( int i = 0; i < 10; i++ ){
            int random = new Random().nextInt(10);
            transId.append(random);
        }
        transactions.setTransactionId(transId.toString());
        transactions.setTransactionDate(new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(transactionRepository.save(transactions), HttpStatus.CREATED);
    }

}
