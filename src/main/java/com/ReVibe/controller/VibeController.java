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
    
    /**
     * Constructor
     * @param vibeService   the VibeService instance used to access the service
     *                      layer of the application
     */
    @Autowired
    public VibeController(VibeService vibeService){
        this.vibeService = vibeService;
    }//VibeController(VibeService)
    
    /**
     * This method saves the created Vibe object.
     * @param vibe  the Vibe object to be saved, passed as JSON in the body
     *              of the request
     * @param jwt   the String to be decoded,
     *              used for login authorization
     * @return      the saved Vibe object as a ResponseEntity;
     *              <code>null</code> otherwise.
     */
    @PostMapping(path="/createVibe", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> save(@RequestBody Vibe vibe, @RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            vibe.setAccountid(id);
            return new ResponseEntity<>(this.vibeService.saveVibe(vibe), HttpStatus.CREATED);
        }catch(java.lang.NullPointerException e) {
            return null;
        }//catch

    }//save(Vibe, String)
    
    /**
     * This method saves replies (Vibes that have parent Vibes).
     * @param vibe          the Vibe object to be saved, passed as JSON in the
     *                      body of the request
     * @param parentVibe    the integer representing the id of the parent Vibe
     * @param jwt           the String to be decoded,
     *                      used for login authorization
     * @return              the saved Vibe object as a ResponseEntity;
     *                      <code>null</code> otherwise. 
     */
    @PostMapping(path="/createReply/{parentVibe}", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> saveReply(@RequestBody Vibe vibe,@PathVariable int parentVibe, @RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            vibe.setAccountid(id);
            return new ResponseEntity<>(this.vibeService.saveReply(vibe, parentVibe), HttpStatus.CREATED);
        }catch(java.lang.NullPointerException e) {
            return null;
        }//catch
    }//saveReply(Vibe, int, String)
    
    /**
     * This method finds Vibes via id.
     * @param vibeId    the integer representing the id of the desired Vibe
     * @param jwt       the String to be decoded,
     *                  used for login authorization
     * @return          the Vibe object with the requested id;
     *                  <code>null</code> otherwise.
     */
    @GetMapping(path="/find/{vibeId}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vibe> findById(@PathVariable int vibeId, @RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findById(vibeId), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }//catch
        
    }//findById(int, String)
    
    /**
     * This method gets all the Vibes.
     * @param jwt   the String to be decoded,
     *              used for login authorization
     * @return      the list of Vibes found;
     *              <code>null</code> otherwise.
     */
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vibe>> findAll(@RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findAll(), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }//catch
    }//findAll()
    
    /**
     * This method retrieves all posts (Vibes that do not have a parent Vibe).
     * @param jwt   the String to be decoded,
     *              used for login authorization
     * @return      the list of Vibes with no parent Vibe;
     *              <code>null</code> otherwise.
     */
    @GetMapping(path="/all/post", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vibe>> findAllPosts(@RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findAllPosts(), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }//catch
    }//findAllPosts(String)
    
    /**
     * This method gets all replies to a specific Vibe.
     * @param parentVibe    the integer representing the id of the parent Vibe
     * @param jwt           the String to be decoded,
     *                      used for login authorization
     * @return              the list of Vibe objects that have the specified 
     *                      parent Vibe;
     *                      <code>null</code> otherwise.
     */
    @GetMapping(path="/allReplies/{parentVibe}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vibe>> findAllReplies(@PathVariable int parentVibe, @RequestHeader("Authorization") String jwt){
    	try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findByParentVibe(parentVibe), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }//catch
    }//findAllReplies(int, String)

    /**
     * This method toggles the Like for the given Vibe.
     * @param vibeId    the integer representing the id of the Vibe object
     * @param jwt       the String to be decoded,
     *                  used for login authorization
     * @return          the Like object if user is logged-in;
     *                  <code>null</code> otherwise.
     */
    @PostMapping(path="/like/{vibeId}")
    public ResponseEntity <Like> like(@PathVariable int vibeId, @RequestHeader("Authorization") String jwt){
        try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.like(vibeId, id), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }//catch
    }//like(int, String)
    
    /**
     * This method finds all Vibes posted by the current Account.
     * @param jwt   the String to be decoded,
     *              used for login authorization
     * @return      the list of Vibe objects posted by the logged-in Account;
     *              <code>null</code> otherwise.
     */
    @GetMapping(path="/find/account", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vibe>> findByPoster(@RequestHeader("Authorization") String jwt){
        try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findByPoster(id), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }//catch
    }//findByPoster(String)
    
    /**
     * This method finds all the Likes with the desired vibeId.
     * @param vibeId    the integer representing the id of the Vibe
     * @param jwt       the String to be decoded,
     *                  used for login authorization
     * @return          the list of Like objects that the desired Vibe received
     *                  if the user is logged in;
     *                  <code>null</code> otherwise.
     */
    @GetMapping("/likes/{vibeId}")
    public ResponseEntity<List<Like>> getAllLikes(@PathVariable int vibeId, @RequestHeader("Authorization") String jwt){
        try {
            int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
            return new ResponseEntity<>(this.vibeService.findLikesByVibeId(vibeId), HttpStatus.OK);
        }catch(java.lang.NullPointerException e) {
            return null;
        }//catch
    }//getAllLikes(int, String)
}//VibeController