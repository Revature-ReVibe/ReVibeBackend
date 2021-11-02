package com.ReVibe.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ReVibe.model.Account;
import com.ReVibe.service.AccountService;
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class JwtControllerTest {

	@Mock
	private AccountService accountService;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private JwtController jwtController;

	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(jwtController).build();
	}

	@Test
	public void testLogUserInSuccess() throws Exception {
		Account account = new Account(12, "userName1", "password1", "Alexander Jackson", null, null, null, null);

		doReturn(account).when(accountService).findByUsernameAndPassword(Mockito.any(String.class),Mockito.any(String.class));

		mockMvc.perform(post("/jwt/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(account)))

			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("jwt").exists());
			verify(accountService,times(1)).findByUsernameAndPassword(Mockito.anyString(),Mockito.anyString());
	}

	@Test
	public void testLogUserInFailure() throws Exception {
		Account account = null;
		doReturn(account).when(accountService).findByUsernameAndPassword(Mockito.any(String.class),Mockito.any(String.class));

		mockMvc.perform(post("/jwt/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(account)))

			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("jwt").doesNotHaveJsonPath());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
