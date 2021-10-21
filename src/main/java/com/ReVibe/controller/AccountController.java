package com.ReVibe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReVibe.model.Account;
import com.ReVibe.service.AccountService;

@CrossOrigin
@RestController
@RequestMapping("/account") //this might be changed?
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public Account saveAccount(Account account) {
		return this.accountService.saveAccount(account);
	}
}
