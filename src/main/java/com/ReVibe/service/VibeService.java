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
    
    /**
     * Constructor
     * @param vibeRepository    the VibeRepository instance used to access the
     *                          repository methods
     * @param likeRepository    the LikeRepository instance used to access the
     *                          repository methods
     */
    @Autowired
    public VibeService(VibeRepository vibeRepository, LikeRepository likeRepository){
        this.vibeRepository = vibeRepository;
        this.likeRepository = likeRepository;
    }//VibeService(VibeRepository, LikeRepository)
    
    /**
     * The saveVibe method takes the Vibe from the controller, configures it,
     * then sends it to the repository to be saved
     * @param vibe  the Vibe object to be saved
     * @return      the Vibe object that was saved
     */
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
    }//saveVibe(Vibe)
    
    /**
     * The saveReply method takes the Vibe from the controller, configures it,
     * then sends the Vibe to the repository to be saved
     * The Vibes passed here are replies to other Vibes, thus they have
     * a parent id
     * @param vibe      the Vibe object to be saved
     * @param parentId  the integer representing the id of the parent Vibe
     * @return          the Vibe object that was saved
     */
    public Vibe saveReply(Vibe vibe, int parentId) {
        log.info("Saving a reply to vibe {}", parentId);      
        vibe.setVibeLike(0);
        LocalDateTime timeStamp = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeStamp.format(format);
        vibe.setDate(timeStamp);
    	vibe.setParentVibe(parentId);

    	Vibe reply = vibeRepository.save(vibe);
    	log.info("{} saved", reply);

    	return reply;
    }//saveReply(Vibe, int)
    
    /**
     * The findById method gets the id from the controller and passes it to 
     * the repository as a search query
     * @param id    the integer representing the id of the Vibe
     * @return      the Vibe object with the requested id; May be
     *              <code>null</code>.
     */
    public Vibe findById(int id){

        log.info("Finding vibe with id {}", id);
        return vibeRepository.findById(id).get();
    }//findById(int)
    
    /**
     * The findAll method retrieves all Vibes from the repository
     * @return  the list of Vibe objects from the repository; May be empty.
     */
    public List<Vibe> findAll(){
        log.info("Finding all vibes");
        
        return vibeRepository.findAll();
    }//findAll()
    
    /**
     * The findAllPosts method retrieves all Vibes that have no parent Vibe
     * from the repository
     * @return  the list of Vibe objects with no parent Vibe from the repository;
     *          May be empty.
     */
    public List<Vibe> findAllPosts(){
        log.info("Finding all original posts");
        List<Vibe> vibes = vibeRepository.findByParentVibeNull();
        
        vibes.forEach(v -> {
            v.setReplyVibes(vibeRepository.findByParentVibe(v.getVibeId()));
        });
        return vibes;
    }//findAllPosts()

    /**
     * The like method searches the repository for a Like that has the desired
     * Vibe id and Account id; It adds the Like if it does not exist and removes
     * the Like if it does exist.
     * @param vibeId        the integer representing the id of the Vibe
     * @param accountId     the integer representing the id of the Account
     * @return              the Like object that meets the criteria;
     *                      <code>null</code> otherwise.
     */
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
            }//else
        }//if vibe is not null
        return null;
    }//like(int, int)
    
    /**
     * The findByParentVibe method gets all replies to a specified Vibe
     * @param parentVibe    the integer representing the id of the parent Vibe
     * @return              the list of Vibe objects with the parent Vibe 
     *                      requested; May be empty.
     */
    public List<Vibe> findByParentVibe(int parentVibe){
        log.info("Find all replies for vibe {}", parentVibe);
        return vibeRepository.findByParentVibe(parentVibe);
    }//findByParentVibe(int)
    
    /**
     * The findLikesByVibeId method gets all Likes for a specified Vibe
     * @param vibeId    the integer representing the id of the Vibe
     * @return          the list of Like objects with the requested Vibe id;
     *                  May be empty.
     */
    public List<Like> findLikesByVibeId(int vibeId){
        log.info("Find all likes for vibe {}", vibeId);

        return likeRepository.findByVibeId(vibeId);
    }//findLikesByVibeId(int)
    
    /**
     * The findByPoster method gets all Likes made by a specified Account
     * @param accountId     the integer representing the id of the Account
     * @return              the list of Like objects with the requested Account
     *                      id; May be empty.
     */
    public List<Vibe> findByPoster(int accountId){
        log.info("Finding all vibes posted by account {}", accountId);
    	return vibeRepository.findByAccountid(accountId);
    }//findByPoster(int)
}//VibeService
