package com.epam.model;

import lombok.Data;

@Data
public class UserTestDetailsDto {

	private int userId;

	private int testId;

	private int totalMarks;

	private int securedMarks;

}
