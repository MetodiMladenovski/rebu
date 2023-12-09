package com.example.uber.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AppBaseException.class})
    protected ResponseEntity<ExceptionErrorMessage> handleConflict(AppBaseException ex) {
        ExceptionErrorMessage errorMessage =
                new ExceptionErrorMessage(LocalDateTime.now(), ex.getStatus().toString(), ex.getMessage());
        logger.error(String.format("Error message: %s | Error status: %s", ex.getMessage(), ex.getStatus()));
        return new ResponseEntity<>(errorMessage, ex.getStatus());
    }
}