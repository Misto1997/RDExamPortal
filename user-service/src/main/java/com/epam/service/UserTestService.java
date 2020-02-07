package com.epam.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.mapper.UserDetailsMapper;
import com.epam.mapper.UserTestDetailsMapper;
import com.epam.model.AllUsersDataDto;
import com.epam.model.UserDataDto;
import com.epam.model.UserTestDataDto;
import com.epam.model.UserTestDetailsDto;
import com.epam.repository.UserDetailsRepository;
import com.epam.repository.UserTestDetailsRepository;

@Service
public class UserTestService {

	@Autowired
	UserTestDetailsRepository userTestDetailsRepository;

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Transactional(rollbackOn = Exception.class)
	public UserTestDetailsDto submitTestDetails(UserTestDetailsDto userTestDetails) throws SQLException {
		UserTestDetailsDto userTestDetailsDto = UserTestDetailsMapper.MAPPER.toUserTestDetailsDto(
				userTestDetailsRepository.findUser(userTestDetails.getTestId(), userTestDetails.getUserId()));
		if (userTestDetailsDto == null) {
			try {
				userTestDetailsRepository.save(UserTestDetailsMapper.MAPPER.toUserTestDetailsEntity(userTestDetails));
			} catch (Exception e) {
				throw new SQLException();
			}
			return userTestDetails;
		} else {
			return null;
		}

	}

	public List<UserTestDataDto> getUserTestData(int userId) throws SQLException {
		try {
			return UserTestDetailsMapper.MAPPER.toUserTestDataDto(userTestDetailsRepository.findByUserId(userId));
		} catch (Exception e) {
			throw new SQLException();
		}

	}

	public List<AllUsersDataDto> getAllUsersTestData(int domainId) throws SQLException {
		List<AllUsersDataDto> allData = new ArrayList<>();
		AllUsersDataDto userData;
		try {
			List<UserDataDto> allUsersDetails = UserDetailsMapper.MAPPER
					.toUserDtos(userDetailsRepository.findByDomainId(domainId));
			for (UserDataDto data : allUsersDetails) {
				List<UserTestDataDto> usersTestDetails = UserTestDetailsMapper.MAPPER
						.toUserTestDataDto(userTestDetailsRepository.findByUserId(data.getUserId()));
				userData = new AllUsersDataDto();
				userData.setUserData(data);
				userData.setUserTestData(usersTestDetails);
				allData.add(userData);
			}
			return allData;
		} catch (Exception e) {
			throw new SQLException();
		}
	}
}
