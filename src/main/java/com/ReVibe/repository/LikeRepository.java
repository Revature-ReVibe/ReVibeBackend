package com.ReVibe.repository;

import com.ReVibe.model.Like;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer>{
    public List<Like> findByUserid(int userId);
    public List<Like> findByVibeid(int vibeId);
    public List findByVibeidAndUserid(int vibeId, int userId);
}//LikeRepository
