package com.api.mockhttp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.api.mockhttp.models.MockResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidStatusCodeException.class)
	public ResponseEntity<MockResponse> handleInvalidStatusCode(InvalidStatusCodeException ex) {
		return ResponseEntity.badRequest().body(new MockResponse(null, ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<MockResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String errorMsg = String.format("Invalid value for parameter '%s': expected a number", ex.getName());
		return ResponseEntity.badRequest().body(new MockResponse(null, errorMsg));
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MockResponse> handleNoHandlerFound(NoHandlerFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MockResponse(null,
				"No matching endpoint found for " + ex.getHttpMethod() + " " + ex.getRequestURL()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MockResponse> handleUnexpected(Exception ex) {
		return ResponseEntity.internalServerError()
				.body(new MockResponse(null, "Unexpected error: " + ex.getMessage()));
	}
}
