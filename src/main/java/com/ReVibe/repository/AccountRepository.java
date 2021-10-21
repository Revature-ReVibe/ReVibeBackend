package com.ReVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ReVibe.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	public default Account saveAccount(Account account) {
		return save(account);
	}
}
