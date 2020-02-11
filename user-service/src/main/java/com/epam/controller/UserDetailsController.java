package com.epam.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.exception.UnableToModifyException;
import com.epam.exception.UpdationFailureException;
import com.epam.model.UserDto;
import com.epam.responsemodel.Response;
import com.epam.service.UserDetailsService;

@RestController
@RequestMapping(value = "/user")
public class UserDetailsController {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	Response responsePojo;

	@PostMapping(value = "/register", consumes = "application/json")
	public ResponseEntity<Response> registerUser(@RequestBody @Valid UserDto user)
			throws UnableToModifyException, UpdationFailureException {
		UserDto userDto;
		try {
			userDto = userDetailsService.registerUser(user);
		} catch (SQLException sql) {
			throw new UpdationFailureException("something went wrong");
		}
		if (userDto == null) {
			throw new UnableToModifyException("User Already registered!");
		}

		responsePojo.setStatus(HttpStatus.OK);
		responsePojo.setMessage("OK");
		responsePojo.setDetail("Registration successfull");
		return new ResponseEntity<>(responsePojo, HttpStatus.OK);

	}

}
