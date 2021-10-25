package com.ReVibe.repository;

import com.ReVibe.model.Account;
import com.ReVibe.model.Vibe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VibeRepository extends JpaRepository<Vibe, Integer>{
<<<<<<< HEAD
//    List<Vibe> findByParentVibe(Vibe parentVibe);
//    List<Vibe> findByPoster(Account user);
=======
    List<Vibe> findByParentVibe(Vibe parentVibe);
    List<Vibe> findByAccountid(Account user);
>>>>>>> 7ff95a110a98ccf3d3146255ae9d0d2a130be134
}