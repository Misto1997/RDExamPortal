package com.epam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.epam.entity.TrainerEntity;
import com.epam.model.TrainerDto;

@Mapper
public interface RegisterMapper {
	TrainerDto toTrainerDto(TrainerEntity trainerData);

	TrainerEntity toTrainerEntity(TrainerDto trainerData);

	RegisterMapper MAPPER = Mappers.getMapper(RegisterMapper.class);

}
