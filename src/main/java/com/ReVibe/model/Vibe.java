package com.ReVibe.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vibe")
public class Vibe {

	@Id
	@Column(name = "vibeid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int vibeId;

	@Column(name = "vibepic")
	private String vibePic;

	@Column(name = "vibemessage")
	private String vibeMessage;
	@Column(name = "vibelike")
	private Integer vibeLike;
	@OneToMany
	@JoinColumn(name = "likeid")
	private List<Like> likes;
	@Column(name = "accountid")
	private int accountid;
	@Column(name = "parentvibe")
	private Integer parentVibe;
	
	@Column(name = "vibetimestamp")
	Date date;

	@Transient
	private List<Vibe> replyVibes;
}

