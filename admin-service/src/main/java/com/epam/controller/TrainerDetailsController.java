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
import com.epam.model.TrainerDto;
import com.epam.responsemodel.Response;
import com.epam.service.RegisterService;

@RestController
@RequestMapping(value = "/trainer")
public class TrainerDetailsController {

	@Autowired
	RegisterService registerService;

	@Autowired
	Response responsePojo;

	@PostMapping(value = "/register")
	public ResponseEntity<Response> registerTrainer(@RequestBody @Valid TrainerDto trainer)
			throws UnableToModifyException, UpdationFailureException {
		TrainerDto trainerDto;
		try {
			trainerDto = registerService.registerTrainer(trainer);
		} catch (UpdationFailureException e) {
			throw new UpdationFailureException(e.getMessage());
		} catch (SQLException sql) {
			throw new UpdationFailureException("something went wrong");
		}
		if (trainerDto == null) {
			throw new UnableToModifyException("Trainer Already registered!");
		}

		responsePojo.setStatus(HttpStatus.OK);
		responsePojo.setMessage("OK");
		responsePojo.setDetail("Registration successfull");
		return new ResponseEntity<>(responsePojo, HttpStatus.OK);

	}

}
