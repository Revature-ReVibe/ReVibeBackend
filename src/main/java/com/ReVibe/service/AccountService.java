package com.ReVibe.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ReVibe.model.Account;
import com.ReVibe.repository.AccountRepository;

@Transactional
@Service("accountService")
public class AccountService {
	
	private AccountRepository accountRepository;
	
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Account findByUserId(int id) {
		return this.accountRepository.findByUserId(id);
	}

	public List<Account> findAll(){
		return this.accountRepository.findAll();
	}

	public Account findByName(String name) {
		return this.accountRepository.findByName(name);
	}
	
	public void merge(Account account) {
		this.accountRepository.setAccountInfoByUserId(account.getName(),account.getPassword(),account.getUsername(),account.getProfilePic(),account.getUserId());
	}

	public List<Account> findBySearchName(String name) {
		return this.accountRepository.findByNameContaining(name);
	}

}
