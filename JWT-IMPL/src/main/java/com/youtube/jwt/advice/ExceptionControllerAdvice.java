package com.youtube.jwt.advice;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.youtube.jwt.dtos.ErrorResponse;
import com.youtube.jwt.exceptions.JwtImplBusinessException;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(JwtImplBusinessException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleBusinessException(JwtImplBusinessException ex) {
		return new ErrorResponse(ex);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
		
		return new ErrorResponse(null,"invalid.request");
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleBusinessException(EntityNotFoundException ex) {
		return new ErrorResponse(ex.getMessage(), "invalid.request");
	}
	
}
