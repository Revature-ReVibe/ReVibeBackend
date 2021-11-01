package com.ReVibe.service;

import com.ReVibe.model.Like;
import com.ReVibe.model.Vibe;
import com.ReVibe.repository.LikeRepository;
import com.ReVibe.repository.VibeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    
    public Vibe saveVibe(Vibe vibe){
        log.info("Saving a new vibe");
        LocalDateTime timeStamp = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeStamp.format(format);
        vibe.setDate(timeStamp);
        vibe.setVibeLike(0);
        Vibe newVibe = vibeRepository.save(vibe);
    	  log.info("{} saved", newVibe);
        return newVibe;
    }
    
    public Vibe saveReply(Vibe vibe, int parentId) {
        log.info("Saving a reply to vibe {}", parentId);      
        Vibe parentVibe = vibeRepository.findById(parentId).get();
        vibe.setVibeLike(0);
        LocalDateTime timeStamp = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeStamp.format(format);
        vibe.setDate(timeStamp);
    	vibe.setParentVibe(parentId);

    	Vibe reply = vibeRepository.save(vibe);
        //parentVibe.getReplyVibes().add(reply);
    	log.info("{} saved", reply);

    	return reply;
    }
    
    public Vibe findById(int id){

        log.info("Finding vibe with id {}", id);
        return vibeRepository.findById(id).get();
    }
    
    public List<Vibe> findAll(){
        log.info("Finding all vibes");
        return vibeRepository.findAll();
    }
    
    public List<Vibe> findAllPosts(){
        log.info("Finding all original posts");
        return vibeRepository.findByParentVibeNull();
    }

    public Like like(int vibeId, int accountId) {
        Vibe vibe = vibeRepository.findById(vibeId).get();
        if (vibe != null){
            log.info("Account {} toggling like for vibe {}", accountId, vibeId);
            Like like = likeRepository.findByVibeIdAndUserId(vibeId, accountId);

            if (like == null){
                log.info("Account {} adding like to vibe {}", accountId, vibeId);
                vibe.setVibeLike(vibe.getVibeLike()+1);
                vibeRepository.save(vibe);
                return likeRepository.save(new Like(vibeId, accountId));
            } else{
                log.info("Account {} removing like from vibe {}", accountId, vibeId);
                vibe.setVibeLike(vibe.getVibeLike()-1);
                vibeRepository.save(vibe);
                likeRepository.delete(like);
            }
        }
        return null;
    }
    
    public List<Vibe> findByParentVibe(int parentVibe){
        log.info("Find all replies for vibe {}", parentVibe);
        return vibeRepository.findByParentVibe(parentVibe);
    }
    
    public List<Like> findLikesByVibeId(int vibeId){
        log.info("Find all likes for vibe {}", vibeId);

        return likeRepository.findByVibeId(vibeId);
    }
	
    public List<Vibe> findByPoster(int accountId){
        log.info("Finding all vibes posted by account {}", accountId);
    	return vibeRepository.findByAccountid(accountId);
    }
}
