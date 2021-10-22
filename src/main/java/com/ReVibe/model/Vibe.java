package com.ReVibe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vibe {

	@Id
	@Column(name="vibeid")
	int vibeId;

	@Column(name="vibepic")
	String vibePic;
	@Column(name="vibemessage")
	String vibeMessage;
	@Column(name="vibedate")
	Date date;
	
	@ManyToOne
	Account poster;	
	@OneToMany @OrderColumn
	Account[] likes;
	@ManyToOne
	Vibe parentVibe;
	
	
			
}
