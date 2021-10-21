package com.ReVibe.model;

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
@Table(name="vibe")
public class Vibe {

	@Id
	@Column(name = "vibeid")
	int vibeId;
	@Column(name = "name")
	String vibeName;
	@Column(name = "vibepic")
	String vibePic;
	@Column(name = "vibemessage")
	String vibeMessage;
	@Column(name = "vibelike")
	int vibeLike;
	@OneToMany
	@JoinColumn(name = "commentid")
	List<Comment> comments;
	
	
}
