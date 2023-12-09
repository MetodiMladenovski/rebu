package com.example.uber.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class AppBaseException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
