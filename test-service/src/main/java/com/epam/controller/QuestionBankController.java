package com.epam.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.exception.NoContentFoundException;
import com.epam.exception.UnableToModifyException;
import com.epam.exception.UpdationFailureException;
import com.epam.model.QuestionBankDto;
import com.epam.responsemodel.Response;
import com.epam.service.QuestionBankService;

@RestController
@Validated
@RequestMapping(value = "/questionBank")
public class QuestionBankController {

	@Autowired
	QuestionBankService questionBankService;

	@Autowired
	Response responsePojo;

	@PostMapping(value = "/addQuestions", consumes = "application/json")
	public ResponseEntity<Response> addQuestions(@RequestBody List<@Valid QuestionBankDto> questions)
			throws UpdationFailureException, UnableToModifyException {
		List<QuestionBankDto> questionBankDto;
		try {
			questionBankDto = questionBankService.addQuestions(questions);
		} catch (SQLException sql) {
			throw new UpdationFailureException("something went wrong!");
		}
		if (questionBankDto.isEmpty()) {
			throw new UnableToModifyException("Unable to modify!");
		}
		responsePojo.setStatus(HttpStatus.OK);
		responsePojo.setMessage("OK");
		responsePojo.setDetail("Questions added successfull");
		return new ResponseEntity<>(responsePojo, HttpStatus.OK);
	}

	@GetMapping(value = "/getQuestions/{testId}", produces = "application/json")
	public ResponseEntity<List<QuestionBankDto>> getQuestions(@PathVariable int testId) throws NoContentFoundException {
		List<QuestionBankDto> questionBankDto = questionBankService.getQuestions(testId);
		if (questionBankDto.isEmpty()) {
			throw new NoContentFoundException("Question bank for this test is not available for now!");
		}
		return new ResponseEntity<>(questionBankDto, HttpStatus.OK);
	}

}
