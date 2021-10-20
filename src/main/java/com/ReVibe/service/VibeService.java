package com.ReVibe.service;

import com.ReVibe.model.Vibe;
import com.ReVibe.repository.VibeRepository;
import org.springframework.stereotype.Service;

@Service
public class VibeService {
    private VibeRepository vibeRepository;
    
    public VibeService(VibeRepository vibeRepository){
        this.vibeRepository = vibeRepository;
    }
    
    public Vibe save(Vibe vibe){
        return vibeRepository.save(vibe);
    }
    
    
}
