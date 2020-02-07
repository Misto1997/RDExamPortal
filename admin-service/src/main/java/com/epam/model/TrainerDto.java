package com.epam.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TrainerDto {

	@Min(value = 500000, message = "invalid trainer id")
	@Max(value = 800000, message = "invalid trainer id")
	private int trainerId;

	@NotNull(message = "name cannot not be null")
	@Size(min = 1, max = 30, message = "trainer name should be atleast of size 1 and atmost 30")
	private String trainerName;

	@Min(value = 1, message = "invalid domain id")
	@Max(value = 10, message = "invalid domain id")
	private int domainId;

	@NotNull(message = "domain name cannot not be null")
	private String domainName;

	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = "weak password")
	private String password;

	@NotNull(message = "key cannot not be null")
	private String key;

}
