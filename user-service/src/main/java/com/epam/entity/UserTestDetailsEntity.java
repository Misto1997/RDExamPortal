package com.epam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_test_details")
@Data
public class UserTestDetailsEntity {

	@Id
	@Column(name = "s_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sNo;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "test_id")
	private int testId;

	@Column(name = "total_marks")
	private int totalMarks;

	@Column(name = "secured_marks")
	private int securedMarks;

}
