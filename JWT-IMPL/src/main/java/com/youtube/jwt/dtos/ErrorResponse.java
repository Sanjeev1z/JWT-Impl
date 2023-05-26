package com.youtube.jwt.dtos;

import com.youtube.jwt.exceptions.JwtImplBusinessException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private String message;

	private String errorCode;
	
	public ErrorResponse(JwtImplBusinessException ex) {
		this.message = ex.getMessage();
		this.errorCode = ex.getErrorCode();
	}	
}