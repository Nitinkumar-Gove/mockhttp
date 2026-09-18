package com.api.mockhttp.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.mockhttp.models.MockResponse;

@Service
public class MockResponseService {
	public MockResponse buildResponse(HttpStatus status, String customMessage) {
		String text = (customMessage != null) ? customMessage : "Mocked response for status " + status.value();

		return status.isError() ? new MockResponse(null, text) : new MockResponse(text, null);
	}
}
