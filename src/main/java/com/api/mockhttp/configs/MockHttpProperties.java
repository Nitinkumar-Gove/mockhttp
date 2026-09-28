package com.api.mockhttp.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "mockhttp")
public record MockHttpProperties(@DefaultValue("5000") long maxDelayMs) {

}
