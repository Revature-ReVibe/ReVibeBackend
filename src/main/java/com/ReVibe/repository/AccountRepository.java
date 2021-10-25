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
		
	
	public Account findByUserId(int id);

	
	public List<Account> findAll();
	
	
	public Account findByName(String name);
	
	
	public List<Account> findByNameContaining(String name);
	
	
//	void setAccountInfoByUserId(String name, String password, String username, String profilePic, int userId);
	
	public default Account saveAccount(Account account) {
		return save(account);
	}
}

