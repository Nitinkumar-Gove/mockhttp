package com.api.mockhttp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockhttpController {
	
	@GetMapping("/mock/{statusCode}")
	public ResponseEntity<String> mockHttpCode(@PathVariable int statusCode){
		HttpStatus status =  HttpStatus.valueOf(statusCode);
		return ResponseEntity.status(status).body("");
	}
}
