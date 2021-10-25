package com.ReVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ReVibe.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

		public Account findByUsername(String username);
		public Account findByUsernameAndPassword(String username, String password);
		public Account save(String username);
	
	}
