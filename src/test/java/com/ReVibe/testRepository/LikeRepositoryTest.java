package com.ReVibe.testRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.ReVibe.model.Like;
import com.ReVibe.repository.LikeRepository;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@Disabled
public class LikeRepositoryTest {
	
	@Autowired
	LikeRepository likeRepository;
	
	@Test
	public void testFindByUserId() {
		int userid = 1;
		
		List<Like> likes = likeRepository.findByUserId(userid);
		
		assertEquals(userid, likes.get(0).getUserId());
		
		
	}
	
	@Test
	public void testFindByVibeId() {
		int vibeid = 1;
		
		List<Like> likes = likeRepository.findByVibeId(vibeid);
		
		assertEquals(vibeid, likes.get(0).getVibeId());
	}

	@Test
	public void testFindByVibeIdAndUserId() {
		int userid = 1;
		int vibeid = 1;
		
		Like like = likeRepository.findByVibeIdAndUserId(vibeid, userid);
		
		assertEquals(userid, like.getUserId());
		assertEquals(vibeid, like.getVibeId());
				
	}
}
