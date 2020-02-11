package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.entity.QuestionBankEntity;

@Repository
public interface QuestionBankRepository extends JpaRepository<QuestionBankEntity, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM question_bank ORDER BY RAND() LIMIT 10")
	List<QuestionBankEntity> findBytestId(int testId);

}
