package com.api.mockhttp.exceptions;

public class InvalidStatusCodeException extends RuntimeException {
	public InvalidStatusCodeException(int statusCode) {
		super("Invalid HTTP status code: " + statusCode);
	}
}
