package com.epam.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.epam.entity.UserEntity;
import com.epam.model.UserDataDto;
import com.epam.model.UserDto;

@Mapper
public interface UserDetailsMapper {
	UserDto toUserDto(UserEntity userData);

	UserEntity toUserEntity(UserDto userData);

	List<UserDataDto> toUserDtos(List<UserEntity> userData);

	UserDetailsMapper MAPPER = Mappers.getMapper(UserDetailsMapper.class);

}
