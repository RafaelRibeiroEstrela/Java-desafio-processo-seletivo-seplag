package com.example.desafioprocessoseletivoseplagapi.handlers;

import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.TokenException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(TokenException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("TokenException");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        standardError.setHttpMethod(request.getMethod());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(BusinessException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("BusinessException");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        standardError.setHttpMethod(request.getMethod());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("ResourceNotFoundException");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        standardError.setHttpMethod(request.getMethod());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("Parameter request exception");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        standardError.setHttpMethod(request.getMethod());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("Body request exception");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        standardError.setHttpMethod(request.getMethod());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> MethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationError err = new ValidationError();
        err.setInstant(LocalDateTime.now());
        err.setCode(status.value());
        err.setError("Validation exception");
        err.setDescrible(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setHttpMethod(request.getMethod());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> exception(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("Exception");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        standardError.setHttpMethod(request.getMethod());
        return ResponseEntity.status(status).body(standardError);
    }

}
