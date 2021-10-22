package com.ReVibe.controller;

import com.ReVibe.model.Vibe;
import com.ReVibe.service.VibeService;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VibeController {
    private VibeService vibeService;
    
    public VibeController(VibeService vibeService){
        this.vibeService = vibeService;
    }
    
    public Vibe save(Vibe vibe){
        return vibeService.save(vibe);
    }
    
    public Vibe findById(int id){
        return vibeService.findById(id);
    }
    
    public List<Vibe> findAll(){
        return vibeService.findAll();
    }
}
