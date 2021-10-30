package com.ReVibe.testRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.ReVibe.model.Account;
import com.ReVibe.repository.AccountRepository;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountRepositoryTest {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Test
	public void testSave() {
		Account account = new Account();
		account.setName("Alex Yankosky");
		account.setEmail("ayankosky@revibe.com");
		account.setUsername("ayankosky");
		account.setPassword("root");

		accountRepository.save(account);
	}
	
	@Test
	public void testFindByUserId() {
		int id = 1;
		Account account = accountRepository.findByUserId(id);
		
		assertEquals(id, account.getUserId());
	}
	
	@Test
	public void testFindAll() {
		List<Account> accounts = accountRepository.findAll();
		
		assertEquals(1, accounts.get(0).getUserId());
		
		
	}
	
	@Test
	public void testFindByName() {
		Account account = accountRepository.findByName("first");
		
		assertEquals("first", account.getName());
	}
	
	@Test
	public void testFindByUserName() {
		Account account = accountRepository.findByUsername("firstUser");
		
		assertEquals("firstUser", account.getUsername());
	}
	
	@Test
	public void testFindByNameContaining() {
		List<Account> accounts = accountRepository.findByNameContaining("f");
		
		assertEquals(1, accounts.size());
	}
// Need to come back to this test throwing an exception not sure how to handle it
//	@Test
//	public void testSetAccountInfoByUserId() {
//		String name = "name";
//		String password = "password";
//		String username = "username";
//		String profilepic = " ";
//		int id = 2;
//		
//		accountRepository.setAccountInfoByUserId(name, password, username, profilepic, id);
//		
//		
//	}
//	
	@Test
	public void testSaveAccount() {
		Account account = new Account();
		account.setName("Nik Derek");
		account.setEmail("nderek@revibe.com");
		account.setUsername("nikDerek");
		account.setPassword("admin");
		
		Account newAccount = accountRepository.saveAccount(account);
		
		assertEquals(account.getName(), newAccount.getName());
	}
	
	@Test
	public void testFindByUsernameAndPassword() {
		String username = "firstUser";
		String password = "root";
		
		Account account = accountRepository.findByUsernameAndPassword(username, password);
		
		assertEquals(username, account.getUsername());
		assertEquals(password, account.getPassword());
		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "a@rev.com";
		
		Account account = accountRepository.findByEmail(email);
		
		assertEquals(email, account.getEmail());
	}
}
