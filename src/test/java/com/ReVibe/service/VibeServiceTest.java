package com.ReVibe.service;

import com.ReVibe.model.Vibe;
import com.ReVibe.repository.VibeRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    
    @BeforeEach
    public void setUp(){
        vibeService = new VibeService(vibeRepository);
    }
    
    /**
     * Test of save method, of class VibeService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Vibe vibe = null;
        VibeService instance = null;
        Vibe expResult = null;
        Vibe result = instance.save(vibe);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class VibeService.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        VibeService instance = null;
        Vibe expResult = null;
        Vibe result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class VibeService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        VibeService instance = null;
        List<Vibe> expResult = null;
        List<Vibe> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
