package com.project.Investment.App.exception;

import com.project.Investment.App.exception.model.ValidationError;
import com.project.Investment.App.exception.model.ErrorDetails;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), "No value is present in DB, Please change your request", request.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<?> emptyInputExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), "Input field is Empty, Please look into it", request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ListEmptyException.class)
    public ResponseEntity<?> listEmptyExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), "List completely empty, we have nothing to return", request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), "Please change http method type", request.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }

    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new SimpleMappingExceptionResolver());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());

    final List<String> errors = new ArrayList<String>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
        errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    final ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return handleExceptionInternal(ex, validationError, headers, validationError.getStatus(), request);
}
}
