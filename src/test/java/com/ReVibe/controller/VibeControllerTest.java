package com.ReVibe.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;

import com.ReVibe.model.Like;
import com.ReVibe.model.Vibe;
import com.ReVibe.service.VibeService;
import com.ReVibe.service.JwtService;

@SpringBootTest
@AutoConfigureMockMvc
public class VibeControllerTest {

	@MockBean
	private VibeService vibeService;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private VibeController vibeController;

	@BeforeEach 
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(vibeController).build();
	}

	@Test
	public void testSaveVibe() throws Exception {
		Vibe newVibe = new Vibe(1, "pic", "message", null, null, 3, null, null, null);

		when(vibeService.saveVibe(Mockito.any(Vibe.class))).thenReturn(newVibe);

		this.mockMvc.perform(post("/vibe/createVibe")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(newVibe))
			.header("Authorization", JwtService.createJWT("abc", "def", "3", 10000)))

			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.vibeId", is(1)))
			.andExpect(jsonPath("$.accountid", is(3)));

		verify(vibeService,times(1)).saveVibe(newVibe);
	}

	@Test
	public void testCreateReply() throws Exception {
		Vibe newReplyVibe = new Vibe(1, "pic", "message", null, null, 3, 10, null, null);

		when(vibeService.saveReply(newReplyVibe, newReplyVibe.getParentVibe())).thenReturn(newReplyVibe);

		this.mockMvc.perform(post("/vibe/createReply/"+newReplyVibe.getParentVibe())
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(newReplyVibe))
			.header("Authorization", JwtService.createJWT("abc", "def", "3", 10000)))

			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.vibeId", is(1)))
			.andExpect(jsonPath("$.accountid", is(3)))
			.andExpect(jsonPath("$.parentVibe", is(10)));

		verify(vibeService,times(1)).saveReply(Mockito.any(Vibe.class),Mockito.any(Integer.class));
	}

	@Test
		public void testFindById() throws Exception{
			Vibe newVibe = new Vibe(11, "pic", "message", null, null, 3, 10, null, null);

			when(vibeService.findById(Mockito.any(Integer.class))).thenReturn(newVibe);

			this.mockMvc.perform(get("/vibe/find/{vibeId}", 11)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", JwtService.createJWT("abc", "def", "3", 10000)))

				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.vibeId", is(11)))
				.andExpect(jsonPath("$.accountid", is(3)))
				.andExpect(jsonPath("$.parentVibe", is(10)));

			verify(vibeService,times(1)).findById(Mockito.anyInt());
	}

	@Test
		public void testFindByPoster() throws Exception{
			List<Vibe> vibes = new LinkedList<>();
			vibes.add(new Vibe(1,"pic1","message1",null,null,2,null,null,null));
			vibes.add(new Vibe(2,"pic2","message2",null,null,2,null,null,null));

			when(vibeService.findByPoster(Mockito.any(Integer.class))).thenReturn(vibes);

			this.mockMvc.perform(get("/vibe/find/account")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", JwtService.createJWT("abc", "def", "2", 10000)))

				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].accountid", is(2)))
				.andExpect(jsonPath("$[1].accountid", is(2)));

			verify(vibeService,times(1)).findByPoster(Mockito.anyInt());
	}

	@Test
		public void testFindAll() throws Exception{
			List<Vibe> vibes = new LinkedList<>();
			vibes.add(new Vibe(0,"pic1","message1",null,null,7,null,null,null));
			vibes.add(new Vibe(10,"pic2","message2",null,null,16,null,null,null));
			vibes.add(new Vibe(11,"pic2","message2",null,null,16,10,null,null));

			when(vibeService.findAll()).thenReturn(vibes);

			this.mockMvc.perform(get("/vibe/all")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", JwtService.createJWT("abc", "def", "7", 10000)))

				.andDo(print())
				.andExpect(status().isOk()) 
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].vibeId", is(0)))
				.andExpect(jsonPath("$[0].accountid", is(7)))
				.andExpect(jsonPath("$[0].parentVibe", nullValue()))
				.andExpect(jsonPath("$[1].vibeId", is(10)))
				.andExpect(jsonPath("$[1].accountid", is(16)))
				.andExpect(jsonPath("$[1].parentVibe", nullValue()))
				.andExpect(jsonPath("$[2].vibeId", is(11)))
				.andExpect(jsonPath("$[2].accountid", is(16)))
				.andExpect(jsonPath("$[2].parentVibe", is(10)));

			verify(vibeService,times(1)).findAll();
	}
	@Test
		public void testGetAllLikes() throws Exception{
			List<Like> likes = new LinkedList<>();
			likes.add(new Like(1,5,10));
			likes.add(new Like(2,5,11));
	
			when(vibeService.findLikesByVibeId(Mockito.anyInt())).thenReturn(likes);
	
			this.mockMvc.perform(get("/vibe/likes/{vibeId}","5")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", JwtService.createJWT("abc", "def", "2", 10000)))
			
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].vibeId", is(5)))
				.andExpect(jsonPath("$[1].vibeId", is(5)));
	
	verify(vibeService,times(1)).findLikesByVibeId(Mockito.anyInt());
	}
	
	@Test
    public void testLike() throws Exception{
        Like newLike = new Like(1,5,10);

        when(vibeService.like(Mockito.anyInt(),Mockito.anyInt())).thenReturn(newLike);

        this.mockMvc.perform(post("/vibe/like/{vibeId}", 5)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", JwtService.createJWT("abc", "def", "10", 10000)))

            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.likeId", is(1)))
            .andExpect(jsonPath("$.vibeId", is(5)))
            .andExpect(jsonPath("$.userId", is(10)));;

        verify(vibeService,times(1)).like(Mockito.anyInt(),Mockito.anyInt());
	}
	
	@Test
	public void testFindAllPosts() throws Exception{
		List<Vibe> vibes = new LinkedList<>();
		vibes.add(new Vibe(0,"pic1","message1",null,null,7,null,null,null));
		vibes.add(new Vibe(10,"pic2","message2",null,null,16,null,null,null));
		vibes.add(new Vibe(11,"pic2","message2",null,null,16,null,null,null));
		
		when(vibeService.findAllPosts()).thenReturn(vibes);
		
		this.mockMvc.perform(get("/vibe/all/post")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", JwtService.createJWT("abc", "def", "7", 10000)))

				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].vibeId", is(0)))
				.andExpect(jsonPath("$[0].accountid", is(7)))
				.andExpect(jsonPath("$[0].parentVibe", nullValue()))
				.andExpect(jsonPath("$[1].vibeId", is(10)))
				.andExpect(jsonPath("$[1].accountid", is(16)))
				.andExpect(jsonPath("$[1].parentVibe", nullValue()))
				.andExpect(jsonPath("$[2].vibeId", is(11)))
				.andExpect(jsonPath("$[2].accountid", is(16)));
		
		verify(vibeService,times(1)).findAllPosts();
		
	}
	
	@Test
	public void testFindAllReplies() throws Exception{
		List<Vibe> vibes = new LinkedList<>();
		vibes.add(new Vibe(0,"pic1","message1",null,null,7,null,null,null));
		vibes.add(new Vibe(10,"pic2","message2",null,null,16,null,null,null));
		vibes.add(new Vibe(11,"pic2","message2",null,null,16,10,null,null));
		
		when(vibeService.findByParentVibe(Mockito.any(Integer.class))).thenReturn(vibes);
		
		this.mockMvc.perform(get("/vibe/allReplies/{parentVibe}" , 10)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", JwtService.createJWT("abc", "def", "7", 10000)))

				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].vibeId", is(0)))
				.andExpect(jsonPath("$[0].accountid", is(7)))
				.andExpect(jsonPath("$[0].parentVibe", nullValue()))
				.andExpect(jsonPath("$[1].vibeId", is(10)))
				.andExpect(jsonPath("$[1].accountid", is(16)))
				.andExpect(jsonPath("$[1].parentVibe", nullValue()))
				.andExpect(jsonPath("$[2].vibeId", is(11)))
				.andExpect(jsonPath("$[2].accountid", is(16)))
				.andExpect(jsonPath("$[2].parentVibe", is(10)));
		
			verify(vibeService,times(1)).findByParentVibe(Mockito.anyInt());
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

