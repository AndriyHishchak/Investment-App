package com.project.Investment.App.exception.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.Date;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    Date timestamp;
    HttpStatus status;
    String message;
    String details;


}
