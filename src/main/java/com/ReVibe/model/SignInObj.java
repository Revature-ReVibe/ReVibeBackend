package com.ReVibe.model;

import lombok.Data;

@Data
public class SignInObj {
	private String username;
	private String password;
	
	@Override
	public String toString() {
		return "SignInObj [username=" + username + ", password=" + password + "]";
	}
}
