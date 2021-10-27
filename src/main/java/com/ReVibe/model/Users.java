package com.ReVibe.model;

import lombok.Data;

@Data
public class Users {
	int id;
    String username;
    String password;
    String firstName;
    String lastName;
    String token;
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", token=" + token + "]";
	}
}
