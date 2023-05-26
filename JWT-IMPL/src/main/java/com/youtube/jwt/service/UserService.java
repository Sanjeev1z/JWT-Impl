package com.youtube.jwt.service;

import com.youtube.jwt.dtos.UserDto;

public interface UserService {
	
	UserDto registerNewUser(UserDto userDto);
	 
	void initDummyUserAndRoleData();
	
}
