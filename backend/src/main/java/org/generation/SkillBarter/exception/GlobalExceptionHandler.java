package org.generation.SkillBarter.exception; // Suggested package for exception handling classes

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler provides centralized exception handling for all controllers
 * in the application. It catches specific exceptions and returns appropriate
 * HTTP responses with detailed error messages.
 */
@ControllerAdvice // This annotation makes this class a global exception handler
public class GlobalExceptionHandler {

    /**
     * Custom exception class to indicate that a resource (e.g., username, email)
     * already exists when it should be unique.
     * This can be nested here or defined as a separate file.
     */
    public static class ResourceAlreadyExistsException extends RuntimeException {
        public ResourceAlreadyExistsException(String message) {
            super(message);
        }
    }

    /**
     * Handles validation errors thrown when @Valid annotation fails.
     * This typically occurs when incoming request body (e.g., DTO) does not
     * meet the specified validation constraints (@NotBlank, @Email, @Size, etc.).
     * Returns HTTP status 400 Bad Request.
     *
     * @param ex The MethodArgumentNotValidException that occurred.
     * @param request The current web request.
     * @return A ResponseEntity containing error details and HTTP status 400.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        // Collect all field errors and their default messages
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation Error");
        body.put("message", "Input validation failed");
        body.put("details", errors); // Provide specific field validation errors
        body.put("path", request.getDescription(false)); // Path of the request

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles custom ResourceAlreadyExistsException.
     * This is thrown when an attempt is made to create a resource (like a user)
     * with a unique identifier (email, username) that already exists.
     * Returns HTTP status 409 Conflict.
     *
     * @param ex The ResourceAlreadyExistsException that occurred.
     * @param request The current web request.
     * @return A ResponseEntity containing error details and HTTP status 409.
     */
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");
        body.put("message", ex.getMessage()); // The specific message from the exception
        body.put("path", request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    /**
     * Handles IOException, typically associated with file upload issues
     * (e.g., reading bytes from MultipartFile, issues with Cloudinary service).
     * Returns HTTP status 500 Internal Server Error.
     *
     * @param ex The IOException that occurred.
     * @param request The current web request.
     * @return A ResponseEntity containing error details and HTTP status 500.
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Failed to process file upload: " + ex.getMessage());
        body.put("path", request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * A general exception handler for any unhandled exceptions.
     * This acts as a fallback for any exceptions not specifically caught by other handlers.
     * Returns HTTP status 500 Internal Server Error.
     *
     * @param ex The Exception that occurred.
     * @param request The current web request.
     * @return A ResponseEntity containing error details and HTTP status 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUncaughtException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "An unexpected error occurred: " + ex.getMessage());
        body.put("path", request.getDescription(false));

        // IMPORTANT: In production, use a proper logging framework (e.g., SLF4J with Logback/Log4j2)
        // instead of ex.printStackTrace().
        ex.printStackTrace(); // For development/debugging purposes

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
