package com.ReVibe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
        
        /**
         * Constructor
         * @param accountService    the AccountService instance used to access
         *                          the service layer of the application
         */
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}//AccountController(AccountService)
	
	@Autowired
	private JavaMailSender mailSender;

	
        /**
         * This method finds all the Accounts.
         * @param jwt   the String to be decoded,
         *              used for login authorization
         * @return      the list of Account objects found;
         *              <code>null</code> otherwise.
         */
  	@GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getall(@RequestHeader("Authorization") String jwt){
                try {
                        int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
                        List<Account> account = this.accountService.findAll();
                        for(int i=0; i<account.size();i++) {
                                account.get(i).setUsername(null);
                                account.get(i).setPassword(null);
                        }//for
                        return account;
  		}catch(java.lang.NullPointerException e) {
  			return null;
  		}//catch
	}//getall(String)

        /**
         * This method finds an Account via decoded id.
         * @param jwt   the String to be decoded,
         *              used for login authorization
         * @return      the Account object found;
         *              <code>null</code> otherwise.
         */
        @GetMapping(path = "/findbyId", produces = MediaType.APPLICATION_JSON_VALUE)
        public Account findByUserId(@RequestHeader("Authorization") String jwt) {
                try {
                        Object id = JwtService.decodeJWT(jwt).get("sub");
                        Account account = this.accountService.findByUserId( Integer.valueOf((String)id));
                        account.setUsername(null);
                        account.setPassword(null);
                        return account;
                }catch(java.lang.NullPointerException e) {
                        return null;
                }//catch
        }//findByuserId(String)

        /**
         * This method finds an Account via the provided name.
         * @param name  the String containing the name
         * @param jwt   the String to be decoded,
         *              used for login authorization
         * @return      the Account object found;
         *              <code>null</code> otherwise.
         */
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
	  	}//catch
	}//findByName(String, String)

        /**
         * This method updates the information of the current Account.
         * @param account   the Account object containing the new data values
         * @param jwt       the String to be decoded,
         *                  used for login authorization
         * @return          <code>true</code> if the user is logged in;
         *                  <code>false</code> otherwise.
         */
	@PostMapping(path="/updateprofile", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateprofile(@RequestBody Account account, @RequestHeader("Authorization") String jwt) {
		try {
		  	int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
                        Account currentAccount = this.accountService.findByUserId(id);
                        account.setUserId(id);
                        if(account.getName() == "") {
                                account.setName(currentAccount.getName());
                        }//if account.getName is empty
                        if(account.getPassword()== "") {
                                account.setPassword(currentAccount.getPassword());
                        }//if account.getPassword is empty
                        if(account.getUsername()== "") {
                                account.setUsername(currentAccount.getUsername());  
                        }//if account.getUsername is empty
                        if(account.getProfilePic()== "") {
                                account.setProfilePic(currentAccount.getProfilePic());
                        }//if account.getProfilePic is empty
                        this.accountService.merge(account);
                        System.out.println(account);
                        return true;
		}catch(java.lang.NullPointerException e) {
  			return false;
                }//catch
	}//updateprofile(Account, String)
	
        /**
         * This method searches for Accounts by name.
         * @param account   the Account object with the name to be searched,
         *                  passed as JSON in the body of the request.
         * @param jwt       the String to be decoded,
         *                  used for login authorization
         * @return          the list of Account objects with the desired name;
         *                  <code>null</code> otherwise.
         */
	@GetMapping(path="/searchaccounts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> searchAccounts(@RequestBody Account account,@RequestHeader("Authorization") String jwt){
		try {
		  	int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
                        List<Account> accounts = this.accountService.findBySearchName(account.getName());
                        for(int i=0; i< accounts.size(); i++) {
                                accounts.get(i).setUsername(null);
                                accounts.get(i).setPassword(null);
                        }//for
                        return accounts;
		}catch(java.lang.NullPointerException e) {
  			return null;
                }//catch
	}//searchAccounts(Account, String)
        
	/**
         * This method allows the user to reset the password associated with 
         * this Account.
         * @param account   the Account object the user wishes to login to
         *                  passed as JSON in the body of the request.
         * @return          <code>true</code> if the Account exists;
         *                  <code>false</code> otherwise.
         */
	@PostMapping(path = "/resetpass", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean resetPass(@RequestBody Account account) {
		try {
			account = this.accountService.findByEmail(account.getEmail());
			account.setPassword("tempPassword"); //generate a password if time allows
			this.accountService.merge(account);
			//call email stuff
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom("revibenov5@gmail.com");
			message.setTo(account.getEmail());
			
			String mailSubject ="Password reset";
			String mailContent = "New Password = " + account.getPassword()+" please change when log in";
			message.setSubject(mailSubject);
			message.setText(mailContent);
			
			mailSender.send(message);
			return true;
		}catch(java.lang.NullPointerException e) {
  			return false;
                }//catch
	}//resetPass(Account)
	
        /**
         * This method saves the Account.
         * @param account   the Account object to be saved, passed as
         *                  JSON in the body of the request.
         * @return          the Account object that was saved;
         *                  <code>null</code> otherwise.
         */
	@PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public Account saveAccount(@RequestBody Account account) {
		try {
                        account= this.accountService.saveAccount(account);
		}catch(org.springframework.dao.DataIntegrityViolationException e) {
			return null;
		}//catch
		return account;
	}//saveAccount(Account)
	
}//AccountController

