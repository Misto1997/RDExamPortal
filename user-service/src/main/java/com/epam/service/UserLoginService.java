package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.entity.UserEntity;
import com.epam.repository.UserDetailsRepository;

@Service
public class UserLoginService implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		int userId = Integer.parseInt(username);
		UserEntity userDetails = userDetailsRepository.findById(userId).orElse(null);

		if (userDetails == null) {
			throw new UsernameNotFoundException("User not found!!!");
		} else {
			return new UserDetailsImpl(userDetails);
		}

	}

}
