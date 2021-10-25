package com.ReVibe.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReVibe.model.Account;
import com.ReVibe.service.AccountService;
import com.ReVibe.service.JwtService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/jwt")
@CrossOrigin
public class JwtController {


	@Autowired
	private AccountService accountservice;
	
	@PostMapping("/login")
	public ResponseEntity<String> logUserIn(@RequestBody Account account) {
		account = this.accountservice.findByUsernameAndPassword(account.getUsername(), account.getPassword());

		if(account!=null) {
		String jwt = JwtService.createJWT(UUID.randomUUID().toString(), "ReViveBackend", String.valueOf(account.getUserId()), 600000L);
		return new ResponseEntity<String>(jwt, HttpStatus.OK);
		}
		return null;
	}
	
	@GetMapping("/authenticate")
	public Boolean isLoggedIn(@RequestHeader("Authorization") String jwt) {
		System.out.println("endpoint hit... jwt:" + jwt);
		Claims claim = JwtService.decodeJWT(jwt);
		if(claim.getIssuer().equals("ReViveBackend")) {
			System.out.println(claim);
			System.out.println(claim.get("sub"));
			return true;
		}
		return false;
	}

	
}
