package com.epam.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.exception.UpdationFailureException;
import com.epam.model.TestDto;
import com.epam.responsemodel.Response;
import com.epam.service.TestService;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	TestService testService;

	@Autowired
	Response responsePojo;

	@PostMapping(value = "/addTest", consumes = "application/json")
	public ResponseEntity<Response> addNewTest(@RequestBody @Valid TestDto test) throws UpdationFailureException {
		try {
			testService.addTest(test);
		} catch (SQLException sql) {
			throw new UpdationFailureException("something went wrong");
		}
		responsePojo.setStatus(HttpStatus.OK);
		responsePojo.setMessage("OK");
		responsePojo.setDetail("Test created successfull");
		return new ResponseEntity<>(responsePojo, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteTest/{testId}")
	public ResponseEntity<Response> deleteTest(@PathVariable int testId) throws UpdationFailureException {
		try {
			testService.deleteTest(testId);
		} catch (SQLException sql) {
			throw new UpdationFailureException("something went wrong");
		}
		responsePojo.setStatus(HttpStatus.OK);
		responsePojo.setMessage("OK");
		responsePojo.setDetail("Test deleted successfull");
		return new ResponseEntity<>(responsePojo, HttpStatus.OK);

	}

}
