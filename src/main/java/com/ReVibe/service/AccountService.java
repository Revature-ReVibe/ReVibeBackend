package com.ReVibe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReVibe.model.Account;
import com.ReVibe.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Service @Slf4j
public class AccountService {
	
	private AccountRepository accountRepository;
	
        /**
         * Constructor
         * @param accountRepository     the AccountRepository instance used to 
         *                              access the repository layer of the
         *                              application
         */
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}//AccountService(AccountRepository)
	
        /**
         * This method finds the Account with the desired user id
         * @param id    the integer representing the id of the Account
         * @return      the Account object found by the repository; May
         *              be <code>null</code>.
         */
	public Account findByUserId(int id) {
		log.info("find user id {} in Database", id);
		return this.accountRepository.findByUserId(id);
	}//findByUserId(int)

        /**
         * This method finds all Accounts
         * @return      the list of Account objects found by the repository;
         *              May be empty.
         */
	public List<Account> findAll(){
		log.info("find all users");
		return this.accountRepository.findAll();
	}//findAll()

        /**
         * This method finds the Account with the desired name
         * @param name  the String representing the name of the Account
         * @return      the Account object found by the repository; May
         *              be <code>null</code>.
         */
	public Account findByName(String name) {
		log.info("find {} in Database", name);
		return this.accountRepository.findByName(name);
	}//findByName(String)

        /**
         * This method updates the information of the current Account
         * @param account   the Account object representing the current user
         */
	public void merge(Account account) {
		log.info("Merge {} ", account);
		this.accountRepository.setAccountInfoByUserId(account.getName(),account.getPassword(),account.getUsername(),account.getProfilePic(),account.getUserId());
	}//merge(Account)

        /**
         * This method searches for all Accounts with a name containing a 
         * specific String
         * @param name  the String to be searched by
         * @return      the list of Account objects found by the repository;
         *              May be empty.
         */
	public List<Account> findBySearchName(String name) {
		log.info("Search {} in Database", name);
		return this.accountRepository.findByNameContaining(name);
	}//findBySearchName(String)

        /**
         * This method saves the Account
         * @param account   the Account object to be saved
         * @return          the Account object that was saved
         */
	public Account saveAccount(Account account) {
		log.info("saving account ({}) in Database", account);

		return this.accountRepository.saveAccount(account);
	}//saveAccount(Account)

        /**
         * This method searches for an Account with the desired username and
         * password
         * @param username  the String representing the username of the Account
         * @param password  the String representing the password of the Account
         * @return          the Account object with the requested username and
         *                  password found by the repository layer; May be 
         *                  <code>null</code>.
         */
	public Account findByUsernameAndPassword(String username, String password) {

		log.info("find user {} and password {}", username, password);
		Account user = this.accountRepository.findByUsernameAndPassword(username, password);
		if (user == null)
			return null; 
		else
			return user;
	}//findByUsernameAndPassword(String, String)

	/**
         * This method searches for an Account with the desired id
         * @param id    the integer representing the id of the Account
         * @return      the Account object with the requested id found by the 
         *              repository; May be <code>null</code>.
         */
        public Account findById(int id) { 
                return this.accountRepository.findById(id).get();
        }//findById(int)

        /**
         * This method searches for an Account with a specific email
         * @param email     the String representing the email of the Account
         * @return          the Account object with the requested email found
         *                  by the repository; May be <code>null</code>.
         */
	public Account findByEmail(String email) {
		log.info("find user by {} in Database", email);
		return this.accountRepository.findByEmail(email);
	}//findByEmail(String)
}//AccountService
	

