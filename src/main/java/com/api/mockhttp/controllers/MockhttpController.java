package com.api.mockhttp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.mockhttp.models.MockResponse;
import com.api.mockhttp.services.MockResponseService;

@RestController
public class MockhttpController {

	private final MockResponseService mockResponseService;

	public MockhttpController(MockResponseService mockResponseService) {
		this.mockResponseService = mockResponseService;
	}

	@GetMapping("/mock/{statusCode}")
	public ResponseEntity<MockResponse> mockHttpCode(@PathVariable int statusCode, @RequestParam(required=false) String message) {
		HttpStatus status = HttpStatus.valueOf(statusCode);

		MockResponse body = mockResponseService.buildResponse(status, message);
		return ResponseEntity.status(status).body(body);
	}
}
