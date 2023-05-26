package com.youtube.jwt.exceptions;

import lombok.Getter;

@Getter
public class JwtImplBusinessException extends JwtImplException {

	private static final long serialVersionUID = -478376748646484516L;

	public JwtImplBusinessException(String message) {
		super(message);
	}

	public JwtImplBusinessException(String message, String errorCode) {
		super(message, errorCode);
	}
}
