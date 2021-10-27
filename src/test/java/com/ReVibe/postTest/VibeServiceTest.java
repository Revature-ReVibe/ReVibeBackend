package com.ReVibe.postTest;

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
    
    /**
     * Test of save method, of class VibeService.
     */
//    @Test
//    public void testSave() {
//        System.out.println("save");
//        Vibe vibe = null;
//        Vibe expResult = vibe;
//        
//        Mockito.when(vibeRepository.save(vibe)).thenReturn(vibe);
//        Vibe result = vibeService.save(vibe);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of findById method, of class VibeService.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        
        Vibe vibe = new Vibe();
        vibe.setVibeId(id);
        Optional<Vibe> vibeOp = Optional.of(vibe);
        Mockito.when(vibeRepository.findById(id)).thenReturn(vibeOp);
        Vibe result = vibeService.findById(id);
        assertEquals(vibeOp.get(), result);
    }

    /**
     * Test of findAll method, of class VibeService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        Vibe vibe1 = null;
        Vibe vibe2 = null;
        List<Vibe> expResult = new ArrayList<>();
        expResult.add(vibe1);
        expResult.add(vibe2);
        Mockito.when(vibeRepository.findAll()).thenReturn(expResult);
        
        List<Vibe> result = vibeService.findAll();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLike(){
        System.out.println("like");
        int vibeid = 1;
        
        
    }
    
}
