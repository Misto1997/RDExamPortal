package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epam.model.UserData;

@Service(value = "userLogin")
public class UserLoginService implements UserDetailsService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserData user = restTemplate.getForObject("http://user-service/login/" + username, UserData.class);

		if (user == null) {
			throw new UsernameNotFoundException("User not found!!!");
		} else {
			return new UserDetailsImpl(user);
		}

	}

}
