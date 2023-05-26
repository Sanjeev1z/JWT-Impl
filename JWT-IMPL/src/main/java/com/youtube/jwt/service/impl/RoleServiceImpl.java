package com.youtube.jwt.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtube.jwt.dtos.RoleDto;
import com.youtube.jwt.entities.Role;
import com.youtube.jwt.mappers.RoleMapper;
import com.youtube.jwt.repositories.RoleRepository;
import com.youtube.jwt.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public RoleDto createNewRole(RoleDto roleDto) {
		Role role = this.roleMapper.toRoleEntity(roleDto);
		role = roleRepository.save(role);
		return roleMapper.toRoleDto(role);		
	}
	
	@Override
	public Role findRoleById(String role) {
		Optional<Role> entity = this.roleRepository.findById(role);
		if (!entity.isPresent()) {
				throw new EntityNotFoundException("Role is not found with id: " + role);
		}		
		return entity.get();
	}
}
