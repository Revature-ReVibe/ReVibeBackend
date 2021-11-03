package com.ReVibe.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ReVibe.model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

        
        /**
         * This method finds the Account with the desired id
         * This method can be removed in favor of the built-in findById method
         * that comes with extending JpaRepository         
         * @param id    the integer representing the id of the Vibe
         * @return      the Account object with the desired id;
         *              <code>null</code> otherwise.
         */

	public Account findByUserId(int id);
	
        /**
         * This method retrieves all Accounts from the database
         * This method can be removed in favor of the built-in findAll method
         * that comes with extending JpaRepository
         * @return      the list of Account objects in the database
         */
	public List<Account> findAll();
        
        /**
         * This method finds the Account with the desired name.
         * @param name  the String representing the name of the Account
         * @return      the Account object with the desired name;
         *              <code>null</code> otherwise.
         */
	public Account findByName(String name);
	
        /**
         * This method finds the Account with the desired username.
         * @param username  the String representing the username of the Account
         * @return          the Account object with the desired username;
         *                  <code>null</code> otherwise.
         */
	public Account findByUsername (String username);
	
        /**
         * This method finds all Accounts with a name that contain the desired
         * String.
         * @param name  the String representing the name to be searched
         * @return      the list of Account objects that meet the criteria;
         *              <code>null</code> otherwise.
         */
	public List<Account> findByNameContaining(String name);
	
        /**
         * This method updates the Account in the database.
         * @param name          the String representing the new name
         * @param password      the String representing the new password
         * @param username      the String representing the new username
         * @param profilepic    the String representing the new profile pic
         * @param userId        the integer representing the requested id
         */
	@Modifying
	@Query("update Account a set a.name = ?1, a.password = ?2, a.username=?3,a.profilePic=?4 where a.userId= ?5")
	void setAccountInfoByUserId(String name, String password, String username,String profilepic, int userId);
	
        /**
         * This method persists the Account to the database
         * This method can be removed in favor of the built-in save method
         * that comes with extending JpaRepository
         * @param account   the Account object to be saved
         * @return          the Account object that was saved
         */
	public default Account saveAccount(Account account) {
		return save(account);
	}//saveAccount(Account)
        
        /**
         * This method finds the Account with the desired username and password
         * Used for login verification
         * @param username  the String representing the username of the Account
         * @param password  the String representing the password of the Account
         * @return          the Account object with the requested username and
         *                  password;
         *                  <code>null</code> otherwise.
         */
	public Account findByUsernameAndPassword(String username, String password);
        
        /**
         * This method finds the Account with the desired email.
         * @param email     the String representing the email of the Account
         * @return          the Account object with the requested email;
         *                  <code>null</code> otherwise.
         */
	public Account findByEmail(String email);

}//AccountRepository




