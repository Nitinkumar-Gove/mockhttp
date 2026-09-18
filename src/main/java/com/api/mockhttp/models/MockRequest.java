package com.api.mockhttp.models;

public record MockRequest(int statusCode, String message, Long delayMs) {
}
