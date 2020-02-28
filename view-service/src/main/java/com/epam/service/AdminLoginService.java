package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epam.model.UserData;

@Service(value = "adminLogin")
public class AdminLoginService implements UserDetailsService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserData user = restTemplate.getForObject("http://admin-service/login/" + username, UserData.class);

		if (user == null) {
			throw new UsernameNotFoundException("Admin not found!!!");
		} else {
			return new UserDetailsImpl(user);
		}

	}

}
