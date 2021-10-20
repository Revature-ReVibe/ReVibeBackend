package com.ReVibe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		this.accountService=accountService;
	}
	
	@PostMapping(path="/updateprofile", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateprofile(@RequestBody Account account) {
		Account currentAccount = this.accountService.findByUserId(account.getUserId());
		if(account.getName() == "") {
			account.setName(currentAccount.getName());
		}
		if(account.getPassword()== "") {
			account.setPassword(currentAccount.getPassword());
		}
		if(account.getUsername()== "") {
			account.setUsername(currentAccount.getUsername());
		}
		this.accountService.merge(account);
	}
	
}
