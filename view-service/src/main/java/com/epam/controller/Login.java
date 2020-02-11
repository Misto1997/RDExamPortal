package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.exception.NoContentFoundException;
import com.epam.model.JwtRequest;
import com.epam.model.JwtResponse;
import com.epam.model.LoginUserDto;
import com.epam.model.MentorOrTrainerDto;
import com.epam.security.JwtTokenUtil;
import com.epam.service.AdminLoginService;
import com.epam.service.LoginUserService;
import com.epam.service.UserLoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController

public class Login {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	UserLoginService userLoginService;

	@Autowired
	AdminLoginService AdminLoginService;

	@PostMapping(value = "/authenticate/user")
	public ResponseEntity<?> createAuthenticationTokenForUser(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		final LoginUserDto loginUserDto = loginUserService.getLoginUserDetail(authenticationRequest.getUsername());

		return ResponseEntity.ok(new JwtResponse("Bearer " + token, loginUserDto));
	}

	@PostMapping(value = "/authenticate/admin")
	public ResponseEntity<?> createAuthenticationTokenForAdmin(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		final LoginUserDto loginUserDto = loginUserService.getLoginUserDetail(authenticationRequest.getUsername());

		return ResponseEntity.ok(new JwtResponse("Bearer " + token, loginUserDto));
	}

	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}

}
