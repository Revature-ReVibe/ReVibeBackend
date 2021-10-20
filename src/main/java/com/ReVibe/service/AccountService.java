package com.ReVibe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReVibe.model.Account;
import com.ReVibe.repository.AccountRepository;

@Transactional
@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	public Account saveAccount(Account account) {
		return this.accountRepository.saveAccount(account);
	}
}
