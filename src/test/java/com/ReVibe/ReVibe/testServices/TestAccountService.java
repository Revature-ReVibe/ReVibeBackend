
package com.ReVibe.ReVibe.testServices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.ReVibe.model.Account;
import com.ReVibe.repository.AccountRepository;
import com.ReVibe.service.AccountService;
import com.google.common.base.Verify;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class TestAccountService {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;
	
	
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(accountService).build();
	}
	
	@Test
	public void testFindByUserId() {
		int id = 1;
		Account account = new Account(1, "james", "root", "james smith", "js@gmail.com", "", null, null);
		
		Mockito.when(accountRepository.findByUserId(id)).thenReturn(account);
		
		assertEquals(1, account.getUserId());
		
				
	}
	
	@Test
	public void testFindAllSuccess() {
		List<Account> accounts = new ArrayList<Account>();
		
		Account account = new Account(1, "james", "root", "james smith", "js@gmail.com", "", null, null);
		Account account2 = new Account(2, "Bob", "root", "Robert James", "RJ@gmail.com", "", null, null);
		accounts.add(account2);
		accounts.add(account);
		
		when(accountRepository.findAll()).thenReturn(accounts);
		
		
		assertEquals(2, accounts.size());
		
	}
	
	@Test
	public void testFindByNameSuccess() {
		String name = "james smith";
		Account account = new Account(1, "james", "root", "james smith", "js@gmail.com", "", null, null);
		
		when(accountRepository.findByName(name)).thenReturn(account);
		
		
		assertEquals(name, account.getName());
	}
	
	@Test
	public void testFindByNameFailed() {
		String name = "Bob";
		Account account = new Account(1, "james", "root", "james smith", "js@gmail.com", "", null, null);
		
		when(accountRepository.findByName(name)).thenReturn(null);
		
		
		
	}
	
	@Test
	public void testMergeSuccess() {
		Account account = new Account(1, "james", "root", "james smith", "js@gmail.com", "", null, null);
		
		accountRepository.setAccountInfoByUserId(account.getName(),account.getPassword(),account.getUsername(),account.getProfilePic(),account.getUserId());
		
		verify(accountRepository, times(1)).setAccountInfoByUserId(account.getName(),account.getPassword(),account.getUsername(),account.getProfilePic(),account.getUserId());
				
	}
	
	@Test
	public void testFindBySearchNameSuccess() {
		String name = "james smith";
		Account account = new Account(1, "james", "root", "james smith", "js@gmail.com", "", null, null);
		Account account3 = new Account(3, "jmoney", "root", "james smith", "jms@gmail.com", "", null, null);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(account3);
		accounts.add(account);
		
		when(accountRepository.findByNameContaining(name)).thenReturn(accounts);
		
		
		assertEquals(name, accounts.get(0).getName());
		assertEquals(name, accounts.get(1).getName());
	}
	
	@Test
	public void testSaveAccount() {
		Account account = new Account(1, "james", "root", "james smith", "js@gmail.com", "", null, null);
		
		accountRepository.saveAccount(account);
		
		verify(accountRepository, times(1)).saveAccount(account);
	}
	
	@Test
	public void testFindByUsernameAndPasswordSuccess() {
		String username = "james";
		String password = "root";
		Account account = new Account(1, "james", "root", "james smith", "js@gmail.com", "", null, null);
		
		when(accountRepository.findByUsernameAndPassword(username, password)).thenReturn(account);
		
		assertEquals(username, account.getUsername());
		assertEquals(password, account.getPassword());
		
	}
	
	@Test
	public void testFindByUsernameAndPasswordFailure() {
		String username = "bill";
		String password = "root";
		
		accountRepository.findByUsernameAndPassword(username, password);
		
		verify(accountRepository, times(1)).findByUsernameAndPassword(username, password);
				
	}
	
	@Test
	public void testFindById() {
		int id = 1;
		Account account = new Account(1, "james", "root", "james smith", "js@gmail.com", "", null, null);
		
		when(accountRepository.getById(id)).thenReturn(account);
		
		assertEquals(id, account.getUserId());
	}
	
	public void testFindByEmail() {
		Account account = new Account(1, "james", "root", "james smith", "js@gmail.com", "", null, null);
		String email = "js@gmail.com";
		
		when(accountRepository.findByEmail(email)).thenReturn(account);
		
		verify(accountRepository, times(1)).findByEmail(email);
		assertEquals(email, account.getEmail());
	}
}

