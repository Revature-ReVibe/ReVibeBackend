package com.ReVibe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vibe {

	@Id
	@Column
	@GeneratedValue
	int vibeId;
	@ManyToOne
	Account poster;
	@Column
	String vibePic;
	@Column
	String vibeMessage;
	@OneToMany
	Account[] likes;
	@ManyToOne
	Vibe parentVibe;
	
	
}
