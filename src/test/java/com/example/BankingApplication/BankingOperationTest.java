package com.example.BankingApplication;

import com.example.BankingApplication.service.AccountUserService;
import com.example.BankingApplication.service.BankingOperationService;
import com.example.BankingApplication.service.TransactionService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BankingOperationTest {

    @Autowired
    private BankingOperationService bankingOperationService;

    @Autowired
    private TransactionService transactionService;

//    @Test
//    public void testDeposit() throws MessagingException {
//        assertEquals(351300.00, bankingOperationService.depositFund("7762437461", 50000.00, transactionService.generateTxnId()).getBody().getAccountBalance());
//    }
//
//    @Test
//    public void testWithdrawal() throws MessagingException {
//        assertEquals(270000.00, bankingOperationService.withdrawFund("4025725023", 50000.00, transactionService.generateTxnId()).getBody().getAccountBalance());
//    }
//
//    @Test
//    public void testTransfer() throws MessagingException {
//        String transId = transactionService.generateTxnId();
//        assertEquals(220000.00, bankingOperationService.withdrawFund("4025725023", 50000.00, transId ).getBody().getAccountBalance());
//        assertEquals(401300.00, bankingOperationService.depositFund("7762437461", 50000.00, transId).getBody().getAccountBalance());
//
//    }

}
