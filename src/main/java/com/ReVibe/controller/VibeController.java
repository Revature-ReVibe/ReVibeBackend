package com.ReVibe.controller;

import com.ReVibe.model.Like;
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
import com.ReVibe.service.JwtService;
import com.ReVibe.service.VibeService;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController("vibeController") @RequestMapping("/vibe")
public class VibeController {
    private VibeService vibeService;
    
    @Autowired
    public VibeController(VibeService vibeService){
        this.vibeService = vibeService;
    }
    
    @PostMapping(path="/createVibe", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> save(@RequestBody Vibe vibe, @RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.saveVibe(vibe), HttpStatus.CREATED);
        }catch(java.lang.NullPointerException e) {
            return null;
        }
       
    }
    
    @PostMapping(path="/createReply", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> saveReply(@RequestBody Vibe vibe, @RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.saveReply(vibe, vibe.getParentVibe()), HttpStatus.CREATED);
        }catch(java.lang.NullPointerException e) {
            return null;
        }
    }
    
    @GetMapping(path="/find/{vibeId}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vibe> findById(@PathVariable int vibeId, @RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findById(vibeId), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }
        
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vibe>> findAll(@RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findAll(), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }
    }

    @PostMapping(path="/like/{vibeId}", consumes=MediaType.ALL_VALUE)
    public ResponseEntity <Like> like(@PathVariable int vibeId, @RequestHeader("Authorization") String jwt){
        try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.like(vibeId, id), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }
    }
    
    @GetMapping(path="/find/account", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vibe>> findByPoster(@RequestHeader("Authorization") String jwt){
        try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findByPoster(id), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }
    }
    
    @GetMapping("/likes/{vibeId}")
    public ResponseEntity<List<Like>> getAllLikes(@PathVariable int vibeId, @RequestHeader("Authorization") String jwt){
        try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findLikesByVibeId(vibeId), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }
    }
}