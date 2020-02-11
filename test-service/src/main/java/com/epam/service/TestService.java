package com.epam.service;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.mapper.TestMapper;
import com.epam.model.TestDto;
import com.epam.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	TestRepository testRepository;

	public void addTest(@Valid TestDto test) throws SQLException {
		try {
			testRepository.save(TestMapper.MAPPER.toTestEntity(test));
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	public void deleteTest(int testId) throws SQLException {
		try {
			testRepository.deleteById(testId);
		} catch (Exception e) {
			throw new SQLException();
		}
	}

}
