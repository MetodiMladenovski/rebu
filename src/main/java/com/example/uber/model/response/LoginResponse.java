package com.example.uber.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String typeOfLoggedUser;
    private String idOfLoggedUser;
}
