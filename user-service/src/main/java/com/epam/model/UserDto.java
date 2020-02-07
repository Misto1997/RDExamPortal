package com.epam.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {

	@Min(value = 500000, message = "invalid user id")
	@Max(value = 800000, message = "invalid user id")
	private int UserId;

	@NotNull(message = "first name cannot not be null")
	@Size(min = 1, max = 30, message = "firstname should be atleast of size 1 and atmost 30")
	private String firstName;

	@NotNull(message = "last  name cannot not be null")
	@Size(min = 1, max = 30, message = "lastname should be atleast of size 1 and atmost 30")
	private String lastName;

	@Min(value = 1, message = "invalid domain id")
	@Max(value = 10, message = "invalid domain id")
	private int domainId;

	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = "weak password")
	private String password;

}
