package com.ReVibe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReVibe.model.Account;
import com.ReVibe.service.AccountService;
@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class AccountController {
	private AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getall(){
		return this.accountService.findall() ;
	}

}
