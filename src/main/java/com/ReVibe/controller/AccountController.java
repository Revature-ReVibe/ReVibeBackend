
package com.ReVibe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ReVibe.model.Account;
import com.ReVibe.service.AccountService;
import com.ReVibe.service.JwtService;

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
	public List<Account> getall(@RequestHeader("Authorization") String jwt){
  		try {
  		int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
		List<Account> account = this.accountService.findAll();
		for(int i=0; i<account.size();i++) {
			account.get(i).setUsername(null);
			account.get(i).setPassword(null);
		}
		return account;
  		}catch(java.lang.NullPointerException e) {
  			return null;
  		}
	}
  
	@GetMapping(path = "/findbyId", produces = MediaType.APPLICATION_JSON_VALUE)
	public Account findByUserId(@RequestHeader("Authorization") String jwt) {
		try {
		Object id = JwtService.decodeJWT(jwt).get("sub");
		Account account = this.accountService.findByUserId( Integer.valueOf((String)id));
		account.setUsername(null);
		account.setPassword(null);
		return account;
		}catch(io.jsonwebtoken.ExpiredJwtException e) {
			return null;
		}
	}

	@GetMapping(path = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
	public Account findByName(@RequestParam String name,@RequestHeader("Authorization") String jwt) {
	  	try {
	  	int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
		Account account = this.accountService.findByName(name);
		account.setUsername(null);
		account.setPassword(null);
		return account;
	  	}catch(java.lang.NullPointerException e) {
	  			return null;
	  	}
	}
	

	@PostMapping(path="/updateprofile", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateprofile(@RequestBody Account account, @RequestHeader("Authorization") String jwt) {
		try {
		  	int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
		Account currentAccount = this.accountService.findByEmail(account.getEmail());
		if(account.getName() == "") {
			account.setName(currentAccount.getName());
		}
		if(account.getPassword()== "") {
			account.setPassword(currentAccount.getPassword());
		}
		if(account.getUsername()== "") {
			account.setUsername(currentAccount.getUsername());  
		}
		if(account.getProfilePic()== "") {
			account.setProfilePic(currentAccount.getProfilePic());
		}
		this.accountService.merge(account);
		return true;
		}catch(java.lang.NullPointerException e) {
  			return false;
  	}
	}

	

	@GetMapping(path="/searchaccounts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> searchAccounts(@RequestBody Account account,@RequestHeader("Authorization") String jwt){
		try {
		  	int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
		List<Account> accounts = this.accountService.findBySearchName(account.getName());
		for(int i=0; i< accounts.size(); i++) {
			accounts.get(i).setUsername(null);
			accounts.get(i).setPassword(null);
		}
		return accounts;
		}catch(java.lang.NullPointerException e) {
  			return null;
  	}
	}
	
	@PostMapping(path = "/resetpass", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void resetPass(String email) {
		
	}
	
	@PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public Account saveAccount(@RequestBody Account account) {
		try {
		account= this.accountService.saveAccount(account);
		}catch(org.springframework.dao.DataIntegrityViolationException e) {
			return null;
		}
		return account;
	}
	
}

