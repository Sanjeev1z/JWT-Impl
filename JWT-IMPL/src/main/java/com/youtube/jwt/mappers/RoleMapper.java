package com.youtube.jwt.mappers;

import org.mapstruct.Mapper;

import com.youtube.jwt.dtos.RoleDto;
import com.youtube.jwt.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	
	RoleDto toRoleDto(Role role);
	
	Role toRoleEntity(RoleDto roleDto);
}
