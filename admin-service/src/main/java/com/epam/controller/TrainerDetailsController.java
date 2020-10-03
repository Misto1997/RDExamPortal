package com.epam.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(value = "/trainer")
public class TrainerDetailsController {

	@Autowired
	RegisterService registerService;

	@Autowired
	Response responsePojo;

	@Value("${name}")
	String name;

	@PostMapping(value = "/register", consumes = "application/json")
	@ApiOperation(value = "endpoint for registering trainer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registration successfull"),
			@ApiResponse(code = 406, message = "something went wrong"),
			@ApiResponse(code = 409, message = "Trainer Already registered!"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<Response> registerTrainer(@RequestBody TrainerDto trainer)
			throws UnableToModifyException, UpdationFailureException {
		System.out.println(name);
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
