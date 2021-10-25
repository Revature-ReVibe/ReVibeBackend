package com.ReVibe.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

//@WebMvcTest(AccountController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	@MockBean
	private AccountService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET /account/getall success")
	public void accountGetAllSuccess() throws Exception {
		List<Account> accounts = new LinkedList<>();
		accounts.add(new Account(1, "userName1", "password1", "George Washington", null, null, null, null));
		accounts.add(new Account(2, "userName2", "password2", "John Adams", null, null, null, null));
		when(service.findAll()).thenReturn(accounts);
		this.mockMvc.perform(get("/account/getall"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].accountid", is(1)))
			.andExpect(jsonPath("$[0].username", is("userName1")))
			.andExpect(jsonPath("$[0].password", is("password1")))
			.andExpect(jsonPath("$[1].accountid", is(2)))
			.andExpect(jsonPath("$[2].username", is("userName2")))
			.andExpect(jsonPath("$[3].password", is("password2")))
	}

	@Test
	@DisplayName("GET /account/{id} Found")
	public void testFindByUserId() throws Exception {
		Account account = new Account(1, "userName1", "password1", "George Washington", null, null, null, null);
		doReturn(Optional.of(newAccount)).when(service).findByUserId(1);

		mockMvc.perform(get("/account/{id}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].accountid", is(1)))
			.andExpect(jsonPath("$[0].username", is("userName1")))
			.andExpect(jsonPath("$[0].password", is("password1")))
	}

	@Test
	@DisplayName("GET /account/{id} Not Found")
	void testFindByUserIdNotFound() throws Exception {
		doReturn(Optional.empty()).when(service).findByUserId(1);

		mockMvc.perform(get("/account/{id}", 1))
			.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("PUT /account/updateprofile/{id} Success")
	public void testUpdateprofile() throws Exception {
		Account accountPut = new Account("userName1", "password1", "Martha Washing", null, null, null, null);
		Account accountSavedReturned = new Account(1, "userName1", "password1", "Martha Washing", null, null, null, null);
		Account accountSavedUpdate = new Account(1, "userName1", "password1", "Martha Washington", null, null, null, null);
		doReturn(Optional.of(accountSavedReturned)).when(service).findByUserId(1);
		doReturn(accountSavedUpdate).when(service).merge(any());
		mockMvc.perform(put("/account/updateprofile/{id}", 1)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(accountPut)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.accountid", is(1)))
			.andExpect(jsonPath("$.username", is("userName1")))
			.andExpect(jsonPath("$.password", is("password1")))
			.andExpect(jsonPath("$.name", is("Martha Washington")))
	}

	@Test
	@DisplayName("PUT /account/updateprofile/{id} Conflict")
	void testUpdateprofileConflict() throws Exception {
		Account accountPut = new Account("userName1", "password1", "Martha Washing", null, null, null, null);
		Account accountReturned = new Account(1, "userName1", "password1", "Martha Washing", null, null, null, null);
		doReturn(Optional.of(accountReturned)).when(service).findByUserId(1);
		doReturn(accountReturned).when(service).merge(any());
		mockMvc.perform(put("/account/updateprofile/{id}", 1)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(accountPut)))
			.andExpect(status().isConflict());
	}

	@Test
	@DisplayName("PUT /account/updateprofile/{id} Not Found")
	void testUpdateprofileNotFound() throws Exception {
		Account accountPut = new Account("userName1", "password1", "Martha Washing", null, null, null, null);
		doReturn(Optional.empty()).when(service).findByUserId(1);
		mockMvc.perform(put("/account/updateprofile/{id}", 1)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(accountPut)))
			.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("POST /account/new")
	public void testSaveAccount() throws Exception {
		Account accountNew = new Account("userName1", "password1", "George Washington", null, null, null, null);
		Account accountSaved = new Account(1, "userName1", "password1", "George Washington", null, null, null, null);
		doReturn(accountSaved).when(service).saveAccount(any());
		mockMvc.perform(post("/account/new")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(accountNew)))
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.accountid", is(1)))
			.andExpect(jsonPath("$.username", is("userName1")))
			.andExpect(jsonPath("$.password", is("password1")))
	}
}
