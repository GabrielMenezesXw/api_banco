package com.fundatec.banco.exception;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super("impossivel criar, pois tal "+message+" entra em conflito com um já existente," +
                "reveja as informações");
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}