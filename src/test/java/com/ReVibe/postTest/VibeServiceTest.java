package com.ReVibe.postTest;

import com.ReVibe.model.Like;
import com.ReVibe.model.Vibe;
import com.ReVibe.repository.LikeRepository;
import com.ReVibe.repository.VibeRepository;
import com.ReVibe.service.VibeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VibeServiceTest {
    
    @InjectMocks
    private VibeService vibeService;
    
    @Mock
    private VibeRepository vibeRepository;
    {
        MockitoAnnotations.openMocks(this);
    }
    
    @Mock
    private LikeRepository likeRepository;
    {
        MockitoAnnotations.openMocks(this);
    }
    
    @BeforeEach
    public void setUp(){
        vibeService = new VibeService(vibeRepository, likeRepository);
    }

    @Test
    public void testFindById() {
        int id = 0;
        
        Vibe vibe = new Vibe();
        vibe.setVibeId(id);
        Optional<Vibe> vibeOp = Optional.of(vibe);
        Mockito.when(vibeRepository.findById(id)).thenReturn(vibeOp);
        Vibe result = vibeService.findById(id);
        assertEquals(vibeOp.get(), result);
    }

    @Test
    public void testFindAll() {
        Vibe vibe1 = new Vibe();
        Vibe vibe2 = new Vibe();
        List<Vibe> expResult = new ArrayList<>();
        expResult.add(vibe1);
        expResult.add(vibe2);
        Mockito.when(vibeRepository.findAll()).thenReturn(expResult);
        
        List<Vibe> result = vibeService.findAll();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLikeAndUnlike(){
        int vibeId = 1;
        int accountid = 3;
        
        Vibe vibe = new Vibe();
        vibe.setVibeId(vibeId);
        
        vibe.setVibeLike(0);
        Like like = new Like(vibeId, accountid);
                
        Mockito.when(vibeRepository.findById(vibeId)).thenReturn(Optional.of(vibe));
        Mockito.when(likeRepository.findByVibeIdAndUserId(vibeId, accountid)).thenReturn(null);
        
        Vibe expVibe = vibe;
        expVibe.setVibeLike(1);
        Mockito.when(vibeRepository.save(vibe)).thenReturn(expVibe);
        
        Like expLike = like;
        Mockito.when(likeRepository.save(like)).thenReturn(expLike);
        
        assertEquals(expLike, vibeService.like(vibeId, accountid));
        assertEquals(expVibe, vibeService.findById(vibeId));
        
        Mockito.when(likeRepository.findByVibeIdAndUserId(vibeId, accountid)).thenReturn(expLike);
        expVibe.setVibeLike(0);
        Mockito.when(vibeRepository.save(vibe)).thenReturn(expVibe);
        
        assertNull(vibeService.like(vibeId, accountid));
        assertEquals(expVibe, vibeService.findById(vibeId));
    }
    
}
