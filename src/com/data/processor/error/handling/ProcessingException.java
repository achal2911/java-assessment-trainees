package com.data.processor.error.handling;

public class ProcessingException extends RuntimeException {
    public ProcessingException(String message) {
        super(message);
    }
}