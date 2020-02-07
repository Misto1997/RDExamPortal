package com.epam.service;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.epam.exception.UpdationFailureException;
import com.epam.mapper.RegisterMapper;
import com.epam.model.TrainerDto;
import com.epam.repository.RegisterRepository;

@Service
public class RegisterService {

	@Autowired
	RegisterRepository registerRepository;

	@Value("${adminKey}")
	private String adminKey;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);

	@Transactional(rollbackOn = Exception.class)
	public TrainerDto registerTrainer(TrainerDto trainer) throws SQLException, UpdationFailureException {
		TrainerDto trainerDetails = RegisterMapper.MAPPER
				.toTrainerDto(registerRepository.findById(trainer.getTrainerId()).orElse(null));
		if (!trainer.getKey().equals(adminKey))
			throw new UpdationFailureException("Wrong key entered!");
		if (trainerDetails == null) {
			try {
				trainer.setPassword(passwordEncoder.encode(trainer.getPassword()));
				registerRepository.save(RegisterMapper.MAPPER.toTrainerEntity(trainer));
			} catch (Exception e) {
				throw new SQLException();
			}
			return trainer;
		} else {
			return null;
		}

	}

}
