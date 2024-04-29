package com.example.BankingApplication.repository;

import com.example.BankingApplication.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    Transactions findByTransactionsId(String transactionsId);

}
