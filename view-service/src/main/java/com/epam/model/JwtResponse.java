package com.epam.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private HttpStatus status;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
		this.status = HttpStatus.OK;
	}

	public String getJwttoken() {
		return jwttoken;
	}

}
