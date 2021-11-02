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
@CrossOrigin(origins="*")
public class JwtController {


	@Autowired
	private AccountService accountservice;
	
        /**
         * This method allows the user to log-in to an Account, given an Account
         * with the provided username and password exists.
         * @param account   the Account object with the username and password
         *                  to be verified, passed as JSON in the body of the 
         *                  request
         * @return          the encoded String to be used for login verification;
         *                  <code>null</code> otherwise.
         */
	@PostMapping("/login")
	public ResponseEntity<String> logUserIn(@RequestBody Account account) {
            account = this.accountservice.findByUsernameAndPassword(account.getUsername(), account.getPassword());
            if(account!=null) {
                String jwt = JwtService.createJWT(UUID.randomUUID().toString(), "ReViveBackend", String.valueOf(account.getUserId()), 600000L);
                return new ResponseEntity<String>("{\"jwt\":\"" + jwt + "\"}", HttpStatus.OK);
            }//if account is not null
            return null;
	}//logUserIn(Account)
	
        /**
         * This method checks if the user is logged in to an Account
         * @param jwt   the String to be decoded,
         *              used for login authorization
         * @return      <code>true</code> if the user is logged in;
         *              <code>false</code> otherwise.
         */
	@GetMapping("/authenticate")
	public Boolean isLoggedIn(@RequestHeader("Authorization") String jwt) {
		try {
		Claims claim = JwtService.decodeJWT(jwt);
		if(claim.getIssuer().equals("ReViveBackend")) {
			return true;
		}}catch(io.jsonwebtoken.ExpiredJwtException e) {
			
		}//catch
		return false;
	}//isLoggedIn(String)
	

}//JwtController
