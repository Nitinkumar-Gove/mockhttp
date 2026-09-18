package com.api.mockhttp.strategies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.api.mockhttp.models.MockRequest;
import com.api.mockhttp.models.MockResponse;
import com.api.mockhttp.services.MockResponseService;

@Component
public class DelayedMockStrategy implements MockStrategy {
	
	private final MockResponseService mockResponseService;
	
	public DelayedMockStrategy(MockResponseService mockResponseService) {
		this.mockResponseService = mockResponseService;
	}

	@Override
	public boolean supports(MockRequest request) {
		return request.delayMs() != null && request.delayMs() > 0;
	}

	@Override
	public ResponseEntity<MockResponse> execute(MockRequest request) {
		try {
			Thread.sleep(request.delayMs());
		}
		catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		 HttpStatus status = HttpStatus.valueOf(request.statusCode());
	     MockResponse body = mockResponseService.buildResponse(status, request.message());
	     return ResponseEntity.status(status).body(body);
	}

}
