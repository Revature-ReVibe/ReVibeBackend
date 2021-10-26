package com.ReVibe.test.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ReVibe.controller.AccountController;
import com.ReVibe.model.Account;
import com.ReVibe.service.AccountService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Mock
	private AccountService accountService;

	@InjectMocks
	private AccountController accountController;

	@Autowired
	private MockMvc mockMvc;

	private Account account;

	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}

	@Test
	public void testGetAll() {
		ArrayList<Account> accounts = new ArrayList<Account>();
		Account account1 = new Account(1, "John", "jsmith", "root", "j@smith.com", null, null, null);
		Account account2 = new Account(2, "Bill", "Bsmith", "toor", "B@smith.com", null, null, null);
		Account account3 = new Account(4, "Tim", "Doe", "admin", "Tim@gmail.com", null, null, null);

		accounts.add(account1);
		accounts.add(account2);
		accounts.add(account3);

		accountController.getall();

		Assertions.assertEquals(3, accounts.size());
		verify(accountService, times(1)).findAll();

	}

	
	@Test
	public void testFindUserById() {
		Account account2 = new Account(2, "Bill", "Bsmith", "toor", "B@smith.com", null, null, null);
		
		Mockito.when(accountService.findByUserId(account2.getUserId())).thenReturn(account2);
		
		Assertions.assertEquals(2, account2.getUserId());
	}
	
	@Test
	public void testFindByName() {
		Account account1 = new Account(1, "John", "jsmith", "root", "j@smith.com", null, null, null);
		
		Mockito.when(accountService.findByName("John")).thenReturn(account1);
		
		Assertions.assertEquals("John", account1.getName());
	}
	//need to finish this once i found out what return type is supposed to resemble
	@Test
	public void testSearchAccounts() {
		ArrayList<Account> accounts = new ArrayList<Account>();
		Account account1 = new Account(1, "John", "jsmith", "root", "j@smith.com", null, null, null);
		Account account2 = new Account(2, "Bill", "Bsmith", "toor", "B@smith.com", null, null, null);
		
		accounts.add(account2);
		accounts.add(account1);
		
//		Mockito.when(accountService.findBySearchName(null))
	
	}
	//placeholder until method body is defined
	@Test
	public void testResetPass() {
		
	}
	//placeholder until method body is debugged
	@Test
	public void testUpdateProfile() {
		
	}
	
	@Test
	public void testSaveAccount() {
		Account account1 = new Account(1, "John", "jsmith", "root", "j@smith.com", null, null, null);
		
		accountService.saveAccount(account1);
		verify(accountService, times(1)).saveAccount(account1);
	}
}
