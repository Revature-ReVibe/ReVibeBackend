package com.ReVibe.service;

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
    
    public Vibe save(Vibe vibe){
    	vibe.setParentVibe(vibe);
        return vibeRepository.save(vibe);
    }
    
    public Vibe saveReply(Vibe vibeReply, int parentId) {	
    	vibeReply.setParentVibe(findById(parentId));
    	return vibeRepository.save(vibeReply);
    }
    
    public Vibe findById(int id){
        return vibeRepository.findById(id).get();
    }
    
    public List<Vibe> findAll(){
        return vibeRepository.findAll();
    }
    
    
//    public List<Vibe> findByPoster(Vibe poster){
//    	return vibeRepository.findByPoster(poster.getPoster().getUserId());
//    }
}