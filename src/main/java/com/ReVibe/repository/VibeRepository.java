package com.ReVibe.repository;

import com.ReVibe.model.Account;
import com.ReVibe.model.Vibe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VibeRepository extends JpaRepository<Vibe, Integer>{
//    List<Vibe> findByParentVibe(Vibe parentVibe);
//    List<Vibe> findByPoster(Account user);
}