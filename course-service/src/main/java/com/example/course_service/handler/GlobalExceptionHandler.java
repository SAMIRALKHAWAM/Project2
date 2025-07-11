package com.example.course_service.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

   
    // Fallback handler for all other exceptions
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
    //     Map<String, String> error = Map.of("error", "Internal Server Error");
    //     return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
}
