package com.api.mockhttp.exceptions;

public class InvalidDelayException extends RuntimeException {
    public InvalidDelayException(long requested, long max) {
        super("delayMs " + requested + " exceeds maximum allowed " + max);
    }
}
