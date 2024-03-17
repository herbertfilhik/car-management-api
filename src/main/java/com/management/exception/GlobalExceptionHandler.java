package com.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarAlreadyExistsException.class)
    public ResponseEntity<String> handleCarAlreadyExists(CarAlreadyExistsException e) {
        // Retorna um ResponseEntity com status de conflito e a mensagem da exceção
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        // Retorna um ResponseEntity com bad request e a mensagem da exceção
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
