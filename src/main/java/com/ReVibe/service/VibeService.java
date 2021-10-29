package com.ReVibe.service;

import com.ReVibe.model.Like;
import com.ReVibe.model.Vibe;
import com.ReVibe.repository.LikeRepository;
import com.ReVibe.repository.VibeRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service @Slf4j
public class VibeService {
    private VibeRepository vibeRepository;
    private LikeRepository likeRepository;
    
    @Autowired
    public VibeService(VibeRepository vibeRepository, LikeRepository likeRepository){
        this.vibeRepository = vibeRepository;
        this.likeRepository = likeRepository;
    }
    
    public Vibe saveVibe(Vibe newVibe){
        log.info("Saving vibe ({})", newVibe);
    	return vibeRepository.save(newVibe);
    }
    
    public Vibe saveReply(Vibe vibeReply, int parentId) {
        log.info("Saving reply ({})", vibeReply);
    	vibeReply.setParentVibe(parentId);
    	return vibeRepository.save(vibeReply);
    }
    
    public Vibe findById(int id){
        log.info("find vibe by id");
        return vibeRepository.findById(id).get();
    }
    
    public List<Vibe> findAll(){
        log.info("Finding all vibes");
        return vibeRepository.findAll();
    }

    public Like like(int vibeId, int accountId) {
        Like likeOp = likeRepository.findByVibeIdAndUserId(vibeId, accountId);

        if (likeOp == null){
            log.info("Account {} added like to vibe {}", accountId, vibeId);
            return likeRepository.save(new Like(vibeId, accountId));
        } else{
            log.info("Account {} removed like from vibe {}", accountId, vibeId);
            likeRepository.delete(likeOp);
            return null;
        }
    }
    
    public List<Vibe> findByParentVibe(int parentVibe){
        log.info("Find all replies for vibe {}", parentVibe);
        return vibeRepository.findByParentVibe(parentVibe);
    }
    
    
    public List<Like> findByVibeId(int vibeId){
        log.info("Find all likes for vibe {}", vibeId);
        return likeRepository.findByVibeId(vibeId);
    }
	
    public List<Vibe> findByPoster(int accountId){
        log.info("find vibes posted by account {}", accountId);
    	return vibeRepository.findByAccountid(accountId);
    }
}
