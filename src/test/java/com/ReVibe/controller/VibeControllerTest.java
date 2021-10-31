package com.ReVibe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ReVibe.model.Vibe;
import com.ReVibe.service.JwtService;
import com.ReVibe.service.VibeService;

import io.jsonwebtoken.Claims;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class VibeControllerTest {
	
	@InjectMocks
	VibeController vibeController;
	
	@Mock
	VibeService vibeService;
	
	@Mock
	JwtService jwtService;
	
	@Autowired
	MockMvc mockMvc;
	
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(vibeController).build();
	}
	
	@Test
	public void testSaveSuccess() {
		Vibe vibe = new Vibe(1, "pic", "message", null, null, 1, null, null, null);
		when(vibeService.saveVibe(vibe)).thenReturn(vibe);
		assertEquals(vibe, vibe);
	}
	

}
