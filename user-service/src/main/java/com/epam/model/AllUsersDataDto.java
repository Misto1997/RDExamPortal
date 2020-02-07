package com.epam.model;

import java.util.List;

import lombok.Data;

@Data
public class AllUsersDataDto {

	UserDataDto userData;

	List<UserTestDataDto> userTestData;

}
