package com.epam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "question_bank")
@Data
public class QuestionBankEntity {

	@Id
	@Column(name = "question_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;

	@Column(name = "question")
	private String question;

	@Column(name = "test_id")
	private int testId;

	@Column(name = "answer")
	private String answer;

	@Column(name = "option1")
	private String option1;

	@Column(name = "option2")
	private String option2;

	@Column(name = "option3")
	private String option3;

	@Column(name = "option4")
	private String option4;

}
