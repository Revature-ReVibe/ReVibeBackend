package com.ReVibe.model;

import java.util.Date;

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

	@Column
	String vibePic;
	@Column
	String vibeMessage;
	@Column
	Date date;
	
	@ManyToOne
	Account poster;	
	@OneToMany
	Account[] likes;
	@ManyToOne
	Vibe parentVibe;
	
	
			
}
