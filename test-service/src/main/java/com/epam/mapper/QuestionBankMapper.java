package com.epam.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.epam.entity.QuestionBankEntity;
import com.epam.model.QuestionBankDto;

@Mapper
public interface QuestionBankMapper {

	List<QuestionBankEntity> toQuestionBankEntity(List<QuestionBankDto> registerDtoList);

	List<QuestionBankDto> toQuestionBankDto(List<QuestionBankEntity> registerEntityList);

	QuestionBankMapper MAPPER = Mappers.getMapper(QuestionBankMapper.class);

}
