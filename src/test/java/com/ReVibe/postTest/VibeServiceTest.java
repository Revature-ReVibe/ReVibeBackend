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
    public void testSaveVibe() {
        Vibe vibe = new Vibe();
        vibe.setVibeMessage("Hello!");
        
        Mockito.when(vibeRepository.save(vibe)).thenReturn(vibe);
        
        Vibe expResult = vibe;
        Vibe result = vibeService.saveVibe(vibe);
        assertEquals(expResult.getVibeMessage(), result.getVibeMessage());
    }
    
    @Test
    public void testSaveReply(){
        Vibe expResult = new Vibe();
        int parentId = 2;
        
        Mockito.when(vibeRepository.save(expResult)).thenReturn(expResult);
        Vibe result = vibeService.saveReply(expResult, parentId);
        expResult.setParentVibe(parentId);
        
        assertEquals(expResult, result);
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
    public void testLike(){
        int vibeid = 1;
        int accountid = 3;
        
        Like expResult = new  Like(vibeid, accountid);
        Mockito.when(likeRepository.save(expResult)).thenReturn(expResult);
        
        Like result = vibeService.like(vibeid, accountid);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUnlike(){
        int vibeid = 1;
        int accountid = 3;
        
        Like expResult = null;
        Mockito.when(likeRepository.save(expResult)).thenReturn(expResult);
        
        vibeService.like(vibeid, accountid);
        Like result = vibeService.like(vibeid, accountid);
        assertEquals(expResult, result);
    }
    
    
}
