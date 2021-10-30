package com.ReVibe.testRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.ReVibe.model.Vibe;
import com.ReVibe.repository.VibeRepository;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class VibeRespositoryTest {
	
	@Autowired
	VibeRepository vibeRepository;
	
	@Test
	public void testFindByParentVibe() {
		
		Vibe vibe = new Vibe();
		vibe.setVibeId(2);
		vibe.setParentVibe(1);
		vibe.setAccountid(2);
		List<Vibe> vibes = vibeRepository.findByParentVibe(vibe);
		
		assertEquals(vibe.getVibeId(), vibes.get(0).getVibeId());
	}
	
	@Test
	public void testFindByAccountId() {
		int accountid = 1;
		List<Vibe> vibes = vibeRepository.findByAccountid(accountid);
		
		assertEquals(accountid, vibes.get(0).getAccountid());
	}

}
