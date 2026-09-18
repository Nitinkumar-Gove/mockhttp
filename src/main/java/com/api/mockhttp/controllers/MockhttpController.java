package com.api.mockhttp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.mockhttp.models.MockRequest;
import com.api.mockhttp.models.MockResponse;
import com.api.mockhttp.services.MockResponseService;
import com.api.mockhttp.strategies.MockStrategy;

@RestController
public class MockhttpController {

	private final List<MockStrategy> strategies;

	public MockhttpController(List<MockStrategy> strategies) {
		this.strategies = strategies;
	}

	@GetMapping("/mock/{statusCode}")
	public ResponseEntity<MockResponse> mockHttpCode(@PathVariable int statusCode,
			@RequestParam(required = false) String message, @RequestParam(required = false) Long delayMs) {

		MockRequest request = new MockRequest(statusCode, message, delayMs);

		return strategies.stream().filter(s -> s.supports(request)).findFirst()
				.orElseThrow(() -> new IllegalStateException("No strategy matched request")).execute(request);
	}
}
