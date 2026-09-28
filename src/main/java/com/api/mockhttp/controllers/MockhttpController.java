package com.api.mockhttp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.mockhttp.configs.MockHttpProperties;
import com.api.mockhttp.exceptions.InvalidDelayException;
import com.api.mockhttp.exceptions.InvalidStatusCodeException;
import com.api.mockhttp.models.MockRequest;
import com.api.mockhttp.models.MockResponse;
import com.api.mockhttp.services.MockResponseService;
import com.api.mockhttp.strategies.MockStrategy;

@CrossOrigin(origins = "*")
@RestController
public class MockhttpController {

	private final List<MockStrategy> strategies;
	private final MockHttpProperties properties;

	public MockhttpController(List<MockStrategy> strategies, MockHttpProperties properties) {
		this.strategies = strategies;
		this.properties = properties;
	}

	@GetMapping("/status/{statusCode}")
	public ResponseEntity<MockResponse> mockHttpCode(@PathVariable int statusCode,
			@RequestParam(required = false) String message, @RequestParam(required = false) Long delayMs) {

		validateHttpStatusCode(statusCode);
		validateDelay(delayMs);
		
		MockRequest request = new MockRequest(statusCode, message, delayMs);

		return strategies.stream().filter(s -> s.supports(request)).findFirst()
				.orElseThrow(() -> new IllegalStateException("No strategy matched request")).execute(request);
	}
	
	private void validateHttpStatusCode(int statusCode) {
		try {
			HttpStatus.valueOf(statusCode);
		}
		catch(IllegalArgumentException e) {
			throw new InvalidStatusCodeException(statusCode);
		}
	}
	
	private void validateDelay(Long delayMs) {
	    if (delayMs != null && delayMs > properties.maxDelayMs()) {
	        throw new InvalidDelayException(delayMs, properties.maxDelayMs());
	    }
	}
}
