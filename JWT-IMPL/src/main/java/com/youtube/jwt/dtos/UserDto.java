package com.youtube.jwt.dtos;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDto {
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String userFirstName;
	
	@NotBlank
	private String userLastName;
	
	@NotBlank
	private String userPassword;
	
	private OffsetDateTime createdAt;

	private OffsetDateTime updatedAt;

}
