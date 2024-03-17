package com.management.exception;

public class InvalidLicensePlateException extends RuntimeException {
    public InvalidLicensePlateException(String message) {
        super(message);
    }
}