package com.ReVibe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReVibe.model.Vibe;
import com.ReVibe.service.VibeService;

@RestController("vibeController") @RequestMapping("/vibe")
public class VibeController {
    private VibeService vibeService;
    
    @Autowired
    public VibeController(VibeService vibeService){
        this.vibeService = vibeService;
    }
    
    @PostMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> save(@RequestBody Vibe vibe){
        return new ResponseEntity<Vibe>(this.vibeService.save(vibe), HttpStatus.CREATED);
    }
    
    
    @GetMapping(path="/find/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vibe> findById(@PathVariable int id){
        return new ResponseEntity<Vibe>(this.vibeService.findById(id), HttpStatus.OK);
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vibe>> findAll(){
    	
        return new ResponseEntity<List<Vibe>>(this.vibeService.findAll(), HttpStatus.OK);
    }
    
    @PostMapping(path="/createReply", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> saveReply(@RequestBody Vibe vibe, int id){
        return new ResponseEntity<Vibe>(this.vibeService.saveReply(vibe, id), HttpStatus.CREATED);
    }
    
    @PostMapping(path="/createLike", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> saveLike(@RequestBody Vibe vibe, int id){
        return new ResponseEntity<Vibe>(this.vibeService.saveLike(vibe, id), HttpStatus.CREATED);
    }
    
}
