package com.example.demo.exception;

import com.example.demo.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * CustomExceptionHandler
 * Handles application-specific exception responses
 * using standard Java exceptions as per helper document.
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles entity not found cases
     * Example: Customer not found, Rule not found
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse> handleNoSuchElement(NoSuchElementException ex) {
        ApiResponse response = new ApiResponse(
                false,
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles validation and bad input cases
     * Example:
     *  - Amount must be positive
     *  - Invalid channel
     *  - Customer ID already exists
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgument(IllegalArgumentException ex) {
        ApiResponse response = new ApiResponse(
                false,
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Fallback handler for unexpected errors
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntime(RuntimeException ex) {
        ApiResponse response = new ApiResponse(
                false,
                "Unexpected server error"
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
