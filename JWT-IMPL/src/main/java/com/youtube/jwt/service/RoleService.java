package com.youtube.jwt.service;

import com.youtube.jwt.dtos.RoleDto;
import com.youtube.jwt.entities.Role;

public interface RoleService {

	RoleDto createNewRole(RoleDto roleDto);
	
	Role findRoleById(String role);
}
