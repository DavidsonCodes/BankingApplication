package com.example.BankingApplication.repository;

import com.example.BankingApplication.model.AccountUser;
import com.example.BankingApplication.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount findByUser(AccountUser user);
    BankAccount findBankAccountByAccountNumber(String accountNumber);
}
