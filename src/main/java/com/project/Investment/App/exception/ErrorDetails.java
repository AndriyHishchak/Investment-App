package com.project.Investment.App.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
public class ErrorDetails {

     Date timestamp;
     String message;
     String details;



}
