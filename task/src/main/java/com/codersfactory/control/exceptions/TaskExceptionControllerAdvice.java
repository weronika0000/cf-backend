package com.codersfactory.control.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

@RestControllerAdvice(basePackages = "com.codersfactory.boundary.controller")
public class TaskExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .collect(toMap(
                        err -> ((FieldError) err).getField(),
                        err -> Optional.ofNullable(err.getDefaultMessage()).orElse("field error"),
                        (msg1, msg2) -> Objects.equals(msg1, msg2) ? msg1 : msg1 + ", " + msg2
                ));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .collect(toMap(
                        err -> err.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TaskNotFoundException.class)
    public ErrorResponse handleTaskNotFoundException(TaskNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TaskAlreadyExistsException.class)
    public ErrorResponse handleTaskAlreadyExistsException(TaskAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TaskAssignmentAlreadyExistsException.class)
    public ErrorResponse handleTaskAssignmentAlreadyExistsException(TaskAssignmentAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }
}
