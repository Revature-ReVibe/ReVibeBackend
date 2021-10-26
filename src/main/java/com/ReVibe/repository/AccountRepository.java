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
	
	@Modifying
	@Query("update Account a set a.name = ?1, a.password = ?2, a.username=?3,a.profilePic=?4 where a.email= ?5")
	void setAccountInfoByUserId(String name, String password, String username,String profilepic, String email);
	
	public default Account saveAccount(Account account) {
		return save(account);
	}

	public Account findByUsernameAndPassword(String username, String password);

	public Account findByEmail(String email);
}