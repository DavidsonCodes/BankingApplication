package com.example.BankingApplication;

import com.example.BankingApplication.config.AccountConfiguration;
import com.example.BankingApplication.model.AccountUser;
import com.example.BankingApplication.model.Role;
import com.example.BankingApplication.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableAsync
public class BankingApplication implements CommandLineRunner {

	@Autowired
	private AccountUserService accountUserService;

	@Autowired
	private AccountConfiguration accountConfiguration;




	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		PasswordEncoder encoder = accountConfiguration.passwordEncoder();

		AccountUser adminUser = new AccountUser();
		adminUser.setFirstName("Admin");
		adminUser.setLastName("Admin");
		adminUser.setRole(Role.ADMIN);
		adminUser.setUsername("admin@gmail.com");
		adminUser.setPassword(encoder.encode("password"));

		AccountUser exist = accountUserService.getAccountUserByUsername("admin@gmail.com").getBody();

		if( exist == null ){
			accountUserService.postAccountUser(adminUser);
		}

	}
}
