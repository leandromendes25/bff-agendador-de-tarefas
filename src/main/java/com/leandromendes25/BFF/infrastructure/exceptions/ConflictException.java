package com.leandromendes25.BFF.infrastructure.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
    public ConflictException(String mensagem, Throwable throwable){
        super(mensagem);
    }
}
