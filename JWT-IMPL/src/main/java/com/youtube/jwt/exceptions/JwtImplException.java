package com.youtube.jwt.exceptions;

import lombok.Getter;

@Getter
public class JwtImplException extends RuntimeException {

	private static final long serialVersionUID = -478376748646484516L;

	protected String errorCode;

	public JwtImplException(String message) {
		super(message);
	}

	public JwtImplException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}