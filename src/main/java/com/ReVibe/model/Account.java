package com.ReVibe.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	@Id
	@Column
	@GeneratedValue
	int userId;
	@Column
	String username;
	@Column
	String password;
	@Column
	String name;
	

}
