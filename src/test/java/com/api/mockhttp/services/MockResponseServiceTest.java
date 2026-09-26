package com.api.mockhttp.services;

import com.api.mockhttp.models.MockResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class MockResponseServiceTest {

    private final MockResponseService service = new MockResponseService();

    @Test
    void successStatusPopulatesMessageNotError() {
        MockResponse response = service.buildResponse(HttpStatus.OK, null);

        assertEquals("Mocked response for status 200", response.message());
        assertNull(response.error());
    }

    @Test
    void errorStatusPopulatesErrorNotMessage() {
        MockResponse response = service.buildResponse(HttpStatus.NOT_FOUND, null);

        assertEquals("Mocked response for status 404", response.error());
        assertNull(response.message());
    }

    @Test
    void customMessageOverridesDefault() {
        MockResponse response = service.buildResponse(HttpStatus.OK, "custom text");

        assertEquals("custom text", response.message());
    }
}