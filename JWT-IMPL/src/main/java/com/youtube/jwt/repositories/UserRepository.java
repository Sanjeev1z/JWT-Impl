package com.youtube.jwt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.youtube.jwt.entities.User;

public interface UserRepository extends CrudRepository<User,String>{

}
