package com.ReVibe.ReVibe.testServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ReVibe.model.Like;
import com.ReVibe.model.Vibe;
import com.ReVibe.repository.LikeRepository;
import com.ReVibe.repository.VibeRepository;
import com.ReVibe.service.VibeService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class TestVibeService {

	@Autowired 
	MockMvc mockMvc;
	
	@InjectMocks
	VibeService vibeService;
	
	@Mock
	VibeRepository vibeRepository;
	
	@Mock
	LikeRepository likeRepository;
	
	
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(vibeService).build();
	}
	
	@Test
	public void testSaveVibe() {
		Vibe vibe = new Vibe(1, "pic", "hello again friend of a friend", 0, null, 1, 0, null, null);
		vibe.setVibeLike(0);
		when(vibeRepository.save(vibe)).thenReturn(vibe);
		
		assertEquals(vibe, vibe);
	}
	
	@Test
	public void testSaveReply() {
		Vibe vibe = new Vibe(1, "pic", "hello again friend of a friend", 0, null, 1, 0, null, null);
		int id = 10;
		vibe.setParentVibe(id);
		when(vibeRepository.save(vibe)).thenReturn(vibe);
		
		assertEquals(id, vibe.getParentVibe());
	}
	
	@Test
	public void testFindById() {
		int id = 1;
		Vibe vibe = new Vibe(1, "pic", "hello again friend of a friend", 0, null, 1, 0, null, null);
		Optional<Vibe> vibes = Optional.of(vibe);
		
		when(vibeRepository.findById(id)).thenReturn(vibes);
		
		assertEquals(id, vibes.get().getVibeId());
	}
	
	@Test
	public void testFindAll() {
		Vibe vibe = new Vibe(1, "pic", "hello again friend of a friend", 0, null, 1, 0, null, null);
		Vibe vibe2 = new Vibe(2, "pic2", "hello again friend of a friend", 1, null, 1, 0, null, null);
		List<Vibe> vibes = new ArrayList<Vibe>();
		vibes.add(vibe);
		vibes.add(vibe2);
		
		when(vibeRepository.findAll()).thenReturn(vibes);
		
		assertEquals(2, vibes.size());
	}
	
	@Test
	public void testLikeAddALike() {
		int vibeId = 1;
		int accountId = 1;
		Vibe vibe = new Vibe(1, "pic", "hello again friend of a friend", 0, null, 1, 0, null, null);
		Like like = new Like(1,1,1);
		vibe.setVibeLike(vibe.getVibeLike()+1);
		vibeRepository.save(vibe);
		when(likeRepository.save(like)).thenReturn(like);
		
		verify(vibeRepository, times(1)).save(vibe);
		
		assertEquals(vibeId, vibe.getVibeId());
		assertEquals(1, vibe.getVibeLike());
			
	}
	
	@Test
	public void testLikeRemoveLike() {
		int vibeId = 1;
		int accountId = 1;
		Vibe vibe = new Vibe(1, "pic", "hello again friend of a friend", 1, null, 1, 0, null, null);
		Like like = new Like(1,1,1);
		
		vibe.setVibeLike(vibe.getVibeLike()-1);
		vibeRepository.save(vibe);
		likeRepository.delete(like);
		
		verify(vibeRepository, times(1)).save(vibe);
		verify(likeRepository, times(1)).delete(like);
		
		assertEquals(0, vibe.getVibeLike());
	}
	
	@Test
	public void testFindLikesByVibeId() {
		int vibeId = 1;
		Like like = new Like(1,1,1);
		Like like2 = new Like(2,1,2);
		List<Like> likes = new ArrayList<Like>();
		likes.add(like2);
		likes.add(like);
		
		when(likeRepository.findByVibeId(vibeId)).thenReturn(likes);
		
		assertEquals(2, likes.size());
	}
	
	@Test
	public void testFindByPoster() {
		int accountId = 1;
		Vibe vibe = new Vibe(1, "pic", "hello again friend of a friend", 1, null, 1, 0, null, null);
		Vibe vibe2 = new Vibe(2, "pic2", "hello again friend of a friend", 1, null, 1, 0, null, null);
		List<Vibe> vibes = new ArrayList<Vibe>();
		
		vibes.add(vibe2);
		vibes.add(vibe2);
		
		when(vibeRepository.findByAccountid(accountId)).thenReturn(vibes);
		
		assertEquals(2, vibes.size());
		
	}
}
