package com.ReVibe.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReVibe.model.SignInObj;
import com.ReVibe.model.Users;
import com.ReVibe.service.AccountService;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RequestMapping("/users")
@RestController
public class AccountController {
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		String moon = "hit the test endpoint";
		System.out.println(moon);
		return new ResponseEntity<String>(moon, HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Users> logUserIn(@RequestBody SignInObj userStuff) {
		System.out.println("hit the endpoint");
		
		String username = userStuff.getUsername();
		String password = userStuff.getPassword();
		
		// mocking a database
		if(username.equals("norman") && password.equals("normanpassword")) {
			String jwt = AccountService.createJWT(UUID.randomUUID().toString(), "ReViveBackend", username, 600000L);
			Users user = new Users();
			user.setFirstName("Norman");
			user.setId(1);
			user.setLastName("brumm");
			user.setPassword(password);
			user.setToken(jwt);
			user.setUsername(username);
			
			return new ResponseEntity<Users>(user, HttpStatus.OK);
		}
		return null;
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

