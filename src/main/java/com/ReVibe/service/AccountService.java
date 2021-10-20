package com.ReVibe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.ReVibe.model.Account;
import com.ReVibe.repository.AccountRepository;

@Transactional
@Service("AccountService")
public class AccountService {

	private AccountRepository accountRepository;
	
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@PostMapping
	public void merge(Account account) {
		this.accountRepository.setAccountInfoByUserId(account.getName(),account.getPassword(),account.getUsername(),account.getUserId());
	}

}
