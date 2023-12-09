package com.example.uber.exceptions.custom;

import com.example.uber.exceptions.AppBaseException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AppBaseException {
    public ResourceNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
