package com.ReVibe.repository;

import com.ReVibe.model.Account;
import com.ReVibe.model.Vibe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VibeRepository extends JpaRepository<Vibe, Integer>{
    Vibe findByParentvibe(Vibe parentVibe);
    Vibe findByPoster(Account user);
}
