package com.ReVibe.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vibe {

	@Id
	@Column
	@GeneratedValue
	int vibeId;
	@Column
	String vibeName;
	@Column
	String vibePic;
	@Column
	String vibeMessage;
	@Column
	int vibeLike;
	@Column
	@OneToMany
	Account account;
	@Column
	@OneToMany
	Comment comment;
	
	
}
