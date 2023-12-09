package com.example.uber.exceptions.custom;

import com.example.uber.exceptions.AppBaseException;
import org.springframework.http.HttpStatus;

public class ProfilePictureMissingException extends AppBaseException {
    public ProfilePictureMissingException(HttpStatus status, String message) {
        super(status, message);
    }
}
