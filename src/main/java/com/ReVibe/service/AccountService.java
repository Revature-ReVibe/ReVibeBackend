package com.ReVibe.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ReVibe.model.Account;
import com.ReVibe.repository.AccountRepository;

@Transactional
@Service
public class AccountService {
	
	private AccountRepository accountRepository;
	
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Account findByUserId(int id) {
		return this.accountRepository.findByUserId(id);
	}

<<<<<<< HEAD
	public List<Account> findAll(){
=======
		public List<Account> findAll(){
>>>>>>> 7ff95a110a98ccf3d3146255ae9d0d2a130be134
		return this.accountRepository.findAll();
	}

	public Account findByName(String name) {
		return this.accountRepository.findByName(name);
	}
	
<<<<<<< HEAD
//	public void merge(Account account) {
//		this.accountRepository.setAccountInfoByUserId(account.getName(),account.getPassword(),account.getUsername(),account.getProfilePic(),account.getUserId());
//	}
=======
	public void merge(Account account) {
//		this.accountRepository.setAccountInfoByUserId(account.getName(),account.getPassword(),account.getUsername(),account.getProfilePic(),account.getUserId());
	}
>>>>>>> 7ff95a110a98ccf3d3146255ae9d0d2a130be134

	public List<Account> findBySearchName(String name) {
		return this.accountRepository.findByNameContaining(name);
	}
<<<<<<< HEAD
  public Account saveAccount(Account account) {
=======

	public Account saveAccount(Account account) {
>>>>>>> 7ff95a110a98ccf3d3146255ae9d0d2a130be134
		return this.accountRepository.saveAccount(account);
	}

	public Account findByUsernameAndPassword(String username, String password) {
		Account user = this.accountRepository.findByUsernameAndPassword(username, password);
		if (user == null) {
			return null; 
			
		}
		else
			return user;
	}

}

