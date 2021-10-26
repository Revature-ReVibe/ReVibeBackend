package com.ReVibe.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "account")
public class Account {

	@Id
	@Column(name = "accountid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int userId;
	@Column(name = "username")
	String username;
	@Column(name = "password")
	String password;
	@Column(name = "name")
	String name;
	@Column(name = "email")
	String email;
	@Column(name = "profilepic")
	String profilePic;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="accountid")
	private List<Vibe> vibe;;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="accountid")
	private List<Like> likes;
	@Override
	public String toString() {
		return "Account [userId=" + userId + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", profilePic=" + profilePic + ", vibe=" + vibe + ", likes=" + likes + "]";
	}

}