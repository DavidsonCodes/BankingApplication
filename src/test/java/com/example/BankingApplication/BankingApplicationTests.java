package com.example.BankingApplication;

import com.example.BankingApplication.controller.AccountUserController;
import com.example.BankingApplication.model.AccountUser;
import com.example.BankingApplication.repository.AccountUserRepository;
import com.example.BankingApplication.service.AccountUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

//@SpringBootTest
class BankingApplicationTests {

	@MockBean
	private AccountUserRepository accountUserRepository;

//	@MockBean
//	private AccountUserService anotherAccountService;

//	@Autowired
	private AccountUserController accountUserController;

//	@Autowired
	private AccountUserService accountUserService;

//	@Test
//	public void getAllAccountUsers(){
//
//		when(accountUserRepository.findAll()).thenReturn(
//				List.of(
//						new AccountUser("String firstName", "String middleName", "String lastName", "admin@gmail.com", "String password", "String phoneNumber"),
//						new AccountUser("String firstName", "String middleName", "String lastName", "String username", "String password", "String phoneNumber")
//				)
//		);
//
//	assertEquals(2, accountUserService.getAllAccountUsers().getBody().size());
//	assertEquals("admin@gmail.com", accountUserService.getAllAccountUsers().getBody().get(0).getUsername());
//
//	}
//
//	@Test
//	public void getAccountUserByUsername(){
//		AccountUser accountUser = new AccountUser("String firstName", "String middleName", "String lastName", "admin@gmail.com", "String password", "String phoneNumber");
//		when(accountUserRepository.getByUsername("admin@gmail.com")).thenReturn(accountUser);
//
////		when(accountUserRepository.findById(8)).thenThrow(new RuntimeException("No such element found"));
//
//		assertNotNull(accountUserService.getAccountUserByUsername("admin@gmail.com").getBody());
//		assertEquals("admin@gmail.com", accountUserService.getAccountUserByUsername("admin@gmail.com").getBody().getUsername());
//		assertEquals(HttpStatus.OK, accountUserService.getAccountUserByUsername("admin@gmail.com").getStatusCode());
////		assertEquals("No such element found", accountUserService.getAccountUserById(8).getBody());
//
//	}
//
//	@Test
//	public void getAccounts(){
//
//		when(accountUserService.getAllAccountUsers()).thenReturn(
//				new ResponseEntity<>(
//						List.of(new AccountUser("String firstName", "String middleName", "String lastName", "admin@gmail.com", "String password", "String phoneNumber"),
//								new AccountUser("String firstName", "String middleName", "String lastName", "admin@gmail.com", "String password", "String phoneNumber"))
//				, HttpStatus.OK)
//		);
//
//		assertEquals(2, accountUserController.getAllAccountUsers().getBody().size());
//		assertEquals(HttpStatus.OK, accountUserController.getAllAccountUsers().getStatusCode());
//
//	}


	@Test
	void contextLoads() {
	}

}
