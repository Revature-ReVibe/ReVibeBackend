package com.ReVibe.repository;

import com.ReVibe.model.Vibe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VibeRepository extends JpaRepository<Vibe, Integer>{

    public List<Vibe> findByParentVibe(Vibe parentVibe);
    public List<Vibe> findByAccountid(int accountId);
    
}