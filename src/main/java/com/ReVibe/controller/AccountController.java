
package com.ReVibe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReVibe.model.Account;
import com.ReVibe.service.AccountService;

@RestController("accountController")
@RequestMapping("/account")
@CrossOrigin(origins="*")
public class AccountController {

	private AccountService accountService;
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	

  	@GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getall(){
		return this.accountService.findAll() ;
	}
  
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Account findByUserId(int id) {
		return this.accountService.findByUserId(id);
	}

	@GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Account findByName(String name) {
		return this.accountService.findByName(name);
	}
	
//	@PostMapping(path="/updateprofile", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void updateprofile(@RequestBody Account account) {
//		Account currentAccount = this.accountService.findByUserId(account.getUserId());
//		if(account.getName() == "") {
//			account.setName(currentAccount.getName());
//		}
//		if(account.getPassword()== "") {
//			account.setPassword(currentAccount.getPassword());
//		}
//		if(account.getUsername()== "") {
//			account.setUsername(currentAccount.getUsername());  
//		}
//		if(account.getProfilePic()== "") {
//			account.setProfilePic(currentAccount.getProfilePic());
//		}
//		this.accountService.merge(account);
//	}
	

	@GetMapping(path="/searchaccounts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> searchAccounts(String name){
		
		List<Account> accounts = this.accountService.findBySearchName(name);
		for(int i=0; i< accounts.size(); i++) {
			accounts.get(i).setUsername(null);
			accounts.get(i).setPassword(null);
		}
		return accounts;
	}
	
	@PostMapping(path = "/resetpass", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void resetPass(String email) {
		
	}
	
	@PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public Account saveAccount(Account account) {
		return this.accountService.saveAccount(account);
	}
	
}

