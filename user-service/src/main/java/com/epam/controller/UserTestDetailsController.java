package com.epam.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.exception.NoContentFoundException;
import com.epam.exception.SqlCustomerException;
import com.epam.exception.UnableToModifyException;
import com.epam.exception.UpdationFailureException;
import com.epam.model.AllUsersDataDto;
import com.epam.model.UserTestDataDto;
import com.epam.model.UserTestDetailsDto;
import com.epam.responsemodel.Response;
import com.epam.service.UserTestService;

@RestController
@RequestMapping(value = "/traineeTest")
public class UserTestDetailsController {

	@Autowired
	Response responsePojo;

	@Autowired
	private UserTestService userTestService;

	@PostMapping(value = "/submit", consumes = "application/json")
	public ResponseEntity<Response> setUserTestDetails(@RequestBody @Valid UserTestDetailsDto userTestDetails)
			throws UnableToModifyException, UpdationFailureException {

		UserTestDetailsDto userTestDetailsDto;
		try {
			userTestDetailsDto = userTestService.submitTestDetails(userTestDetails);
		} catch (SQLException sql) {
			throw new UpdationFailureException("something went wrong");
		}
		if (userTestDetailsDto == null) {
			throw new UnableToModifyException("Test Already given");
		}
		responsePojo.setStatus(HttpStatus.OK);
		responsePojo.setMessage("OK");
		responsePojo.setDetail("Test Submitted successfully");
		return new ResponseEntity<>(responsePojo, HttpStatus.OK);
	}

	@GetMapping(value = "/details/{userId}", produces = "application/json")
	public ResponseEntity<List<UserTestDataDto>> getUserTestDetails(@PathVariable int userId)
			throws NoContentFoundException, SqlCustomerException {
		List<UserTestDataDto> userTestDataDto = new ArrayList<>();
		try {
			userTestDataDto = userTestService.getUserTestData(userId);
		} catch (SQLException e) {
			throw new SqlCustomerException("Something went wrong from our side sorry!");

		}
		if (userTestDataDto.isEmpty())
			throw new NoContentFoundException("You haven't given any test yet!");

		return new ResponseEntity<>(userTestDataDto, HttpStatus.OK);

	}

	@GetMapping(value = "/traineeDetails/{domainId}", produces = "application/json")
	public ResponseEntity<List<AllUsersDataDto>> getUsersfTestDetails(@PathVariable int domainId)
			throws NoContentFoundException, SqlCustomerException {
		List<AllUsersDataDto> allUsersTestDto = new ArrayList<>();
		try {
			allUsersTestDto = userTestService.getAllUsersTestData(domainId);
		} catch (SQLException e) {
			throw new SqlCustomerException("Something went wrong from our side sorry!");

		}
		if (allUsersTestDto.isEmpty())
			throw new NoContentFoundException("no user registered to this domain yet");

		return new ResponseEntity<>(allUsersTestDto, HttpStatus.OK);

	}

}
