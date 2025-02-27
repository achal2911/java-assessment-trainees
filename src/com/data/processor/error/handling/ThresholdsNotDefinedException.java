package com.data.processor.error.handling;

public class ThresholdsNotDefinedException extends RuntimeException {
    public ThresholdsNotDefinedException(String message) {
        super(message);
    }
}