package com.management.exception;

public class YearOutOfRangeException extends RuntimeException {
    public YearOutOfRangeException(String message) {
        super(message);
    }
}