package com.management.service;

public class ModelIsNotEmptyException extends RuntimeException {
    public ModelIsNotEmptyException (String message) {
        super(message);
    }
}
