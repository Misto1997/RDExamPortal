package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.entity.TrainerEntity;
import com.epam.repository.RegisterRepository;

@Service
public class AdminLoginService implements UserDetailsService {

	@Autowired
	RegisterRepository registerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		int AdminId = Integer.parseInt(username);
		TrainerEntity userDetails = registerRepository.findById(AdminId).orElse(null);

		if (userDetails == null) {
			throw new UsernameNotFoundException("Admin not found!!!");
		} else {
			return new AdminDetailsImpl(userDetails);
		}

	}

}
