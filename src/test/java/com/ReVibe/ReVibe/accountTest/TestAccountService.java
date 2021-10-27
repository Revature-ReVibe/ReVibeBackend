package com.ReVibe.ReVibe.accountTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
	public void testGetAll() throws Exception {
		List<Account> accounts = new LinkedList<>();
		accounts.add(new Account(1, "userName1", "password1", "George Washington", null, null, null, null));
		accounts.add(new Account(2, "userName2", "password2", "John Adams", null, null, null, null));
		accounts.add(new Account(3, "userName3", "password3", "Thomas Jefferson", null, null, null, null));
		Mockito.when(accountRepository.findAll()).thenReturn(accounts);
		List<Account> testAccounts = accountService.findAll();
		assertThat(testAccounts)
			.isNotNull()
			.hasSize(3);
	}
	
	@Test
	public void testFindByUserId() {
		Mockito.when(accountService.findByUserId(Mockito.any(Integer.class))).thenReturn(new Account());
		Account account = accountService.findByUserId(Mockito.any(Integer.class));
		assertThat(account).isNotNull();
	}
	
	@Test
	public void testFindByName() {
		Mockito.when(accountService.findByName(Mockito.any(String.class))).thenReturn(new Account());
		Account account = accountService.findByName(Mockito.any(String.class));
		assertThat(account).isNotNull();
	}

	@Test
	public void testSave() {
		Mockito.when(accountRepository.saveAccount(new Account(1, "KCastillo", "p4ssword", "Kevin Castillo", null, null, null, null)))
		.thenReturn(new Account(1, "KCastillo", "p4ssword", "Kevin Castillo", null, null, null, null));

		Account account = accountService.saveAccount(new Account(1, "KCastillo", "p4ssword", "Kevin Castillo", null, null, null, null));

		assertThat(account).isNotNull();
		verify(accountRepository, times(1)).save(account);
	}
}
