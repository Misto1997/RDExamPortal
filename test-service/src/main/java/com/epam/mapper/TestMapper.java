package com.epam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.epam.entity.TestEntity;
import com.epam.model.TestDto;

@Mapper
public interface TestMapper {

	TestDto toTestDto(TestEntity testData);

	TestEntity toTestEntity(TestDto trainerData);

	TestMapper MAPPER = Mappers.getMapper(TestMapper.class);

}
