package com.ReVibe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@Column(name = "commentid")
	int commentId;
	@Column(name = "message")
	String message;
	@Column(name = "accountid")
	int accountid;
	@Column(name = "vibeid")
	int vibeid;

}
