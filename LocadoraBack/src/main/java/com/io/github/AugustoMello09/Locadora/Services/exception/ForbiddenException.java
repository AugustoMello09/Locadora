package com.io.github.AugustoMello09.Locadora.Services.exception;

public class ForbiddenException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ForbiddenException(String message) {
		super(message);
	}

}
