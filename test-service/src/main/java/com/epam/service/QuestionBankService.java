package com.epam.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.mapper.QuestionBankMapper;
import com.epam.model.QuestionBankDto;
import com.epam.repository.QuestionBankRepository;

@Service
public class QuestionBankService {

	@Autowired
	QuestionBankRepository questionBankRepository;

	@Transactional(rollbackOn = Exception.class)
	public List<QuestionBankDto> addQuestions(List<@Valid QuestionBankDto> questions) throws SQLException {
		try {
			questionBankRepository.saveAll(QuestionBankMapper.MAPPER.toQuestionBankEntity(questions));
		} catch (Exception e) {
			throw new SQLException();
		}
		return questions;
	}

	public List<QuestionBankDto> getQuestions(int testId) {
		return QuestionBankMapper.MAPPER.toQuestionBankDto(questionBankRepository.findBytestId(testId));
	}

}
