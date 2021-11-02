package com.ReVibe.repository;

import com.ReVibe.model.Vibe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VibeRepository extends JpaRepository<Vibe, Integer>{
    /**
     * This method finds all Vibes that are replies to different, specific Vibe
     * @param parentVibe    the integer representing the id of the parent Vibe
     * @return              the list of Vibe objects with the requested 
     *                      parent Vibe;
     *                      <code>null</code> otherwise.
     */
    List<Vibe> findByParentVibe(int parentVibe);
    
    /**
     * This method finds all Vibes posted by a specific Account
     * @param accountId     the integer representing the id of the Account
     * @return              the list of Vibe objects with the requested Account id
     */
    List<Vibe> findByAccountid(int accountId);
    
    /**
     * This method finds all Vibes with no parent Vibe
     * It is used to distinguish original Vibe posts from Vibe replies.
     * @return  the list of Vibe objects with no parent Vibe
     */
    List<Vibe> findByParentVibeNull();

}//VibeRepository