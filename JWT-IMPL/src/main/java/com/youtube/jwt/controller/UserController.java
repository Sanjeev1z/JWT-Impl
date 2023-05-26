package com.youtube.jwt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.jwt.dtos.UserDto;
import com.youtube.jwt.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registerNewUser")
	public UserDto registerNewUser(@RequestBody @Valid UserDto userDto) {
		return this.userService.registerNewUser(userDto);
	}
	
	@GetMapping("/forAdmin")
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "only for admin";
	}
	
	@GetMapping("/forUser")
	@PreAuthorize("hasRole('User')")
	public String forUser() {
		return "only for User";
	}
	
	@PostMapping("/initDummy")
	public void initDummyUserAndRoleData() {
		this.userService.initDummyUserAndRoleData();
	}	
}
