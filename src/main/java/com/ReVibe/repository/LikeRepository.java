package com.ReVibe.repository;

import com.ReVibe.model.Like;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer>{
    /**
     * This method finds all Likes with the desired userId. 
     * @param userId    the integer representing the id of an Account object
     * @return          the list of Like objects with the requested userId
     */
    public List<Like> findByUserId(int userId);
    
    /**
     * This method finds all Likes with the desired vibeId.
     * @param vibeId    the integer representing the id of a Vibe object 
     * @return          the list of Like objects with the requested vibeId
     */
    public List<Like> findByVibeId(int vibeId);
    
    /**
     * This method finds the Like with the desired vibeId and userId.
     * @param vibeId    the integer representing the id of a Vibe object
     * @param userId    the integer representing the id of an Account object
     * @return          the Like object that meets the requested criteria;
     *                  <code>null</code> otherwise.
     */
    public Like findByVibeIdAndUserId(int vibeId, int userId);
}//LikeRepository
