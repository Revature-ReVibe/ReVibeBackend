package com.ReVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ReVibe.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	@Modifying
	@Query("update Account a set a.name = ?1, a.password = ?2, a.profilepic=?3 where a.id= ?4")
	void setAccountInfoByUserId(String name, String password, String username, Integer userId);
	
}
