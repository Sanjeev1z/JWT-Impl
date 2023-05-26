package com.youtube.jwt.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.youtube.jwt.dtos.UserDto;
import com.youtube.jwt.entities.Role;
import com.youtube.jwt.entities.User;
import com.youtube.jwt.mappers.UserMapper;
import com.youtube.jwt.repositories.RoleRepository;
import com.youtube.jwt.repositories.UserRepository;
import com.youtube.jwt.service.RoleService;
import com.youtube.jwt.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;	
	
	@Override
	public UserDto registerNewUser(UserDto userDto) {
		Set<Role> role = new HashSet<>();
		
		Role entity = this.roleService.findRoleById("User");
		User user = userMapper.toUserEntity(userDto);		
		role.add(entity);
		user.setRole(role);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		
		user = userRepository.save(user);
		return userMapper.toUserDto(user);
	}

	@Override
	public void initDummyUserAndRoleData() {	
		
		Role role2 = new Role();
		role2.setRoleName("User");
		role2.setRoleDescription("Default role for newly created record");
		
		Role role1 = new Role();
		role1.setRoleName("Admin");
		role1.setRoleDescription("Admin Rights");	
		
		this.roleRepository.saveAll(Arrays.asList(role2,role1));		
		
		User user1 = new User();
		user1.setUserName("snjv22");
		user1.setUserFirstName("Sanjeev");
		user1.setUserLastName("Soni");
		user1.setUserPassword(getEncodedPassword("123456"));
		Set<Role> user1Role= new HashSet<>();
		user1Role.add(role1);
		user1.setRole(user1Role);
		

//		User user2 = new User();
//		user2.setUserName("goyal33");
//		user2.setUserFirstName("Shubham");
//		user2.setUserLastName("Goyal");
//		user2.setUserPassword(getEncodedPassword("654321"));
//		Set<Role> user2Role= new HashSet<>();
//		user2Role.add(role2);
//		user2.setRole(user2Role);
		
		this.userRepository.saveAll(Arrays.asList(user1));	
	}
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}	
}
