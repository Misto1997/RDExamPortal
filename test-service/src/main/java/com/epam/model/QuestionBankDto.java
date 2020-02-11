package com.epam.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class QuestionBankDto {

	@Size(min = 10, max = 500, message = "question should be atleast of size 10 and atmost 500")
	private String question;

	@Min(value = 1, message = "invalid test id")
	private int testId;

	@Size(min = 1, max = 1, message = "invalid answer")
	private String answer;

	@Size(min = 1, max = 500, message = "option should be atleast of size 1 and atmost 500")
	private String option1;

	@Size(min = 1, max = 500, message = "option should be atleast of size 1 and atmost 500")
	private String option2;

	@Size(min = 1, max = 500, message = "option should be atleast of size 1 and atmost 500")
	private String option3;

	@Size(min = 1, max = 500, message = "option should be atleast of size 1 and atmost 500")
	private String option4;

}
