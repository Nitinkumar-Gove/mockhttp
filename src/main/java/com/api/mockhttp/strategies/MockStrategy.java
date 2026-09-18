package com.api.mockhttp.strategies;

import org.springframework.http.ResponseEntity;

import com.api.mockhttp.models.MockRequest;
import com.api.mockhttp.models.MockResponse;

public interface MockStrategy {
	boolean supports(MockRequest request);

	ResponseEntity<MockResponse> execute(MockRequest request);
}
