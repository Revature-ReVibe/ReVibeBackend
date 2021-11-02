package com.ReVibe.controller;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import com.ReVibe.model.Account;
import com.ReVibe.service.AccountService;
import com.ReVibe.service.JwtService;
//import com.ReVibe.service.JwtService;
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Mock	
	private AccountService accountService;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private AccountController accountController;

	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}

	private JwtService jwtService; 

	@Test
	@DisplayName("GET /account/getall")
	public void accountGetAllSuccess() throws Exception {
		List<Account> accounts = new LinkedList<>();
		accounts.add(new Account(1, "userName1", "password1", "George Washington", null, null, null, null));
		accounts.add(new Account(2, "userName2", "password2", "John Adams", null, null, null, null));
		for(int i=0; i<accounts.size();i++) {
			accounts.get(i).setUsername(null);
			accounts.get(i).setPassword(null);
		}
		when(accountService.findAll()).thenReturn(accounts);
		this.mockMvc.perform(get("/account/getall").header("Authorization", jwtService.createJWT("abc", "def", "100", 10000)))
		.andDo(print())
		.andExpect(status().isOk())	
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].userId", is(1)))
		.andExpect(jsonPath("$[0].username", nullValue()))
		.andExpect(jsonPath("$[0].password", nullValue()))
		.andExpect(jsonPath("$[0].name", is("George Washington")))
		.andExpect(jsonPath("$[1].userId", is(2)))
		.andExpect(jsonPath("$[1].username", nullValue()))
		.andExpect(jsonPath("$[1].password", nullValue()))
		.andExpect(jsonPath("$[1].name", is("John Adams")));
		verify(accountService,times(1)).findAll();
	}	

	@Test
	public void testFindByUserId() throws Exception {
		when(accountService.findByUserId(Mockito.any(Integer.class))).thenReturn(new Account(1,"userName","password","MyName", null, null, null, null));
		this.mockMvc.perform(get("/account/findbyId").header("Authorization", jwtService.createJWT("abc", "def", "100", 10000)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.userId", is(1)));
		verify(accountService,times(1)).findByUserId(Mockito.anyInt());
	}

	@Test
	public void testFindByName() throws Exception {
		when(accountService.findByName(Mockito.any(String.class))).thenReturn(new Account(1,"userName","password","MyName", null, null, null, null));
		this.mockMvc.perform(get("/account/name").header("Authorization", jwtService.createJWT("abc", "def", "100", 10000)).param("name","asdf"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.userId", is(1)));
		verify(accountService,times(1)).findByName(Mockito.any(String.class));
	}
	
	@Test
	public void testUpdateProfile() throws Exception {
		when(accountService.findByUserId(Mockito.anyInt())).thenReturn(new Account());
		this.mockMvc.perform(post("/account/updateprofile").header("Authorization", jwtService.createJWT("abc", "def", "100", 10000)).contentType(MediaType.APPLICATION_JSON)
				 .content("{ \n"
				 		+ "\"name\""
				 		+ ":"
				 		+ "\"a\", \n"
				 		+ "\"password\""
				 		+ ":"
				 		+ "\"p\", \n"
				 		+ "\"username\""
				 		+ ":"
				 		+ "\"u\" \n"
				 		+ "}")
				);
		verify(accountService,times(1)).merge(Mockito.any(Account.class));
	}
	
	@Test
	public void testSearchAccount() throws Exception {
		List<Account> accounts = new LinkedList<>();
		accounts.add(new Account(1, "userName1", "password1", "Name1", null, null, null, null));
		accounts.add(new Account(2, "userName2", "password2", "Name2", null, null, null, null));
		
		when(accountService.findBySearchName(Mockito.any(String.class))).thenReturn(accounts);
		this.mockMvc.perform(get("/account/searchaccounts").header("Authorization", jwtService.createJWT("abc", "def", "100", 10000)).contentType(MediaType.APPLICATION_JSON)
				 .content("{ \n"
				 		+ "\"name\""
				 		+ ":"
				 		+ "\"c\" \n"
				 		+ "}")
				);
		verify(accountService,times(1)).findBySearchName(Mockito.anyString());
	}
	
	@Test
	public void testResetPass() throws Exception {
		Account account = new Account();
		when(accountService.findByEmail(Mockito.any(String.class))).thenReturn(account);
		when(accountService.findByUserId(Mockito.anyInt())).thenReturn(account);
		this.mockMvc.perform(post("/account/resetpass").contentType(MediaType.APPLICATION_JSON)
				 .content("{ \n"
				 		+ "\"username\""
				 		+ ":"
				 		+ "\"aUserName\", \n"
				 		+ "\"password\""
				 		+ ":"
				 		+ "\"aPassWord\", \n"
				 		+ "\"email\""
				 		+ ":"
				 		+ "\"isthisanemail@yahoo.com\", \n"
				 		+ "\"name\""
				 		+ ":"
				 		+ "\"aName\" \n"
				 		+ "}")
				);
		verify(accountService,times(1)).findByEmail(Mockito.anyString());
	}
	@Test
	public void testSaveAccount() throws Exception {
		Account account = new Account(0,"userName","password","MyName", null, null, null, null);
		when(accountService.saveAccount(Mockito.any(Account.class))).thenReturn(account);
		this.mockMvc.perform(post("/account/new").contentType(MediaType.APPLICATION_JSON)
				 .content("{ \n"
				 		+ "\"username\""
				 		+ ":"
				 		+ "\"userName\", \n"
				 		+ "\"password\""
				 		+ ":"
				 		+ "\"password\", \n"
				 		+ "\"name\""
				 		+ ":"
				 		+ "\"MyName\" \n"
				 		+ "}")
				);
		verify(accountService,times(1)).saveAccount(account);
	}
}
