package com.io.github.AugustoMello09.Locadora.Services.exception;

public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String message) {
		super(message);
	}

}
