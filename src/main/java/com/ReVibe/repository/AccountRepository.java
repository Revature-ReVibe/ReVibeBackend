package com.ReVibe.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ReVibe.model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	public<S extends Account>S save(S account);
		
	@Query("select name, username, profilepic from Account where Account.id = ?1")
	public Account findByUserId(int id);

//	@Query("select name, username, profilepic from Account")
//	public List<Account> findAll();
	
	@Query("select name, username, profilepic from Account where Account.name = ?1")
	public Account findByName(String name);
	
//	@Query("select name, username, profilepic from Account where Account.name like ?1")
//	public List<Account> findByNameContaining(String name);
	
	@Modifying
	@Query("update Account a set a.name = ?1, a.password = ?2, a.username=?3, a.profilepic=?4, where a.id= ?5")
	void setAccountInfoByUserId(String name, String password, String username, String profilePic, Integer userId);
	
	public default Account saveAccount(Account account) {
		return save(account);
	}
}

