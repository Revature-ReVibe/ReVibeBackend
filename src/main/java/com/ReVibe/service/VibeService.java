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
//        Vibe vibe = findById(vibeId);
        Like like = new Like(vibeId, accountId);
        Optional<Like> likeOp = likeRepository.findByVibeIdAndUserId(vibeId, accountId);

        if (likeOp.isEmpty()){
//            vibe.getLikes().add(like);
            log.info("Account {} added like to vibe {}", accountId, vibeId);
            return likeRepository.save(like);
        } else{
//            vibe.getLikes().remove(like);
            log.info("Account {} removed like from vibe {}", accountId, vibeId);
            likeRepository.delete(like);
            return null;
        }

    }
    
//	public Vibe unlike(Vibe vibe, int accountId) {
//    	//Working on likes
//		
//		Like like = new Like();
//		
//		like.setVibeId(vibe.getVibeId());	
//		like.setUserId(accountId);
//		//Check if Like exists, if it does remove it
//		if(vibe.getLikes().contains(like)) {
//			vibe.getLikes().remove(like);
//		}
//    	return vibeRepository.save(vibe);
//	}
	
    //In case needed
    public List<Like> getAllLikes(Vibe vibe){
        return vibe.getLikes();	
    }
	
    public List<Vibe> findByPoster(int accountId){
        log.info("find vibes posted by account {}", accountId);
    	return vibeRepository.findByAccountid(accountId);
    }
}
