package com.api.mockhttp.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MockResponse(String message, String error) {
}
