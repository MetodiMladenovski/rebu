package com.example.uber.exceptions.custom;

import com.example.uber.exceptions.AppBaseException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AppBaseException {
    public UserAlreadyExistsException(HttpStatus status, String message) {
        super(status, message);
    }
}
