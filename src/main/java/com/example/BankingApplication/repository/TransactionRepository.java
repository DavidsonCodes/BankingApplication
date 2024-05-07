package com.example.BankingApplication.repository;

import com.example.BankingApplication.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByTransactionId(String transactionId);

}
