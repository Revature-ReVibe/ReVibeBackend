package com.ReVibe.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.mockito.Mockito;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;

import com.ReVibe.model.Account;
import com.ReVibe.service.AccountService;
import com.ReVibe.service.JwtService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	private static Account userAccount;
	private static String userJWT;
	private static String expiredUserJWT;
	private static String expiredJWT;

	@MockBean
	private AccountService service;

	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		userAccount = new Account(99, "userName99", "password99", "James Madison", null, null, null, null);
		userJWT = JwtService.createJWT(UUID.randomUUID().toString(), "ReViveBackend", String.valueOf(99), 600000L);
		expiredUserJWT = JwtService.createJWT(UUID.randomUUID().toString(), "ReViveBackend", String.valueOf(99), 0L);
	}

	@Test
	public void accountGetAllSuccess() throws Exception {
		List<Account> accounts = new LinkedList<>();
		accounts.add(new Account(1, "userName1", "password1", "George Washington", null, null, null, null));
		accounts.add(new Account(2, "userName2", "password2", "John Adams", null, null, null, null));

		for(int i=0; i<accounts.size();i++) {
			accounts.get(i).setUsername(null);
			accounts.get(i).setPassword(null);
		}

		System.out.println("accountsaccountsaccountsaccounts: " + accounts);

		when(service.findAll()).thenReturn(accounts);
		mockMvc.perform(get("/account/getall")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", userJWT))

			//.andDo(print())
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
	}

	@Test
	public void testFindByUserId() throws Exception {
		int id = Integer.valueOf((String) JwtService.decodeJWT(userJWT).get("sub"));
		doReturn(userAccount).when(service).findByUserId(id);

		userAccount.setUsername(null);
		userAccount.setPassword(null);

		mockMvc.perform(get("/account/findbyId")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", userJWT))

			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.userId", is(id)))
			.andExpect(jsonPath("$.username", nullValue()))
			.andExpect(jsonPath("$.password", nullValue()))
			.andExpect(jsonPath("$.name", is("James Madison")));
	}

	@Test
	public void testFindByUserIdNotFound() throws Exception {
		doReturn(userAccount).when(service).findByUserId(66);

		userAccount.setUsername(null);
		userAccount.setPassword(null);

		mockMvc.perform(get("/account/findbyId")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", userJWT))

			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId").doesNotExist())
			.andExpect(jsonPath("$.username").doesNotExist())
			.andExpect(jsonPath("$.password").doesNotExist())
			.andExpect(jsonPath("$.name").doesNotExist());
	}

	//@Test
	//public void testUpdateprofile() throws Exception {
	//  Account accountPut = new Account("userName22", "password22", "Martha Washing", null, null, null, null);
	//  Account accountSavedReturned = new Account(22, "userName22", "password22", "Martha Washing", null, null, null, null);
	//  Account accountSavedUpdate = new Account(22, "userName22", "password22", "Martha Washington", null, null, null, null);
	//  String updateUserJWT = JwtService.createJWT(UUID.randomUUID().toString(), "ReViveBackend", String.valueOf(22), 600000L);

	//  doReturn(accountSavedReturned).when(service).findByUserId(22);
	//  doReturn(accountSavedUpdate).when(service).merge(any());

	//  mockMvc.perform(put("/account/updateprofile")
	//    .contentType(MediaType.APPLICATION_JSON)
	//    .header("Authorization", updateUserJWT)
	//    .content(asJsonString(accountPut)))

	//    .andExpect(status().isOk())
	//    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	//    .andExpect(jsonPath("$.userId", is(22)))
	//    .andExpect(jsonPath("$.username", is("userName22")))
	//    .andExpect(jsonPath("$.password", is("password22")))
	//    .andExpect(jsonPath("$.name", is("Martha Washington")));
	//}
}
