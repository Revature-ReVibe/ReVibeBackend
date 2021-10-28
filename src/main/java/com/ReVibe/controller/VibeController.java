package com.ReVibe.controller;

import com.ReVibe.model.Account;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity <Vibe> saveReply(@RequestBody Vibe vibe, int parentVibe, @RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.saveReply(vibe, parentVibe), HttpStatus.CREATED);
        }catch(java.lang.NullPointerException e) {
            return null;
        }
    }
    
    @GetMapping(path="/find/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(path="/like", method=RequestMethod.POST)
    public ResponseEntity <Like> like(@RequestParam int vibeId, @RequestHeader("Authorization") String jwt){
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
    
    @RequestMapping("/likes")
    public ResponseEntity<List<Like>> getAllLikes(@RequestParam int vibeId, @RequestHeader("Authorization") String jwt){
        try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findByVibeId(vibeId), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }
    }
}