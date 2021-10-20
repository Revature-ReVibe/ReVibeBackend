package com.ReVibe.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ReVibe.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	public Account findByUserId(int id);
	public List<Account> findAll();
	public Account findByName(String name);

	@Modifying
	@Query("update Account a set a.name = ?1, a.password = ?2, a.profilepic=?3 where a.id= ?4")
	void setAccountInfoByUserId(String name, String password, String username, Integer userId);
	
}
