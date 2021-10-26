package com.ReVibe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="liketable")
public class Like {

	@Id
	@Column(name = "likeid")
	int likeId;
	@Column(name = "vibeid")
	int vibeId;
	@Column(name = "accountid")
	int userId;
	
}
