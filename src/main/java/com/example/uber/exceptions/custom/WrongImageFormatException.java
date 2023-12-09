package com.example.uber.exceptions.custom;

import com.example.uber.exceptions.AppBaseException;
import org.springframework.http.HttpStatus;

public class WrongImageFormatException extends AppBaseException {
    public WrongImageFormatException(HttpStatus status, String message) {
        super(status, message);
    }
}
