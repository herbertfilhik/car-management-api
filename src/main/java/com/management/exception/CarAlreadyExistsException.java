package com.management.exception;

public class CarAlreadyExistsException extends RuntimeException {

    public CarAlreadyExistsException(String message) {
        super(message);
    }    
}
