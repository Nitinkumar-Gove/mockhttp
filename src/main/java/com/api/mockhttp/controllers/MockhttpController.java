package com.api.mockhttp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.mockhttp.models.MockResponse;

@RestController
public class MockhttpController {
	
	@GetMapping("/mock/{statusCode}")
	public ResponseEntity<MockResponse> mockHttpCode(@PathVariable int statusCode){
		HttpStatus status =  HttpStatus.valueOf(statusCode);
		
		MockResponse body = status.isError() ? new MockResponse(null, "Mocked error response for status " + statusCode) : new MockResponse("Mocked response for status " + statusCode, null);
		return ResponseEntity.status(status).body(body);
	}
}
