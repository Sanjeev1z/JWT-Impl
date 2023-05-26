package com.youtube.jwt.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class JwtRequest {
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String userPassword;

}
