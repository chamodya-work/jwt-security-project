package com.security.jwt.dto;

import lombok.Data;

@Data

public class LoginRequest {
    private String userName;
    private String userPassword;

}
