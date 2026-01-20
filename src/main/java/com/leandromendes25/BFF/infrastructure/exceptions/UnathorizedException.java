package com.leandromendes25.BFF.infrastructure.exceptions;

public class UnathorizedException extends RuntimeException {
    public UnathorizedException(String message) {
        super(message);
    }
}
