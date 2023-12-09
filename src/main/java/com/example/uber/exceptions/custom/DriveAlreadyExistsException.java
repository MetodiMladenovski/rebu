package com.example.uber.exceptions.custom;

import com.example.uber.exceptions.AppBaseException;
import org.springframework.http.HttpStatus;

public class DriveAlreadyExistsException extends AppBaseException {
    public DriveAlreadyExistsException(HttpStatus status, String message) {
        super(status, message);
    }
}
