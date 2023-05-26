package com.youtube.jwt.dtos;

import com.youtube.jwt.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class JwtResponse {
	
	private User user;
	
	private String jwtToken;
}
