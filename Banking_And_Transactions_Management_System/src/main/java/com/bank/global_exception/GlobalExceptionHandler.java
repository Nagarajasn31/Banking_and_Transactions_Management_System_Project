package com.bank.global_exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bank.exception.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.NOT_FOUND.value(), // HTTP status code for Not Found
                ex.getMessage(), // Custom error message from the exception
                ex.getClass().getSimpleName() // Type of exception
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // ErrorDetails is a static nested class used to encapsulate error information
    // that will be sent in the error response.
    public static class ErrorDetails {
        private int statusCode; // HTTP status code
        private String message; // Error message
        private String exceptionType; // Type of exception

        public ErrorDetails() {
            super();
        }

        // Constructor
        public ErrorDetails(int statusCode, String message, String exceptionType) {
            this.statusCode = statusCode;
            this.message = message;
            this.exceptionType = exceptionType;
        }

        // Getters and setters for each field

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getExceptionType() {
            return exceptionType;
        }

        public void setExceptionType(String exceptionType) {
            this.exceptionType = exceptionType;
        }
    }
}
