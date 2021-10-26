package com.ReVibe.service;

import com.ReVibe.model.Like;
import com.ReVibe.model.Vibe;
import com.ReVibe.repository.VibeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VibeService {
    private VibeRepository vibeRepository;
    
    public VibeService(VibeRepository vibeRepository){
        this.vibeRepository = vibeRepository;
    }
    
    public Vibe saveVibe(Vibe newVibe){
    	//Has no parentVibe. parentVibe uninitialized
    	return vibeRepository.save(newVibe);
    }
    
    public Vibe saveReply(Vibe vibeReply, int parentId) {	
    	vibeReply.setParentVibe(parentId);
    	return vibeRepository.save(vibeReply);
    }
    
    public Vibe findById(int id){
        return vibeRepository.findById(id).get();
    }
    
    public List<Vibe> findAll(){
        return vibeRepository.findAll();
    }

	public Vibe like(Vibe vibe, int accountId) {
    	//Working on likes
		
		Like like = new Like();
		
		like.setVibeId(vibe.getVibeId());	
		like.setUserId(accountId);
		vibe.getLikes().add(like);
    	return vibeRepository.save(vibe);
	}
    
	public Vibe unlike(Vibe vibe, int accountId) {
    	//Working on likes
		
		Like like = new Like();
		
		like.setVibeId(vibe.getVibeId());	
		like.setUserId(accountId);
		
		//Check if Like exists, if it does remove it
		if(vibe.getLikes().contains(like)) {
			vibe.getLikes().remove(like);
		}
    	return vibeRepository.save(vibe);
	}
	
	//In case needed
	public List<Like> getAllLikes(Vibe vibe){
		return vibe.getLikes();	
	}
	
    public List<Vibe> findByPoster(int accountId){
    	return vibeRepository.findByAccountid(accountId);
    }
}
