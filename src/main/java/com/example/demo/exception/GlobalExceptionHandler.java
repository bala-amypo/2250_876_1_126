// package com.example.demo.exception;

// import com.example.demo.dto.ApiResponse;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// import java.util.NoSuchElementException;

// @RestControllerAdvice
// public class GlobalExceptionHandler {
    
//     @ExceptionHandler(NoSuchElementException.class)
//     public ResponseEntity<ApiResponse> handleNoSuchElement(NoSuchElementException e) {
//         return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                 .body(new ApiResponse(false, e.getMessage(), null));
//     }
    
//     @ExceptionHandler(IllegalArgumentException.class)
//     public ResponseEntity<ApiResponse> handleIllegalArgument(IllegalArgumentException e) {
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                 .body(new ApiResponse(false, e.getMessage(), null));
//     }
    
//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<ApiResponse> handleGenericException(Exception e) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                 .body(new ApiResponse(false, "An error occurred: " + e.getMessage(), null));
//     }
// }