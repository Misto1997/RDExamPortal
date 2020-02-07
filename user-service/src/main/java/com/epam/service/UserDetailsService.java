package com.epam.service;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.epam.mapper.UserDetailsMapper;
import com.epam.model.UserDto;
import com.epam.repository.UserDetailsRepository;

@Service
public class UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);

	@Transactional(rollbackOn = Exception.class)
	public UserDto registerUser(UserDto user) throws SQLException{
		UserDto userDetails = UserDetailsMapper.MAPPER
				.toUserDto(userDetailsRepository.findById(user.getUserId()).orElse(null));
		if (userDetails == null) {
			try {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userDetailsRepository.save(UserDetailsMapper.MAPPER.toUserEntity(user));
			} catch (Exception e) {
				throw new SQLException();
			}
			return user;
		} else {
			return null;
		}

	}

}
