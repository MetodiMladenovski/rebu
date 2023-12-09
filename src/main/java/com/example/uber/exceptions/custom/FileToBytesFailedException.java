package com.example.uber.exceptions.custom;

import com.example.uber.exceptions.AppBaseException;
import org.springframework.http.HttpStatus;

public class FileToBytesFailedException extends AppBaseException {
    public FileToBytesFailedException(HttpStatus status, String message) {
        super(status, message);
    }
}
