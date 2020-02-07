package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.epam.entity.UserTestDetailsEntity;

public interface UserTestDetailsRepository extends JpaRepository<UserTestDetailsEntity, Integer> {

	@Query("select d from UserTestDetailsEntity d where d.testId = ?1 AND d.userId = ?2")
	public UserTestDetailsEntity findUser(int testId, int userId);

	@Query("select d from UserTestDetailsEntity d where d.userId= ?1")
	public List<UserTestDetailsEntity> findByUserId(int userId);

}
