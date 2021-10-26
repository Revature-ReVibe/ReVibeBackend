package com.ReVibe.test.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ReVibe.controller.JwtController;
import com.ReVibe.model.Account;
import com.ReVibe.service.AccountService;
import com.ReVibe.service.JwtService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class JWTControllerTest {
	
	@Mock
	private AccountService accountService;
	@Mock
	private JwtService jwtService;
	@InjectMocks
	private JwtController jwtController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(jwtController).build();
	}
	
	@Test
	public void testLogUserIn() {
		String user = "John";
		String password = "password";
		Account account = new Account(1, "John", "jsmith", "root", "j@smith.com", null, null, null);
		
		Mockito.when(accountService.findByUsernameAndPassword(user, password)).thenReturn(account);
		
		
			String jwt = JwtService.createJWT(password, user, password, 0);
			
//			Assertions.assertEquals(HttpStatus, null);
//		
	}
	
	
	

}
