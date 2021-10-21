package com.ReVibe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="account")
public class Account {
	
	@Id
	@Column(name = "accountid")
	int userId;
	@Column(name = "username")
	String username;
	@Column(name = "password")
	String password;
	@Column (name = "name")
	String name;
	@Column(name = "profilepic")
	String profilepic;
	@OneToMany
	@JoinColumn(name = "accountid")
	List<Vibe> vibes = new ArrayList<Vibe>();
	@OneToMany
	@JoinColumn(name = "accountid")
	List<Comment> comments = new ArrayList<Comment>();
	

}
