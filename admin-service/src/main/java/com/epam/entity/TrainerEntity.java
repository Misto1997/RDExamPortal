package com.epam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "trainer_details")
@Data
public class TrainerEntity {

	@Id
	@Column(name = "trainer_id")
	private int trainerId;

	@Column(name = "trainer_name")
	private String trainerName;

	@Column(name = "domain_id")
	private int domainId;

	@Column(name = "domain_name")
	private String domainName;

	@Column(name = "password")
	private String password;

	@Column(name = "register_key")
	private String key;

}
