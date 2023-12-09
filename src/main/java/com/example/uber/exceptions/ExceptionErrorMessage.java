package com.example.uber.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionErrorMessage {
    private LocalDateTime timestamp;
    private String status;
    private String message;
}
