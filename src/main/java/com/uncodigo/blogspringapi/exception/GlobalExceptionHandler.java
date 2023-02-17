package com.uncodigo.blogspringapi.exception;

import com.uncodigo.blogspringapi.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Handler specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlerResourceNotFoundException(ResourceNotFoundException exception,
                                                                         WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        errorDetails.setErrors(Collections.singletonList(exception.getMessage()));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handlerBlogAPIException(BlogAPIException exception,
                                                                WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        errorDetails.setErrors(Collections.singletonList(exception.getMessage()));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handlerGlobalException(Exception exception,
                                                               WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "Ups! something went wrong.",
                webRequest.getDescription(false));

        errorDetails.setErrors(Collections.singletonList(exception.getMessage()));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                        WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "Validations Errors",
                webRequest.getDescription(false));

        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(
                (error) -> errors.add(StringUtils.capitalize(((FieldError) error).getField()) + ": " + error.getDefaultMessage())
        );

        errorDetails.setErrors(errors);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handlerAccessDeniedException(AccessDeniedException exception,
                                                                         WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        errorDetails.setErrors(Collections.singletonList(exception.getMessage()));

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
}
