package com.example.BankingApplication.repository;

import com.example.BankingApplication.model.AccountUser;
import com.example.BankingApplication.model.KnowYourCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KYCRepository extends JpaRepository<KnowYourCustomer, Integer> {
    KnowYourCustomer getByUser(AccountUser user);
}
