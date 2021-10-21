package com.ReVibe.controller;

import com.ReVibe.model.Vibe;
import com.ReVibe.service.VibeService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController("vibeController") @RequestMapping("/vibe")
public class VibeController {
    private VibeService vibeService;
    
    @Autowired
    public VibeController(VibeService vibeService){
        this.vibeService = vibeService;
    }
    
    @PostMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> save(@RequestBody Vibe vibe){
        return new ResponseEntity<Vibe>(vibeService.save(vibe), HttpStatus.CREATED);
    }
    
    @GetMapping(path="/find/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vibe> findById(int id){
        return new ResponseEntity<Vibe>(vibeService.findById(id), HttpStatus.OK);
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vibe>> findAll(){
    	
        return new ResponseEntity<List<Vibe>>(vibeService.findAll(), HttpStatus.OK);
    }
    
    
}
