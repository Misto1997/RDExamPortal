package com.epam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "test_details")
@Data
public class TestEntity {

	@Id
	@Column(name = "test_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int testId;

	@Column(name = "test_name")
	private String testName;

	@Column(name = "domain_id")
	private int domainId;

}
