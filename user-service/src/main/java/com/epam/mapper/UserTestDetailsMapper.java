package com.epam.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.epam.entity.UserTestDetailsEntity;
import com.epam.model.UserTestDataDto;
import com.epam.model.UserTestDetailsDto;

@Mapper
public interface UserTestDetailsMapper {

	UserTestDetailsDto toUserTestDetailsDto(UserTestDetailsEntity userTestDetailsData);

	List<UserTestDetailsDto> toUserTestDetailsDto(List<UserTestDetailsEntity> userTestDetailsData);

	List<UserTestDataDto> toUserTestDataDto(List<UserTestDetailsEntity> userTestData);

	UserTestDetailsEntity toUserTestDetailsEntity(UserTestDetailsDto userTestDetailsData);

	UserTestDetailsMapper MAPPER = Mappers.getMapper(UserTestDetailsMapper.class);

}
