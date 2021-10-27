package com.ReVibe.repository;

import com.ReVibe.model.Like;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer>{
    public List<Like> findByUserId(int userId);
    public List<Like> findByVibeId(int vibeId);
    public Optional<Like> findByVibeIdAndUserId(int vibeId, int userId);
}//LikeRepository
