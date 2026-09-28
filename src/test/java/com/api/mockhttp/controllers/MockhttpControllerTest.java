package com.api.mockhttp.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class MockhttpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsRequestedStatusCode() throws Exception {
        mockMvc.perform(get("/status/404"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Mocked response for status 404"))
                .andExpect(jsonPath("$.message").doesNotExist());
    }

    @Test
    void successStatusReturnsMessageNotError() throws Exception {
        mockMvc.perform(get("/status/200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Mocked response for status 200"))
                .andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    void invalidStatusCodeReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/status/999"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid HTTP status code: 999"));
    }

    @Test
    void nonNumericStatusCodeReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/status/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void unmappedRouteReturnsNotFound() throws Exception {
        mockMvc.perform(get("/status/"))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void delayedRequestTakesAtLeastSpecifiedTime() throws Exception {
        long start = System.currentTimeMillis();

        mockMvc.perform(get("/status/200").param("delayMs", "300"))
                .andExpect(status().isOk());

        long elapsed = System.currentTimeMillis() - start;
        assertTrue(elapsed >= 300, "Expected at least 300ms delay, got " + elapsed);
    }

    @Test
    void returnsCorsHeadersWhenOriginProvided() throws Exception {
        mockMvc.perform(get("/status/200").header("Origin", "https://nitinkumar-gove.github.io"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"));
    }
}