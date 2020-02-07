package com.epam.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.epam.exception.NoContentFoundException;
import com.epam.exception.NotAbleToSaveException;
import com.epam.exception.SqlCustomerException;
import com.epam.exception.UnableToModifyException;
import com.epam.exception.UpdationFailureException;
import com.epam.responsemodel.Response;

@RestControllerAdvice
public class ErrorHandler {

	@Autowired
	Response responsePojo;

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> error(Exception exception) {
		responsePojo.setStatus(HttpStatus.BAD_REQUEST);
		responsePojo.setMessage("Bad Request");
		responsePojo.setDetail(exception.getMessage());

		return new ResponseEntity<>(responsePojo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoContentFoundException.class)
	public ResponseEntity<Object> error(NoContentFoundException exception) {
		responsePojo.setStatus(HttpStatus.NOT_FOUND);
		responsePojo.setMessage("Not Found");
		responsePojo.setDetail(exception.getMessage());
		return new ResponseEntity<>(responsePojo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotAbleToSaveException.class)
	public ResponseEntity<Response> error(NotAbleToSaveException exception) {
		responsePojo.setStatus(HttpStatus.NOT_ACCEPTABLE);
		responsePojo.setMessage("Not Able to Save");
		responsePojo.setDetail(exception.getMessage());
		return new ResponseEntity<>(responsePojo, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(UnableToModifyException.class)
	public ResponseEntity<Object> error(UnableToModifyException exception) {
		responsePojo.setStatus(HttpStatus.CONFLICT);
		responsePojo.setMessage("Conflict");
		responsePojo.setDetail(exception.getMessage());
		return new ResponseEntity<>(responsePojo, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UpdationFailureException.class)
	public ResponseEntity<Object> error(UpdationFailureException exception) {
		responsePojo.setStatus(HttpStatus.NOT_ACCEPTABLE);
		responsePojo.setMessage("Not Acceptable");
		responsePojo.setDetail(exception.getMessage());

		return new ResponseEntity<>(responsePojo, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(SqlCustomerException.class)
	public ResponseEntity<Response> error(SqlCustomerException exception) {
		responsePojo.setStatus(HttpStatus.CONFLICT);
		responsePojo.setMessage("SQL Error");
		responsePojo.setDetail(exception.getMessage());
		return new ResponseEntity<>(responsePojo, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Response> error(ConstraintViolationException exception) {
		responsePojo.setStatus(HttpStatus.NOT_ACCEPTABLE);
		responsePojo.setMessage("Not Acceptable");
		responsePojo.setDetail(exception.getMessage());
		return new ResponseEntity<>(responsePojo, HttpStatus.NOT_ACCEPTABLE);

	}

}
