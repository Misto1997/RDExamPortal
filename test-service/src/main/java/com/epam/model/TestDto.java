package com.epam.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TestDto {

	@Size(min = 4, max = 30, message = "testname should be atleast of size 4 and atmost 30")
	private String testName;

	@Min(value = 1, message = "invalid domain id")
	@Max(value = 10, message = "invalid domain id")
	private int domainId;

}
