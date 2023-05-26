package com.youtube.jwt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.youtube.jwt.entities.Role;

public interface RoleRepository extends CrudRepository<Role,String>{
	
}
