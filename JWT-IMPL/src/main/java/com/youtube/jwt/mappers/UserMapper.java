package com.youtube.jwt.mappers;

import org.mapstruct.Mapper;

import com.youtube.jwt.dtos.UserDto;
import com.youtube.jwt.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDto toUserDto(User user);
	
	User toUserEntity(UserDto userDto);
}
