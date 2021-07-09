package com.project.Investment.App.exception.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError {

    Date timestamp;
    HttpStatus status;
    String message;
    String details;
    Map<String, String> errors;

}
