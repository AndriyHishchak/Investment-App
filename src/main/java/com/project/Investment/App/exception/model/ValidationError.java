package com.project.Investment.App.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Getter
public class ValidationError {

    private final HttpStatus status;
    private final String message;
    List<String> errors;

}
