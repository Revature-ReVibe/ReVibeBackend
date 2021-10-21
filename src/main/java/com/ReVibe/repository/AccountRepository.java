package com.ReVibe.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ReVibe.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	public Account findByUserId(int id);
	public List<Account> findAll();
	public Account findByName(String name);
	public List<Account> findByNameContaining(String name);
	
	@Modifying
	@Query("update Account a set a.name = ?1, a.password = ?2, a.username=?3, a.profilepic=?4, where a.id= ?5")
	void setAccountInfoByUserId(String name, String password, String username, String profilePic, Integer userId);
	
	
}
