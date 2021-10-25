package com.ReVibe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import io.jsonwebtoken.Claims;

@CrossOrigin(origins = "http://localhost:4200/")

@RestController 
public class AccountController {
	
	private AccountService accountService;
	
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		String moon = "hit the test endpoint";
		System.out.println(moon);
		return new ResponseEntity<String>(moon, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public Account getUserByUsername(@RequestParam String username) {
		return this.accountService.findByUsername(username);
	}

	@PostMapping(path = "/signIn", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> signIn(@RequestBody Account account, HttpServletRequest request) {

		account = this.accountService.findByUsernameAndPassword(account.getUsername(), account.getPassword());

		if (account == null) {
			return new ResponseEntity<String>("Incorrect user or password", HttpStatus.BAD_REQUEST);
		} else {

			HttpSession session = request.getSession();
			session.setAttribute("userId", account.getUserId());
			session.setAttribute("user", account);
			System.out.println((Integer) session.getAttribute("userId"));
			return new ResponseEntity<String>("Signed in", HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/authenticate")
	public Boolean isLoggedIn(@RequestHeader("Authorization") String jwt) {
		System.out.println("endpoint hit... jwt:" + jwt);
		Claims claim = AccountService.decodeJWT(jwt);
		if(claim.getIssuer().equals("ReViveBackend")) {
			System.out.println("It Worked!!!");
			return true;
		}
		return false;
	}

}

