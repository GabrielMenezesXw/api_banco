package com.fundatec.banco.exception;

public class NotAllowedException extends RuntimeException{

    public NotAllowedException(String message) {
        super("metodo não permitido, pois "+ message);
    }

    public NotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
