package com.example.demo.exception;

import com.example.demo.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<AuthResponse> handleNoSuchElement(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new AuthResponse(false, ex.getMessage(), null));
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AuthResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new AuthResponse(false, ex.getMessage(), null));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthResponse> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new AuthResponse(false, "An error occurred: " + ex.getMessage(), null));
    }
}
